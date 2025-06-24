package eproma.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eproma.domain.model.Property;


public class PropertyRepository {


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
	 * find all property in db
	 * @return
	 */
	public List<Property> findAll(){
		List<Property> properties = new ArrayList<>();
		try
        {  
            String sql = "SELECT * FROM property where isDeleted = 0";
            
            Connection con = openConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet result=stmt.executeQuery();
            
            // minetras que result tenga filas pendientes de recorrer
            while(result.next() == true) {
            	int id = result.getInt("id");
            	int personId = result.getInt("personId");
            	double price = result.getDouble("price");
            	Integer roomNo = result.getInt("roomNo");
            	Integer bathroomNo = result.getInt("bathroomNo");
            	Double area = result.getDouble("area");
            	String type = result.getString("type");
            	Double outsideArea = result.getDouble("outsideArea");
            	Integer storiesNo = result.getInt("storiesNo");
            	Boolean hasPool = result.getBoolean("hasPool");
            	Boolean hasGym = result.getBoolean("hasGym");
            	Integer floorNo = result.getInt("floorNo");
            	Boolean hasBalcony = result.getBoolean("hasBalcony");
            	Boolean hasElevator = result.getBoolean("hasElevator");
            	String country = result.getString("country");
            	String region = result.getString("region");
            	String city = result.getString("city");
            	String role = result.getString("type");
            	String zip = result.getString("zip");
            	String street = result.getString("street");
            	boolean isDeleted = result.getBoolean("isDeleted");
            	
            	
            	Property property = new Property(id,personId,price,roomNo, bathroomNo,area,type, outsideArea,storiesNo, 
            			hasPool, hasGym, floorNo, hasBalcony, hasElevator, country, region,city, zip, street, isDeleted);
            	
            	
            	properties.add(property);
            }
            
            result.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return properties;
	}
	
	/**
	 * 
	 * @param personToFind with id set
	 * @return
	 */
	public Property find(Property propertyToFind) {
		Property pr = null;
		try
        {  
            String sql = "SELECT * FROM property WHERE id = ? and isDeleted = '0'";
            
            Connection con = openConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, propertyToFind.getId());
            
            ResultSet result=stmt.executeQuery();
            
            if(result.next()) {
            	int id = result.getInt("id");
            	int personId = result.getInt("personId");
            	double price = result.getDouble("price");

            	Integer roomNo = result.getInt("roomNo");
            	Integer bathroomNo = result.getInt("bathroomNo");
            	Double area = result.getDouble("area");
            	String type = result.getString("type");
            	Double outsideArea = result.getDouble("outsideArea");
            	Integer storiesNo = result.getInt("storiesNo");
            	Boolean hasPool = result.getBoolean("hasPool");
            	Boolean hasGym = result.getBoolean("hasGym");
            	Integer floorNo = result.getInt("floorNo");
            	Boolean hasBalcony = result.getBoolean("hasBalcony");
            	Boolean hasElevator = result.getBoolean("hasElevator");
            	String country = result.getString("country");
            	String region = result.getString("region");
            	String city = result.getString("city");
            	String role = result.getString("role");
            	String zip = result.getString("zip");
            	String street = result.getString("street");
            	boolean isDeleted = result.getBoolean("isDeleted");
            	
            	
            	pr = new Property(id,personId,price, roomNo, bathroomNo,area,type, outsideArea,storiesNo, 
            			hasPool, hasGym, floorNo, hasBalcony, hasElevator, country, region,city, zip, street, isDeleted);
            	
            	
            	            	            
            }
            
            result.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return pr;
	}
	
	/**
	 * 
	 * @param propertyToUpdate
	 * @return
	 */
	public Property update(Property propertyToUpdate) {
		Property result = null;
		try
        {  
            String sql = "UPDATE property SET "
            		+ "personId=?,"
            		+ "roomNo=?,"
            		+ "bathroomNo=?,"
            		+ "area=?,"
            		+ "type=?,"
            		+ "outsideArea=?,"
            		+ "storiesNo=?,"           		
            		+ "hasPool=?,"
            		+ "hasGym=?,"
            		+ "floorNo=?,"
            		+ "hasBalcony=?,"
            		+ "hasElevator=?,"
            		+ "country=?,"
            		+ "region=?,"
            		+ "city=?,"
            		+ "zip=?,"
            		+ "street=?,"
            		+ "isDeleted=?,"
            		+ "price=? "
            		+"WHERE id = ?";
            
            Connection con = openConnection();
            
            /*int isdeleted = 0;
            
            if(personToCreate.isDeleted() == true) {
            	isdeleted = 1;
            }else {
            	isdeleted = 0;
            }*/
            
            String columnNames[] = new String[] { "id" };
            
            PreparedStatement stmt = con.prepareStatement(sql,columnNames);
            stmt.setInt(1, propertyToUpdate.getPersonId());
            stmt.setInt(2, propertyToUpdate.getRoomno());
            stmt.setInt(3, propertyToUpdate.getBathroomno());
            stmt.setDouble(4, propertyToUpdate.getArea());
            stmt.setString(5, propertyToUpdate.getType());
            stmt.setDouble(6, propertyToUpdate.getOutsidearea());
            stmt.setInt(7, propertyToUpdate.getStoriesno());
            stmt.setInt(8, propertyToUpdate.getHaspool()?1:0);
            stmt.setInt(9, propertyToUpdate.getHasgym()?1:0);
            stmt.setInt(10, propertyToUpdate.getFloorno());
            stmt.setInt(11, propertyToUpdate.getHasbalcony()?1:0);
            stmt.setInt(12, propertyToUpdate.getHaselevator()?1:0);
            stmt.setString(13, propertyToUpdate.getCountry());
            stmt.setString(14, propertyToUpdate.getRegion());
            stmt.setString(15, propertyToUpdate.getCity());
            stmt.setString(16, propertyToUpdate.getZip());
            stmt.setString(17, propertyToUpdate.getStreet());
            stmt.setInt(18, propertyToUpdate.isDeleted()?1:0);
            stmt.setDouble(19, propertyToUpdate.getPrice());
            stmt.setInt(20, propertyToUpdate.getId());
            stmt.executeUpdate();
           
            
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
	 * 
	 * @param propertyToCreate without id
	 * @return property with id
	 */
	public Property create(Property propertyToCreate) {
		Property result = null;
		try
        {  
            String sql = "INSERT INTO property "
            		+ "(personId,"
            		+ "roomNo,"
            		+ "bathroomNo,"
            		+ "area,"
            		+ "type,"
            		+ "outsideArea,"
            		+ "storiesNo,"           		
            		+ "hasPool,"
            		+ "hasGym,"
            		+ "floorNo,"
            		+ "hasBalcony,"
            		+ "hasElevator,"
            		+ "country,"
            		+ "region,"
            		+ "city,"
            		+ "zip,"
            		+ "street,"
            		+ "isDeleted,"
            		+ "price) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            Connection con = openConnection();
            
            /*int isdeleted = 0;
            
            if(personToCreate.isDeleted() == true) {
            	isdeleted = 1;
            }else {
            	isdeleted = 0;
            }*/
            
            String columnNames[] = new String[] { "id" };
            
            PreparedStatement stmt = con.prepareStatement(sql,columnNames);
            stmt.setInt(1, propertyToCreate.getPersonId());
            stmt.setInt(2, propertyToCreate.getRoomno());
            stmt.setInt(3, propertyToCreate.getBathroomno());
            stmt.setDouble(4, propertyToCreate.getArea());
            stmt.setString(5, propertyToCreate.getType());
            stmt.setDouble(6, propertyToCreate.getOutsidearea());
            stmt.setInt(7, propertyToCreate.getStoriesno());
            stmt.setInt(8, propertyToCreate.getHaspool()?1:0);
            stmt.setInt(9, propertyToCreate.getHasgym()?1:0);
            stmt.setInt(10, propertyToCreate.getFloorno());
            stmt.setInt(11, propertyToCreate.getHasbalcony()?1:0);
            stmt.setInt(12, propertyToCreate.getHaselevator()?1:0);
            stmt.setString(13, propertyToCreate.getCountry());
            stmt.setString(14, propertyToCreate.getRegion());
            stmt.setString(15, propertyToCreate.getCity());
            stmt.setString(16, propertyToCreate.getZip());
            stmt.setString(17, propertyToCreate.getStreet());
            stmt.setInt(18, propertyToCreate.isDeleted()?1:0);
            stmt.setDouble(19, propertyToCreate.getPrice());
            int generatedId = 0;
            if (stmt.executeUpdate() > 0) {
                java.sql.ResultSet generatedKeys = stmt.getGeneratedKeys();
                if ( generatedKeys.next() ) {
                	generatedId = generatedKeys.getInt(1);
                }
            }
           
            result = propertyToCreate;
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
