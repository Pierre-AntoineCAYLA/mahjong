
package gui;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import controller.SplashController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

public class Splash extends StackPane implements BeanPostProcessor
{
    SplashController ctrl = null;
    public Splash() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Splash.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        ctrl = (SplashController) fxmlLoader.getController();
    }

    @Override
    public Object postProcessBeforeInitialization(final Object o, final String s) throws BeansException
    {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException
    {
        return o;
    }
}
