package classes.foo;

import org.joda.time.DateTime;

public class Foo {
	
	private static int id = 0;
	private String ticarica;
	private DateTime dateTime;
	
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

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}	
	

}
