package eproma.presentation;

import java.io.IOException;

import eproma.domain.model.Operation;
import eproma.domain.model.Person;
import eproma.domain.model.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DashboardController {
	
	@FXML
	private TitledPane txtTitle;
	
	@FXML
	private AnchorPane rightPane;

	public DashboardController() {
	}

	@FXML
	protected void initialize() {
		txtTitle.setText("Welcome "+Session.getUser().getName());

	}
	
	private FXMLLoader changeView(String nameOfView) {
		Pane newLoadedPane;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(nameOfView));
				newLoadedPane = loader.load();
				
				rightPane.getChildren().clear();
				rightPane.getChildren().add(newLoadedPane);
				return loader;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	public void openPersonForm(boolean creating,boolean viewing, boolean editing, boolean deleting, Person selectedPerson) {
		Pane newLoadedPane;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonForm.fxml"));
			newLoadedPane = loader.load();
			PersonFormController controller = loader.getController();
			controller.setCreating(creating);
			controller.setViewing(viewing);
			controller.setEditing(editing);
			controller.setDeleting(deleting);
			controller.setPerson(selectedPerson);
			controller.updateFields();
			rightPane.getChildren().clear();
			rightPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openPropertyForm(boolean creating,boolean viewing, boolean editing, boolean deleting, Property selectedProperty) {
		Pane newLoadedPane;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PropertyForm.fxml"));
			newLoadedPane = loader.load();
			PropertyFormController controller = loader.getController();
			controller.setCreating(creating);
			controller.setViewing(viewing);
			controller.setEditing(editing);
			controller.setDeleting(deleting);
			controller.setProperty(selectedProperty);
			controller.updateFields();
			controller.setDashboardController(this);

			rightPane.getChildren().clear();
			rightPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void onClickManageCustomer(ActionEvent event) {
		System.out.println("manage customer!");
		FXMLLoader loader =changeView("ManageCustomer.fxml");
		ManageCustomerController controller = loader.getController();
		controller.setDashboardController(this);
	}
	
	
	@FXML
	public void onClickManageProperties(ActionEvent event) {
		System.out.println("manage properties!");
		FXMLLoader loader =changeView("ManageProperties.fxml");
		ManagePropertyController controller = loader.getController();
		controller.setDashboardController(this);
		
	}
	
	
	@FXML
	public void onClickManageOperations(ActionEvent event) {
		System.out.println("manage operation!");
		FXMLLoader loader =changeView("ManageOperation.fxml");
		ManageOperationController controller = loader.getController();
		controller.setDashboardController(this);
	}

	public void openOperationForm(boolean creating,boolean viewing, boolean editing, boolean deleting,Property property, Operation operation ) {
		// TODO Auto-generated method stub
		Pane newLoadedPane;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("OperationForm.fxml"));
			newLoadedPane = loader.load();
			OperationFormController controller = loader.getController();
			controller.setProperty(property);
			controller.setOperation(operation);
			controller.setViewing(viewing);
			controller.setViewing(viewing);
			controller.setEditing(editing);
			controller.setDeleting(deleting);
			controller.updateFields();
			/*controller.setCreating(creating);
			controller.setViewing(viewing);
			controller.setEditing(editing);
			controller.setDeleting(deleting);
			controller.setProperty(selectedProperty);
			controller.updateFields();
			controller.setDashboardController(this);*/

			rightPane.getChildren().clear();
			rightPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
