package eproma.presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import eproma.domain.logic.PersonLogic;
import eproma.domain.model.Person;
import javafx.event.ActionEvent;

public class LoginController {

	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPass;
	
	
	/**
	 * reference to person logic
	 */
	private PersonLogic logic;

	/**
	 * constructor
	 */
	public LoginController() {
		logic = new PersonLogic();
	}

	/**
	 * takes the data from the form an tries to login. if login is correct, opens dashbord, else clears fields
	 * @param event
	 */
	@FXML
	public void onLogIn(ActionEvent event) {

		String email = txtEmail.getText();
		String pass = txtPass.getText();
		Person person = new Person(email, pass);
		Person result = logic.login(person);

		System.out.println(result);

		if (result == null) {
			txtEmail.setText("");
			txtPass.setText("");
		} else {
			
			Session.logIn(result);

			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("Dashboard.fxml"));
				Scene scene  = new Scene(fxmlLoader.load(), 630, 800);
				Stage stage = new Stage();
				stage.setTitle("EPROMA - Dashboard");
				stage.setScene(scene);
				//stage.setMaximized(true);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
