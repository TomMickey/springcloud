package grgbanking;

public class Test {
	 public static Object object = new Object();
	    public static void main(String[] args) {
	        Thread1 thread1 = new Thread1();
	        Thread2 thread2 = new Thread2();
	         
	        thread1.start();
	         
	        try {
	            Thread.sleep(10000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	         
	        thread2.start();
	    }
	     
	    static class Thread1 extends Thread{
	        @Override
	        public void run() {
	        	System.out.println("Thread1");
	        	System.out.println(Thread.currentThread().getName());
	            synchronized (object) {
	                try {
	                	System.out.println("---Thread1");
	                    object.wait(20000);
	                    System.out.println("�ͷ���---");
	                } catch (InterruptedException e) {
	                }
	                System.out.println("�߳�"+Thread.currentThread().getName()+"��ȡ������");
	            }
	        }
	    }
	     
	    static class Thread2 extends Thread{
	        @Override
	        public void run() {
	        	System.out.println("Thread2");
	            synchronized (object) {
                	System.out.println("---Thread2");
	                object.notify();
	                try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	                System.out.println("�߳�"+Thread.currentThread().getName()+"������object.notify()");
	            }
	            System.out.println("�߳�"+Thread.currentThread().getName()+"�ͷ�����");
	        }
	    }


}
