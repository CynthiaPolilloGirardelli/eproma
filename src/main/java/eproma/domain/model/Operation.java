package eproma.domain.model;

import java.time.LocalDate;

public class Operation {
	private int id;
	private int sellerId;
	private int purchaserId;
	private int propertyId;
	private double price;
	private String type;
	private LocalDate registerDate;
	private String observations;
	private  LocalDate endDate;
	private LocalDate startDate;
	private LocalDate physicalTransferDate;
	private boolean isDeleted;
	public Operation(int id, int sellerId, int purchaserId,
			int propertyId, double price, String type,
			LocalDate registerDate, String observations,
			LocalDate endDate, LocalDate startDate,
			LocalDate physicalTransferDate, boolean isDeleted) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.purchaserId = purchaserId;
		this.propertyId = propertyId;
		this.price = price;
		this.type = type;
		this.registerDate = registerDate;
		this.observations = observations;
		this.endDate = endDate;
		this.startDate = startDate;
		this.physicalTransferDate = physicalTransferDate;
		this.isDeleted = isDeleted;
	}
	public Operation(int sellerId, int purchaserId,
			int propertyId, double price, String type,
			LocalDate registerDate,
			String observations, LocalDate endDate,
			LocalDate startDate,
			LocalDate physicalTransferDate,
			boolean isDeleted) {
		super();
		this.sellerId = sellerId;
		this.purchaserId = purchaserId;
		this.propertyId = propertyId;
		this.price = price;
		this.type = type;
		this.registerDate = registerDate;
		this.observations = observations;
		this.endDate = endDate;
		this.startDate = startDate;
		this.physicalTransferDate = physicalTransferDate;
		this.isDeleted = isDeleted;
	}
	public Operation() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(int purchaserId) {
		this.purchaserId = purchaserId;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDate getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getPhysicalTransferDate() {
		return physicalTransferDate;
	}
	public void setPhysicalTransferDate(LocalDate physicalTransferDate) {
		this.physicalTransferDate = physicalTransferDate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	
	
	
}
