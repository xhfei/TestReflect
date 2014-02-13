package reflect.my;

public class Car {
	private int priace;
	private String brand;
	public Car(){
		
	}
	public Car(int priace,String brand){
		this.priace=priace;
		this.brand=brand;
	}
	public int getPriace() {
		return priace;
	}
	public void setPriace(int priace) {
		this.priace = priace;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
