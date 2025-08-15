package mypack;

public class A extends B {
	
	public void dsiA() {
		System.out.println("A class");
	}
	
	public void disB2() {
		System.out.println("dis b A class");
	}
	
	public static void main(String[] args) {
		
		B ob=new A();
		A obj= (A) ob;
		
		obj.dsiA();
		obj.dsiB();
		obj.disB2();
		
		ob.disB2();
		
		
		
		
		
		

	}

}

class B {
	public void dsiB() {
		System.out.println("B class");
	}
	
	public void disB2() {
		System.out.println("dis b B class");
	}
	
	
	
}
