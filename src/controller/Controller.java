package controller;

import models.Models;
import models.Stations;
import models.Trains;
import views.Board;
import views.DispatchMenu;
import views.StationUI;

public class Controller 
{
	private Models model;
	private Board board;
	private DispatchMenu dispatch;
	private StationUI stationUI;
	
	public Controller()
	{
		
	}
	
	public void attachModels(Models model)
	{
		this.model = model;
	}
	
	public void attachBoard(Board board)
	{
		this.board = board;
	}
	
	public void attachDispatch(DispatchMenu dispatch)
	{
		this.dispatch = dispatch;
	}
	
	public void attachStationUI(StationUI stationUI)
	{
		this.stationUI = stationUI;
	}
	
	public void makeTrain(String name, int capacity, String color)
	{
		if(model.getTrain(name) == null)
		{
			Trains newTrain = new Trains(name, capacity, color);
			
			newTrain.setDispatch(model.getStops().get(0));
			newTrain.setStop(model.getStops().get(0));
			newTrain.setCoords(newTrain.getStop().getX(), newTrain.getStop().getY());
			
			board.addTrain(color, newTrain.getId(), newTrain.getX(), newTrain.getY());
			
			model.addTrain(newTrain);
			dispatch.cancel();
		}
		else
		{
			dispatch.setErrorMsg("Error: Train already exists.");
		}
	}
	
	public void addPassenger(String source, String destination)
	{
		Stations A = (Stations)model.getStop(source);
		Stations B = (Stations)model.getStop(destination);
		
		model.addPassenger(A, B);
		
		stationUI.confirmAdd();
	}
	
	public void initiateStops()
	{
		model.initiateStops();
	}
	
	public void terminateStops()
	{
		model.terminateStops();
	}
	
	public void stopPassenger(int id)
	{
		
	}
	
	public void updateStationUI(String name)
	{
		if(stationUI != null)
		{
			if(stationUI.getName().equals(name))
			{
//				stationUI.updateContent();
			}
		}
	}
}
