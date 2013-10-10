import java.util.Date;

public class Person implements SimulatorObject{

	private int id;
	private Station destiny;
	private Station origin;
	private Date start;
	
	public Person(){
		id = (int)(Math.random() * 100000);
		start = new Date();
	}

	public void event(double timestamp){
	}
	
}
