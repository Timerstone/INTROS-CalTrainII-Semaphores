package models;

import java.util.ArrayList;

public class ThreadManager 
{
	private ArrayList<Stops> stops;
	private ArrayList<Thread> stopThreads;
	private ArrayList<Trains> trains;
	private ArrayList<Thread> trainThreads;
	private ArrayList<Passengers> passengers;
	private ArrayList<Thread> passengerThreads;
	
	public ThreadManager()
	{
		stops = new ArrayList<>();
		stopThreads = new ArrayList<>();
		trains = new ArrayList<>();
		trainThreads = new ArrayList<>();
		passengers = new ArrayList<>();
		passengerThreads = new ArrayList<>();
	}
	
	public void addStop(Stops stop)
	{
		stops.add(stop);
	}
	
	public void addStopThread(Thread thread)
	{
		stopThreads.add(thread);
	}
	
	public void addTrain(Trains train)
	{
		trains.add(train);
	}
	
	public void addTrainThread(Thread thread)
	{
		trainThreads.add(thread);
	}
	
	public void addPassenger(Passengers passenger)
	{
		passengers.add(passenger);
	}
	
	public void addPassengerThread(Thread thread)
	{
		passengerThreads.add(thread);
	}
	
	/******************************************/
	
	public Stops getStop(int index)
	{
		return stops.get(index);
	}
	
	public Stops getStop(String name)
	{
		for(int i = 0; i < stops.size(); i++)
		{
			if(stops.get(i).getName().equals(name))
				return stops.get(i);
		}
		
		return null;
	}
	
	public ArrayList<Stops> getStops()
	{
		return stops;
	}
	
	/******************************************/
	
	public Trains getTrain(int id)
	{
		for(int i = 0; i < trains.size(); i++)
		{
			if(trains.get(i).getId() == id)
				return trains.get(i);
		}
		
		return null;
	}
	
	public Trains getTrain(String name)
	{
		for(int i = 0; i < trains.size(); i++)
		{
			if(trains.get(i).getName().equals(name))
				return trains.get(i);
		}
		
		return null;
	}
	
	public ArrayList<Trains> getTrains()
	{
		return trains;
	}
	
	/******************************************/
	
	public Passengers getPassenger(int id)
	{
		for(int i = 0; i < passengers.size(); i++)
		{
			if(passengers.get(i).getId() == id)
				return passengers.get(i);
		}
		
		return null;
	}
	
	public ArrayList<Passengers> getPassengers()
	{
		return passengers;
	}
	
	/******************************************/
	
	public Thread getStopThread(int index)
	{
		return stopThreads.get(index);
	}
	
	public Thread getStopThread(String name)
	{
		for(int i = 0; i < stopThreads.size(); i++)
		{
			if(stopThreads.get(i).getName().equals(name))
				return stopThreads.get(i);
		}
		
		return null;
	}
	
	public ArrayList<Thread> getStopThreads()
	{
		return stopThreads;
	}
	
	/******************************************/
	
	public Thread getTrainThread(int index)
	{
		return trainThreads.get(index);
	}
	
	public Thread getTrainThread(String name)
	{
		for(int i = 0; i < trainThreads.size(); i++)
		{
			if(trainThreads.get(i).getName().equals(name))
				return trainThreads.get(i);
		}
		
		return null;
	}
	
	public ArrayList<Thread> getTrainThreads()
	{
		return trainThreads;
	}
	
	/******************************************/
	
	public Thread getPassengerThread(int index)
	{
		return passengerThreads.get(index);
	}
	
	public Thread getPassengerThread(String name)
	{
		for(int i = 0; i < passengerThreads.size(); i++)
		{
			if(passengerThreads.get(i).getName().equals(name))
				return passengerThreads.get(i);
		}
		
		return null;
	}
	
	public ArrayList<Thread> getPassengerThreads()
	{
		return passengerThreads;
	}
	
	/******************************************/
}
