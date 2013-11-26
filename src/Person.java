
import java.util.Date;

public class Person {

	private int id;
	private Station destiny;
	private Station origin;
	private Date start;
	private static long totalPeople = 0;
	private static long actualPeople = 0;
	
	public Person(Station origin, Station destiny){
		this.origin = origin;
		this.destiny = destiny;
		incPeople();
	}

	public Station getDestiny(){
		return destiny;
	}
	
	public void descend(){
		
	}
	
	public boolean hasToDecend(Station destiny){
		return (this.destiny != null) && (this.destiny.equals(destiny));
	}
	
	public Station getOrigin(){
		return origin;
	}
	

	private static void incPeople(){
		totalPeople++;
		actualPeople++;
		if(totalPeople %10000 == 0)
			System.out.println("people ->" + totalPeople);
	}
	
	public static long getPeople() {
		return totalPeople;
	}
	
	public static long getActualPeople() {
		return actualPeople;
	}
	
	public static void descendPeople() {
		actualPeople--;
	}
}
