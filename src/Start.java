import java.util.ArrayList;
import java.util.List;


public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Person> array = new ArrayList<Person>();
		for (int i = 0; i < 2000000; i++){
			array.add(new Person());
		}

		System.out.println("END");
		double d = 0;
		while(d >= 0){
			for(Person p: array)
				p.event(1);
			System.out.println(d);
			d++;
		}
	}

}
