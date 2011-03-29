package org.infoaxon.fkm.facebook.pojo;

/**
 * 
 *@author apoorva.prakash
 * 
 */

public class PersonalDetails {
	String 		id					="";
	String 		name 				= "";
	String 		first_name 			= "";
	String 		last_name 			= "";
	String 		birthday 			= "";
	String 		gender 				= "";
	String 		relationshipStatus 	= "";
	String 		religion 			= "";
	String 		politicalView 		= "";
	String 		bio 				= "";
	String 		quote 				= "";
	String 		link 				= "";
	String		picture				= "";
	Contents 	hometown 			= new Contents();
	Contents 	location 			= new Contents();
	Contents 	work[];
	Contents 	education[];
	Contents 	languages[];
	Contents 	music[];
	Contents 	movies[];
	Contents 	books[];
	Contents 	television[];
	Contents 	activities[];
	Contents 	interests[];
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String firstName) {
		first_name = firstName;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String lastName) {
		last_name = lastName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelationshipStatus() {
		return relationshipStatus;
	}
	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getPoliticalView() {
		return politicalView;
	}
	public void setPoliticalView(String politicalView) {
		this.politicalView = politicalView;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Contents getHometown() {
		return hometown;
	}
	public void setHometown(Contents hometown) {
		this.hometown = hometown;
	}
	public Contents getLocation() {
		return location;
	}
	public void setLocation(Contents location) {
		this.location = location;
	}
	public Contents[] getWork() {
		return work;
	}
	public void setWork(Contents[] work) {
		this.work = work;
	}
	public Contents[] getEducation() {
		return education;
	}
	public void setEducation(Contents[] education) {
		this.education = education;
	}
	public Contents[] getLanguages() {
		return languages;
	}
	public void setLanguages(Contents[] languages) {
		this.languages = languages;
	}
	public Contents[] getMusic() {
		return music;
	}
	public void setMusic(Contents[] music) {
		this.music = music;
	}
	public Contents[] getMovies() {
		return movies;
	}
	public void setMovies(Contents[] movies) {
		this.movies = movies;
	}
	public Contents[] getBooks() {
		return books;
	}
	public void setBooks(Contents[] books) {
		this.books = books;
	}
	public Contents[] getTelevision() {
		return television;
	}
	public void setTelevision(Contents[] television) {
		this.television = television;
	}
	public Contents[] getActivities() {
		return activities;
	}
	public void setActivities(Contents[] activities) {
		this.activities = activities;
	}
	public Contents[] getInterests() {
		return interests;
	}
	public void setInterests(Contents[] interests) {
		this.interests = interests;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}