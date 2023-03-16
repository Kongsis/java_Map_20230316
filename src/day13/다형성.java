package day13;

import java.util.ArrayList;
import java.util.List;

public class 다형성 {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
	}
	public static void test(Animal a) {
		a.cry();
	}
	

}
class Animal{
	Animal a = new Animal();
	public void eat() {
		System.out.println("먹다");
	}
	public void cry() {
		System.out.println("울다");
	}
}
class Dog extends Animal{
	public void cry() {
		System.out.println("멍멍");
	}
	public void run() {
		System.out.println("걷다");
	}
}
class Cat extends Animal{
	public void cry() {
		System.out.println("야옹");
	}
}







