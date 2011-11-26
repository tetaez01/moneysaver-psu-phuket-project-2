package edusoft.android.reuse;

public class BalanceObject {
	private String activityId = "";
	private String accountId = "";
	private String typeUsing = "";
	private String description = "";
	private String date = "";
	private String time = "";
	private String netPrice = "";
	
	public void setActivityId(String activityId){
		this.activityId = activityId;
	}
	public String getActivityId(){
		return activityId;
	}
	public void setAccountId(String accountId){
		this.accountId = accountId;
	}
	public String getAccountId(){
		return accountId;
	}
	public void setTypeUsing(String typeUsing){
		this.typeUsing = typeUsing;
	}
	public String getTypeUsing(){
		return typeUsing;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}	
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return date;
	}
	public void setTime(String time){
		this.time  = time;
	}
	public String getTime(){
		return time;
	}
	public void setNetPrice(String netPrice){
		this.netPrice = netPrice;
	}
	public String getNetPrice(){
		return netPrice;
	}
}
