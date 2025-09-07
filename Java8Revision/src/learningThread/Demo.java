package learningThread;

public class Demo {
	//volatile boolean status=true;
	public synchronized void dis(int n) {
		for(int i=1 ; i<=5  ;i++) {
			System.out.println(i*n);
			try {
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

}
