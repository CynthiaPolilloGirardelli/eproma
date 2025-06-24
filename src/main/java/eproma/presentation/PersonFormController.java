package eproma.presentation;

import java.util.Optional;

import eproma.domain.logic.PersonLogic;
import eproma.domain.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PersonFormController {
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSurname;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtPassword;
	@FXML
	private TextField txtPhone;
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
	private ComboBox cmbRole;
	
	@FXML
	private Button btnCreate;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnDelete;
	
	
	private boolean creating = false;
	private boolean viewing = false;
	private boolean editing = false;
	private boolean deleting = false;
	private Person person;
	
	private final ObservableList<String> bindingRoles= FXCollections.observableArrayList();

	
	private PersonLogic logic = new PersonLogic();
	
	@FXML
	protected void initialize() {
		this.bindingRoles.add("cust");
		this.bindingRoles.add("agent");
		
		cmbRole.setItems(bindingRoles);
	}

	@FXML
	public void onCreate(ActionEvent event) {
		Person personToCreate = getPersonFromForm();
		logic.signup(personToCreate);
		
	}
	
	@FXML
	public void onSave(ActionEvent event) {
		// revisar esto
		Person personToUpdate = getPersonFromForm();
		personToUpdate.setId(person.getId());
		logic.updatePerson(personToUpdate);
	}
	
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
			person.setDeleted(true);
			logic.updatePerson(person);
		}
		
		
	}
	
	@FXML
	public void onExit(ActionEvent event) {
		

		
	}
	
	
	private Person getPersonFromForm() {
		Person newPerson = new Person(txtEmail.getText(), txtPassword.getText(),
				txtName.getText(),txtSurname.getText(), txtPhone.getText(),
				txtCountry.getText(), txtRegion.getText(), txtCity.getText(),
				txtZip.getText(), txtStreet.getText(), (String)cmbRole.getSelectionModel().getSelectedItem(),false);
		return newPerson;
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

	public void updateFields() {
		
		if(this.viewing == true || this.deleting == true) {
			System.out.println("view or delete mode..");
			txtName.setEditable(false);
			txtSurname.setEditable(false);
			txtEmail.setEditable(false);
			txtPassword.setEditable(false);
			txtPhone.setEditable(false);
			txtCountry.setEditable(false);
			txtRegion.setEditable(false);
			txtZip.setEditable(false);
			txtCity.setEditable(false);
			txtStreet.setEditable(false);
			cmbRole.setDisable(true);
		}
		
		if(this.viewing == true || this.deleting == true || this.editing == true) {
			txtName.setText(this.person.getName());
			txtSurname.setText(this.person.getSurname());
			txtEmail.setText(this.person.getEmail());
			txtPassword.setText(this.person.getPass());
			txtPhone.setText(this.person.getPhone());
			txtCountry.setText(this.person.getCountry());
			txtRegion.setText(this.person.getRegion());
			txtCity.setText(this.person.getCity());
			txtStreet.setText(this.person.getStreet());
			txtZip.setText(this.person.getZip());
			
			cmbRole.getSelectionModel().select(this.person.getRole());
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

	public void setPerson(Person selectedPerson) {
		this.person = selectedPerson;
		
	}
	
	
	
	
	

}
