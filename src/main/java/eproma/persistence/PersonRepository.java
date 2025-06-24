package eproma.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eproma.domain.model.Person;

public class PersonRepository {
	
	/**
	 * opens a connection with the database
	 * @return a connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("❌ Driver MySQL no encontrado. ¿Agregaste el .jar del conector?");
			throw new RuntimeException("Falta el driver MySQL. Verifica tu configuración de dependencias.", e);
		}

		try {
			String url = "jdbc:mysql://localhost:3306/epromadb";
			String user = "root";
			String password = "root2025#!";

			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("✅ Conexión exitosa a MySQL.");
			return con;

		} catch (SQLException e) {
			System.err.println("❌ Falló la conexión a MySQL. Verifica URL, user y password.");
			throw new RuntimeException("Error de conexión a la base de datos.", e);
		}
	}




	/**
	 * find all people in db
	 * @return list of people
	 */
	public List<Person> findAll(){
		List<Person> people = new ArrayList<>();
		try
        {  
            String sql = "SELECT * FROM person WHERE isDeleted = 0";
            
            Connection con = openConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet result=stmt.executeQuery();
            
            while(result.next()) {
            	int id = result.getInt("id");
            	String name = result.getString("name");
            	String surname = result.getString("surname");
            	String phone = result.getString("phone");
            	String email = result.getString("email");
            	String pass = result.getString("pass");
            	String country = result.getString("country");
            	String region = result.getString("region");
            	String city = result.getString("city");
            	String zip = result.getString("zip");
            	String street = result.getString("street");
            	String role = result.getString("role");
            	boolean isDeleted = result.getBoolean("isDeleted");
            	

            	
            	Person p = new Person(id,email,pass,name,surname,phone,country,region,city,zip,street,role,isDeleted);
            	people.add(p);
            }
            
            result.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return people;
	}
	

	/**
	 * finds a person by its email
	 * @param personToFind with id set
	 * @return a found person
	 */
	public Person findByEmail(Person personToFind) {
		Person person = null;
		try
        {  
            String sql = "SELECT * FROM person WHERE email = ? and isDeleted = 0";
            
            Connection con = openConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, personToFind.getEmail());
            
            ResultSet result=stmt.executeQuery();
            
            if(result.next()) {
            	int id = result.getInt("id");
            	String name = result.getString("name");
            	String surname = result.getString("surname");
            	String phone = result.getString("phone");
            	String email = result.getString("email");
            	String pass = result.getString("pass");
            	String country = result.getString("country");
            	String region = result.getString("region");
            	String city = result.getString("city");
            	String zip = result.getString("zip");
            	String street = result.getString("street");
            	String role = result.getString("role");
            	boolean isDeleted = result.getBoolean("isDeleted");
            	
            	person = new Person (id,email,pass,name,surname,phone,country,region,city,zip,street,role,isDeleted);
            }
            
            result.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return person;
	}
	
	/**
	 * finds a person by its id
	 * @param personToFind with id set
	 * @return a found person
	 */
	public Person findById(Person personToFind) {
		Person person = null;
		try
        {  
            String sql = "SELECT * FROM person WHERE id = ? and isDeleted = 0";
            
            Connection con = openConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, personToFind.getId());
            
            ResultSet result=stmt.executeQuery();
            
            if(result.next()) {
            	int id = result.getInt("id");
            	String pass = result.getString("pass");
            	String name = result.getString("name");
            	String surname = result.getString("surname");
            	String phone = result.getString("phone");
            	String email = result.getString("email");
            	String country = result.getString("country");
            	String region = result.getString("region");
            	String city = result.getString("city");
            	String zip = result.getString("zip");
            	String street = result.getString("street");
            	String role = result.getString("role");
            	boolean isDeleted = result.getBoolean("isDeleted");
            	
            	person = new Person  (id,email,pass,name,surname,phone,country,region,city,zip,street,role,isDeleted);
            }
            
            result.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return person;
	}
	
	/**
	 * updates all fields of a person
	 * @param personToUpdate
	 * @return updated person
	 */
	public Person update(Person personToUpdate) {
		Person result = null;
		try
        {  
            String sql = "UPDATE person SET"
            		+ " city=?,"
            		+ "country=?,"
            		+ "email= ?,"
            		+ "isDeleted=?,"
            		+ "name=?,"
            		+ "pass=?,"
            		+ "phone=?,"
            		+ "region=?,"
            		+ "role=?,"
            		+ "street=?,"
            		+ "surname=?,"
            		+ "zip=? WHERE id = ?";
            
            Connection con = openConnection();
            
            /*int isdeleted = 0;
            
            if(personToCreate.isDeleted() == true) {
            	isdeleted = 1;
            }else {
            	isdeleted = 0;
            }*/
            
            String columnNames[] = new String[] { "id" };
            
            PreparedStatement stmt = con.prepareStatement(sql,columnNames);
            stmt.setString(1, personToUpdate.getCity());
            stmt.setString(2, personToUpdate.getCountry());
            stmt.setString(3, personToUpdate.getEmail());
            stmt.setInt(4, personToUpdate.isDeleted()?1:0);
            stmt.setString(5, personToUpdate.getName());
            stmt.setString(6, personToUpdate.getPass());
            stmt.setString(7, personToUpdate.getPhone());
            stmt.setString(8, personToUpdate.getRegion());
            stmt.setString(9, personToUpdate.getRole());
            stmt.setString(10, personToUpdate.getStreet());
            stmt.setString(11, personToUpdate.getSurname());
            stmt.setString(12, personToUpdate.getZip());
            stmt.setInt(13, personToUpdate.getId());
            int generatedId = 0;
            if (stmt.executeUpdate() > 0) {
                java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys();
                if ( generatedKeys.next() ) {
                	generatedId = generatedKeys.getInt(1);
                }
            }
           
            result = personToUpdate;
            result.setId(generatedId);
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return result;
	}
	
	/**
	 * inserts a new person
	 * @param personToCreate without id
	 * @return new person with id
	 */
	public Person create(Person personToCreate) {
		Person result = null;
		try
        {  
            String sql = "INSERT INTO person "
            		+ "(city,"
            		+ "country,"
            		+ "email,"
            		+ "isDeleted,"
            		+ "name,"
            		+ "pass,"
            		+ "phone,"
            		+ "region,"
            		+ "role,"
            		+ "street,"
            		+ "surname,"
            		+ "zip) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            
            Connection con = openConnection();
            
            /*int isdeleted = 0;
            
            if(personToCreate.isDeleted() == true) {
            	isdeleted = 1;
            }else {
            	isdeleted = 0;
            }*/
            
            String columnNames[] = new String[] { "id" };
            
            PreparedStatement stmt = con.prepareStatement(sql,columnNames);
            stmt.setString(1, personToCreate.getCity());
            stmt.setString(2, personToCreate.getCountry());
            stmt.setString(3, personToCreate.getEmail());
            stmt.setInt(4, personToCreate.isDeleted()?1:0);
            stmt.setString(5, personToCreate.getName());
            stmt.setString(6, personToCreate.getPass());
            stmt.setString(7, personToCreate.getPhone());
            stmt.setString(8, personToCreate.getRegion());
            stmt.setString(9, personToCreate.getRole());
            stmt.setString(10, personToCreate.getStreet());
            stmt.setString(11, personToCreate.getSurname());
            stmt.setString(12, personToCreate.getZip());
            
            int generatedId = 0;
            if (stmt.executeUpdate() > 0) {
                java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys();
                if ( generatedKeys.next() ) {
                	generatedId = generatedKeys.getInt(1);
                }
            }
           
            result = personToCreate;
            result.setId(generatedId);
            
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return result;
	}
	
	
}
