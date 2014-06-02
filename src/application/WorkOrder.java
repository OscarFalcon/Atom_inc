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
	
	public String getWorkOrder(){
		return workOrder.get();
	}
	public String getDate(){
		return workOrderDate.get();
	}
	public String getName(){
		return person.getFirstName() + " " + person.getLastName();
	}
	public String getVehicle(){
		return vehicleYear.get() + " "	+ vehicleMake.get() + " " + vehicleModel.get();
	}
	
}
