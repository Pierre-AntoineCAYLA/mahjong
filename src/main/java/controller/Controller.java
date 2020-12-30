package controller;

import javafx.scene.Parent;

public abstract class Controller
{
    Parent view;

    public Parent getView()
    {
        return view;
    }

    public void setView(Parent view)
    {
        this .view = view;
    }

}
