package day13;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientDTO {
	private static int firstNumber = 100;
	private static int number = 1000;
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm:ss");
	
	private String account;
	private String id;
	private String password;
	private String name;
	private long balance;
	private String joinDate;
	
	public ClientDTO() {
		if(number+1 == 1011) {
			firstNumber++;
			number = 1000;
		}
		this.account = firstNumber+"-"+number++;
		this.joinDate = DTF.format(LocalDateTime.now());
	}
	
	public String getAccount() {
		return account;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		String str = account+"\t"+id+"\t"+password+"\t"+name+"\t"+
				balance+"\t"+joinDate;
		return str;
	}
	
}




