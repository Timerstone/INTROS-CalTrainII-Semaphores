package models;

public class Passengers implements Runnable
{
	private static int idCount = 0;
	
	private int id;
	private Stations source;
	private Stations destination;
	private Trains train;
	
	private Thread t1;
	private boolean running;
	private boolean isDone;
	
	public Passengers(Stations source, Stations destination)
	{
		this.source = source;
		this.destination = destination;
		id = idCount;
		
		idCount++;
		System.out.println("[D] Passenger count: " + idCount);
		running = true;
		isDone = false;
	}
	
	/******************************************/
	
	public void run()
	{
		boolean loadedin = false;
		while(running && !isDone)
		{
			if(train == null)
				System.out.println("[P] Passenger " + id + " is waiting at station " + source.getName() + ".");
			else
				System.out.println("[P] Passenger " + id + " is in train: " + train.getName() + ".");
				
			synchronized(this)
			{
				pause();
			}
			
			if(train == null)
			{
				System.out.println("[P] Passenger " + id + " is trying to load " + source.getTrain().getName() + ".");
				loadedin = source.getTrain().tryLoadIn();
				
				if(loadedin)
				{
					System.out.println("[P] Passenger " + id + " loaded " + source.getTrain().getName() + ".");
					train = source.getTrain();
					train.addPassenger(id);
					source.removePassenger(id);
					synchronized(source)
					{
						if(source.getTickets() == source.getMax() || train.isFull())
							source.signal();
					}
					source = null;
				}
				else
					System.out.println("[P] Passenger " + id + " failed to load in " + source.getTrain().getName() + ".");
			}
			else
			{
				if(destination.getId() == train.getStop().getId())
				{
					System.out.println("[P] Passenger " + id + " has arrived at " + destination.getName() + ".");
					train.removePassenger(id);
					setDone();
				}
			}
		}
		
		if(isDone)
		{
			System.out.println("[P] Passenger " + id + " is now terminating.");
			//model.deletePassenger(id);
		}
	}
	
	public synchronized void pause()
	{
		try 
		{
			this.wait();
		} 
		catch(InterruptedException e) 
		{
			System.err.println("[P] Passenger " + id + " failed to wait.");
		}
	}
	
	@SuppressWarnings("static-access")
	public synchronized void sleep(long milliSeconds)
	{
		try 
		{
			t1.sleep(milliSeconds);
		} catch (InterruptedException e) 
		{
			System.err.println("[P] Passenger " + id + " failed to sleep.");
		}
	}
	
	public synchronized void signal()
	{
		this.notify();
	}
	
	public synchronized void stop()
	{
		this.notify();
		running = false;
	}
	
	public Thread getThread()
	{
		return t1;
	}
	
	public void setThread(Thread thread)
	{
		t1 = thread;
	}
	
	/******************************************/
	
	public void setDestination(Stations destination)
	{
		this.destination = destination;
	}
	
	public void setSource(Stations source)
	{
		this.source = source;
	}
	
	public void setTrain(Trains train)
	{
		this.train = train;
	}	
	
	public int getId() 
	{
		return id;
	}
	
	public void removeTrain()
	{
		train = null;
	}
	
	public void setDone()
	{
		isDone = true;
	}
	
	public Trains getTrain()
	{
		return train;
	}
	
	public Stations getSource()
	{
		return source;
	}
	
	public Stations getDestination()
	{
		return destination;
	}
	
	public static int getIdCount()
	{
		return idCount;
	}
}
