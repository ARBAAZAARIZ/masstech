package learningThread;

public class MyThread1 extends Thread {
	Demo d;
	public MyThread1(Demo d) {
		this.d=d;
	}
	int n=3;
	public void run() {
		d.dis(n);
	}

}
