package gui;

import com.actelion.research.jfx.gui.misc.TDialog;

import controller.PlayerController;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import service.PlayerService;
import util.FXMLHelper;

public class PlayerDialog extends TDialog {
	private PlayerService srv;

	public PlayerDialog(Stage parent, PlayerService srv) {
		super(parent);
		this.srv = srv;
	}

	@Override
	public void buildGUI(BorderPane parent) {
		PlayerController cntrl = (PlayerController) FXMLHelper.loadFXML("/fxml/PlayerView.fxml");
		cntrl.setService(srv);
		parent.setCenter(cntrl.getView());
		cntrl.setCallBack(this);
	}
}
