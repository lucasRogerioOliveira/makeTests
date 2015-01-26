import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class ClassLoaderTests {

	public void test() {
		
		String fileToCompile = "C:/Users/Lucas/Desktop/classes/Test.java";
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		System.out.println(compiler);
		int compilationResult =	compiler.run(null, null, null, fileToCompile);
		if(compilationResult == 0){
			System.out.println("Compilation is successful");
			
			URL dirUrl;
			try {
				dirUrl = new URL("file:/C:/Users/Lucas/Desktop/classes/");

				URLClassLoader cl = new URLClassLoader(new URL[] { dirUrl }, getClass().getClassLoader());
				Class<?> loadedClass = cl.loadClass("Test");
				System.out.println(loadedClass.getName());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("Compilation Failed");
		}
		
		
	}
	
	public static void main(String[] args) {
		new ClassLoaderTests().test();
	
	}

}
