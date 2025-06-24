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

import eproma.domain.logic.OperationLogic;
import eproma.domain.logic.PersonLogic;
import eproma.domain.logic.PropertyLogic;
import eproma.domain.model.Operation;
import eproma.domain.model.Person;
import eproma.domain.model.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.DatePicker;

public class OperationFormController {
	@FXML
	private Label lblSeller;
	@FXML
	private Label lblPurchaser;
	@FXML
	private Label lblEndDate;
	@FXML
	private Label lblStartDate;
	@FXML
	private Label lblPhysicalTransferDate;
	@FXML
	private Button btCreate;
	@FXML
	private Button btSave;
	@FXML
	private Button btDelete;
	@FXML
	private ComboBox cmbSeller;
	@FXML
	private ComboBox cmbPurchaser;
	@FXML
	private Spinner numPrice;
	@FXML
	private ComboBox cmbType;
	@FXML
	private DatePicker dpRegisterDate;
	@FXML
	private DatePicker dpEndDate;
	@FXML
	private DatePicker dpStartDate;
	@FXML
	private DatePicker dpPhysicalTransferDate;
	@FXML
	private TextField txtObservation;

	private Property property;

	private final ObservableList<Person> bindingSellers = FXCollections.observableArrayList();
	private final ObservableList<Person> bindingPurchasers = FXCollections.observableArrayList();
	private final ObservableList<String> bindingTypes = FXCollections.observableArrayList();

	private PersonLogic personLogic = new PersonLogic();
	private OperationLogic logic = new OperationLogic();
	private Operation operation;
	
	private boolean creating = false;
	private boolean viewing = false;
	private boolean editing = false;
	private boolean deleting = false;

	@FXML
	public void initialize() {

		numPrice.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999));

		// ver esto con bindinglist
		bindingSellers.addAll(personLogic.findAllCustomers());
		bindingPurchasers.addAll(personLogic.findAllCustomers());
		bindingTypes.add("purchase");
		bindingTypes.add("rent");

		cmbPurchaser.setItems(bindingPurchasers);
		cmbSeller.setItems(bindingSellers);
		cmbType.setItems(bindingTypes);
		cmbType.getSelectionModel().select(0);

		checkType();

		cmbPurchaser.setConverter(new StringConverter<Person>() {

			@Override
			public Person fromString(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Person p) {
				if (p != null) {
					return p.getName() + " " + p.getSurname();
				} else {
					return "";
				}

			}
		});

		cmbSeller.setConverter(new StringConverter<Person>() {

			@Override
			public Person fromString(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString(Person p) {
				if (p != null) {
					return p.getName() + " " + p.getSurname();
				} else {
					return "";
				}

			}
		});

	}

	public void updateFields() {
		
		if(this.viewing == true || this.deleting == true) {
			System.out.println("view or delete mode..");

			cmbSeller.setDisable(true);
			cmbPurchaser.setDisable(true);
			cmbType.setDisable(true);

			txtObservation.setEditable(false);
			numPrice.setDisable(true);
			dpEndDate.setDisable(true);
			dpStartDate.setDisable(true);
			dpRegisterDate.setDisable(true);
			dpPhysicalTransferDate.setDisable(true);
			
		}
		
		if(this.viewing == true) {
			btCreate.setVisible(false);
			btSave.setVisible(false);
			btDelete.setVisible(false);
		}
		
		if(this.editing == true) {
			btCreate.setVisible(false);
			btDelete.setVisible(false);
		}
		
		if(this.creating == true) {
			btSave.setVisible(false);
			btDelete.setVisible(false);
		}
		
		if(this.deleting == true) {
			btCreate.setVisible(false);
			btSave.setVisible(false);
		}
		
		
		if(property !=null) {
			numPrice.getValueFactory().setValue(this.property.getPrice());
			int sellerIndex = 0;
			for(Person p: bindingSellers) {
				if(p.getId() == property.getPersonId()) {
					break;
				}
				sellerIndex++;
			}
			cmbSeller.getSelectionModel().select(sellerIndex);;
		}
		
		if(operation!=null) {
			numPrice.getValueFactory().setValue(this.operation.getPrice());
			
			int sellerIndex = 0;
			for(Person p: bindingSellers) {
				if(p.getId() == this.operation.getSellerId()) {
					break;
				}
				sellerIndex++;
			}
			cmbSeller.getSelectionModel().select(sellerIndex);;
			
			int purchaserIndex = 0;
			for(Person p: bindingPurchasers) {
				if(p.getId() == this.operation.getPurchaserId()) {
					break;
				}
				purchaserIndex++;
			}
			cmbPurchaser.getSelectionModel().select(purchaserIndex);;
			
			
			cmbType.getSelectionModel().select(this.operation.getType());
			
			checkType();

			
			dpRegisterDate.setValue(this.operation.getRegisterDate());
			
			if(this.operation.getStartDate()!=null) {
				dpStartDate.setValue(this.operation.getStartDate());
			}
			if(this.operation.getStartDate()!=null) {
				dpEndDate.setValue(this.operation.getEndDate());
			}
			if(this.operation.getPhysicalTransferDate()!=null) {
				dpPhysicalTransferDate.setValue(this.operation.getPhysicalTransferDate());
			}
			if(this.operation.getObservations()!=null) {
				txtObservation.setText(this.operation.getObservations());
			}
			
		}
		
		
		
	}

	@FXML
	public void onTypeSelected(ActionEvent event) {
		checkType();
	}

	// Event Listener on Button[#btCreate].onAction
	@FXML
	public void onCreate(ActionEvent event) {
		Operation op = getOperationFromForm();
		logic.create(op);
	}

	// Event Listener on Button[#btSave].onAction
	@FXML
	public void onSave(ActionEvent event) {
		Operation op = getOperationFromForm();
		op.setId(this.operation.getId());
		logic.update(op);
	}

	// Event Listener on Button[#btDelete].onAction
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

			this.operation.setDeleted(true);
			logic.update(this.operation);
		}
		
	}

	public void setProperty(Property prop) {
		this.property = prop;
	}

	private Operation getOperationFromForm() {
		Operation op = new Operation(
				((Person)cmbSeller.getSelectionModel().getSelectedItem()).getId(),
				((Person)cmbPurchaser.getSelectionModel().getSelectedItem()).getId(),
				property==null?this.operation.getPropertyId() : property.getId(),
				(double)numPrice.getValueFactory().getValue(),
				(String)cmbType.getSelectionModel().getSelectedItem(),
				dpRegisterDate.getValue(),
				txtObservation.getText(),
				dpEndDate.getValue(),
				dpStartDate.getValue(),
				dpPhysicalTransferDate.getValue(),
				false);
		return op;
	}
	
	private void checkType() {
		String type = (String) cmbType.getSelectionModel().getSelectedItem();

		switch (type) {
		case "purchase":
			lblSeller.setText("Seller");
			lblPurchaser.setText("Purchaser");
			dpStartDate.setVisible(false);
			dpEndDate.setVisible(false);
			lblStartDate.setVisible(false);
			lblEndDate.setVisible(false);
			dpPhysicalTransferDate.setVisible(true);
			lblPhysicalTransferDate.setVisible(true);
			break;

		case "rent":
			lblSeller.setText("Land lord");
			lblPurchaser.setText("Tenant");
			dpStartDate.setVisible(true);
			dpEndDate.setVisible(true);
			lblStartDate.setVisible(true);
			lblEndDate.setVisible(true);
			dpPhysicalTransferDate.setVisible(false);
			lblPhysicalTransferDate.setVisible(false);
			break;
		}
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
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
	
	
}
