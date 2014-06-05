package workshop;

import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
	private final SimpleStringProperty vehicleVIN;
    private final SimpleStringProperty vehicleLic;
    private final SimpleStringProperty vehicleTags;
    private final SimpleStringProperty vehicleYear;
    private final SimpleStringProperty vehicleMake;
    private final SimpleStringProperty vehicleModel;
    private final SimpleStringProperty vehicleTrans;
    private final SimpleStringProperty vehicleMiles;
    private final SimpleStringProperty vehicleEngine;
	    
	public Vehicle(String VIN,String licnum, String tags,String year,String make,String model,String engine,String trans,String miles){
		this.vehicleVIN = new SimpleStringProperty(VIN);
        this.vehicleLic = new SimpleStringProperty(licnum);
        this.vehicleTags = new SimpleStringProperty(tags);
        this.vehicleYear = new SimpleStringProperty(year);
        this.vehicleMake = new SimpleStringProperty(make);
        this.vehicleModel = new SimpleStringProperty(model);
        this.vehicleEngine = new SimpleStringProperty(engine);
        this.vehicleTrans = new SimpleStringProperty(trans);
        this.vehicleMiles = new SimpleStringProperty(miles);
	}
    
	public String getVIN(){
		return vehicleVIN.get();
	}
	public String getYear(){
		return vehicleYear.get();
	}
	public String getMake(){
		return vehicleMake.get();
	}
	public String getModel(){
		return vehicleModel.get();
	}
	public String getLicense(){
		return vehicleLic.get();
	}
	
	
	
	
	    
	    
	
}
