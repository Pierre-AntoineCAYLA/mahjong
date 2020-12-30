package controller;

import com.actelion.research.jfx.gui.misc.DialogCallback;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import service.PlayerService;

public class PlayerController extends Controller {

	@FXML
	TextField name1;
	@FXML
	RadioButton vent1;
	@FXML
	TextField name2;
	@FXML
	RadioButton vent2;
	@FXML
	TextField name3;
	@FXML
	RadioButton vent3;
	@FXML
	TextField name4;
	@FXML
	RadioButton vent4;
	@FXML
	ToggleGroup choice;
	@FXML
	Button ok;

	private DialogCallback callBack;
	private PlayerService srv;
	
	@FXML
	void initialize() {
		name1.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				checkForm();
			}
		});
		
		name2.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				checkForm();
			}
		});
		
		name3.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				checkForm();
			}
		});
		
		name4.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				checkForm();
			}
		});
		
		choice.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {
				checkForm();
			}
		});
		
		vent1.setUserData(0);
		vent2.setUserData(1);
		vent3.setUserData(2);
		vent4.setUserData(3);
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				srv.createPlayer(name1.getText(),name2.getText(),name3.getText(),name4.getText(),(Integer)choice.getSelectedToggle().getUserData());
				callBack.onOK();
			}
		});
	}
	
	private void checkForm() {
		if(!name1.getText().equals("") && !name2.getText().equals("") && !name3.getText().equals("") && !name4.getText().equals("") && choice.getSelectedToggle()!=null)
			ok.setDisable(false);
		else
			ok.setDisable(true);
	}

	public void setService(PlayerService srv) {
		this.srv=srv;
	}
	
	public void setCallBack(DialogCallback callBack)
    {
        this.callBack = callBack;
    }
	
}
