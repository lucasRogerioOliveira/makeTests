package classes.bar;

import java.util.Date;

public class Bar {
	
	private static int id = 0;
	private String description;
	private Date date;
	
	
	public Bar(){
		id++;
	}
	
	@Override
	public int hashCode() {
		return id;
	}	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

}
