
public class TrainFactory implements SimulatorObject{

	private BouncePoint bouncePoint;
	private boolean creation;
	private int qty;
	
	public TrainFactory(BouncePoint bouncePoint){
		this.bouncePoint = bouncePoint;
		creation = true;
		qty = 0;
	}
	
	public void start() throws Exception{
		SimulatorScheduler.getInstance().registerEvent(1L, new SchedulerRegistrator(this, "train creation"));

	}
	
	public void trainArrival(){
		creation = false;
	}

	@Override
	public void event(Long timestamp) throws Exception {
		if(creation){
			bouncePoint.trainArrival(new Train("Train-" + qty + "-" + bouncePoint.getName(),bouncePoint.getLine()), timestamp, true);
			SimulatorScheduler.getInstance().registerEvent(timestamp + bouncePoint.getLine().getFrequency(), new SchedulerRegistrator(this, "train creation in " + bouncePoint.getName()));
			qty++;
			System.out.println("trains created in bp:" + bouncePoint.getName() + " qty:" + qty);
		}
	}
	
}
