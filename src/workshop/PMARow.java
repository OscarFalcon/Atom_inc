package workshop;

import pma.PMAObject;
import javafx.beans.property.SimpleStringProperty;

public class PMARow {
	
	public static final String[] descriptions = {
		"DRIVER SIDE FRONT TIRE",
		"PASSENGER SIDE FRONT TIRE",
		"PASSENGER SIDE REAR TIRE",
		"DRIVERS SIDE REAR TIRE",
		"SPARE",
		
		"WINDOWS AND EXTERIOR",
		"LOCKS",
		"A/C AND HEATER",
		"HEATER",
		"WIPER",
		"HORN",
		"HEADLAMPS",
		"PARK/TAIL/LIC PLATE LAMP",
		"BRAKE LAMP",
		"DENTS/SCRATHES",
		
		"ENGINE DIAGNOSTIC",
		"MOTOR MOUNTS",
		"BATTERY CABLES & BATTERY",
		"ENGINE OIL",
		"TRANSMISSION FLUID",
		"WASHER FLUID",
		"BRAKE FLUID",
		"POWER STEERING FLUID",
		"COOLANT",
		"SERPENTINE BELT",
		"AIR FILTER",
		"FUEL FILTER",
		"RADIATOR",
		"UPPER RADIATOR HOSE",
		"LOWER RADIATOR HOSE",
		"HEATER/BYPASS HOSE",
		
		"STEERING RACK AND PINION",
		"STEERING LINKAGE",
		"SUSPENSION",
		"ALIGNMENT",
		"FRONT STRUT/ SHOCKS",
		"REAR STRUT / SHOCKS",
		"FRONT BRAKES",
		"REAR BRAKES",
		"CV AXLE / DRIVE SHAFT",
		"MUFFLER",
		"EXHAUST PIPES"
	};
	
	
	private final SimpleStringProperty description;
	private final SimpleStringProperty techComments;
	private final SimpleStringProperty repairComments;
	private final SimpleStringProperty priority;
	private final SimpleStringProperty parts;
	private final SimpleStringProperty labor;
	
	public PMARow(String description,String techcomments,String repairComments,String priority, String parts,String labor){
		this.description = new SimpleStringProperty(description);
		this.techComments = new SimpleStringProperty(techcomments);
		this.repairComments = new SimpleStringProperty(repairComments);
		this.priority = new SimpleStringProperty(priority);
		this.parts = new SimpleStringProperty(parts);
		this.labor = new SimpleStringProperty(labor);
		
	}
	
	public String getDescription(){
		return description.get();
	}
	public String getTechComments(){
		return techComments.get();
	}
	public String getRepairComments(){
		return repairComments.get();
	}
	public String getPriority(){
		return priority.get();
	}
	public String getParts(){
		return parts.get();
	}
	public String getLabor(){
		return labor.get();
	}
	
	public String toString(){
		return "Summary: " + description.get() + techComments.get();
	}
	
	
}
