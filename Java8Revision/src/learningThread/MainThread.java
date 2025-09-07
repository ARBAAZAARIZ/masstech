package learningThread;

public class MainThread {
	
	public static void main(String[] args) throws Exception {
		Demo dm=new Demo();
		MyThread1 t1=new MyThread1(dm);
		MyThread2 t2=new MyThread2(dm);
		
		
		t1.start();
		t2.start();
		
		Thread.sleep(5000);
//		dm.status=false;
		
		
	}

}
