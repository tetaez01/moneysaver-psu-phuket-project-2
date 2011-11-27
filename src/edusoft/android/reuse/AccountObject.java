package edusoft.android.reuse;

import java.util.List;

public class AccountObject {
	
	private String bankId = "";
	private String accountId = "";
	private String accountNumber = "";
	private String accountName = "";
	private String accountTypeId = "";
	private String limitUsage = "";
	private String currentBalance = "";
	private List listAccount;

	public void setBankId(String bankId){
		this.bankId = bankId;
	} 
	public String getBankId(){
		return bankId;
	}
	public void setAccountId(String accountId){
		this.accountId = accountId;
	} 
	public String getAccountNumber(){
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	} 
	public String getAccountName(){
		return accountName;
	}
	public void setAccountName(String accountName){
		this.accountName = accountName;
	} 
	public String getAccountId(){
		return accountId;
	}
	public void setAccountTypeId(String accountTypeId){
		this.accountTypeId = accountTypeId;
	} 
	public String getAccountTypeId(){
		return accountTypeId;
	}
	public void setLimitUsage(String limitUsage){
		this.limitUsage = limitUsage;
	}
	public String getLimitUsage(){
		return limitUsage;
	}
	public void setCurrentBalance(String currentBalance){
		this.currentBalance = currentBalance;
	}
	public String getCurrentBalance(){
		return currentBalance;
	}
	public void setListAccount(List listAccount){
		this.listAccount = listAccount;
	}
	public List getListAccount(){
		return listAccount;
	}
}
