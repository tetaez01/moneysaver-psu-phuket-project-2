package edusoft.android.reuse;

public class BankObject {
	private String bankId = "";
	private String bankName = "";
	private String bankAcronym = "";
	
	public void setBankId(String bankId){
		this.bankId = bankId;
	} 
	public String getBankId(){
		return bankId;
	}
	public void setBankName(String bankName){
		this.bankName = bankName;
	} 
	public String getBankName(){
		return bankName;
	}
	public void setBankAcronym(String bankAcronym){
		this.bankAcronym = bankAcronym;
	}
	public String getBankAcronym(){
		return bankAcronym;
	}
}
