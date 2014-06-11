package workshop;


import javafx.beans.property.SimpleStringProperty;


 public class Person {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty addressId;
    private final SimpleStringProperty city;
    private final SimpleStringProperty state;
    private final SimpleStringProperty zip;
    
    
    private final SimpleStringProperty emailId;
    private final SimpleStringProperty phoneId;
    private final SimpleStringProperty customerId;

 
    public Person(String customerID, String firstName, String lastName,String address, String city,String state,String zip,String phone,String email) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.addressId = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.zip = new SimpleStringProperty(zip);
        this.emailId = new SimpleStringProperty(email);
        this.phoneId = new SimpleStringProperty(phone);
        this.customerId = new SimpleStringProperty(customerID);
    }
    
    public String getFirstName() {				/** FIRST NAME **/
        return firstName.get();
    }
    public void setFirstName(String fName) {
        firstName.set(fName);
    }
    public String getLastName() {				/** LAST NAME **/
        return lastName.get();
    }
    public void setLastName(String fName) {
        lastName.set(fName);
    }
    public String getAddressId() {				/** ADDRESS **/
        return addressId.get();
    }
    public void setAddressId(String fName) {
        addressId.set(fName);
    }
    public String getCity(){					/** CITY **/
    	return city.get();
    }
    public String getState(){					/** STATE **/
    	return state.get();
    }
    public String getZip(){						/** ZIP **/
    	return zip.get();
    }
    public String getPhoneId() {				/** PHONE **/
        return phoneId.get();
    }
    public void setPhoneId(String fName) {
        phoneId.set(fName);
    }
    public String getEmailId() {				/** EMAIL **/
        return emailId.get();
    }
    public void setEmailId(String fName) {
        emailId.set(fName);
    }
    public String getCustomerId() {			/** PATIENT ID **/
        return customerId.get();
    }
    public void setCustomerId(String fName) {
        customerId.set(fName);
    }
        
}