import java.util.ArrayList;
import java.util.List;


public class Train implements SimulatorObject {

	private List<Person> passangers;
	private int size;
	private String name;
	
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

	public String getName(){
		return name;
	}
	
	public void downloadPassanger(Person p){
		p.descend();
		passangers.remove(p);
	}
	
	public List<Person> getPassangers(){
		return passangers;
	}
	
	@Override
	public void event(double timestamp) {
		// TODO Auto-generated method stub
		
	}

}
