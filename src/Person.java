
import java.util.Date;

public class Person {

	private int id;
	private Station destiny;
	private Station origin;
	private Date start;
	private static long people = 0;
	
	public Person(Station destiny){
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
	
	private static void incPeople(){
		people++;
		if(people %10000 == 0)
			System.out.println("people ->" + people);
	}
	
}
