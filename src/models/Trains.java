package models;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Trains implements Runnable
{
	private static int idCount = 0;
	
	private String name;
	private String color;
	private int id;
	private Stops stop;
	private Stops dispatch;
	private ArrayList<Passengers> passengers;
	private Semaphore capacity;
	private int maxCap;
	private boolean endOfShift;
	private int x;
	private int y;
	private Models model;
	
	private Thread t1;
	
	public Trains(String name, int capacity, String color)
	{
		this.name = name;
		id = idCount;
		idCount++;
		stop = null;
		passengers = new ArrayList<>();
		this.capacity = new Semaphore(capacity, true);
		endOfShift = false;
		maxCap = capacity;
	}
	
	/******************************************/
	
	public void run()
	{
		System.out.println("[T] Train " + name + " is starting.");
		boolean trying = false;
		boolean start = true;
//		int count = 0;
		
		stop = stop.getNextStop();
		
		while(!trying)
			trying = stop.tryGetSlot();
		
		trying = false;
		
		while(!endOfShift || stop.getId() != 1)
		{			
			System.out.println("[T] Train " + name + " is going to stop: " + stop.getName());
			if(stop.getName().equals("S1") && !start)
			{
				path(216, 528, false);
				path(stop.getX(), stop.getY(), true);
			}
			else
			{
				if(stop.getName().equals("S3") || 
					stop.getName().equals("S4") || 
					stop.getName().equals("S5") || 
					stop.getName().equals("S7") ||
					stop.getName().equals("S8") ||
					stop.getName().equals("PY"))
				{
					path(stop.getX(), stop.getY(), true);
				}
				else
					path(stop.getX(), stop.getY(), false);
			}
			
			System.out.println("[T] Train " + name + " arrives at stop: " + stop.getName());
			stop.setTrain(this);
			
			System.out.println("[T] Train " + name + " signals stop: " + stop.getName());
			synchronized(stop)
			{
				stop.signal();
			}
			
			System.out.println("[T] Train " + name + " signals its passengers.");
			notifyPassengers();
			
			System.out.println("[T] Train " + name + " is waiting for response from stop: " + stop.getName());
			synchronized(this)
			{
				pause();
			}
			
			stop = stop.getNextStop();
			
			while(!trying)
				trying = stop.tryGetSlot();
			
			trying = false;
			start = false;
			
//			count++;
//			
//			if(count == 17)
//			{
//				endOfShift = true;
//				stop.releaseSlot();
//			}
		}
		
		if(endOfShift)
		{
			System.out.println("[T] Train " + name + " is ending shift, proceeding to dispatch");
			path(216, 528, false);
			path(dispatch.getX(), dispatch.getY(), true);
			path(55, y, false);
		}
	}
	
	public void pause()
	{
		try 
		{
			this.wait();
		} 
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public void sleep(long milliSeconds)
	{
		try 
		{
			t1.sleep(milliSeconds);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void signal()
	{
		this.notify();
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
	
	public void setDispatch(Stops stop)
	{
		dispatch = stop;
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
	
	public void endShift()
	{
		endOfShift = true;
	}
	
	public boolean getStatus()
	{
		return endOfShift;
	}
	
	public boolean isFull()
	{
		if(capacity.availablePermits() == 0)
			return true;
		
		return false;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getCapacity()
	{
		return maxCap;
	}
	
	public int getPassengerCount()
	{
		return passengers.size();
	}
	
	public String getColor()
	{
		return color;
	}
	
	public synchronized void loadIn()
	{
		try 
		{
			capacity.acquire();
		} 
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public synchronized boolean tryLoadIn()
	{
		return capacity.tryAcquire();
	}
	
	public synchronized void loadOut()
	{
		capacity.release();
	}
	
	public void setStop(Stops stop)
	{
		this.stop = stop;
	}
	
	public Stops getStop()
	{
		return stop;
	}
	
	public void removeStop()
	{
		stop = null;
	}
	
	public void setModels(Models model)
	{
		this.model = model;
	}
	
	public void addPassenger(int id)
	{
		System.out.println("[D] Called addPassenger: " + id + ".");
		passengers.add(model.getPassenger(id));
	}
	
	public void removePassenger(int id)
	{
		for(int i = 0; i < passengers.size(); i++)
		{
			if(passengers.get(i).getId() == id)
				passengers.remove(i);
		}
		
		capacity.release();
	}
	
	public void notifyPassengers()
	{
		for(int i = 0; i < passengers.size(); i++)
		{
			synchronized (passengers.get(i)) 
			{
				passengers.get(i).signal();
			}
		}
	}
	
	private void path(int x, int y, boolean downfirst)
	{
		if(downfirst)
		{
			if(getY() > y)
			{
				while(getY() != y)
				{
					setY(getY()-1);
					sleep(20);
				}
			}
			else if(getY() < y)
			{
				while(getY() != y)
				{
					setY(getY()+1);
					sleep(20);
				}
			}
			
			if(getX() > x)
			{
				while(getX() != x)
				{
					setX(getX()-1);
					sleep(20);
				}
			}
			else if(getX() < x)
			{
				while(getX() != x)
				{
					setX(getX()+1);
					sleep(20);
				}
			}
			
		}
		else 
		{
			if(getX() > x)
			{
				while(getX() != x)
				{
					setX(getX()-1);
					sleep(20);
				}
			}
			else if(getX() < x)
			{
				while(getX() != x)
				{
					setX(getX()+1);
					sleep(20);
				}
			}
			
			if(getY() > y)
			{
				while(getY() != y)
				{
					setY(getY()-1);
					sleep(20);
				}
			}
			else if(getY() < y)
			{
				while(getY() != y)
				{
					setY(getY()+1);
					sleep(20);
				}
			}
		}
	}
}
