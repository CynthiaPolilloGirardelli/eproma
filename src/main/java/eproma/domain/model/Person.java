package eproma.domain.model;

public  class Person {
	private int id;
	private String email;
	private String pass;
	private String name;
	private String surname;
	private String phone;
	private String country;
	private String region;
	private String city;
	private String zip;
	private String street;
	private String role;
	private boolean isDeleted;
	
	
	
	public Person(int id, String email, String pass,
			String name, String surname, String phone, String country,
			String region,String city, String zip,
			String street, String role, boolean isDeleted) {
		super();
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.country = country;
		this.region = region;
		this.city = city;
		this.zip = zip;
		this.street = street;
		this.role = role;
		this.isDeleted = isDeleted;
	}
	
	

	public Person(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}



	public Person() {
		super();
	}


	public Person(String email, String pass, String name,
			String surname, String phone, String country,
			String region,String city,String zip, String street,
			String role, boolean isDeleted) {
		super();
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.country = country;
		this.region = region;
		this.city = city;
		this.zip = zip;
		this.street = street;
		this.role = role;
		this.isDeleted = isDeleted;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
