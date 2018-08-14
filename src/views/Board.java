package views;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Models;
import models.TrainImage;
import models.Trains;

public class Board implements Initializable
{
	@FXML private AnchorPane mainBoard;
	@FXML private ImageView s1_light;
    @FXML private ImageView s2_light;
    @FXML private ImageView s3_light;
    @FXML private ImageView s4_light;
    @FXML private ImageView s5_light;
    @FXML private ImageView s6_light;
    @FXML private ImageView s7_light;
    @FXML private ImageView s8_light;

    @FXML private Label dispatchButton;
    @FXML private Label s1_button;
    @FXML private Label s2_button;
    @FXML private Label s3_button;
    @FXML private Label s4_button;
    @FXML private Label s5_button;
    @FXML private Label s6_button;
    @FXML private Label s7_button;
    @FXML private Label s8_button;
    
    @FXML private ImageView pass2_light;
    @FXML private ImageView pass3_light;
    @FXML private ImageView pass4_light;
    @FXML private ImageView pass5_light;
    @FXML private ImageView pass6_light;
    @FXML private ImageView pass7_light;
    @FXML private ImageView pass8_light;
    @FXML private ImageView passX_light;
    
    private Stage stage;
    private DispatchMenu dispatch;
    private StationUI stationUI;
    private TrainUI trainUI;
    private Models model;
    private Controller control;
    private int trainCount;
    private ArrayList<TrainImage> trains;
    private boolean running;
    private Thread updater;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
    	assert mainBoard != null : "fx:id=\"mainBoard\" was not injected: check your FXML file 'Board.fxml'.";
        assert s6_light != null : "fx:id=\"s6_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert s7_light != null : "fx:id=\"s7_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert s8_light != null : "fx:id=\"s8_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert s1_light != null : "fx:id=\"s1_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert s2_light != null : "fx:id=\"s2_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert s3_light != null : "fx:id=\"s3_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert s4_light != null : "fx:id=\"s4_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert s5_light != null : "fx:id=\"s5_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert dispatchButton != null : "fx:id=\"dispatchButton\" was not injected: check your FXML file 'Board.fxml'.";
        assert s1_button != null : "fx:id=\"s1_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert s2_button != null : "fx:id=\"s2_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert s3_button != null : "fx:id=\"s3_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert s4_button != null : "fx:id=\"s4_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert s5_button != null : "fx:id=\"s5_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert s6_button != null : "fx:id=\"s6_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert s7_button != null : "fx:id=\"s7_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert s8_button != null : "fx:id=\"s8_button\" was not injected: check your FXML file 'Board.fxml'.";
        assert pass2_light != null : "fx:id=\"pass2_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert pass3_light != null : "fx:id=\"pass3_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert pass4_light != null : "fx:id=\"pass4_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert pass5_light != null : "fx:id=\"pass5_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert pass6_light != null : "fx:id=\"pass6_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert pass7_light != null : "fx:id=\"pass7_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert pass8_light != null : "fx:id=\"pass8_light\" was not injected: check your FXML file 'Board.fxml'.";
        assert passX_light != null : "fx:id=\"passX_light\" was not injected: check your FXML file 'Board.fxml'.";
        
        dispatchButton.setId("dispatch");
        s1_button.setId("s1");
        s2_button.setId("s2");
        s3_button.setId("s3");
        s4_button.setId("s4");
        s5_button.setId("s5");
        s6_button.setId("s6");
        s7_button.setId("s7");
        s8_button.setId("s8");
        
        dispatchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s1_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s2_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s3_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s4_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s5_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s6_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s7_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        s8_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new StationEvent());
        
        trainCount = 0;
        
        control = new Controller();
        model = new Models();
        
        control.attachModels(model);
        control.attachBoard(this);
        
        model.attachController(control);
        
        control.initiateStops();
        
        trains = new ArrayList<>();
        
        running = true;
        startUpdating();
    }
    
    private class StationEvent implements EventHandler<Event>
    {
    	@Override
    	public void handle(Event event)
    	{
    		String name = null;
    		String id = ((Control)event.getSource()).getId();
    		
    		switch(id)
    		{
    			case "dispatch": dispatchMenu(); break;
    			case "s1": name = "S1"; break;
    			case "s2": name = "S2"; break;
    			case "s3": name = "S3"; break;
    			case "s4": name = "S4"; break;
    			case "s5": name = "S5"; break;
    			case "s6": name = "S6"; break;
    			case "s7": name = "S7"; break;
    			case "s8": name = "S8"; break;
    		}
    		
    		System.out.println("[D] Opening Station UI " + name + ".");
    		
    		if(name != null)
    			stationMenu(name);
    	}
    }
    
    private class TrainEvent implements EventHandler<MouseEvent>
    {
    	@Override
    	public void handle(MouseEvent event)
    	{
    		String id = ((Node)event.getSource()).getId();
    		System.out.println("[D] Node id: " + id);
    		trainMenu(model.getTrain(Integer.parseInt(id)));
    	}
    }
    
    private void dispatchMenu()
    {
    	try 
    	{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DispatchMenu.fxml"));
			Scene scene = new Scene(loader.load());
			
			stage = new Stage();
			stage.setScene(scene);
			
			dispatch = loader.getController();
			dispatch.attachController(control);
			dispatch.setTrainCount(trainCount);
			control.attachDispatch(dispatch);
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} 
    	catch(Exception e) 
    	{
			e.printStackTrace();
		}
    }
    
    private void stationMenu(String station)
    {
    	try 
    	{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StationUI.fxml"));
			Scene scene = new Scene(loader.load());
			
			stage = new Stage();
			stage.setScene(scene);
			
			stationUI = loader.getController();
			stationUI.attachController(control);
			stationUI.attachModels(model);
			control.attachStationUI(stationUI);
			stationUI.setName(station);
			stationUI.startContent();
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
		} 
    	catch(Exception e) 
    	{
			e.printStackTrace();
		}
    }
    
    private void trainMenu(Trains train)
    {
    	try 
    	{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TrainUI.fxml"));
			Scene scene = new Scene(loader.load());
			
			stage = new Stage();
			stage.setScene(scene);
			
			trainUI = loader.getController();
			trainUI.setTrain(train);
			trainUI.updateInfo();
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
		} 
    	catch(Exception e) 
    	{
			e.printStackTrace();
		}
    }
    
    public void addTrain(String color, int id, int x, int y)
    {
    	trainCount++;
    	ImageView image = new ImageView("views/img/car_" + color + ".png");
    	
    	image.setFitHeight(40);
    	image.setFitWidth(38);
    	image.setX(x);
    	image.setY(y);
    	image.setId(Integer.toString(id));
    	image.addEventHandler(MouseEvent.MOUSE_CLICKED, new TrainEvent());
    	
    	mainBoard.getChildren().add(image);
    	
    	trains.add(new TrainImage(id, image));
    	
    }
    
    public TrainImage getTrainImage(int id)
    {
    	for(int i = 0; i < trains.size(); i++)
    	{
    		if(trains.get(i).getId() == id)
    			return trains.get(i);
    	}
    	
    	return null;
    }
    
    private void startUpdating()
    {
    	updater = new Thread("Updater")
		{
    		@SuppressWarnings("static-access")
			public void run()
    		{    			
    			while(running)
    			{
    				for(int i = 0; i < trains.size(); i++)
    				{
    					trains.get(i).getImage().setX(model.getTrain(trains.get(i).getId()).getX());
    					trains.get(i).getImage().setY(model.getTrain(trains.get(i).getId()).getY());
    					
    					if(trains.get(i).getImage().getX() == 55)
    						trains.get(i).getImage().setVisible(false);
    				}
    				
    				try 
    				{
						updater.sleep(15);
					} 
    				catch (InterruptedException e) 
    				{
						e.printStackTrace();
					}
    			}
    		}
		};
		
		updater.start();
    }
}
