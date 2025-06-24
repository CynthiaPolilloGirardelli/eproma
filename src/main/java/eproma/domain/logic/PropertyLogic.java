package eproma.domain.logic;

import java.util.ArrayList;
import java.util.List;

import eproma.domain.model.Property;
import eproma.persistence.PropertyRepository;


public class PropertyLogic {
	
	private PropertyRepository repository;

	public PropertyLogic() {
		super();
		this.repository = new PropertyRepository();
	}

	public List<Property> findAllProperties() {
		return repository.findAll();
	}

	public void create(Property propertyFromForm) {
		repository.create(propertyFromForm);
		
	}
	
	public void update(Property propertyToUPdate) {
		repository.update(propertyToUPdate);
	}
}
