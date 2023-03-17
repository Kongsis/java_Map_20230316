package day13;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ClientService {
	// 싱글톤패턴
	private static ClientService service = new ClientService(); // 싱글톤(접근제한)
	private ClientService() {}
	public static ClientService getInstance() {
		return service;
	}
	
	private Scanner sc = new Scanner(System.in);
	private ClientRepository repository = ClientRepository.getInstance(); //싱글톤 getInstance() 메소드를 통한 객체 사용
	private String loginId = null;
	private String loginPassword = null;
	
//	public void save() {
//		ClientDTO clientDTO = new ClientDTO();
//		System.out.print("id> ");
//		clientDTO.setId(repository.duChk());
//		System.out.print("password> ");
//		clientDTO.setPassword(sc.next());
//		System.out.print("name> ");
//		clientDTO.setName(sc.next());
//		if(repository.save(clientDTO)) {
//			System.out.println("회원가입성공");
//		}else {
//			System.out.println("회원가입실패");
//		}
//	}
	public void save() {
		ClientDTO clientDTO = new ClientDTO();
		System.out.print("id> ");
		while(true) {
			boolean find = false;

			clientDTO.setId(sc.next());
			if(repository.overlapCheck(clientDTO.getId())) {
				System.out.print("중복된 아이디입니다. 다시입력> ");
				find = true;
				continue;
			}else {
				System.out.print("password> ");
				clientDTO.setPassword(sc.next());
				System.out.print("name> ");
				clientDTO.setName(sc.next());
				if(repository.save(clientDTO)) {
					System.out.println("회원가입성공");
				}else {
					System.out.println("회원가입실패");
				}
			}
			if(!find) {
				break;
			}
		}
	}
	public boolean loginCheck() {
		System.out.print("id> ");
		String id = sc.next();
		System.out.print("password> ");
		String password = sc.next();
		
		if(repository.loginCheck(id, password)) {
			loginId = id;
			loginPassword = password;
			System.out.println("로그인성공");
			return true;
		}else {
			System.out.println("로그인실패");
			return false;
		}	
	}
	public void findAll() {
		Map<String, ClientDTO> cMap = repository.findAll();
		Map<String, ClientDTO> sortedMap = new TreeMap<>(cMap);
		System.out.println("계좌번호\t\t아이디\t비밀번호\t예금주\t잔액\t가입일");
		System.out.println("-------------------------------------------------------");
//		for(String key : cMap.keySet()) {
//			System.out.println(cMap.get(key));
//		}
		for(String b : sortedMap.keySet()) {
			System.out.println(sortedMap.get(b));
		}
	}
	public void findById() {
		ClientDTO clientDTO = repository.findById(loginId, loginPassword);
		if(clientDTO == null) {
			System.out.println("로그인 오류");
		}else {
			System.out.println("계좌번호\t\t아이디\t비밀번호\t예금주\t잔액\t가입일");
			System.out.println("-------------------------------------------------------");
			System.out.println(clientDTO.toString());
			System.out.println("-------------------------------------------------------");
			Map<String, BreakdownDTO> bMap = repository.breakList(clientDTO.getAccount());
			if(bMap.size() == 0) {
				System.out.println("입출금 내역이 없습니다");
			}else {
				System.out.println("▼입출금내역▼");
				System.out.println("-------------------------------------------------------");
				System.out.println("계좌번호\t\t구분\t거래금액\t거래후잔액\t거래일");
				for(String b : bMap.keySet()) {
					System.out.println(bMap.get(b));
				}
			}
			System.out.println("-------------------------------------------------------");
		}
	}
	public void deposit() {
		String account = repository.getAccount(loginId, loginPassword);
		if(account == null) {
			System.out.println("로그인 오류");
		}else {
			System.out.print("입금금액> ");
			long money = sc.nextLong();
			if(repository.deposit(account, money)) {
				System.out.println("입금성공");
			}else {
				System.out.println("입금실패");
			}
		}
	}
	public void withdraw() {
		String account = repository.getAccount(loginId, loginPassword);
		if(account == null) {
			System.out.println("로그인 오류");
		}else {
			System.out.print("출금금액> ");
			long money = sc.nextLong();
			if(repository.withdraw(account, money)) {
				System.out.println("출금성공");
			}else {
				System.out.println("잔액부족");
			}
		}
	}
	public void transfer() {
		String account = repository.getAccount(loginId, loginPassword);
		if(account == null) {
			System.out.println("로그인 오류");
			return;
		}
		System.out.print("이체할 계좌번호> ");
		String transferAccount = sc.next();
		System.out.print("이체할 금액> ");
		long transferMoney = sc.nextLong();
		if(repository.transferCheck(transferAccount)) {
			if(repository.withdraw(account, transferMoney)) {
				if(repository.deposit(transferAccount, transferMoney)) {
					System.out.println("이체성공");
				}
			}else {
				System.out.println("이체할 잔액부족");
			}
		}else {
			System.out.println("이체할 계좌번호가 없습니다");
		}
	}
	public void update() {
		System.out.print("비밀번호 확인> ");
		String password = sc.next();
		if(password.equals(loginPassword)) {
			System.out.print("수정할 비밀번호> ");
			String updatePassword = sc.next();
			if(repository.update(loginId, loginPassword, updatePassword)) {
				loginPassword = updatePassword;
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패");
			}
		}else {
			System.out.println("비밀번호가 일치하지 않습니다");
		}
	}
	public boolean delete() {
		if(repository.delete(loginId, loginPassword)) {
			System.out.println("삭제성공");
			return false;
		}else {
			System.out.println("삭제실패");
			return true;
		}
	}
	public void logout() {
		loginId = null;
		loginPassword = null;
		System.out.println("로그아웃");
	}
}
