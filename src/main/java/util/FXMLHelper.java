package util;

import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class FXMLHelper
{
        public static Controller loadFXML(String resroute)
        {
            if (Platform.isFxApplicationThread()) {
                FXMLLoader fxmlLoader = new FXMLLoader(FXMLHelper.class.getResource(resroute));
                try {
                    Parent view = (Parent)fxmlLoader.load();
                    Controller controler = (Controller)fxmlLoader.getController();
                    controler.setView(view);
                    return controler;
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
            return null;
        }

        public static Node load(String resroute) {
            if (Platform.isFxApplicationThread()) {
                FXMLLoader fxmlLoader = new FXMLLoader(FXMLHelper.class.getResource(resroute));
                try {
                    return (Node)fxmlLoader.load();
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
            return null;
        }
}
