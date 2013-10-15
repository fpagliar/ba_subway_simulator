import java.util.ArrayList;
import java.util.List;


public class Start {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Station last = new Station(null, 5);
		last.name = "last";
		BetweenStationSpace space2 = new BetweenStationSpace(last, 5, "entre2");
		Station middle = new Station(space2, 2);
		middle.name = "middle";
		BetweenStationSpace space = new BetweenStationSpace(middle, 10, "entre1");
		Station first = new Station(space, 3);
		first.name = "first";

		Train train = new Train();
		Person p = null;

		for(int i = 0; i < 70; i++){
			p = new Person(last);
			train.passengerIn(p);
		}

		for(int i = 0; i < 10; i++){
			p = new Person(middle);
			train.passengerIn(p);
		}

		for(int i = 0; i < 50; i++){
			p = new Person(last);
			middle.personArrival(p);
		}

		first.trainArrival(train, 0);
		
		for(int i = 0; i < 10; i++){
			System.out.println("tick");
			SimulatorScheduler.getInstance().advanceTime();
		}
		
		System.out.println("Final State");

		System.out.println("last -> train:" + last.train + " #:" + last.getPersonsWaiting());
		System.out.println("middle -> train:" + middle.train + " #:" + middle.getPersonsWaiting());
		System.out.println("first -> train:" + first.train + " #:" + first.getPersonsWaiting());

		System.out.println("train -> #:" + train.getPassangers().size());
		
	}

}
