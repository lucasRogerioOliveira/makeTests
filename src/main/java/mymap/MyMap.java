package mymap;

public class MyMap<D,T,R,K> {
	
	private static int id;
	private T fieldT;
	private D fieldD;
	private R fieldR;
	private R fieldK;
	
	public MyMap(){
		id++;
	}
	
	@Override
	public int hashCode(){
		return id;
	}
	
	public T getFieldT() {
		return fieldT;
	}
	public void setFieldT(T fieldT) {
		this.fieldT = fieldT;
	}
	public D getFieldD() {
		return fieldD;
	}
	public void setFieldD(D fieldD) {
		this.fieldD = fieldD;
	}
	public R getFieldR() {
		return fieldR;
	}
	public void setFieldR(R fieldR) {
		this.fieldR = fieldR;
	}
	public R getFieldK() {
		return fieldK;
	}
	public void setFieldK(R fieldK) {
		this.fieldK = fieldK;
	}
	

}
