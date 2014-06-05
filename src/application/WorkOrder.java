package application;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;

public class WorkOrder {

	private final SimpleStringProperty workOrder;
    private final SimpleStringProperty vehicleVIN;
    private final SimpleStringProperty vehicleLic;
    private final SimpleStringProperty vehicleTags;
    private final SimpleStringProperty vehicleYear;
    private final SimpleStringProperty vehicleMake;
    private final SimpleStringProperty vehicleModel;
    private final SimpleStringProperty vehicleTrans;
    private final SimpleStringProperty vehicleMiles;
    private final SimpleStringProperty vehicleEngine;
    private final SimpleStringProperty workOrderDate;
    
    private final Person person;
    
    


	
	public WorkOrder(String wo,Date date,String VIN,String licnum, String tags,String year,String make,String model,String engine,String trans,String miles,Person person) {
        this.workOrder = new SimpleStringProperty(wo);
		this.vehicleVIN = new SimpleStringProperty(VIN);
        this.vehicleLic = new SimpleStringProperty(licnum);
        this.vehicleTags = new SimpleStringProperty(tags);
        this.vehicleYear = new SimpleStringProperty(year);
        this.vehicleMake = new SimpleStringProperty(make);
        this.vehicleModel = new SimpleStringProperty(model);
        this.vehicleEngine = new SimpleStringProperty(engine);
        this.vehicleTrans = new SimpleStringProperty(trans);
        this.vehicleMiles = new SimpleStringProperty(miles);
        this.workOrderDate = new SimpleStringProperty(date.toString());
        this.person = person;
	}
	
	public String getVIN(){
		return vehicleVIN.get();
	}
	public String getLic(){
		return vehicleLic.get();
	}
	public String getMake(){
		return vehicleMake.get();
	}
	public String getModel(){
		return vehicleModel.get();
	}
	public String getYear(){
		return vehicleYear.get();
	}
	public String getEngine(){
		return vehicleEngine.get();
	}
	public String getTransmission(){
		return vehicleTrans.get();
	}
	public String getMiles(){
		return vehicleMiles.get();
	}
	
	public String getWorkOrder(){
		return workOrder.get();
	}
	public String getDate(){
		return workOrderDate.get();
	}
	
	
	
	public String getFullName(){
		return person.getFirstName() + " " + person.getLastName();
	}
	public String getFirstName(){
		return person.getFirstName();
	}
	public String getLastName(){
		return person.getLastName();
	}
	public String getPhone(){
		return person.getPhoneId();
	}
	public String getEmail(){
		return person.getEmailId();
	}
	public String getAddress(){
		return person.getAddressId();
	}
	public String getCity(){
		return person.getCity();
	}
	public String getState(){
		return person.getState();
	}
	public String getZip(){
		return person.getZip();
	}
	public String getLocation(){
		return person.getCity() + "," + person.getState() + " " + person.getZip();
	}
	
	
	
	public String getVehicle(){
		return vehicleYear.get() + " "	+ vehicleMake.get() + " " + vehicleModel.get();
	}
	
}
