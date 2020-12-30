package gui;

import java.util.List;

import org.springframework.beans.factory.config.BeanPostProcessor;

import com.actelion.research.share.gui.DialogResult;

import controller.MainFrameController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.PlayerService;
import util.FXMLHelper;

public class MainFrame {

	public static final int WINDOW_WIDTH = 1100;
	public static final int WINDOW_HEIGHT = 1000;

	public MainFrame(BeanPostProcessor r, List<String> args) {
	}

	public void start(final Stage stage) throws Exception {
		PlayerService srv = PlayerService.getInstance();
		PlayerDialog dlg = new PlayerDialog(stage, srv);
		if (dlg.doModal() == DialogResult.IDOK) {
			final MainFrameController controller = (MainFrameController) FXMLHelper.loadFXML("/fxml/MainFrame.fxml");
			Scene scene = new Scene(controller.getView(), WINDOW_WIDTH, WINDOW_HEIGHT);

			scene.getStylesheets().add(GuiUtils.getPlatFormStyleSheet());
			stage.setScene(scene);
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent windowEvent) {
					stage.hide();
				}
			});
			stage.setOnShown(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent windowEvent) {
					controller.initialUpdate();
				}
			});
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
			stage.show();
		}
	}

}
