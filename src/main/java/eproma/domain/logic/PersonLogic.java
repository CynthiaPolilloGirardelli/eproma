package eproma.domain.logic;

import java.util.ArrayList;
import java.util.List;

import eproma.domain.model.Person;
import eproma.persistence.PersonRepository;


public class PersonLogic {

	private PersonRepository repository;


	
	public PersonLogic() {
		super();
		this.repository = new PersonRepository();
	}

	
	public Person signup(Person persoToCreate) {
		return this.repository.create(persoToCreate);
	}

	public Person find(Person personToFind) {
		return this.repository.findById(personToFind);
	}
	
	public List<Person> findAllCustomers(){
		List<Person> all = repository.findAll();
		List<Person> customer = new ArrayList<>();
		
		for(Person p: all) {
			if(p.getRole().equals("cust")) { 
				customer.add(p);
			}
		}
		
		return customer;
	}


	public Person login(Person personForm) {
		Person personDB = repository.findByEmail(personForm);
		if (personDB == null) {
			return null;
		} else {
			if (personForm.getPass().equals(personDB.getPass())) {
				return personDB;
			} else {
				return null;
			}
		}

	}

	public void updatePerson(Person personToUpdate) {
		this.repository.update(personToUpdate);
		
	}
}
