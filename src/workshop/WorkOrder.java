package workshop;

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
	
										/** vin **/
	public String getVIN(){
		return vehicleVIN.get();
	}
	public void setVIN(String vin){
		vehicleVIN.set(vin);
	}
	public String getLic(){				/** lic **/
		return vehicleLic.get();
	}
	public void setLic(String lic){		
		vehicleLic.set(lic);
	}
	public String getMake(){			/** make **/
		return vehicleMake.get();
	}
	public void setMake(String make){	
		vehicleMake.set(make);
	}
	public String getModel(){			/** model **/
		return vehicleModel.get();
	}
	public void setModel(String model){
		vehicleModel.set(model);
	}
	public String getYear(){			/** year **/
		return vehicleYear.get();
	}
	public void setYear(String year){	
		vehicleYear.set(year);
	}
	public String getEngine(){			/** engine **/
		return vehicleEngine.get();
	}
	public void setEngine(String engine){
		vehicleEngine.set(engine);
	}
	public String getTransmission(){	/** trans **/
		return vehicleTrans.get();
	}
	public void setTransmission(String trans){
		vehicleTrans.set(trans);
	}
	public String getMiles(){			/** miles **/
		return vehicleMiles.get();
	}
	public void setMiles(String miles){
		vehicleMiles.set(miles);
	}
	public String getWorkOrder(){		/** work order **/
		return workOrder.get();
	}
	public int getWorkOrderAsInt(){
		return Integer.parseInt(workOrder.get());
	}
	public void setWorkOrder(String workOrder){
		this.workOrder.set(workOrder);
	}
	public String getDate(){			/** date **/
		return workOrderDate.get();
	}
	public void setDate(Date date){
		workOrderDate.set(date.toString());
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
