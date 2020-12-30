package gui;

import javafx.geometry.Point2D;
import javafx.scene.Scene;

public class GuiUtils {

    public static String getPlatFormStyleSheet()
    {
       return "/style/Windows-7.css";
    }

    public static Point2D toScreen(Scene scene,Point2D pt)
    {
        final Point2D windowCoord = new Point2D(scene.getWindow().getX(), scene.getWindow().getY());
	    final Point2D sceneCoord = new Point2D(scene.getX(), scene.getY());
        return new Point2D(windowCoord.getX()+sceneCoord.getX()+pt.getX(),windowCoord.getY()+sceneCoord.getY()+pt.getY());
    }
}
