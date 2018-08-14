package models;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Stations extends Stops
{
	private ArrayList<Passengers> passengers;
	private Semaphore capacity;
	private boolean running;
	
	public Stations(String name, int capacity)
	{
		super(name);
		
		this.capacity = new Semaphore(capacity, true);
		passengers = new ArrayList<>();
		running = true;
	}
	
	/******************************************/
	
	public void run()
	{
		System.out.println("[S] Station " + name + " is starting.");
		
		while(running)
		{
			
			System.out.println("[S] Station " + name + " is waiting for a train.");
			pause();
			
			if(train != null)
			{
				System.out.println("[S] Station " + name + " has received train: " + train.getName());
				
				System.out.println("[S] Station " + name + " is notifying passengers.");
				notifyAllPassengers();
				
				System.out.println("[S] Station " + name + " is checking the next station's availability.");
				while(next.hasTrain())
					sleep(20);
				
				if(capacity.availablePermits() == 200 || train.isFull())
					sleep(7000);
				else
				{
					sleep(5000);
					synchronized(this)
					{
						pause();
					}
				}
				
				synchronized (train)
				{
					train.signal();
				}
				
				System.out.println("[S] Station " + name + " has released train: " + train.getName());
				
				train = null;
				releaseSlot();
			}
		}
	}
	
	/******************************************/
	
	public void addPassenger(Passengers passenger)
	{
		tryAddingPassenger();
		passengers.add(passenger);
	}
	
	public boolean tryAddingPassenger()
	{
		return capacity.tryAcquire();
	}
	
	public void removePassenger(int id)
	{
		int temp;
		
		if((temp = getPassenger(id)) == -1)
		{
			passengers.get(temp);
			capacity.release();
		}
	}
	
	private int getPassenger(int id)
	{
		for(int i = 0; i < passengers.size(); i++)
		{
			if(passengers.get(i).getId() == id)
				return passengers.get(i).getId();
		}
		
		return -1;
	}
	
	public void notifyAllPassengers()
	{
		for(int i = 0; i < passengers.size(); i++)
		{
			synchronized(passengers.get(i))
			{
				passengers.get(i).signal();
				System.out.println("[D] Notified passenger " + passengers.get(i).getId() + ".");
			}
		}
		
		control.updateStationUI(name);
	}
	
	public void endRun()
	{
		running = false;
	}
	
	public int getMax()
	{
		return 200;
	}
	
	public int getTickets()
	{
		return capacity.availablePermits();
	}
}
