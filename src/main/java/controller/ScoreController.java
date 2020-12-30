package controller;

import java.util.ArrayList;
import java.util.List;

import com.actelion.research.jfx.gui.misc.DialogCallback;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import model.Player;
import service.PlayerService;
import service.ScoreService;

public class ScoreController extends Controller {

	@FXML
	Label j1;
	@FXML
	TextField score1;
	@FXML
	RadioButton mahjong1;
	@FXML
	Label j2;
	@FXML
	TextField score2;
	@FXML
	RadioButton mahjong2;
	@FXML
	Label j3;
	@FXML
	TextField score3;
	@FXML
	RadioButton mahjong3;
	@FXML
	Label j4;
	@FXML
	TextField score4;
	@FXML
	RadioButton mahjong4;
	@FXML
	ToggleGroup choice;
	@FXML
	Button ok;

	private DialogCallback callBack;
	
	@FXML
	void initialize() {
		List<Player> players = PlayerService.getInstance().getPlayers();
		j1.setText(players.get(0).getName());
		j2.setText(players.get(1).getName());
		j3.setText(players.get(2).getName());
		j4.setText(players.get(3).getName());
		score1.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				checkForm();
			}
		});
		
		score2.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				checkForm();
			}
		});
		
		score3.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				checkForm();
			}
		});
		
		score4.textProperty().addListener(new ChangeListener<String>() {
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
		
		mahjong1.setUserData(0);
		mahjong2.setUserData(1);
		mahjong3.setUserData(2);
		mahjong4.setUserData(3);
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				ArrayList<Integer>scores= new ArrayList<>();
				scores.add(Integer.valueOf(score1.getText()));
				scores.add(Integer.valueOf(score2.getText()));
				scores.add(Integer.valueOf(score3.getText()));
				scores.add(Integer.valueOf(score4.getText()));
				ScoreService.updateScorePlayer(scores,(Integer)choice.getSelectedToggle().getUserData());
				callBack.onOK();
			}
		});
	}
	
	private void checkForm() {
		if(!score1.getText().equals("") && !score2.getText().equals("") && !score3.getText().equals("") && !score4.getText().equals("") && choice.getSelectedToggle()!=null)
			ok.setDisable(false);
		else
			ok.setDisable(true);
	}

	public void setCallBack(DialogCallback callBack)
    {
        this.callBack = callBack;
    }
	
}
