package package1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Emp {

	int id;
	String name;
	double salary;
	
	
	public Emp(int id,String name,double salary) {
		
		this.id=id;
		this.name=name;
		this.salary=salary;
		
	}
	
	
	public static void main(String[] args) {
		
		
		List<Emp> obj=new ArrayList<Emp>();
		
		obj.add(new Emp(101,"Jhon",5000));
		obj.add(new Emp(102,"rohan",2000));
		obj.add(new Emp(103,"Soham",30000));
		obj.add(new Emp(104,"sam",30000));
		
		obj.stream()
		   .filter(o -> o.salary >= 10000)
		   .map(x -> x.id + " - " + x.name) // Combine id and name
		   .forEach(System.out::println);

		
//		System.out.println(e.getFirst().id +" "+ e.getFirst().name +" "+ e.getFirst().salary);
		
		
		
		
	}

}
