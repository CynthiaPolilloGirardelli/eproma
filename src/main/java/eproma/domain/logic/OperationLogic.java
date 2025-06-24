package eproma.domain.logic;

import java.util.List;

import eproma.domain.model.Operation;
import eproma.persistence.OperationRepository;

/**
 * 
 * @author Cynthia Polillo
 *
 */
public class OperationLogic {
	
	/**
	 * Operation repository
	 */
	private OperationRepository repository;

	/**
	 * constructor for operationLogic
	 */
	public OperationLogic() {
		super();
		this.repository = new OperationRepository();
	}

	/**
	 * finds all operations in the repository
	 * @return a list of operations 
	 */
	public List<Operation> findAllOperations() {
		return repository.findAll();
	}

	/**
	 * creates an operations
	 * @param operationFromForm 
	 */
	public void create(Operation operationFromForm) {
		repository.create(operationFromForm);
		
	}
	
	/**
	 * updates an operation
	 * @param operationToUPdate
	 */
	public void update(Operation operationToUPdate) {
		repository.update(operationToUPdate);
	}
}
