package controller;

import java.util.Iterator;
import java.util.List;

import com.actelion.research.share.gui.DialogResult;

import gui.ScoreDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Player;
import service.PlayerService;

public class MainFrameController extends Controller {

	@FXML
	VBox score1;
	@FXML
	VBox score2;
	@FXML
	VBox score3;
	@FXML
	VBox score4;
	@FXML
	Button mancheBtn;
	@FXML
	Button tournerBtn;
	Font font=new Font("Arial", 30);
	Font fontBold = Font.font("Arial", FontWeight.BOLD,30);
	Font fontBoldItalic = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC,30);

	private List<Player> players = PlayerService.getInstance().getPlayers();

	public void initialUpdate() {
		Stage stage = (Stage) getView().getScene().getWindow();
		stage.setTitle("Mah-Jong Score");
		getView().setStyle("-fx-background-image: url('images/background.png');");

		Label name1=new Label(players.get(0).getName());
		name1.setFont(fontBold);
		score1.getChildren().add(name1);
		Label name2 = new Label(players.get(1).getName());
		name2.setFont(fontBold);
		score2.getChildren().add(name2);
		Label name3 = new Label(players.get(2).getName());
		name3.setFont(fontBold);
		score3.getChildren().add(name3);
		Label name4= new Label(players.get(3).getName());
		name4.setFont(fontBold);
		score4.getChildren().add(name4);

		tournerBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Iterator<Player> iterator = players.iterator();
				boolean find = false;
				while (iterator.hasNext() && !find) {
					Player player = iterator.next();
					if ((player).isEast()) {
						find = true;
						player.setEast(false);
						if (iterator.hasNext())
							iterator.next().setEast(true);
						else
							players.get(0).setEast(true);
					}
				}
				writeScore();
			}
		});

		mancheBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				ScoreDialog dlg = new ScoreDialog(stage);
				if (dlg.doModal() == DialogResult.IDOK) {
					writeScore();
				}
			}
		});
		writeScore();
	}

	private void writeScore() {
		Label label1 = new Label(players.get(0).getScore() + (players.get(0).isEast() ? "*" : ""));
		label1.setFont(font);
		score1.getChildren().add(label1);
		Label label2 = new Label(players.get(1).getScore() + (players.get(1).isEast() ? "*" : ""));
		label2.setFont(font);
		score2.getChildren().add(label2);
		Label label3 = new Label(players.get(2).getScore() + (players.get(2).isEast() ? "*" : ""));
		label3.setFont(font);
		score3.getChildren().add(label3);
		Label label4 = new Label(players.get(3).getScore() + (players.get(3).isEast() ? "*" : ""));
		label4.setFont(font);
		score4.getChildren().add(label4);
		changeEst();
	}
	
	private void changeEst() {
		if(players.get(0).isEast()) {
			((Label)score1.getChildren().get(0)).setTextFill(Color.BLUE);
			((Label)score1.getChildren().get(0)).setFont(fontBoldItalic);;
		}else {
			((Label)score1.getChildren().get(0)).setTextFill(Color.BLACK);
			((Label)score1.getChildren().get(0)).setFont(fontBold);;
		}
		
		if(players.get(1).isEast()) {
			((Label)score2.getChildren().get(0)).setTextFill(Color.BLUE);
			((Label)score2.getChildren().get(0)).setFont(fontBoldItalic);;
		}else {
			((Label)score2.getChildren().get(0)).setTextFill(Color.BLACK);
			((Label)score2.getChildren().get(0)).setFont(fontBold);;
		}
		
		if(players.get(2).isEast()) {
			((Label)score3.getChildren().get(0)).setTextFill(Color.BLUE);
			((Label)score3.getChildren().get(0)).setFont(fontBoldItalic);;
		}else {
			((Label)score3.getChildren().get(0)).setTextFill(Color.BLACK);
			((Label)score3.getChildren().get(0)).setFont(fontBold);;
		}
		
		if(players.get(3).isEast()) {
			((Label)score4.getChildren().get(0)).setTextFill(Color.BLUE);
			((Label)score4.getChildren().get(0)).setFont(fontBoldItalic);;
		}else {
			((Label)score4.getChildren().get(0)).setTextFill(Color.BLACK);
			((Label)score4.getChildren().get(0)).setFont(fontBold);;
		}
	}
}
