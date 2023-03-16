package day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepository {
	private static ClientRepository repository = new ClientRepository();
	private ClientRepository() {}
	public static ClientRepository getInstance() {
		return repository;
	}
	Map<String, ClientDTO> clientMap = new HashMap<>();
//	List<ClientDTO> cList = new ArrayList<>();
	List<BreakdownDTO> bList = new ArrayList<>();
	
	public boolean save(ClientDTO clientDTO) {
		if(clientMap.put(clientDTO.getAccount(),clientDTO)==null) {
			return true;
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
	public List<BreakdownDTO> breakList(String account){
		List<BreakdownDTO> list = new ArrayList<>();
		for(BreakdownDTO b : bList) {
			if(b.getAccount().equals(account)) {
				list.add(b);
			}
		}
		return list;
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
				bList.add(breakdownDTO);
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
					bList.add(breakdownDTO);
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














