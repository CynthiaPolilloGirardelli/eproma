package eproma.domain.model;

public class Property {
	private int id;
	private int personId;
	private double price;
	private Integer roomno;
	private Integer bathroomno;
	private Double area;
	private String type;
	private Double outsidearea;
	private Integer storiesno;
	private Boolean haspool;
	private Boolean hasgym;
	private Integer floorno;
	private Boolean hasbalcony;
	private Boolean haselevator;
	private String country;
	private String region;
	private String city;
	private String zip;
	private String street;
	private boolean isDeleted;

	public Property(int id, int personId, double price,
			Integer roomno, 
			Integer bathroomno,
			Double area, String type,
			Double outsidearea,
			Integer storiesno,
			Boolean haspool,
			Boolean hasgym,
			Integer floorno,
			Boolean hasbalcony,
			Boolean haselevator, 
			String country,
			String region, 
			String city, 
			String zip, String street,
			boolean isDeleted) {
		super();
		this.id = id;
		this.personId = personId;
		this.price = price;
		this.roomno = roomno;
		this.bathroomno = bathroomno;
		this.area = area;
		this.type = type;
		this.outsidearea = outsidearea;
		this.storiesno = storiesno;
		this.haspool = haspool;
		this.hasgym = hasgym;
		this.floorno = floorno;
		this.hasbalcony = hasbalcony;
		this.haselevator = haselevator;
		this.country = country;
		this.region = region;
		this.city = city;
		this.zip = zip;
		this.street = street;
		this.isDeleted = isDeleted;
	}

	public Property(int personId, double price, Integer roomno,
			Integer bathroomno, Double area, String type,
			Double outsidearea,Integer storiesno,
			Boolean haspool, Boolean hasgym, Integer floorno,
			Boolean hasbalcony,Boolean haselevator,
			String country, String region,
			String city, String zip, String street,
			boolean isDeleted) {
		super();
		this.personId = personId;
		this.price = price;
		this.roomno = roomno;
		this.bathroomno = bathroomno;
		this.area = area;
		this.type = type;
		this.outsidearea = outsidearea;
		this.storiesno = storiesno;
		this.haspool = haspool;
		this.hasgym = hasgym;
		this.floorno = floorno;
		this.hasbalcony = hasbalcony;
		this.haselevator = haselevator;
		this.country = country;
		this.region = region;
		this.city = city;
		this.zip = zip;
		this.street = street;
		this.isDeleted = isDeleted;
	}

	public Property() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Integer getRoomno() {
		return roomno;
	}

	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}

	public Integer getBathroomno() {
		return bathroomno;
	}

	public void setBathroomno(Integer bathroomno) {
		this.bathroomno = bathroomno;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getOutsidearea() {
		return outsidearea;
	}

	public void setOutsidearea(Double outsidearea) {
		this.outsidearea = outsidearea;
	}

	public Integer getStoriesno() {
		return storiesno;
	}

	public void setStoriesno(Integer storiesno) {
		this.storiesno = storiesno;
	}

	public Boolean getHaspool() {
		return haspool;
	}

	public void setHaspool(Boolean haspool) {
		this.haspool = haspool;
	}

	public Boolean getHasgym() {
		return hasgym;
	}

	public void setHasgym(Boolean hasgym) {
		this.hasgym = hasgym;
	}

	public Integer getFloorno() {
		return floorno;
	}

	public void setFloorno(Integer floorno) {
		this.floorno = floorno;
	}

	public Boolean getHasbalcony() {
		return hasbalcony;
	}

	public void setHasbalcony(Boolean hasbalcony) {
		this.hasbalcony = hasbalcony;
	}

	public Boolean getHaselevator() {
		return haselevator;
	}

	public void setHaselevator(Boolean haselevator) {
		this.haselevator = haselevator;
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


	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}