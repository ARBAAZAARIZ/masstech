package mypack;

public class TestClass implements TestInterface {

	public static void main(String[] args) {
		
		
		TestClass obj=new TestClass();
		obj.display(obj);

	}

	@Override
	public void display(TestInterface obj) {
		obj.details();
		
	}

	@Override
	public void details() {
		String name="jhon";
		String email="jhon@gmail.com";
		System.out.println( "Name : "+ name + " Email : " + email );
		
	}

}

interface TestInterface{
	void display(TestInterface obj);
	void details();
}
