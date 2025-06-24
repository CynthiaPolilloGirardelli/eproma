package eproma.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.util.List;

import eproma.domain.logic.OperationLogic;
import eproma.domain.logic.PropertyLogic;
import eproma.domain.model.Operation;
import eproma.domain.model.Person;
import eproma.domain.model.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageOperationController {
	@FXML
	private TableView table;
	
	
/**
 * list of operations
 */
	private final ObservableList<Operation> bindingList = FXCollections.observableArrayList();

	/**
	 * reference to operation logic
	 */
	private OperationLogic logic = new OperationLogic();

	/**
	 * reference to dashboard controller
	 */
	private DashboardController dashboard;

	
	/**
	 * initialises properties list and creates the table columns
	 */
	@FXML
	protected void initialize()
	{

		List<Operation> allProperties = logic.findAllOperations();

		bindingList.addAll(allProperties);
		table.setItems(bindingList);

		TableColumn<Property, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Property, Integer> colPropId = new TableColumn<>("Property ID");
		colPropId.setCellValueFactory(new PropertyValueFactory<>("propertyId"));

		TableColumn<Property, Double> colPrice = new TableColumn<>("Price");
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

		TableColumn<Property, String> colType = new TableColumn<>("Type");
		colType.setCellValueFactory(new PropertyValueFactory<>("type"));

		TableColumn<Property, LocalDate> colRegister = new TableColumn<>("Register At");
		colRegister.setCellValueFactory(new PropertyValueFactory<>("registerDate"));

		

		table.getColumns().addAll(colId, colPropId, colPrice, colType, colRegister);
	}
	
	
	
	/**
	 * opens the operation form in view mode
	 * @param event
	 */
	@FXML
	public void onView(ActionEvent event) {
		Operation selectedOperation= (Operation) table.getSelectionModel().getSelectedItem();

		this.dashboard.openOperationForm(false,true,false,false,null,selectedOperation);
		
	}
	
	/**
	 * opens the operation form in edit mode
	 * @param event
	 */
	@FXML
	public void onEdit(ActionEvent event) {
		Operation selectedOperation= (Operation) table.getSelectionModel().getSelectedItem();

		this.dashboard.openOperationForm(false,false,true,false,null,selectedOperation);

		
	}
	
	/**
	 * opens operation form in delete mode if user has permissions
	 * @param event
	 */
	@FXML
	public void onDelete(ActionEvent event) {
		
		if(Session.getUser().getRole().equals("admin")) {
			Operation selectedOperation= (Operation) table.getSelectionModel().getSelectedItem();
			this.dashboard.openOperationForm(false,false,false,true,null,selectedOperation);
		}
		else {
			ButtonType btnYes = new ButtonType("OK", ButtonData.OK_DONE);
			Alert alert = new Alert(AlertType.ERROR,
			        "You have no permission to access to this section",
			        btnYes
			        );

			alert.setTitle("Error");
			alert.show();
		}
		

	}
	
	public void setDashboardController(DashboardController dashboard) {
		this.dashboard = dashboard;
	}

}
