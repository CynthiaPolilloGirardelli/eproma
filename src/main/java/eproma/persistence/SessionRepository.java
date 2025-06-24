package eproma.persistence;

import eproma.domain.model.Person;

public class SessionRepository {
	
	/**
	 * the session user- logged user
	 */
	private Person person;

	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
	
	
}
