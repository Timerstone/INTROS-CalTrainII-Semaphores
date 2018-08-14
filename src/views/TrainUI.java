package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Trains;

public class TrainUI implements Initializable
{
    @FXML private Button btnEnd;
    @FXML private Button btnCancel;
    @FXML private Label lblMessage;
    @FXML private Label lblName;
    @FXML private Label lblCapacity;
    @FXML private Label lblPassengers;
    @FXML private Label lblStation;
    @FXML private Button btnRefresh;

    private Trains train;
    
    @FXML
    private void close() 
    {
    	Stage stage = (Stage)btnCancel.getScene().getWindow();
    	stage.close();
    }

    @FXML
    private void end() 
    {

    }
    
    @FXML
    public void updateInfo()
    {
    	lblName.setText(train.getName());
    	lblCapacity.setText(Integer.toString(train.getCapacity()));
    	lblPassengers.setText(Integer.toString(train.getPassengerCount()));
    	lblStation.setText(train.getStop().getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        assert btnEnd != null : "fx:id=\"btnEnd\" was not injected: check your FXML file 'TrainUI.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'TrainUI.fxml'.";
        assert lblMessage != null : "fx:id=\"lblMessage\" was not injected: check your FXML file 'TrainUI.fxml'.";
        assert lblName != null : "fx:id=\"lblName\" was not injected: check your FXML file 'TrainUI.fxml'.";
        assert lblCapacity != null : "fx:id=\"lblCapacity\" was not injected: check your FXML file 'TrainUI.fxml'.";
        assert lblPassengers != null : "fx:id=\"lblPassengers\" was not injected: check your FXML file 'TrainUI.fxml'.";
        assert lblStation != null : "fx:id=\"lblStation\" was not injected: check your FXML file 'TrainUI.fxml'.";
        assert btnRefresh != null : "fx:id=\"btnRefresh\" was not injected: check your FXML file 'TrainUI.fxml'.";
        
        lblMessage.setVisible(false);
        
        btnEnd.setDisable(true);
    }
    
    public void setTrain(Trains train)
    {
    	this.train = train;
    }
}
