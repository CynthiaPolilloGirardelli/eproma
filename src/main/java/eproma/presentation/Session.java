package eproma.presentation;

import eproma.domain.logic.SessionLogic;
import eproma.domain.model.Person;

public final class Session {
	private static SessionLogic logic = new SessionLogic();
	
	public static final Person getUser() {
		return logic.getLoggedPerson();
	}
	
	public static final void logIn(Person p) {
		logic.logInPerson(p);
	}
	
	public static final void logOut() {
		logic.logOutPerson();
	}
}
