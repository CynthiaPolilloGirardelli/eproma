package eproma.presentation;


import java.util.List;

import eproma.domain.logic.PersonLogic;
import eproma.domain.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	
	@Override
	public void start(Stage primary) throws Exception {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			
			primary.setScene(scene);
			primary.show();
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
