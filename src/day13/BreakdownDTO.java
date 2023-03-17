package day13;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BreakdownDTO {
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm:ss");
	private static int number = 1;
	
	private String line;
	private String account;
	private String division;
	private long dealMoney;
	private long totalMoney;
	private String dealDate;
	
	public BreakdownDTO() {
		this.line = "B"+number++;
		this.dealDate = DTF.format(LocalDateTime.now());
	}
	
	public String getLine() {
		return line;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public long getDealMoney() {
		return dealMoney;
	}
	public void setDealMoney(long dealMoney) {
		this.dealMoney = dealMoney;
	}
	public long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(long totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	@Override
	public String toString() {
		String str = account+"\t"+division+"\t"+dealMoney+
				"\t"+totalMoney+"\t"+dealDate;
		return str;
	}
	
}
