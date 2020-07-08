package com.github.cristnascimento.contactlistapp;

public class Contact {

  private long id = 0;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String phoneCategory;
  private String address;
  private String city;
  private String state;
  private String zip;
  private String closeFriend;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhoneCategory() {
    return phoneCategory;
  }

  public void setPhoneCategory(String phoneCategory) {
    this.phoneCategory = phoneCategory;
  }

  public String getAddress() {
    return address;
  }

  public void setAddres(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCloseFriend() {
    return closeFriend;
  }

  public void setCloseFriend(String closeFriend) {
    this.closeFriend = closeFriend;
  }
}