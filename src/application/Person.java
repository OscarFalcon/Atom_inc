package application;



import javafx.beans.property.SimpleStringProperty;


 public class Person {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty emailId;
    private final SimpleStringProperty phoneId;
    private final SimpleStringProperty addressId;
    private final SimpleStringProperty patientId;

 
    public Person(String patientID, String fName, String lName, String phone, String address, String email) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.emailId = new SimpleStringProperty(email);
        this.phoneId = new SimpleStringProperty(phone);
        this.addressId = new SimpleStringProperty(address);
        this.patientId = new SimpleStringProperty(patientID);
    }
 
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String fName) {
        firstName.set(fName);
    }
        
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String fName) {
        lastName.set(fName);
    }
    
    public String getEmailId() {
        return emailId.get();
    }
    public void setEmailId(String fName) {
        emailId.set(fName);
    }
    public String getAddressId() {
        return addressId.get();
    }
    public void setAddressId(String fName) {
        addressId.set(fName);
    }
    public String getPhoneId() {
        return phoneId.get();
    }
    public void setPhoneId(String fName) {
        phoneId.set(fName);
    }
      public String getPatientId() {
        return patientId.get();
    }
    public void setPatientId(String fName) {
        patientId.set(fName);
    }
        
}