import java.util.ArrayList;
import java.util.List;


public class Train implements SimulatorObject {

	List<Person> passangers;
	int size;
	
	public Train(){
		passangers = new ArrayList<Person>();
		size = 10;
	}
	
	public boolean personEntering(Person p){
		if(passangers.size() < size){
			passangers.add(p);
			return true;
		}
		return false;
	}
	
	@Override
	public void event(double timestamp) {
		// TODO Auto-generated method stub
		
	}

}
