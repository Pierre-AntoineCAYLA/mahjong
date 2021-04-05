package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.actelion.research.share.gui.DialogResult;

import gui.ScoreDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
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
	HBox scores;
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
	Button undoBtn;
	@FXML
	Button tournerBtn;
	@FXML
	RadioButton scoreRbtn;
	@FXML
	RadioButton historyRbtn;
	Font font=new Font("Arial", 30);
	Font fontBold = Font.font("Arial", FontWeight.BOLD,30);
	Font fontBoldItalic = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC,30);

	private List<Player> players = PlayerService.getInstance().getPlayers();

	public void initialUpdate() {
		Stage stage = (Stage) getView().getScene().getWindow();
		stage.setTitle("Mah-Jong Score");
		getView().setStyle("-fx-background-image: url('images/background.png');");

		ToggleGroup group = new ToggleGroup();
	    scoreRbtn.setToggleGroup(group);
	    historyRbtn.setToggleGroup(group);
	    
		score1.getChildren().add(newLabelName(players.get(0).getName()));
		score2.getChildren().add(newLabelName(players.get(1).getName()));
		score3.getChildren().add(newLabelName(players.get(2).getName()));
		score4.getChildren().add(newLabelName(players.get(3).getName()));

		tournerBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				List<Player> playersInverse = new ArrayList<Player>(players);
				Collections.sort(playersInverse, new Comparator<Player>() {
					@Override
					public int compare(Player o1, Player o2) {
						if(o1.getIndex()==o2.getIndex())
							return 0;
						return o1.getIndex()<o2.getIndex()?1:-1;
					}
					
				});
				for(Player player : players) {
					player.addScore(0);
				}
				Iterator<Player> iterator = playersInverse.iterator();
				boolean find = false;
				Player newEast=null;
				while (iterator.hasNext() && !find) {
					Player player = iterator.next();
					if ((player).isEast()) {
						find = true;
						if (iterator.hasNext()) {
							newEast=iterator.next();
						}else {
							newEast=playersInverse.get(0);
						}
						newEast.setEast(true);
					}
				}
				for(Player player : players) {
					if(!player.equals(newEast)) {
						player.setEast(false);
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
		
		undoBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				for(Player player : players) {
					if(player.getHistory().size()>1) {
						int lastPoint = player.getHistory().get(player.getHistory().size()-1);
						player.setLastScore(-lastPoint);
						player.getHistory().remove(player.getHistory().size()-1);
						player.getEastHistory().remove(player.getEastHistory().size()-1);
						player.setLastEast();
						((VBox)scores.getChildren().get(player.getIndex())).getChildren().remove(((VBox)scores.getChildren().get(player.getIndex())).getChildren().size()-1);
					}
				}
			}
		});
		
		historyRbtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				for(Player player : players) {
					List<Node> scoreList = ((VBox)scores.getChildren().get(player.getIndex())).getChildren();
					scoreList.clear();
					scoreList.add(newLabelName(player.getName()));
					int i=0;
					for(int score : player.getHistory()) {
						scoreList.add(newLabel(score, player.getEastHistory().get(i)));
						i=i+1;
					}
				}
			}
		});
		
		scoreRbtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				for(Player player : players) {
					List<Node> scoreList = ((VBox)scores.getChildren().get(player.getIndex())).getChildren();
					scoreList.clear();
					scoreList.add(newLabelName(player.getName()));
					int scoreTot=0;
					int i=0;
					for(int score : player.getHistory()) {
						scoreTot=scoreTot+score;
						scoreList.add(newLabel(scoreTot, player.getEastHistory().get(i)));
						i=i+1;
					}
				}
			}
		});
		scoreRbtn.setSelected(true);
		writeScore();
	}

	private void writeScore() {
		if(scoreRbtn.isSelected()) {
			score1.getChildren().add(newLabel(players.get(0).getScore(),players.get(0).isEast()));
			score2.getChildren().add(newLabel(players.get(1).getScore(),players.get(1).isEast()));
			score3.getChildren().add(newLabel(players.get(2).getScore(),players.get(2).isEast()));
			score4.getChildren().add(newLabel(players.get(3).getScore(),players.get(3).isEast()));
		}else {
			score1.getChildren().add(newLabel(players.get(0).getHistory().get(players.get(0).getHistory().size()-1),players.get(0).isEast()));
			score2.getChildren().add(newLabel(players.get(1).getHistory().get(players.get(1).getHistory().size()-1),players.get(1).isEast()));
			score3.getChildren().add(newLabel(players.get(2).getHistory().get(players.get(2).getHistory().size()-1),players.get(2).isEast()));
			score4.getChildren().add(newLabel(players.get(3).getHistory().get(players.get(3).getHistory().size()-1),players.get(3).isEast()));
		}
		changeEst();
	}
	
	private Label newLabelName(String name) {
		Label nameLbl=new Label(name);
		nameLbl.setFont(fontBold);
		return nameLbl;
	}
	
	private Label newLabel(Integer score, boolean east) {
		Label label = new Label(score + (east ? "*" : ""));
		label.setFont(font);
		return label;
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
