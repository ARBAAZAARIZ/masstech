package learningThread;

public class MyThread2  extends Thread{
	boolean status=true;
	Demo d;
	public MyThread2(Demo d) {
		this.d=d;
	}
	int n=4;
	public void run() {
		d.dis(n);
	}
	

}
