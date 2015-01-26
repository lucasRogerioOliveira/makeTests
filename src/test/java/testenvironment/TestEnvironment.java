package testenvironment;

import java.util.LinkedList;
import java.util.List;

public abstract class TestEnvironment {
	
	private static List<Class<?>> processedClasses = new LinkedList<Class<?>>();
	private boolean runEveryTime = false;
	
	public abstract void run();
	
	public void setClassesEnvironmentRequireds(Class<? extends TestEnvironment> ...classes) throws InstantiationException, IllegalAccessException{
		for (Class<? extends TestEnvironment>  c : classes){
			if (!processedClasses.contains(c) || runEveryTime){
				TestEnvironment obj = c.newInstance();
				obj.run();
				processedClasses.add(c);
			}
		}
	}
	
	public void setRunEveryTime(boolean runEveryTime){
		this.runEveryTime = runEveryTime; 
	}
	
}
