
import java.util.Date;

public class Person {

	private int id;
	private Station destiny;
	private Station origin;
	private Date start;
	
	public Person(Station destiny){
		this.destiny = destiny;
	}

	public Station getDestiny(){
		return destiny;
	}
	
	public void descend(){
		
	}
	
	public boolean hasToDecend(Station destiny){
		return (this.destiny != null) && (this.destiny.equals(destiny));
	}
	
}
