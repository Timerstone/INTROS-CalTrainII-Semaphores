package views;

import controller.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DispatchMenu implements Initializable
{

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField txfName;
    @FXML private Button btnConfirm;
    @FXML private Button btnCancel;
    @FXML private Label lblErrorMsg;
    @FXML private TextField txfCapacity;
    @FXML private ChoiceBox<String> chbColor;
    
    private Controller control;
    private int trainCount;
    private ObservableList<String> color = FXCollections.observableArrayList("Red", "Teal", "Green", "Purple", "Blue", "Orange");

    @FXML
    public void cancel() 
    {
    	Stage stage = (Stage)btnCancel.getScene().getWindow();
    	stage.close();   	
    }

    @FXML
    private void confirmAdd() 
    {
    	if(trainCount == 16)
    		setErrorMsg("Error: Train limit reached.");
    	else
    	{
	    	String name = txfName.getText();
	    	int capacity = Integer.parseInt(txfCapacity.getText());
	    	String color = chbColor.getValue().toLowerCase();
	    	
	    	control.makeTrain(name, capacity, color);
    	}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        assert txfName != null : "fx:id=\"txfName\" was not injected: check your FXML file 'DispatchMenu.fxml'.";
        assert btnConfirm != null : "fx:id=\"btnConfirm\" was not injected: check your FXML file 'DispatchMenu.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'DispatchMenu.fxml'.";
        assert lblErrorMsg != null : "fx:id=\"lblErrorMsg\" was not injected: check your FXML file 'DispatchMenu.fxml'.";
        assert txfCapacity != null : "fx:id=\"txfCapacity\" was not injected: check your FXML file 'DispatchMenu.fxml'.";
        
        txfCapacity.textProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                if (!newValue.matches("\\d{0,9}?")) 
                {
                    txfCapacity.setText(oldValue);
                }
            }
        });
        
        chbColor.setItems(color);
        chbColor.getSelectionModel().selectFirst();
        
        chbColor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() 
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) 
            {
            	chbColor.setValue((chbColor.getItems().get((Integer) number2)));
            }
        });
        
        lblErrorMsg.setVisible(false);
    }
	
	public void attachController(Controller control)
	{
		this.control = control;
	}
	
	public void setErrorMsg(String message)
    {
    	lblErrorMsg.setText(message);
    	lblErrorMsg.setVisible(true);
    }
	
	public void setTrainCount(int trainCount)
	{
		this.trainCount = trainCount;
		
		if(this.trainCount >= 16)
		{
			btnConfirm.setDisable(true);
		}
		else
		{
			btnConfirm.setDisable(false);
		}
	}
}
