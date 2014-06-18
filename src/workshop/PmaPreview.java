package workshop;

import javafx.beans.property.SimpleStringProperty;

public class PmaPreview {

	private final SimpleStringProperty description;
	private final SimpleStringProperty techComments;
	private final SimpleStringProperty repairComments;
	private final SimpleStringProperty priority;
	private final SimpleStringProperty parts;
	private final SimpleStringProperty labor;
	private final SimpleStringProperty cost;
	
	public PmaPreview(String description,String techcomments,String repairComments,String priority, String parts,String labor, String cost){
		this.description = new SimpleStringProperty(description);
		this.techComments = new SimpleStringProperty(techcomments);
		this.repairComments = new SimpleStringProperty(repairComments);
		this.priority = new SimpleStringProperty(priority);
		this.parts = new SimpleStringProperty(parts);
		this.labor = new SimpleStringProperty(labor);
		this.cost = new SimpleStringProperty(cost);
		
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
	public String getCost(){
		return cost.get();
	}
	
	
	
}
