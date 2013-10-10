
public class BetweenStationSpace implements SimulatorObject{

	private Station next;
	private double lenght;
	
	public void BetweenStationSpace(Station next, double length){
		this.next = next;
		this.lenght = length;
	}
	
	@Override
	public void event(double timestamp) {
		// TODO Auto-generated method stub
		
	}

	
	
}
