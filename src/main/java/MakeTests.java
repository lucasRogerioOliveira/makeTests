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
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTime;

import comparator.FieldComparator;
import comparator.MapComparator;
import comparator.MethodComparator;
import comparator.ObjectComparator;

public class MakeTests {

	private static Object objectFather = null;
	private static Integer numberOfRecursions = 0;
	private static Set<String> imports = new HashSet<String>();
	private static Set<Object> statics = new HashSet<Object>();
	private final static String ESP = "    "; //1*4
	private final static String ESP2 = "        "; //2*4
	
	private static void init(){
		objectFather = null;
		numberOfRecursions = 0;
		imports = new HashSet<String>();
		statics = new HashSet<Object>();
//		imports.clear();
//		statics.clear();
	}

	public static String makeSettersWith(Object object, boolean withMethodsAsserts) throws IOException, IllegalArgumentException, IllegalAccessException{
		init();
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
		List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
		if (withMethodsAsserts){
			sb.append(generateMethods(methods, lcn));
		}
		sb.append("}\n");
		saveOrUpdateFile(sb, packageName, cn);
		return sb.toString();
	}

	public static String generateMethods(List<Method> methods, String objectName){
		StringBuilder sb = new StringBuilder();
		Collections.sort(methods, new MethodComparator());
		for (Method m : methods){
			String rtn = m.getReturnType().getName(); // return type name
			if (!rtn.equals("void")) {
				sb.append(ESP + "@Test\n");
				sb.append(ESP + "public void " + m.getName() + "Test(){\n");
				if(!Modifier.isStatic(m.getModifiers())){
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

	private static String makeSetters(Object object, String nameObj) throws IllegalArgumentException, IllegalAccessException{
		StringBuilder sb = new StringBuilder();
		try {
			if (object != null){
				if (objectFather == null){
					objectFather = object;
				}			
				Class<?> clazz = object.getClass();
				if (nameObj == null){ 
					nameObj = getClassName(clazz).toLowerCase();
				}
				imports.add(object.getClass().getName());
				
				List<Field> fields = Arrays.asList(clazz.getDeclaredFields());		
				Collections.sort(fields, new FieldComparator());
				Set<String> methods = getMethodsNames(clazz);
				//... continuação ... 
				for(Field f : fields){
					f.setAccessible(true);
					if (f.get(object) != null && methods.contains("set" + capitalize(f.getName()))){
						sb.append(buildWithTypeOfObject(f.getType(), f, object, nameObj));
					}
				}
				if (object == objectFather){
					numberOfRecursions = 0;
				}
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}

	private static String buildWithTypeOfObject(Class<?> clazz, Field f, Object object, String nameObj) throws IllegalArgumentException, IllegalAccessException{
		StringBuilder sb = new StringBuilder();
		String setFieldName = "set" + capitalize(f.getName());
		if(java.lang.reflect.Modifier.isStatic(f.getModifiers()) ){
			if (statics.contains(f)){
				return "";
			}
			statics.add(f);
			nameObj = object.getClass().getName();
		}
		if (clazz.equals(String.class) || clazz.equals(char.class)){
			sb.append(ESP2 + nameObj + "." + setFieldName + "(" + '"' + f.get(object)  + '"' + ");\n");
		} else if (clazz.isEnum()) {
			sb.append(ESP2 + nameObj + "." + setFieldName + "(" + f.getType().getName() + "." + f.get(object)  + ");\n");
		} else if (Arrays.asList(clazz.getInterfaces()).contains(Collection.class)){
			Collection<?> collection = (Collection<?>) f.get(object);
			if (collection != null && !collection.isEmpty()){
				String listName = (f.getName() + (++numberOfRecursions).toString()).toLowerCase(); 
				String typeOfList = getTypeOfCollection(collection).getName();
				String instance = getInterfaceOfCollection(collection);
				imports.add("java.util." + instance);
				sb.append(ESP2 + instance + "<" + typeOfList + ">" + listName + " = new " + collection.getClass().getName() +  "<" + typeOfList + ">();\n");
				List<Object> ordenedList = new LinkedList<Object>(collection);
				Collections.sort(ordenedList, new ObjectComparator());
				for(Object objOfList : ordenedList){
					String className = objOfList.getClass().getName();					
					String fieldName = (getClassName(objOfList.getClass()) + (++numberOfRecursions).toString()).toLowerCase();
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
		} 
//		else if (clazz.equals(Pair.class) && f.get(object) != null){
//				Pair<?,?> pair = (Pair<?,?>)f.get(object);
//				imports.add(clazz.getName());
//				Object k = pair.getKey();
//				Object v = pair.getValue();
//				String kObjName = "null";
//				String vObjName = "null";
//				String namePair = getClassName(pair.getClass()) + (++numberOfRecursons).toString();
//				if (k != null){
//					kObjName = getClassName(k.getClass()).toLowerCase() + (++numberOfRecursons).toString();
//					sb.append(writeBisicInformations(k, kObjName));
//				}
//				if (v != null){
//					vObjName = getClassName(v.getClass()).toLowerCase() + (++numberOfRecursons).toString();
//					sb.append(writeBisicInformations(v, vObjName));
//				}
//				if (k != null && v != null){
//					String pairString = "Pair<" + k.getClass().getName() + "," + v.getClass().getName() + ">";
//					sb.append(ESP2 + pairString + " " + namePair + " = new " + pairString + "(" + kObjName +"," + vObjName+ ");\n");
//					sb.append(ESP2 + nameObj + "." + setFieldName + "(" + namePair + ");\n");
//				}
//		} 
		else if (clazz.equals(Map.class)){
			Map<?,?> map = (Map<?,?>)f.get(object);
			if(map != null && map.size() > 0){
				imports.add(clazz.getName());
				Entry<Class<?>, Class<?>> entry = getTypeOfMap(map);
				String keyName = entry.getKey().getName();
				String valueName = entry.getValue().getName();
				String nameMap = "map" + (++numberOfRecursions).toString();
				sb.append(ESP2 + "Map<" + keyName + "," + valueName + "> " + nameMap +  " = new " + map.getClass().getName() + "<" + keyName + "," + valueName + ">();\n");
				MapComparator mc = new MapComparator(map);
				TreeMap<Object,Object> sortedMap = new TreeMap<Object, Object>(mc);
				sortedMap.putAll(map);
				for(Entry<?, ?> e : sortedMap.entrySet()){
					Object k = e.getKey();
					String kObjName = getClassName(k.getClass()).toLowerCase() + (++numberOfRecursions).toString();
					Object v = e.getValue();
					String vObjName = getClassName(v.getClass()).toLowerCase() + (++numberOfRecursions).toString();
					sb.append(writeBisicInformations(k, kObjName));
					sb.append(writeBisicInformations(v, vObjName));
					sb.append(ESP2 + nameMap + ".put(" + kObjName + "," + vObjName + ");\n");
				}
				sb.append(ESP2 + nameObj + "." + setFieldName + "(" + nameMap + ");\n");
			}
		} else if (isDate(clazz) && f.get(object) != null){
			String nameField = f.getName() + (++numberOfRecursions).toString();
			sb.append(getDate(f.get(object), nameField) );
			sb.append(ESP2 + nameObj + "." + setFieldName + "(" + nameField + ");\n");
		} else if (!isBasicTypes(clazz)){
			if (f.get(object) != null){
				String filedClassNameAndPackage = f.get(object).getClass().getName(); 
				String fieldClassName = f.getName() + (++numberOfRecursions).toString();
				String typesParams = getTypeParameters(f.get(object).getClass());
				if(!fieldClassName.equals(capitalize(nameObj))){
					
					sb.append("\n" + ESP2 + filedClassNameAndPackage + typesParams +  " " + fieldClassName.toLowerCase() + " = new " + filedClassNameAndPackage + typesParams + "();\n");
					sb.append(makeSetters(f.get(object), fieldClassName.toLowerCase()));
					sb.append(ESP2 + nameObj + "." + setFieldName + "(" + fieldClassName.toLowerCase()  + ");\n\n");
				}
			}
		} else {
			sb.append(ESP2 + nameObj + "." + setFieldName + "(" + f.get(object) + ");\n");
		}
		return sb.toString();
	}
	
//	public static String constructObject(Class<?> clazz, Field f,String nameObjParent, String setFieldName, String nameObj){
//		return ESP2 + (java.lang.reflect.Modifier.isStatic(f.getModifiers()) ?  clazz.getName() : nameObjParent ) + "." + setFieldName + "(" + nameObj  + ");\n";
//	}
	
	public static String getTypeParameters(Class<?> clazz){
		String result = "";
		for (int i=0; i < clazz.getTypeParameters().length; i++){
			result += ",Object"; 
		}
		return result == "" ? "" : "<" + result.substring(1) + ">";
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
				String typesParams = getTypeParameters(obj.getClass());
				sb.append(ESP2 + className + typesParams + " " + objName + " = new " + className + typesParams + "();\n");
				sb.append(makeSetters(obj, objName));
			}
		}
		return sb.toString();		
	}
	
	public static String getInterfaceOfCollection(Collection<?> collection){
		if (Arrays.asList(collection.getClass().getInterfaces()).contains(List.class)){
			return "List";
		} 
		if (Arrays.asList(collection.getClass().getInterfaces()).contains(Set.class)){
			return "Set";			
		}
		return "****NOT YET IMPLEMENTED**** type:" + (collection.getClass().getName()) + " \n" +
			"please, send a message to the developer to him develop on: \n" + 
			"github: https://github.com/lucasRogerioOliveira/makeTests or in a email: 'lucasoliveiracampos@gmail.com' thanks a lot :D\n";
	}
	
	public static boolean isAlpha(Object obj){
		return obj.getClass().equals(String.class) || obj.getClass().equals(char.class) || obj.getClass().equals(Character.class);
	}
	
	public static boolean isDate(Class<?> clazz){
		return clazz.equals(java.sql.Date.class) || clazz.equals(java.util.Date.class) 
				|| clazz.equals(Calendar.class) || clazz.equals(DateTime.class);
	}
	
	public static Set<String> getMethodsNames(Class<?> clazz){
		Set<String> result = new HashSet<String>();
		for(Method m : clazz.getMethods()){
			result.add(m.getName());
		}
		return result;
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
				imports.add("org.joda.time.DateTime");
				imports.add("org.joda.time.DateTimeZone");
				DateTime dt = (DateTime)obj;
				sb.append(ESP2 + "DateTime " + objName + " = new DateTime(" + 	dt.getMillis() + "L, DateTimeZone.forID(" + '"' + dt.getZone().getID()  + '"' + "));\n");
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
	
	public static Boolean isBasicTypes(Class<?> clazz){
		return clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Boolean.class) 
				|| clazz.equals(Double.class) || clazz.equals(Float.class) 
				|| clazz.equals(Character.class) || clazz.isPrimitive(); 
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