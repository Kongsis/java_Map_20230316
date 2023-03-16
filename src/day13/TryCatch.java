package day13;

public class TryCatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(9/0);
		try {
			int[] array = new int[5];
			System.out.println(array[5]);
			System.out.println(9/0);
		}catch(ArithmeticException a) {
			System.out.println("0으로 나눌 수 없습니다");
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println("인덱스 초과");
		}catch(Exception e) {
			System.out.println("예외발생");
		}finally {
			System.out.println("무조껀 실행");
		}
		System.out.println("프로그램 끝");
	}

}
