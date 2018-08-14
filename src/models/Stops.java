package models;

import java.util.concurrent.Semaphore;

import controller.Controller;

public class Stops implements Runnable
{
	private static int idCount = 0;
	
	protected String name;
	protected int id;
	protected Trains train;
	protected Semaphore isAvailable;
	protected int x;
	protected int y;
	protected Stops next;
	private boolean running;
	protected Controller control;
	
	protected Thread t1;
	
	public Stops(String name)
	{
		this.name = name;
		id = idCount;
		idCount++;
		train = null;
		isAvailable = new Semaphore(1, true);
		running = true;
	}
	
	/******************************************/
	
	public void run()
	{
		System.out.println("[S] Stop " + name + " is starting.");
		
		while(running)
		{
			System.out.println("[S] Stop " + name + " is waiting for a train.");
			synchronized(this)
			{
				pause();
			}
			
			if(train != null)
			{
				System.out.println("[S] Stop " + name + " has received train: " + train.getName());
				System.out.println("[S] Stop " + name + " is checking the next station's availability.");
				
				while(next.hasTrain())
					sleep(20);
				
				sleep(20);
				
				synchronized (train)
				{
					train.signal();
				}
				
				System.out.println("[S] Stop " + name + " has released train: " + train.getName());
				
				train = null;
				releaseSlot();
			}
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
			System.err.println("[S] Stop " + name + " failed to wait.");
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
			System.err.println("[S] Stop " + name + " failed to sleep.");
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
	
	public void setNextStop(Stops stop)
	{
		next = stop;
	}
	
	public Stops getNextStop()
	{
		return next;
	}
	
	public void setCoords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public synchronized void getSlot()
	{
		try 
		{
			isAvailable.acquire();
		} 
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public synchronized boolean tryGetSlot()
	{
		return isAvailable.tryAcquire();
	}
	
	public synchronized void releaseSlot()
	{
		isAvailable.release();
	}
	
	public void setTrain(Trains train)
	{
		this.train = train;
	}
	
	public synchronized Trains getTrain()
	{
		return train;
	}
	
	public boolean hasTrain()
	{
		if(isAvailable.availablePermits() == 0)
			return true;
		
		return false;
	}
	
	public void removeTrain()
	{
		train = null;
		releaseSlot();
		pause();
	}
	
	public void endRun()
	{
		running = false;
	}
	
	public void setController(Controller control)
	{
		this.control = control;
	}
}
