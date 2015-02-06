package classes.foo;

public class Foo {
	
	private static int id = 0;
	private String ticarica;
	
	public Foo(){
		id++;
	}
	
	@Override
	public int hashCode() {
		return id;
	}	

	public String getTicarica() {
		return ticarica;
	}

	public void setTicarica(String ticarica) {
		this.ticarica = ticarica;
	}
	
	

}
