package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;

public class SplashController {

    private static final int MAX_BEAN_COUNT = 30;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void initialize() {
    }


    int count = 0;
    public void before(Object o, String s)
    {
        count++;
    }

    public void after(Object o, String s)
    {
    }

    class Foo extends SimpleDoubleProperty
    {
        @Override
        public double get()
        {
            return (double) (count) / MAX_BEAN_COUNT;
        }

        public void invalidate()
        {
            fireValueChangedEvent();
        }

    }
}
