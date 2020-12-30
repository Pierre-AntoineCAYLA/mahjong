package mahjong;

import gui.GuiUtils;
import gui.MainFrame;
import gui.Splash;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class App extends Application{

	 @Override
	    public void start(final Stage stage) throws Exception
	    {
	        final Splash root = new Splash();
	        Scene scene = new Scene(root);
	        stage.initStyle(StageStyle.UNDECORATED);
	        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
	        scene.getStylesheets().add(GuiUtils.getPlatFormStyleSheet());
	        stage.setScene(scene);
	        stage.setOnShown(new EventHandler<WindowEvent>()
	        {
	            @Override
	            public void handle(WindowEvent windowEvent)
	            {
	                Platform.runLater(new Runnable()
	                {
	                    @Override
	                    public void run()
	                    {
	                        final MainFrame mf = new MainFrame(root, getParameters().getRaw());
	                        try {
	                            mf.start(new Stage());
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        } finally {
	                            stage.close();
	                        }
	                    }
	                });
	            }
	        });

	        stage.show();
	    }


	    public static void main(String[] args)
	    {
	        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
	        {
	            public void uncaughtException(Thread t, Throwable e)
	            {
	                System.err.printf("ERROR; UNCOUGHT Exception %s\n", e);
	                e.printStackTrace();
	            }
	        });

	        new Thread(new Runnable()
	        {
	            @Override
	            public void run()
	            {

	            }
	        }).start();

	        banner();
	        launch(args);
	    }

	    private static void banner()
	    {
	        System.out.printf("MahJong 2021, PAC.\n");
	    }
}
