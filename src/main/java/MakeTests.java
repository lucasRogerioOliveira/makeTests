import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class MakeTests {

	private static Integer numberOfRecursons = 0;
	private static Set<String> imports = new HashSet<String>();
	private final static String ESP = "    "; //1*4
	private final static String ESP2 = "        "; //2*4
	//	private final static String ESP3 = "            "; //3*4

	public static void makeAllTestsWith(Object object) throws IOException, IllegalArgumentException, IllegalAccessException{
		Class<?> clazz = object.getClass();
		StringBuilder sb = new StringBuilder();
		String cn = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
		String lcn = cn.toLowerCase();
		String setters = makeSetters(object, null);
		String packageName = object.getClass().getPackage().getName();
		sb.append("package " + packageName + ";\n");
		sb.append(getImports());
		sb.append("\n\n");
		sb.append("public class " + cn + "Tests {\n\n\n");
		sb.append(ESP + "private " + cn + " " + lcn + " = new " + cn + "();\n\n");
		sb.append(ESP + "@Before\n");
		sb.append(ESP + "public void beforeTest(){\n");
		sb.append(setters);
		sb.append(ESP + "}\n\n");
		sb.append(getMethods(clazz.getDeclaredMethods(), lcn));
		sb.append("}\n");	
		saveOrUpdateFile(sb, packageName, cn);
	}

	public static String getMethods(Method[] methods, String objectName){
		StringBuilder sb = new StringBuilder();
		for (Method m : methods){
			String rtn = m.getReturnType().getName(); // return type name
			if (!rtn.equals("void")) {
				sb.append(ESP + "@Test\n");
				sb.append(ESP + "public void " + m.getName() + "Test(){\n");
				if(!Modifier.isStatic(m.getModifiers())){
					//					sb.append(ESP2 + cn + " " + lcn + " = new " + cn + "();\n");
					//					sb.append(setters);
					sb.append(ESP2 + rtn + " actual = " + objectName + "." + m.getName() + "(" + getParametersTypeStr(m)  + ");\n");
					sb.append(ESP2 + "//make your asserts here :)\n");
				}
				sb.append(ESP +"}\n\n");
			}
		}
		return sb.toString();		
	}

	public static String getImports(){
		StringBuilder sb = new StringBuilder("import org.junit.Test;\n");
		sb.append("import org.junit.Before;\n");
		//		sb.append("import org.junit.BeforeClass;\n");
		for(String imp : imports){
			sb.append("import " + imp + ";\n");
		}
		return sb.toString();
	}

	public static boolean saveOrUpdateFile(StringBuilder sb, String packageName, String className) throws IOException{
		//		String path = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String pathStr = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target", "src/test/java");
		pathStr = pathStr.substring(0,pathStr.indexOf("src/test/java") + 13) + "/" + packageName.replace(".", "/") + "/";
		//		String path = System.getProperty("user.dir") + "src\test\java";
		File f = new File(pathStr);
		if (!f.exists()){
			boolean maked = f.mkdirs();
			if (maked){
				BufferedWriter writer = new BufferedWriter(new FileWriter(pathStr + className + "Tests.java"));
				writer.write(sb.toString());
				writer.close();
				return true;
			}
		}
		//		String lines = joinStringArray((String[]) Files.readAllLines(path).toArray());
		return true;
	}

	public static String makeSetters(Object object, String nameObj) throws IllegalArgumentException, IllegalAccessException{
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = object.getClass();
		if (nameObj == null){ 
			nameObj = getClassName(clazz).toLowerCase();
		}
		if (object.getClass().getName().equals("[Ljava.util.HashMap$Node")){
			System.out.println(object.getClass().getName());
		}
		imports.add(object.getClass().getName());
		for(Field f : clazz.getDeclaredFields()){
			f.setAccessible(true);
			sb.append(buildWithTypeOfObject(f.getType(), f, object, nameObj));
		}
		return sb.toString();
	}

	private static String buildWithTypeOfObject(Class<?> clazz, Field f, Object object, String nameObj) throws IllegalArgumentException, IllegalAccessException{
		StringBuilder sb = new StringBuilder();
		//		if(!java.lang.reflect.Modifier.isStatic(f.getModifiers()))
		if (clazz.equals(String.class)){
			sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + '"' + f.get(object)  + '"' + ");\n");
		} else if (clazz.isEnum()) {
			sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + f.getType().getName() + "." + f.get(object)  + ");\n");
		} else if (Arrays.asList(clazz.getInterfaces()).contains(Collection.class)){
			Collection<?> collection = (Collection<?>) f.get(object);
			if (collection != null && !collection.isEmpty()){
				String listName = (f.getName() + (++numberOfRecursons).toString()).toLowerCase(); 
				String fieldName = "";
				String className = "";
				imports.add("java.util.List");
				imports.add("java.util.ArrayList");
				String typeOfList = getTypeOfList(collection).getName(); 
				sb.append(ESP2 + "List<" + typeOfList + ">" + listName + " = new ArrayList<" + typeOfList + ">();\n");
				for(Object objOfList : collection){
					className = objOfList.getClass().getName();
					fieldName = (getClassName(objOfList.getClass()) + (++numberOfRecursons).toString()).toLowerCase();
					if (objOfList.getClass().equals(String.class) || objOfList.getClass().equals(char.class) ){
						sb.append(ESP2 + listName + ".add(" + '"' + objOfList  + '"' + ");\n");
					} else if(isBasicTypes(objOfList.getClass())) {
						sb.append(ESP2 + listName + ".add(" + objOfList + ");\n");
					} else {
						sb.append(ESP2 + className + " " + fieldName + " = new " + className + "();\n");
						sb.append(makeSetters(objOfList, fieldName));
						sb.append(ESP2 + listName + ".add(" + fieldName + ");\n");
					}
				}
				sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName() + "(" + listName + ");\n\n"));
				sb.append("\n");
			}
			//			} else if (f.getType().equals(Map.class)){
			//				if (f.get(object) != null){
			//					Map<?,?> map = (Map<?,?>)f.get(object);
			//					if(map.size() > 0){
			//						Object key = map.keySet().toArray()[0].getClass();
			//						Object value = map.get(map.keySet().toArray()[0]).getClass();
			//						String keyName = getClassName(key.getClass());
			//						String valueName = getClassName(value.getClass());
			//						sb.append(ESP2 + "Map<" + keyName + "," + valueName + "> map" + (++numberOfRecursons).toString() + " = new " + map.getClass().getName() + "<" + keyName + "," + valueName + ">();\n");
			//						for(Entry<?, ?> e : map.entrySet()){
			//							Object k = e.getKey();
			//							String kNameClass = getClassName(k.getClass()); 
			//							String kNameObj = getClassName(k.getClass()).toLowerCase() + (++numberOfRecursons).toString();
			//							sb.append(ESP2 + kNameClass + " " + kNameObj + " = new " + kNameClass);
			//							Object v = e.getValue();
			//						}							
			//						
			//						System.out.println(nameObj + " Map");
			//					}
			//				}
			//				
			//			} else if (f.getType().equals(Set.class)){
			//				if (f.get(object) != null){
			//					System.out.println(nameObj + " Set");
			//				}
		} else if (!isBasicTypes(clazz)){
			if (f.get(object) != null){
				String filedClassNameAndPackage = f.get(object).getClass().getName(); 
				String fieldClassName = filedClassNameAndPackage.substring(filedClassNameAndPackage.lastIndexOf(".") + 1);
				if(!fieldClassName.equals(capitalize(nameObj))){
					sb.append("\n" + ESP2 + filedClassNameAndPackage + " " + fieldClassName.toLowerCase() + " = new " + filedClassNameAndPackage + "();\n");
					sb.append(makeSetters(f.get(object), fieldClassName.toLowerCase()));
					sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName() + "(" + fieldClassName.toLowerCase()  + ");\n\n"));
				} else {
					sb.append("\n" + ESP2 + filedClassNameAndPackage + " " + fieldClassName.toLowerCase() + (++numberOfRecursons).toString() + " = new " + filedClassNameAndPackage + "();\n");
					sb.append(makeSetters(f.get(object), nameObj + (numberOfRecursons).toString()));
					sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName() + "(" + fieldClassName.toLowerCase() + (numberOfRecursons).toString() + ");\n\n"));
				}
			}
		} else {
			sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + f.get(object) + ");\n");
		}
		return sb.toString();

	}
	
	public static Class<?> getTypeOfList(Collection<?> collection){
		Set<Class<?>> set = new HashSet<Class<?>>();
		for (Object o  : collection){
			set.add(o.getClass());
			if (set.size() > 1){
				return Object.class;
			}
		}
		return collection.toArray()[0].getClass();
	}

	private static Boolean isBasicTypes(Class<?> clazz){
		return clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Boolean.class) 
				|| clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.isPrimitive(); 
	}

	public static String getParametersTypeStr(Method method){
		String result = "";
		for(Class<?> clazz : method.getParameterTypes()){
			result += clazz.getName() + ", ";
		}
		return result.length() > 2 ? result.substring(0, result.length()-1) : result;
	}

	public static String getClassName(Class<?> clazz){
		return clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
	}

	private static String capitalize(String line){
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

}
