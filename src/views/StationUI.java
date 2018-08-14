package views;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Models;
import models.Passengers;
import models.Stops;

public class StationUI implements Initializable
{
    @FXML private Label lblStation;
    @FXML private Button btnClose;
    @FXML private ChoiceBox<String> chbDestination;
    @FXML private Button btnConfirm;
    @FXML private Label lblErrorMsg;
    @FXML private ChoiceBox<String> chbPassenger;
    @FXML private Button btnTerminate;
    @FXML private Label lblCount;
    @FXML private Label lblMessage;
    
    private Controller control;
    private Models model;

    @FXML
    private void addPassenger() 
    {
    	control.addPassenger(lblStation.getText(), chbDestination.getValue());
    }
    
    @FXML
    private void close() 
    {
    	control.attachStationUI(null);
    	Stage stage = (Stage)btnClose.getScene().getWindow();
    	stage.close();   
    }
    
    @FXML
    private void stopPassenger() 
    {
    	control.stopPassenger(Integer.parseInt(chbPassenger.getValue()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    	assert lblStation != null : "fx:id=\"lblStation\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert chbDestination != null : "fx:id=\"chbDestination\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert lblErrorMsg != null : "fx:id=\"lblErrorMsg\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert lblMessage != null : "fx:id=\"lblMessage\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert chbPassenger != null : "fx:id=\"chbPassenger\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert btnTerminate != null : "fx:id=\"btnTerminate\" was not injected: check your FXML file 'StationUI.fxml'.";
        assert lblCount != null : "fx:id=\"lblCount\" was not injected: check your FXML file 'StationUI.fxml'.";
        
        chbPassenger.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() 
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) 
            {
            	if(!chbPassenger.getItems().isEmpty())
            		chbPassenger.setValue((chbPassenger.getItems().get((Integer) number2)));
            }
        });
        
        chbDestination.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() 
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) 
            {
            	chbDestination.setValue((chbDestination.getItems().get((Integer) number2)));
            }
        });
    }
    
    public void startContent()
    {
    	getDestinations();
        getPassengers();
        
        chbPassenger.getSelectionModel().selectFirst();
        chbDestination.getSelectionModel().selectFirst();
        
        lblErrorMsg.setVisible(false);
        lblMessage.setVisible(false);
        lblCount.setText(Integer.toString(chbPassenger.getItems().size()));
    }
    
    public void updateContent()
    {
    	getPassengers();
        chbPassenger.getSelectionModel().selectFirst();
        
        lblErrorMsg.setVisible(false);
        lblMessage.setVisible(false);
        lblCount.setText(Integer.toString(chbPassenger.getItems().size()));
    }
    
    public void getDestinations()
    {
    	ArrayList<Stops> stops = model.getStops();
    	ArrayList<String> names = new ArrayList<>();
    	chbDestination.getItems().clear();
    	
    	for(int i = 0; i < stops.size(); i++)
    	{
    		String name = stops.get(i).getName();
    		String cname[] = stops.get(i).getClass().getName().split("\\.");
    		
    		if(!name.equals(lblStation.getText()) && !name.equals("Depot") && cname[1].equals("Stations"))
    		{
    			names.add(stops.get(i).getName());
    		}
    	}
    	
    	chbDestination.getItems().addAll(names);
    }
    
    public void getPassengers()
    {
    	ArrayList<Passengers> passengers =  model.getPassengers();
    	ArrayList<String> ids = new ArrayList<>();
    	chbPassenger.getItems().clear();
    	
    	for(int i = 0; i < passengers.size(); i++)
    	{
    		if(passengers.get(i).getSource() != null)
    		{
    			if(passengers.get(i).getSource().getName().equals(lblStation.getText()))
	    		{
	    			ids.add(Integer.toString(passengers.get(i).getId()));
	    		}
    		}
    	}
    	
    	chbPassenger.getItems().addAll(ids);
    }
    
    public void attachController(Controller control)
    {
    	this.control = control;
    }
    
    public void attachModels(Models model)
    {
    	this.model = model;
    }
    
    public void setErrorMsg(String message)
    {
    	lblMessage.setVisible(false);
    	lblErrorMsg.setText(message);
    	lblErrorMsg.setVisible(true);
    }
    
    public void setName(String name)
    {
    	lblStation.setText(name);
    }
    
    public void confirmAdd()
    {
    	lblErrorMsg.setVisible(false);
    	lblMessage.setText("Passenger added.");
    	lblMessage.setVisible(true);
    	
    	getPassengers();
    	lblCount.setText(Integer.toString(chbPassenger.getItems().size()));
    }
    
    public String getName()
    {
    	return lblStation.getText();
    }
}
