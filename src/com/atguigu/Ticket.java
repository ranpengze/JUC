package com.atguigu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 多线程
 * 例子：三个卖票员卖30张票 
 * 卖票员      卖票      票的张数
 *a:线程      操作     资源类
  b:高内聚     低耦合
      结论：资源类自身以高内聚的方式，自身携带同步的方法，对外暴露给多线程调用
 * 
 * 
 */
public class Ticket {

	public static void main(String[] args) {
		TicketNumber t =new TicketNumber();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 30; i++) {
					t.sale();
				}
			}
		},"a").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 30; i++) {
					t.sale();
				}
			}
		},"b").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 30; i++) {
					t.sale();
				}
			}
		},"c").start();
	}
	
}
class TicketNumber{
	private int number=30;
	
	private Lock lock = new ReentrantLock();
	public void sale() {
		lock.lock();
		try {
			if(number>0) {
				System.out.println(Thread.currentThread().getName()+"\t卖出第:"+number+"\t还剩：" +--number);
			}
		} catch (Exception e) {
		
		}finally {
			lock.unlock();
		}
	}
}