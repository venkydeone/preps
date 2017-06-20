package com.preps.practice.java;

import java.util.Random;
import java.util.concurrent.Semaphore;


public class ThreadSample {
	
	static class DeadLock{
		
		static Resource res1 = new Resource("Resource 1");
		static Resource res2 = new Resource("Resource 2");
		
		public static void main(String[] args) {
			
			Thread thread1 = new Thread(new Thread1());
			thread1.setName("Thread1");
			thread1.start();
			
			Thread thread2 = new Thread(new Thread1());
			thread2.setName("Thread2");
			thread2.start();
		}
		
		static class Thread1 implements Runnable{
			public void run() {
				synchronized (res1) {
					System.out.println(this.toString() + res1);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (res2) {
						System.out.println(this.toString() + res2);
					}
				}
			}
		}
		
		static class Thread2 implements Runnable{
			public void run() {
				synchronized (res2) {
					System.out.println(this.toString() + res2);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (res1) {
						System.out.println(this.toString() + res1);
					}
				}
			}
		}
		
		static class Resource{
			String name;
			
			public Resource(String res) {
				this.name = res;
			}
			
			@Override
			public String toString() {
				return "Accessing .." + this.name;
			}
		}
	}

	
	static class SimpleThreads {

	    // Display a message, preceded by
	    // the name of the current thread
	    static void threadMessage(String message) {
	        String threadName =
	            Thread.currentThread().getName();
	        System.out.format("%s: %s%n",
	                          threadName,
	                          message);
	    }

	    private static class MessageLoop
	        implements Runnable {
	        public void run() {
	            String importantInfo[] = {
	                "Mares eat oats",
	                "Does eat oats",
	                "Little lambs eat ivy",
	                "A kid will eat ivy too"
	            };
	            try {
	                for (int i = 0;
	                     i < importantInfo.length;
	                     i++) {
	                    // Pause for 4 seconds
	                    Thread.sleep(4000);
	                    // Print a message
	                    threadMessage(importantInfo[i]);
	                }
	            } catch (InterruptedException e) {
	            	try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                threadMessage("I wasn't done!");
	            }
	        }
	    }

	    public static void main(String args[])
	        throws InterruptedException {

	        // Delay, in milliseconds before
	        // we interrupt MessageLoop
	        // thread (default one hour).
	        long patience = 5 * 1000;

	        threadMessage("Starting MessageLoop thread");
	        long startTime = System.currentTimeMillis();
	        Thread t = new Thread(new MessageLoop());
	        t.start();

	        threadMessage("Waiting for MessageLoop thread to finish");
	        // loop until MessageLoop
	        // thread exits
	        while (t.isAlive()) {
	            threadMessage("Still waiting...");
	            // Wait maximum of 1 second
	            // for MessageLoop thread
	            // to finish.
	            t.join(1000); //TODO Try removing 1000 , the main thread waits indefinitely 
	            if (((System.currentTimeMillis() - startTime) > patience)
	                  && t.isAlive()) {
	                threadMessage("Tired of waiting!");
	                t.interrupt();
	                // Shouldn't be long now
	                // -- wait indefinitely
	                t.join();// Need for this??? Lets say the Running thread has something to finish while it's interrupted - check the 5 sec delay in catch block - this joins makes main thread to wait till the 5 Sec delay completes
	            }
	        }
	        threadMessage("Finally!");
	    }
	}
	
	static class ProducerConsumer{
		
		public static void main(String[] args) {
			Drop drop = new Drop();
	        (new Thread(new Producer(drop))).start();
	        (new Thread(new Consumer(drop))).start();
		}
		
		
		static class Drop {
			// Message sent from producer
			// to consumer.
			private String message;
			// True if consumer should wait
			// for producer to send message,
			// false if producer should wait for
			// consumer to retrieve message.
			private boolean empty = true;

			public synchronized String take() {
				// Wait until message is
				// available.
				while (empty) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
				// Toggle status.
				empty = true;
				// Notify producer that
				// status has changed.
				notifyAll();
				return message;
			}

			public synchronized void put(String message) {
				// Wait until message has
				// been retrieved.
				while (!empty) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
				// Toggle status.
				empty = false;
				// Store message.
				this.message = message;
				// Notify consumer that status
				// has changed.
				notifyAll();
			}
		}
		
		static class Consumer implements Runnable {
		    private Drop drop;

		    public Consumer(Drop drop) {
		        this.drop = drop;
		    }

		    public void run() {
		        Random random = new Random();
		        for (String message = drop.take();
		             ! message.equals("DONE");
		             message = drop.take()) {
		            System.out.format("MESSAGE RECEIVED: %s%n", message);
		            try {
		                Thread.sleep(random.nextInt(5000));
		            } catch (InterruptedException e) {}
		        }
		    }
		}
		
		
		static class Producer implements Runnable {
		    private Drop drop;

		    public Producer(Drop drop) {
		        this.drop = drop;
		    }

		    public void run() {
		        String importantInfo[] = {
		            "Mares eat oats",
		            "Does eat oats",
		            "Little lambs eat ivy",
		            "A kid will eat ivy too"
		        };
		        Random random = new Random();

		        for (int i = 0;
		             i < importantInfo.length;
		             i++) {
		            drop.put(importantInfo[i]);
		            try {
		                Thread.sleep(random.nextInt(5000));
		            } catch (InterruptedException e) {}
		        }
		        drop.put("DONE");
		    }
		}
	}	
	
	static class YieldExample{
		public static void main(String[] args)
		{
			Thread producer = new Thread(){
				public void run()
				{
					for (int i = 0; i < 5; i++)
					{
						System.out.println("I am Producer : Produced Item " + i);
						Thread.yield();
					}
				}
			};
			Thread consumer = new Thread(){
				public void run()
				{
					for (int i = 0; i < 5; i++)
					{
						System.out.println("I am Consumer : Consumed Item " + i);
						Thread.yield();
					}
				}
			};
			
			producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
			consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority
			
			producer.start();
			consumer.start();
		}
	}
	
	static class SemaphoreTest {

		// max 4 people
		static Semaphore semaphore = new Semaphore(4);

		static class MyATMThread extends Thread {

			String name = "";

			MyATMThread(String name) {
				this.name = name;
			}

			public void run() {

				try {


					System.out.println(name + " : acquiring lock...");
					System.out.println(name + " : available Semaphore permits now: "
									+ semaphore.availablePermits());

					semaphore.acquire();
					System.out.println(name + " : got the permit!");

					try {

						for (int i = 1; i <= 5; i++) {

							System.out.println(name + " : is performing operation " + i
									+ ", available Semaphore permits : "
									+ semaphore.availablePermits());

							// sleep 1 second
							Thread.sleep(1000);

						}

					} finally {

						// calling release() after a successful acquire()
						System.out.println(name + " : releasing lock...");
						semaphore.release();
						System.out.println(name + " : available Semaphore permits now: "
									+ semaphore.availablePermits());

					}

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			}

		}

		public static void main(String[] args) {

			System.out.println("Total available Semaphore permits : "
					+ semaphore.availablePermits());

			MyATMThread t1 = new MyATMThread("A");
			t1.start();

			MyATMThread t2 = new MyATMThread("B");
			t2.start();

			MyATMThread t3 = new MyATMThread("C");
			t3.start();

			MyATMThread t4 = new MyATMThread("D");
			t4.start();

			MyATMThread t5 = new MyATMThread("E");
			t5.start();

			MyATMThread t6 = new MyATMThread("F");
			t6.start();

		}
	}
}