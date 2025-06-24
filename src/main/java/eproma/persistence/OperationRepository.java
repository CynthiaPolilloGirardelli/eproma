package eproma.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import eproma.domain.model.Operation;
import eproma.domain.model.Property;

public class OperationRepository {

	/**
	 * opens a connection with the database
	 * 
	 * @return returns a connection object
	 */
	private Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver is available
		} catch (ClassNotFoundException e) {
			System.err.println("❌ MySQL Driver not found. Did you add the connector .jar?");
			throw new RuntimeException("MySQL JDBC Driver is missing. Please add it to your classpath.", e);
		}

		try {
			String url = "jdbc:mysql://localhost:3306/epromadb";
			String user = "root";
			String password = "root2025#!";

			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("✅ Connected to MySQL.");
			return con;

		} catch (SQLException e) {
			System.err.println("❌ Failed to connect to MySQL. Please verify URL, username, and password.");
			throw new RuntimeException("Database connection error.", e);
		}
	}



	/**
	 * find all Operations in db
	 * 
	 * @return
	 */
	public List<Operation> findAll() {
		List<Operation> operations = new ArrayList<>();
		try {
			String sql = "SELECT * FROM operation where isDeleted = 0";

			Connection con = openConnection();

			PreparedStatement stmt = con.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			// minetras que result tenga filas pendientes de recorrer
			while (result.next() == true) {
				int id = result.getInt("id");
				int sellerId = result.getInt("sellerId");
				int purchaserId = result.getInt("purchaserId");
				int propertyId = result.getInt("propertyId");
				double price = result.getDouble("price");
				Date registerDate = result.getDate("registerDate");
				String observations = result.getString("observations");
				String type = result.getString("type");
				Date endDate = result.getDate("endDate");
				Date startDate = result.getDate("startDate");
				Date physicalTransferDate = result.getDate("physicalTransferDate");
				boolean isDeleted = result.getBoolean("isDeleted");

				Operation operation = new Operation(id, sellerId, purchaserId, propertyId, price, type,
						registerDate == null ? null : registerDate.toLocalDate(), observations,
						endDate == null ? null : endDate.toLocalDate(),
						startDate == null ? null : startDate.toLocalDate(),
						physicalTransferDate == null ? null : physicalTransferDate.toLocalDate(), isDeleted);

				operations.add(operation);
			}

			result.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return operations;

	}

	/**
	 * finds an operation by its id
	 * @param operationToFind with id set
	 * @return
	 */
	public Operation find(Operation operationToFind) {
		Operation op = null;
		try {
			String sql = "SELECT * FROM operation WHERE id = ? and isDeleted = '0'";

			Connection con = openConnection();

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, operationToFind.getId());

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int id = result.getInt("id");
				int sellerId = result.getInt("sellerId");
				int purchaserId = result.getInt("purchaserId");
				int propertyId = result.getInt("propertyId");
				double price = result.getDouble("price");
				Date registerDate = result.getDate("registerDate");
				String observations = result.getString("observations");
				String type = result.getString("type");
				Date endDate = result.getDate("endDate");
				Date startDate = result.getDate("startDate");
				Date physicalTransferDate = result.getDate("physicalTransferDate");
				boolean isDeleted = result.getBoolean("isDeleted");

				op = new Operation(id, sellerId, purchaserId, propertyId, price, type,
						registerDate == null ? null : registerDate.toLocalDate(), observations,
						endDate == null ? null : endDate.toLocalDate(),
						startDate == null ? null : startDate.toLocalDate(),
						physicalTransferDate == null ? null : physicalTransferDate.toLocalDate(), isDeleted);

			}

			result.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return op;
	}

	/**
	 * updates the fields of the operation
	 * @param operationToUpdate
	 * @return
	 */
	public Operation update(Operation operationToUpdate) {
		Operation result = null;
		try {
			String sql = "UPDATE operation SET " + "sellerId=?," + "purchaserId=?," + "propertyId=?," + "price=?,"
					+ "registerDate=?," + "observations=?," + "type=?," + "endDate=?," + "startDate=?,"
					+ "physicalTransferDate=?," + "isDeleted=? " + "WHERE id = ?";

			Connection con = openConnection();

			/*
			 * int isdeleted = 0;
			 * 
			 * if(personToCreate.isDeleted() == true) { isdeleted = 1; }else { isdeleted =
			 * 0; }
			 */

			String columnNames[] = new String[] { "id" };

			PreparedStatement stmt = con.prepareStatement(sql, columnNames);
			stmt.setInt(1, operationToUpdate.getSellerId());
			stmt.setInt(2, operationToUpdate.getPurchaserId());
			stmt.setInt(3, operationToUpdate.getPropertyId());
			stmt.setDouble(4, operationToUpdate.getPrice());
			stmt.setDate(5, java.sql.Date.valueOf(operationToUpdate.getRegisterDate()));
			stmt.setString(6, operationToUpdate.getObservations());
			stmt.setString(7, operationToUpdate.getType());
			stmt.setDate(8, operationToUpdate.getEndDate() == null ? null
					: java.sql.Date.valueOf(operationToUpdate.getEndDate()));
			stmt.setDate(9, operationToUpdate.getStartDate() == null ? null
					: java.sql.Date.valueOf(operationToUpdate.getStartDate()));
			stmt.setDate(10, operationToUpdate.getPhysicalTransferDate() == null ? null
					: java.sql.Date.valueOf(operationToUpdate.getPhysicalTransferDate()));
			stmt.setInt(11, operationToUpdate.isDeleted() ? 1 : 0);
			stmt.setInt(12, operationToUpdate.getId());// ESTO VA ACA O NO????
			stmt.executeUpdate();

			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * inserts an operation into the database
	 * @param operationToCreate without id
	 * @return operation with id
	 */
	public Operation create(Operation operationToCreate) {
		Operation result = null;
		try {
			String sql = "INSERT INTO OPERATION " + "(sellerId," + "purchaserId," + "propertyId," + "price,"
					+ "registerDate," + "observations," + "type," + "endDate," + "startDate," + "physicalTransferDate,"
					+ "isDeleted) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			Connection con = openConnection();

			/*
			 * int isdeleted = 0;
			 * 
			 * if(personToCreate.isDeleted() == true) { isdeleted = 1; }else { isdeleted =
			 * 0; }
			 */

			String columnNames[] = new String[] { "id" };

			PreparedStatement stmt = con.prepareStatement(sql, columnNames);
			stmt.setInt(1, operationToCreate.getSellerId());
			stmt.setInt(2, operationToCreate.getPurchaserId());
			stmt.setInt(3, operationToCreate.getPropertyId());
			stmt.setDouble(4, operationToCreate.getPrice());
			stmt.setDate(5, java.sql.Date.valueOf(operationToCreate.getRegisterDate()));
			stmt.setString(6, operationToCreate.getObservations());
			stmt.setString(7, operationToCreate.getType());
			stmt.setDate(8, operationToCreate.getEndDate() == null ? null
					: java.sql.Date.valueOf(operationToCreate.getEndDate()));
			stmt.setDate(9, operationToCreate.getStartDate() == null ? null
					: java.sql.Date.valueOf(operationToCreate.getStartDate()));
			stmt.setDate(10, operationToCreate.getPhysicalTransferDate() == null ? null
					: java.sql.Date.valueOf(operationToCreate.getPhysicalTransferDate()));
			stmt.setInt(11, operationToCreate.isDeleted() ? 1 : 0);
			int generatedId = 0;
			if (stmt.executeUpdate() > 0) {
				java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					generatedId = generatedKeys.getInt(1);
				}
			}

			result = operationToCreate;
			result.setId(generatedId);

			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}


}
