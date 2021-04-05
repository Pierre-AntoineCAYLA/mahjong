package gui;

import com.actelion.research.jfx.gui.misc.TDialog;

import controller.ScoreController;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.FXMLHelper;

public class ScoreDialog extends TDialog {

	public ScoreDialog(Stage parent) {
		super(parent);
	}

	@Override
	public void buildGUI(BorderPane parent) {
		ScoreController cntrl = (ScoreController) FXMLHelper.loadFXML("/fxml/ScoreView.fxml");
		parent.setCenter(cntrl.getView());
		cntrl.setCallBack(this);
	}
}
