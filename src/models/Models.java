package models;

import java.util.ArrayList;

import controller.Controller;

public class Models 
{
	private ThreadManager manager;
	private Controller control;
	
	public Models()
	{
		manager = new ThreadManager();
		
		  /************************************/
		 /**** Adding Runnable Stops *********/
		/************************************/
		
		manager.addStop(new Stations("Depot", 0));
		manager.getStop(0).setCoords(90, 605);
		
		manager.addStop(new Stations("S1", 200));
		manager.getStop(1).setCoords(320, 605);
		manager.getStop(0).setNextStop(manager.getStop(1));
		
		manager.addStop(new Stops("P2"));
		manager.getStop(2).setCoords(580, 605);
		manager.getStop(1).setNextStop(manager.getStop(2));
		
		manager.addStop(new Stations("S2", 200));
		manager.getStop(3).setCoords(700, 605);
		manager.getStop(2).setNextStop(manager.getStop(3));
		
		manager.addStop(new Stops("P3"));
		manager.getStop(4).setCoords(960, 532);
		manager.getStop(3).setNextStop(manager.getStop(4));
		
		manager.addStop(new Stations("S3", 200));
		manager.getStop(5).setCoords(860, 445);
		manager.getStop(4).setNextStop(manager.getStop(5));
		
		manager.addStop(new Stops("P4"));
		manager.getStop(6).setCoords(711, 286);
		manager.getStop(5).setNextStop(manager.getStop(6));
		
		manager.addStop(new Stations("S4", 200));
		manager.getStop(7).setCoords(788, 241);
		manager.getStop(6).setNextStop(manager.getStop(7));
		
		manager.addStop(new Stops("P5"));
		manager.getStop(8).setCoords(959, 121);
		manager.getStop(7).setNextStop(manager.getStop(8));
		
		manager.addStop(new Stations("S5", 200));
		manager.getStop(9).setCoords(877, 47);
		manager.getStop(8).setNextStop(manager.getStop(9));
		
		manager.addStop(new Stops("P6"));
		manager.getStop(10).setCoords(658, 47);
		manager.getStop(9).setNextStop(manager.getStop(10));
		
		manager.addStop(new Stations("S6", 200));
		manager.getStop(11).setCoords(495, 47);
		manager.getStop(10).setNextStop(manager.getStop(11));
		
		manager.addStop(new Stops("P7"));
		manager.getStop(12).setCoords(351, 99);
		manager.getStop(11).setNextStop(manager.getStop(12));
		
		manager.addStop(new Stations("S7", 200));
		manager.getStop(13).setCoords(248, 172);
		manager.getStop(12).setNextStop(manager.getStop(13));
		
		manager.addStop(new Stops("P8"));
		manager.getStop(14).setCoords(80, 268);
		manager.getStop(13).setNextStop(manager.getStop(14));
		
		manager.addStop(new Stations("S8", 200));
		manager.getStop(15).setCoords(383, 376);
		manager.getStop(14).setNextStop(manager.getStop(15));
		
		manager.addStop(new Stops("PX"));
		manager.getStop(16).setCoords(645, 411);
		manager.getStop(15).setNextStop(manager.getStop(16));
		
		manager.addStop(new Stops("PY"));
		manager.getStop(17).setCoords(265, 491);
		manager.getStop(16).setNextStop(manager.getStop(17));
		manager.getStop(17).setNextStop(manager.getStop(1));
		
		  /************************************/
		 /****** Adding Thread Stops *********/
		/************************************/
		
		manager.addStopThread(new Thread(manager.getStop("Depot"), manager.getStop("Depot").getName()));
		manager.addStopThread(new Thread(manager.getStop("S1"), manager.getStop("S1").getName()));
		manager.addStopThread(new Thread(manager.getStop("P2"), manager.getStop("P2").getName()));
		manager.addStopThread(new Thread(manager.getStop("S2"), manager.getStop("S2").getName()));
		manager.addStopThread(new Thread(manager.getStop("P3"), manager.getStop("P3").getName()));
		manager.addStopThread(new Thread(manager.getStop("S3"), manager.getStop("S3").getName()));
		manager.addStopThread(new Thread(manager.getStop("P4"), manager.getStop("P4").getName()));
		manager.addStopThread(new Thread(manager.getStop("S4"), manager.getStop("S4").getName()));
		manager.addStopThread(new Thread(manager.getStop("P5"), manager.getStop("P5").getName()));
		manager.addStopThread(new Thread(manager.getStop("S5"), manager.getStop("S5").getName()));
		manager.addStopThread(new Thread(manager.getStop("P6"), manager.getStop("P6").getName()));
		manager.addStopThread(new Thread(manager.getStop("S6"), manager.getStop("S6").getName()));
		manager.addStopThread(new Thread(manager.getStop("P7"), manager.getStop("P7").getName()));
		manager.addStopThread(new Thread(manager.getStop("S7"), manager.getStop("S7").getName()));
		manager.addStopThread(new Thread(manager.getStop("P8"), manager.getStop("P8").getName()));
		manager.addStopThread(new Thread(manager.getStop("S8"), manager.getStop("S8").getName()));
		manager.addStopThread(new Thread(manager.getStop("PX"), manager.getStop("PX").getName()));
		manager.addStopThread(new Thread(manager.getStop("PY"), manager.getStop("PY").getName()));
		
		  /*******************************************/
		 /****** Syncing Threads and Runnables ******/
		/*******************************************/
		
		manager.getStop("Depot").setThread(manager.getStopThread("Depot"));
		manager.getStop("S1").setThread(manager.getStopThread("S1"));
		manager.getStop("P2").setThread(manager.getStopThread("P2"));
		manager.getStop("S2").setThread(manager.getStopThread("S2"));
		manager.getStop("P3").setThread(manager.getStopThread("P3"));
		manager.getStop("S3").setThread(manager.getStopThread("S3"));
		manager.getStop("P4").setThread(manager.getStopThread("P4"));
		manager.getStop("S4").setThread(manager.getStopThread("S4"));
		manager.getStop("P5").setThread(manager.getStopThread("P5"));
		manager.getStop("S5").setThread(manager.getStopThread("S5"));
		manager.getStop("P6").setThread(manager.getStopThread("P6"));
		manager.getStop("S6").setThread(manager.getStopThread("S6"));
		manager.getStop("P7").setThread(manager.getStopThread("P7"));
		manager.getStop("S7").setThread(manager.getStopThread("S7"));
		manager.getStop("P8").setThread(manager.getStopThread("P8"));
		manager.getStop("S8").setThread(manager.getStopThread("S8"));
		manager.getStop("PX").setThread(manager.getStopThread("PX"));
		manager.getStop("PY").setThread(manager.getStopThread("PY"));
}
	
	public void initiateStops()
	{
		manager.getStopThread(0).start();
		manager.getStopThread(1).start();
		manager.getStopThread(2).start();
		manager.getStopThread(3).start();
		manager.getStopThread(4).start();
		manager.getStopThread(5).start();
		manager.getStopThread(6).start();
		manager.getStopThread(7).start();
		manager.getStopThread(8).start();
		manager.getStopThread(9).start();
		manager.getStopThread(10).start();
		manager.getStopThread(11).start();
		manager.getStopThread(12).start();
		manager.getStopThread(13).start();
		manager.getStopThread(14).start();
		manager.getStopThread(15).start();
		manager.getStopThread(16).start();
		manager.getStopThread(17).start();
	}
	
	public void terminateStops()
	{
		for(int i = 0; i < manager.getStops().size(); i++)
		{
			manager.getStops().get(i).stop();
		}
	}
	
	public void addTrain(Trains train)
	{
		System.out.println("[D] Adding Train.");
		
		train.setModels(this);
		manager.addTrain(train);
		manager.addTrainThread(new Thread(manager.getTrain(train.getName()), train.getName()));
		manager.getTrain(train.getName()).setThread(manager.getTrainThread(train.getName()));
		
		manager.getTrainThread(train.getName()).start();
	}
	
	public void addPassenger(Stations source, Stations destination)
	{
		Passengers passenger = new Passengers(source, destination);
		manager.addPassenger(passenger);
		
		System.out.println("[D] Added new passenger " + passenger.getId() + " starting from " + passenger.getSource().getName() + " to " + passenger.getDestination().getName() + ".");
		manager.addPassengerThread(new Thread(manager.getPassenger(passenger.getId()), Integer.toString(passenger.getId())));
		manager.getPassenger(passenger.getId()).setThread(manager.getPassengerThread(Integer.toString(passenger.getId())));
		
		source.addPassenger(manager.getPassenger(passenger.getId()));
		manager.getPassengerThread(passenger.getId()).start();
	}
	
	public ArrayList<Stops> getStops()
	{
		return manager.getStops();
	}
	
	public Stops getStop(String name)
	{
		return manager.getStop(name);
	}
	
	public void notifyStop(String name)
	{
		manager.getStop(name).signal();
	}
	
	public void notifyTrain(String name)
	{
		manager.getTrain(name).signal();
	}
	
	public Trains getTrain(String name)
	{
		return manager.getTrain(name);
	}
	
	public Trains getTrain(int id)
	{
		return manager.getTrain(id);
	}
	
	public ArrayList<Passengers> getPassengers()
	{
		return manager.getPassengers();
	}
	
	public void attachController(Controller control)
	{	
		this.control = control;
		
		for(int i = 0; i < manager.getStops().size(); i++)
		{
			manager.getStop(i).setController(this.control);
		}
	}
	
	public Passengers getPassenger(int id)
	{
		return manager.getPassenger(id);
	}
}
