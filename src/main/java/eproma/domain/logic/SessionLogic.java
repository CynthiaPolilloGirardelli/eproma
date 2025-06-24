package eproma.domain.logic;

import eproma.domain.model.Person;
import eproma.persistence.SessionRepository;

public class SessionLogic {
	private SessionRepository repository = new SessionRepository();
	
	public void logInPerson(Person p) {
		repository.setPerson(p);
	}
	
	public void logOutPerson() {
		repository.setPerson(null);
	}
	
	public Person getLoggedPerson() {
		return repository.getPerson();
	}
}
