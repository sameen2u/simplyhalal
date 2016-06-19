package com.halal.web.sa.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Business {
	private String name;
	private String description;
	private List<String> cuisine;
	private String streetAddress;
	private String city;
	private int pincode;
	private String landmark;
	private int phone;
	private String email;
	private String ownerEmail;
	private String status;
	private String website;
	private String facebookPage;
	private String twitterPage;
	private String otherInfo;
	private Date lastUpdated;
	private Map<String, String> workingHours;
	private List<String> facility;
	private String authenticity;
	private String halalOfferings;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getCuisine() {
		return cuisine;
	}
	public void setCuisine(List<String> cuisine) {
		this.cuisine = cuisine;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getFacebookPage() {
		return facebookPage;
	}
	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
	}
	public String getTwitterPage() {
		return twitterPage;
	}
	public void setTwitterPage(String twitterPage) {
		this.twitterPage = twitterPage;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Map<String, String> getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(Map<String, String> workingHours) {
		this.workingHours = workingHours;
	}
	public List<String> getFacility() {
		return facility;
	}
	public void setFacility(List<String> facility) {
		this.facility = facility;
	}
	public String getAuthenticity() {
		return authenticity;
	}
	public void setAuthenticity(String authenticity) {
		this.authenticity = authenticity;
	}
	public String getHalalOfferings() {
		return halalOfferings;
	}
	public void setHalalOfferings(String halalOfferings) {
		this.halalOfferings = halalOfferings;
	}

}
