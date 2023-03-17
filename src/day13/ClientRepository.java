package day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ClientRepository {
	private static ClientRepository repository = new ClientRepository(); // 싱글톤(접근제한)
	private ClientRepository() {}
	public static ClientRepository getInstance() {
		return repository;
	}
	Map<String, ClientDTO> clientMap = new HashMap<>(); // HashMap<String, ClientDTO>는 <>로 생략이 되었을때 앞에 있는 부모객체의 
//	Map<String, ClientDTO> clientMap = new LinkedHashMap<>();
//	List<ClientDTO> cList = new ArrayList<>();
//	List<BreakdownDTO> bList = new ArrayList<>();
	Map<String, BreakdownDTO> bMap = new TreeMap<>();
//	Scanner sc = new Scanner(System.in);
	

	public boolean save(ClientDTO clientDTO) {
		if(clientMap.put(clientDTO.getAccount(),clientDTO)==null) {
			return true;
		}
		return false;
	}
//	public String duChk() {
//		String result;
//		
//		while(true) {
//			boolean find = false;
//			result = sc.next();
//			for(String c : clientMap.keySet()) {
//				if(result.equals(clientMap.get(c).getId())){
//					System.out.print("중복된 아이디 입니다. 다시입력> ");
//					find = true;
//					break;
//				}
//			}
//			if(!find) {
//				break;
//			}
//		}
//		return result;
//	}
	public boolean overlapCheck(String id) {
		for(String d : clientMap.keySet()) {
			if(id.equals(clientMap.get(d).getId())) {
				return true;
			}
		}
		return false;
	}
	public boolean loginCheck(String id, String password) {
		for(String c : clientMap.keySet()) {
			if(id.equals(clientMap.get(c).getId()) && password.equals(clientMap.get(c).getPassword())) {
				return true;
			}
		}
		return false;
	}
	public Map<String, ClientDTO> findAll(){
		return clientMap;
	}
	public ClientDTO findById(String id, String password) {
		for(String c : clientMap.keySet()) {
			if(id.equals(clientMap.get(c).getId()) && password.equals(clientMap.get(c).getPassword())) {
				return clientMap.get(c);
			}
		}
		return null;
	}
	public Map<String, BreakdownDTO> breakList(String account){
		Map<String, BreakdownDTO> Map = new TreeMap<>();
		for(String b : bMap.keySet()) {
			if(account.equals(bMap.get(b).getAccount())) {
				Map.put(b,bMap.get(b));
//				list.add(b);
			}
		}
		return Map;
	}
	public String getAccount(String id, String password) {
		for(String c : clientMap.keySet()) {
			if(id.equals(clientMap.get(c).getId()) && password.equals(clientMap.get(c).getPassword())) {
				return clientMap.get(c).getAccount();
			}
		}
		return null;
	}
	public boolean deposit(String account, long money) {
		for(String c : clientMap.keySet()) {
			if(account.equals(clientMap.get(c).getAccount())) {
				clientMap.get(c).setBalance(clientMap.get(c).getBalance()+money);
				BreakdownDTO breakdownDTO = new BreakdownDTO();
				breakdownDTO.setAccount(account);
				breakdownDTO.setDivision("입금");
				breakdownDTO.setDealMoney(money);
				breakdownDTO.setTotalMoney(clientMap.get(c).getBalance());
				bMap.put(breakdownDTO.getLine(), breakdownDTO);
//				bList.add(breakdownDTO);
				return true;
			}
		}
		return false;
	}
	public boolean withdraw(String account, long money) {
		for(String c : clientMap.keySet()) {
			if(account.equals(clientMap.get(c).getAccount()))
				if(clientMap.get(c).getBalance() >= money) {
					clientMap.get(c).setBalance(clientMap.get(c).getBalance()-money);
					BreakdownDTO breakdownDTO = new BreakdownDTO();
					breakdownDTO.setAccount(account);
					breakdownDTO.setDivision("출금");
					breakdownDTO.setDealMoney(money);
					breakdownDTO.setTotalMoney(clientMap.get(c).getBalance());
					bMap.put(breakdownDTO.getLine(), breakdownDTO);
//					bList.add(breakdownDTO);
					return true;
				}else {
					return false;
				}
		}
		return false;
	}
	public boolean transferCheck(String transferAccount) {
		for(String c : clientMap.keySet()) {
			if(transferAccount.equals(clientMap.get(c).getAccount())) {
				return true;
			}
		}
		return false;
	}
	public boolean update(String id, String password, String updatePassword) {
		for(String c : clientMap.keySet()) {
			if(id.equals(clientMap.get(c).getId()) && password.equals(clientMap.get(c).getPassword())) {
				clientMap.get(c).setPassword(updatePassword);
				return true;
			}
		}
		return false;
	}
	public boolean delete(String id, String password) {
		for(String c : clientMap.keySet()) {
			if(id.equals(clientMap.get(c).getId()) && password.equals(clientMap.get(c).getPassword())) {
				clientMap.remove(c);
				return true;
			}
		}
		return false;
	}
	
}
