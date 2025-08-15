package mypack;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

		
		Random r=new Random();
		int num=r.nextInt(0,100);
		
		int count=0;
		Scanner s=new Scanner(System.in);
		
		int inp=-1;
		
		
		while(inp!=num) {
			count++;
			System.out.println("Enter your number");
			inp=s.nextInt();
			
			try {
				if(inp>num) {
					throw new BiggerNumberException("Go for smaller number");
				}else if(inp<num) {
					throw new SmallerNumberException("Go for bigger number");
				}else {
					System.out.println("U guessed it, its " + num );
					System.out.println("You have taken " + count + " times");
				}		
					
					
			}catch(BiggerNumberException e) {
				System.out.println(e.getMessage());
				
			}catch(SmallerNumberException e) {
				System.out.println(e.getMessage());
			}
			
		}

	}
	
	

}

class BiggerNumberException extends Exception{
	public BiggerNumberException(String message) {
		super(message);
	}
}

class SmallerNumberException extends Exception{
public SmallerNumberException(String message) {
	super(message);
}
}
