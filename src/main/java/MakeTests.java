import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;
import java.util.Set;

import javafx.util.Pair;

import org.joda.time.DateTime;


public class MakeTests {

	private static Integer numberOfRecursons = 0;
	private static Set<String> imports = new HashSet<String>();
	private static Set<String> statics = new HashSet<String>();
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
					sb.append(ESP2 + "Assert.fail(" + '"' + "make your asserts here :)" + '"' + ");\n");
				}
				sb.append(ESP +"}\n\n");
			}
		}
		return sb.toString();		
	}

	public static String getImports(){
		StringBuilder sb = new StringBuilder("import org.junit.Test;\n");
		sb.append("import org.junit.Before;\n");
		sb.append("import org.junit.Assert;\n");
		//		sb.append("import org.junit.BeforeClass;\n");
		for(String imp : imports){
			if (isValidClass(imp)){
				sb.append("import " + imp + ";\n");
			}
		}
		return sb.toString();
	}
	
	public static boolean isValidClass(String importAndClass){
		try {
			if(importAndClass.contains("$")){
				return false;
			}
			Class.forName(importAndClass);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
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
			if (f.get(object) != null){
				sb.append(buildWithTypeOfObject(f.getType(), f, object, nameObj));
			}
		}
		return sb.toString();
	}

	private static String buildWithTypeOfObject(Class<?> clazz, Field f, Object object, String nameObj) throws IllegalArgumentException, IllegalAccessException{
		StringBuilder sb = new StringBuilder();
//		if(!java.lang.reflect.Modifier.isStatic(f.getModifiers()))
		if (clazz.equals(String.class) || clazz.equals(char.class)){
			sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + '"' + f.get(object)  + '"' + ");\n");
		} else if (clazz.isEnum()) {
			sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + f.getType().getName() + "." + f.get(object)  + ");\n");
		} else if (Arrays.asList(clazz.getInterfaces()).contains(Collection.class)){
			Collection<?> collection = (Collection<?>) f.get(object);
			if (collection != null && !collection.isEmpty()){
				imports.add("java.util.List");
				String listName = (f.getName() + (++numberOfRecursons).toString()).toLowerCase(); 
				String typeOfList = getTypeOfCollection(collection).getName(); 
				sb.append(ESP2 + "List<" + typeOfList + ">" + listName + " = new ArrayList<" + typeOfList + ">();\n");
				for(Object objOfList : collection){
					String className = objOfList.getClass().getName();
					String fieldName = (getClassName(objOfList.getClass()) + (++numberOfRecursons).toString()).toLowerCase();
//					sb.append(writeBisicInformations(objOfList, fieldName));
					if (isAlpha(objOfList)){
						sb.append(ESP2 + listName + ".add(" + '"' + objOfList  + '"' + ");\n");
					} else if(isBasicTypes(objOfList.getClass())) {
						sb.append(ESP2 + listName + ".add(" + objOfList + ");\n");
					} else if(isDate(objOfList.getClass())) {
						getDate(objOfList, fieldName);
						sb.append(ESP2 + listName + ".add(" + fieldName + ";\n");
					} else {
						sb.append(ESP2 + className + " " + fieldName + " = new " + className + "();\n");
						sb.append(makeSetters(objOfList, fieldName));
						sb.append(ESP2 + listName + ".add(" + fieldName + ");\n");
					}
				}
				sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName() + "(" + listName + ");\n\n"));
				sb.append("\n");
			}
		} else if (clazz.equals(Pair.class) && f.get(object) != null){
				Pair<?,?> pair = (Pair<?,?>)f.get(object);
				imports.add(clazz.getName());
				Object k = pair.getKey();
				Object v = pair.getValue();
				String kObjName = "null";
				String vObjName = "null";
				String namePair = getClassName(pair.getClass()) + (++numberOfRecursons).toString();
				if (k != null){
					kObjName = getClassName(k.getClass()).toLowerCase() + (++numberOfRecursons).toString();
					sb.append(writeBisicInformations(k, kObjName));
				}
				if (v != null){
					vObjName = getClassName(v.getClass()).toLowerCase() + (++numberOfRecursons).toString();
					sb.append(writeBisicInformations(v, vObjName));
				}
				if (k != null && v != null){
					String pairString = "Pair<" + k.getClass().getName() + "," + v.getClass().getName() + ">";
					sb.append(ESP2 + pairString + " " + namePair + " = new " + pairString + "(" + kObjName +"," + vObjName+ ");\n");
					sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + namePair + ");\n");
				}
		} else if (clazz.equals(Map.class)){
			Map<?,?> map = (Map<?,?>)f.get(object);
			if(map != null && map.size() > 0){
				imports.add(clazz.getName());
				Entry<Class<?>, Class<?>> entry = getTypeOfMap(map);
				String keyName = entry.getKey().getName();
				String valueName = entry.getValue().getName();
				String nameMap = "map" + (++numberOfRecursons).toString();
				sb.append(ESP2 + "Map<" + keyName + "," + valueName + "> " + nameMap +  " = new " + map.getClass().getName() + "<" + keyName + "," + valueName + ">();\n");
				for(Entry<?, ?> e : map.entrySet()){
					Object k = e.getKey();
					String kObjName = getClassName(k.getClass()).toLowerCase() + (++numberOfRecursons).toString();
					Object v = e.getValue();
					String vObjName = getClassName(v.getClass()).toLowerCase() + (++numberOfRecursons).toString();
					sb.append(writeBisicInformations(k, kObjName));
					sb.append(writeBisicInformations(v, vObjName));
					sb.append(ESP2 + nameMap + ".put(" + kObjName + "," + vObjName + ");\n");
				}
				sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + nameMap + ");\n");
				System.out.println(nameObj + " Map");
			}
		} else if (isDate(clazz) && f.get(object) != null){
			String nameField = f.getName() + (++numberOfRecursons).toString();
			sb.append(getDate(f.get(object), nameField) );
			sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + nameField + ");\n");
		} else if (!isBasicTypes(clazz)){
			if (f.get(object) != null){
				String filedClassNameAndPackage = f.get(object).getClass().getName(); 
				String fieldClassName = f.getName() + (++numberOfRecursons).toString();
				if(!fieldClassName.equals(capitalize(nameObj))){
					sb.append("\n" + ESP2 + filedClassNameAndPackage + " " + fieldClassName.toLowerCase() + " = new " + filedClassNameAndPackage + "();\n");
					sb.append(makeSetters(f.get(object), fieldClassName.toLowerCase()));
					sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + fieldClassName.toLowerCase()  + ");\n\n");
				} else {
					sb.append("\n" + ESP2 + filedClassNameAndPackage + " " + fieldClassName + " = new " + filedClassNameAndPackage + "();\n");
					sb.append(makeSetters(f.get(object), fieldClassName));
					sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + fieldClassName + ");\n\n");
				}
			}
		} else {
			sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + f.get(object) + ");\n");
		}
		return sb.toString();
	}
	
	public static String writeBisicInformations(Object obj, String objName) throws IllegalArgumentException, IllegalAccessException{
		StringBuilder sb = new StringBuilder();
		String className = obj.getClass().getName();
		if(obj != null){
			if (isAlpha(obj)){
				sb.append(ESP2 + className + " " + objName + " = " + '"' + obj + '"' + "\n");
			} else if(isBasicTypes(obj.getClass())) {
				sb.append(ESP2 + className + " " + objName + " = " + obj + "\n");
			} else if(isDate(obj.getClass())) {
				sb.append(getDate(obj, objName));
			} else {
				sb.append(ESP2 + className + " " + objName + " = new " + className + "();\n");
				sb.append(makeSetters(obj, objName));
			}
		}
		return sb.toString();		
	}
	
	public static boolean isAlpha(Object obj){
		return obj.getClass().equals(String.class) || obj.getClass().equals(char.class);
	}
	
	private static boolean isDate(Class<?> clazz){
		return clazz.equals(java.sql.Date.class) || clazz.equals(java.util.Date.class) || clazz.equals(Calendar.class);
	}
	
	public static String getDate(Object obj, String objName){
		StringBuilder sb = new StringBuilder();
		if (obj != null){
			if (obj.getClass().equals(java.util.Date.class)){
				imports.add(obj.getClass().getName());
				java.util.Date date = (java.util.Date)obj;
				sb.append(ESP2 + "Date " + objName + " = new Date(" + date.getTime() + "L);\n");				
			} else if (obj.getClass().isInstance(Calendar.class) || obj.getClass().getSuperclass().equals(Calendar.class)){
				imports.add("java.util.TimeZone");
				imports.add("java.util.Calendar");
				Calendar calendar = (Calendar) obj;
				sb.append(ESP2 + "//sorry, but a can't get the Locale =(\n");
				sb.append(ESP2 + "Calendar " + objName + " = Calendar.getInstance(TimeZone.getTimeZone(" + '"' + calendar.getTimeZone().getID() + '"' + "));\n");
			} else if (obj.getClass().equals(DateTime.class)){
				
			}
		}
		return sb.toString();
	}
	
	public static Class<?> getTypeOfCollection(Collection<?> collection){
		Set<Class<?>> set = new HashSet<Class<?>>();
		imports.add(collection.getClass().getName());
		for (Object o  : collection){
			set.add(o.getClass());
			if (set.size() > 1){
				return Object.class;
			}
		}
		imports.add(collection.toArray()[0].getClass().getName());
		return collection.toArray()[0].getClass();
	}
	
	public static Entry<Class<?>,Class<?>> getTypeOfMap(Map<?,?> map){
		imports.add(map.getClass().getName());
		Class<?> keyType = getTypeOfCollection(map.keySet());
		Class<?> valueType = getTypeOfCollection(map.values());
		return new AbstractMap.SimpleEntry<Class<?>, Class<?>>(keyType,valueType);
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
