package eproma.presentation;

import java.util.List;
import java.util.Optional;

import eproma.domain.logic.PersonLogic;
import eproma.domain.logic.SessionLogic;
import eproma.domain.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author Cynthia Polillo
 *
 */
public class ManageCustomerController {
	@FXML
	private TableView table;
	
	/**
	 * a bindinglist containing a list of people
	 */
	private final ObservableList<Person> bindingList = FXCollections.observableArrayList();

	/**
	 * reference to person logic object
	 */
	private PersonLogic logic = new PersonLogic();
	
	/**
	 * reference to dashboard object
	 */
	private DashboardController dashboard;

	/**
	 * sets all bindings and creates the table columns
	 */
	@FXML
	protected void initialize() {

		List<Person> allCustomers = logic.findAllCustomers();
		
		bindingList.addAll(allCustomers);
		table.setItems(bindingList);
		
		TableColumn<Person, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Person, String> colName = new TableColumn<>("Name");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Person, String> colPhone = new TableColumn<>("Phone");
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		TableColumn<Person, String> colCountry = new TableColumn<>("Country");
		colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));	

		table.getColumns().addAll(colId, colName,colPhone,colCountry);

	}
	
	/**
	 * handler for create button. opens the person form in creating mode
	 * @param event
	 */
	@FXML
	public void onCreate(ActionEvent event) {
		this.dashboard.openPersonForm(true, false, false, false,null);
		
	}
	
	/**
	 * handler for view button. opens the person form in viewing mode
	 * @param event
	 */
	@FXML
	public void onView(ActionEvent event) {
		Person selectedPerson = (Person) table.getSelectionModel().getSelectedItem();

		this.dashboard.openPersonForm(false,true, false, false,selectedPerson);
		
	}
	
	/**
	 * handler for edit button. opens the person form in editing mode
	 * @param event
	 */
	@FXML
	public void onEdit(ActionEvent event) {
		Person selectedPerson = (Person) table.getSelectionModel().getSelectedItem();
		this.dashboard.openPersonForm(false,false,true, false,selectedPerson);

		
	}
	
	/**
	 * handler for delete button. if user has admin rights, opens the person form in editing mode.
	 * @param event
	 */
	@FXML
	public void onDelete(ActionEvent event) {
		if(Session.getUser().getRole().equals("admin")) {
			Person selectedPerson = (Person) table.getSelectionModel().getSelectedItem();
			this.dashboard.openPersonForm(false,false, false, true,selectedPerson);
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
	
	/**
	 * sets the dashboard object
	 * @param dashboard
	 */
	public void setDashboardController(DashboardController dashboard) {
		this.dashboard = dashboard;
	}

}
