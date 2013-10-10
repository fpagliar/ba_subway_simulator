import java.util.ArrayList;
import java.util.List;


public class Station implements SimulatorObject {

	List<Person> persons;
	Train train;
	Station next;
	
	public Station(){
		persons = new ArrayList<Person>();
		train = null;
		next = null;
	}

	@Override
	public void event(double timestamp) {
		
	}
	
}
