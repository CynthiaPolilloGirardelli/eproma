package eproma.presentation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.util.StringConverter;

import java.util.Optional;

import eproma.domain.logic.PersonLogic;
import eproma.domain.logic.PropertyLogic;
import eproma.domain.model.Person;
import eproma.domain.model.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.CheckBox;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class PropertyFormController {
	@FXML
	private TextField txtType;
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnDelete;
	
	@FXML
	private Spinner numPrice;
	@FXML
	private Spinner numRoomno;
	@FXML
	private Spinner numBathno;
	@FXML
	private Spinner numArea;
	@FXML
	private Spinner numOutArea;
	@FXML
	private Spinner numStoriesno;
	@FXML
	private CheckBox cbHasPool;
	@FXML
	private CheckBox cbHasGym;
	@FXML
	private Spinner numFoorno;
	@FXML
	private CheckBox cbHasBalcony;
	@FXML
	private CheckBox cbHasElevator;
	@FXML
	private TextField txtCountry;
	@FXML
	private TextField txtRegion;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtZip;
	@FXML
	private TextField txtStreet;
	@FXML
	private ComboBox cmbOwner;
	
	/**
	 * attribute that enables creating mode
	 */
	private boolean creating = false;
	/**
	 * attribute that enables viewing mode
	 */
	private boolean viewing = false;
	/**
	 * attribute that enables editing mode
	 */
	private boolean editing = false;
	/**
	 * attribute that enables deleting mode
	 */
	private boolean deleting = false;
	/**
	 * reference to the property
	 */
	private Property property;
	/**
	 * reference to the dashboard
	 */
	private DashboardController dashboard;
	

	private final ObservableList<Person> bindingList = FXCollections.observableArrayList();

	
	private PropertyLogic logic = new PropertyLogic();
	// ver esto con binding list
	private PersonLogic personLogic= new PersonLogic();
	@FXML
	public void initialize() {

		
		numRoomno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
		numFoorno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
		numBathno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
		numStoriesno.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));
		numArea.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9000000));
		numOutArea.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9000000));
		numPrice.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 99999999));
		
		// ver esto con bindinglist
		bindingList.addAll(personLogic.findAllCustomers());
		cmbOwner.setItems(bindingList);
		cmbOwner.setConverter(new StringConverter<Person>() {

			@Override
			public Person fromString(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Person p) {
				if(p!=null) {return p.getName()+ " " + p.getSurname();}
				else {
					return "";
				}
				
			}
		});
		
	}

	// Event Listener on Button[#btnCreate].onAction
	@FXML
	public void onCreate(ActionEvent event) {
		Property propertyFromForm = getPropertyFromForm();
		logic.create(propertyFromForm);
	}
	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void onSave(ActionEvent event) {
		Property propertyFromForm = getPropertyFromForm();
		propertyFromForm.setId(property.getId());
		logic.update(propertyFromForm);
	}
	// Event Listener on Button[#btnDelete].onAction
	@FXML
	public void onDelete(ActionEvent event) {
		
		ButtonType btnYes = new ButtonType("yes", ButtonData.YES);
		ButtonType btnNo = new ButtonType("no", ButtonData.CANCEL_CLOSE);
		Alert alert = new Alert(AlertType.CONFIRMATION,
		        "Are you sure?",
		        btnYes,
		        btnNo);

		alert.setTitle("Confirmation");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.orElse(btnYes) == btnYes) {

			
			property.setDeleted(true);
			logic.update(property);
		}
		
	}
	
	@FXML
	public void onOperation(ActionEvent event) {
		this.dashboard.openOperationForm(true,false,false,false,property,null);
	}
	
	// Event Listener on Button[#btnExit].onAction
	@FXML
	public void onExit(ActionEvent event) {
		// TODO Autogenerated
	}
	
	
	
public boolean isCreating() {
		return creating;
	}
	public void setCreating(boolean creating) {
		this.creating = creating;
	}
	public boolean isViewing() {
		return viewing;
	}
	public void setViewing(boolean viewing) {
		this.viewing = viewing;
	}
	public boolean isEditing() {
		return editing;
	}
	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	public boolean isDeleting() {
		return deleting;
	}
	public void setDeleting(boolean deleting) {
		this.deleting = deleting;
	}
	
	private Property getPropertyFromForm() {
		
		int ownerID = ((Person)cmbOwner.getSelectionModel().getSelectedItem()).getId();
		Property newProperty = new Property(ownerID,(Double)numPrice.getValue(),(Integer)numRoomno.getValue(),(Integer)numBathno.getValue(),
				(Double)numArea.getValue(),txtType.getText(),(Double)numOutArea.getValue(),
				(Integer)numStoriesno.getValue(),cbHasPool.isSelected(),cbHasGym.isSelected(),
				(Integer)numFoorno.getValue(),cbHasBalcony.isSelected(),cbHasElevator.isSelected(),	
				txtCountry.getText(), txtRegion.getText(), txtCity.getText(),
				txtZip.getText(), txtStreet.getText(),false);
		return newProperty;
	}
	
public void updateFields() {
		
		if(this.viewing == true || this.deleting == true) {
			System.out.println("view or delete mode..");
			cmbOwner.setEditable(false);
			numStoriesno.setEditable(false);
			numRoomno.setEditable(false);
			numBathno.setEditable(false);
			numArea.setEditable(false);
			numOutArea.setEditable(false);
			txtCountry.setEditable(false);
			txtRegion.setEditable(false);
			txtZip.setEditable(false);
			txtCity.setEditable(false);
			txtStreet.setEditable(false);
			txtType.setEditable(false);
		}
		
		if(this.viewing == true || this.deleting == true || this.editing == true) {
			numStoriesno.getValueFactory().setValue(this.property.getStoriesno());
			numRoomno.getValueFactory().setValue(this.property.getRoomno());
			numArea.getValueFactory().setValue(this.property.getArea());
			numOutArea.getValueFactory().setValue(this.property.getOutsidearea());
			numBathno.getValueFactory().setValue(this.property.getBathroomno());
			numFoorno.getValueFactory().setValue(this.property.getFloorno());
			numPrice.getValueFactory().setValue(this.property.getPrice());
			txtCountry.setText(this.property.getCountry());
			txtRegion.setText(this.property.getRegion());
			txtCity.setText(this.property.getCity());
			txtStreet.setText(this.property.getStreet());
			txtZip.setText(this.property.getZip());
			txtType.setText(this.property.getType());
			cbHasBalcony.setSelected(this.property.getHasbalcony().booleanValue());
			cbHasElevator.setSelected(this.property.getHaselevator().booleanValue());
			cbHasGym.setSelected(this.property.getHasgym().booleanValue());
			cbHasPool.setSelected(this.property.getHaspool().booleanValue());
			
			int index = 0;
			for(Person p: bindingList) {
				if(p.getId() == property.getPersonId()) {
					break;
				}
				index++;
			}
			cmbOwner.getSelectionModel().select(index);
		}
		
		if(this.viewing == true) {
			btnCreate.setVisible(false);
			btnSave.setVisible(false);
			btnDelete.setVisible(false);
		}
		
		if(this.editing == true) {
			btnCreate.setVisible(false);
			btnDelete.setVisible(false);
		}
		
		if(this.creating == true) {
			btnSave.setVisible(false);
			btnDelete.setVisible(false);
		}
		
		if(this.deleting == true) {
			btnCreate.setVisible(false);
			btnSave.setVisible(false);
		}
		
		
	}

	public void setProperty(Property selectedProperty) {
		this.property = selectedProperty;
		
	}

	public void setDashboardController(DashboardController dashboardController) {
		// TODO Auto-generated method stub
		this.dashboard = dashboardController;
	}
}
