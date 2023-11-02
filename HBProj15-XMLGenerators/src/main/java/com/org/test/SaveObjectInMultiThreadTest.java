package com.org.test;

public class SaveObjectInMultiThreadTest {
	public static void main(String[] args) {
		
		RunnableInterfaceTest test = new RunnableInterfaceTest();
		
		Thread th1 = new Thread(test);
		th1.start();
		
		Thread th2 = new Thread(test);
		th2.start();
		
		Thread th3 = new Thread(test);
		th3.start();
	}

}
