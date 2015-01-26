import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;


public class MakeTests {

	private static Integer numberOfRecursons = 0;
	private static Set<String> imports = new HashSet<String>();
	private final static String ESP = "    "; //1*4
	private final static String ESP2 = "        "; //2*4
	//	private final static String ESP3 = "            "; //3*4

	public static void makeAllTestsWith(Object object) throws IOException{
		Class<?> clazz = object.getClass();
		StringBuilder sb = new StringBuilder();
		String cn = clazz.getName().substring(clazz.getName().indexOf(".") + 1);
		String lcn = cn.toLowerCase();
		String setters = makeSetters(object, null);
		sb.append("package " + getPackage(cn) + ";\n");
		sb.append(getImports());
		sb.append("\n\n");
		sb.append("public class " + cn + "Tests {\n\n\n");
		sb.append(ESP + "private " + cn + " " + lcn + " = new " + cn + "();\n\n");
		sb.append(ESP + "@Before\n");
		sb.append(ESP + "public void beforeTest(){\n");
		sb.append(setters);
		sb.append(ESP + "}\n\n");
		for (Method m : clazz.getDeclaredMethods()){
			String rtn = m.getReturnType().getName(); // return type name
			if (!rtn.equals("void")) {
				sb.append(ESP + "@Test\n");
				sb.append(ESP + "public void " + m.getName() + "Test(){\n");
				if(!Modifier.isStatic(m.getModifiers())){
					//					sb.append(ESP2 + cn + " " + lcn + " = new " + cn + "();\n");
					//					sb.append(setters);
					sb.append(ESP2 + rtn + " actual = " + lcn + "." + m.getName() + "(" + getParametersTypeStr(m)  + ");\n");
					sb.append(ESP2 + "//make your asserts here :)\n");
				}
				sb.append(ESP +"}\n\n");
			}
		}
		sb.append("}\n");
		saveOrUpdateFile(sb, cn);
	}

	public static String getImports(){
		StringBuilder sb = new StringBuilder("import org.junit.Test;\n");
		sb.append("import org.junit.Before;\n");
		sb.append("import org.junit.BeforeClass;\n");
		for(String imp : imports){
			sb.append("import " + imp + ";\n");
		}
		return sb.toString();
	}

	public static boolean saveOrUpdateFile(StringBuilder sb, String className) throws IOException{
		//		String path = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String pathStr = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target", "src/test/java");
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

	public static String getPackage(String className){
		String path = MakeTests.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		return path.substring(path.indexOf("target") + 7, path.length()-1);
	}

	public static String makeSetters(Object object, String nameObj){
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = object.getClass();
		if (nameObj == null){ 
			nameObj = getClassName(clazz).toLowerCase();
		}
		imports.add(object.getClass().getName());
		for(Field f : clazz.getDeclaredFields()){
			try {
				f.setAccessible(true);
				if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())){
					if (f.getType().equals(String.class)){
						sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + '"' + f.get(object)  + '"' + ");\n");
					} else if (f.getType().isEnum()) {
						sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + f.getType().getName() + "." + f.get(object)  + ");\n");
					} else if (f.getType().equals(List.class)){
						if (!f.get(object).toString().equals("[]")){
							List<?> list = (List<?>)f.get(object);
							if(list.size() > 0){
								String listName = (f.getName() + (++numberOfRecursons).toString()).toLowerCase(); 
								String fieldName = "";
								String className = getClassName(list.get(0).getClass());
								imports.add("java.util.List");
								imports.add("java.util.ArrayList");
								sb.append(ESP2 + "List<" + className  + "> " + listName + " = new ArrayList<" + className + ">();\n");
								for(Object objOfList : list){
									fieldName = (getClassName(list.get(0).getClass()) + (++numberOfRecursons).toString()).toLowerCase();
									sb.append(ESP2 + className + " " + fieldName + " = new " + className + "();\n");
									sb.append(makeSetters(objOfList, fieldName));
									sb.append(ESP2 + listName + ".add(" + fieldName + ");\n");
									sb.append("\n");
								}
							}
						}

					} else if (!isBasicTypes(f)){
						if (f.get(object) != null){
							String fieldClassName = f.get(object).getClass().getName().substring(f.get(object).getClass().getName().indexOf(".") + 1);
							if(!fieldClassName.equals(capitalize(nameObj))){
								sb.append("\n" + ESP2 + fieldClassName + " " + fieldClassName.toLowerCase() + " = new " + fieldClassName + "();\n");
								sb.append(makeSetters(f.get(object), fieldClassName.toLowerCase()));
								sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName() + "(" + fieldClassName.toLowerCase()  + ");\n\n"));
							} else {
								sb.append("\n" + ESP2 + fieldClassName + " " + fieldClassName.toLowerCase() + (++numberOfRecursons).toString() + " = new " + fieldClassName + "();\n");
								sb.append(makeSetters(f.get(object), nameObj + (numberOfRecursons).toString()));
								sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName() + "(" + fieldClassName.toLowerCase() + (numberOfRecursons).toString() + ");\n\n"));
							}
						}
					} else {
						sb.append(ESP2 + nameObj + ".set" + capitalize(f.getName()) + "(" + f.get(object) + ");\n");
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String makeSettersWichJson(Object object, String nameObj){
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValueAsString(object);
			JsonNode jsonNode = mapper.valueToTree(object);
			if (jsonNode.isObject()) {
				ObjectNode jsonObj = (ObjectNode) jsonNode; 
				Iterator<Entry<String,JsonNode>> iterator = jsonObj.getFields();
				while (iterator.hasNext()) {
					Entry<String,JsonNode> obj = iterator.next();
					obj = obj;
				}

			}			 
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapper.toString();
	}

	private static Boolean isBasicTypes(Field f){
		return f.getType().equals(String.class) || f.getType().equals(Integer.class) || f.getType().equals(Boolean.class) 
				|| f.getType().equals(Double.class) || f.getType().equals(Float.class); 
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

	private static String joinStringArray(String[] strings){
		StringBuilder builder = new StringBuilder();

		for (String string : strings) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(string);
		}

		return builder.toString();
	}

}
