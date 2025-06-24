package eproma.presentation;

import java.util.List;

import eproma.domain.logic.PropertyLogic;
import eproma.domain.model.Operation;
import eproma.domain.model.Person;
import eproma.domain.model.Property;
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

public class ManagePropertyController {
	@FXML
	private TableView table;

	private final ObservableList<Property> bindingList = FXCollections.observableArrayList();

	private PropertyLogic logic = new PropertyLogic();

	private DashboardController dashboard;

	
	@FXML
	protected void initialize()
	{

		List<Property> allProperties = logic.findAllProperties();

		bindingList.addAll(allProperties);
		table.setItems(bindingList);

		TableColumn<Property, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Property, Integer> colRoomNo = new TableColumn<>("Room No");
		colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomno"));

		TableColumn<Property, Float> colArea = new TableColumn<>("Area");
		colArea.setCellValueFactory(new PropertyValueFactory<>("area"));

		TableColumn<Property, Float> colOutsideArea = new TableColumn<>("Outside area");
		colOutsideArea.setCellValueFactory(new PropertyValueFactory<>("outsidearea"));

		TableColumn<Property, String> colCountry = new TableColumn<>("Country");
		colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

		TableColumn<Property, String> colCity = new TableColumn<>("City");
		colCity.setCellValueFactory(new PropertyValueFactory<>("city"));

		table.getColumns().addAll(colId, colRoomNo, colArea, colOutsideArea, colCountry, colCity);
	}
	
	

	@FXML
	public void onCreate(ActionEvent event) {
		this.dashboard.openPropertyForm(true, false, false, false,null);
		
	}
	
	@FXML
	public void onView(ActionEvent event) {
		Property selectedProperty = (Property) table.getSelectionModel().getSelectedItem();

		this.dashboard.openPropertyForm(false,true, false, false,selectedProperty);
		
	}
	
	@FXML
	public void onEdit(ActionEvent event) {
		Property selectedProperty = (Property) table.getSelectionModel().getSelectedItem();
		this.dashboard.openPropertyForm(false,false,true, false,selectedProperty);

		
	}
	
	@FXML
	public void onDelete(ActionEvent event) {
		
		if(Session.getUser().getRole().equals("admin")) {
			Property selectedProperty = (Property) table.getSelectionModel().getSelectedItem();
			this.dashboard.openPropertyForm(false,false, false, true,selectedProperty);
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
