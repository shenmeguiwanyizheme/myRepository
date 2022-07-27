package com.company;

import java.util.Random;

public class MyBlockingQueue {
   private int [] items=new int [10];
   private volatile int size=0;
   private int head=0;
   private int tail=0;

     public void put(int value){
         synchronized (this){
             while(size==items.length) {
                 try {
                     this.wait();
                 } catch (InterruptedException e) {
                     break;
                 }

             }
             items[tail]=value;
             tail=(tail+1)%items.length;
             size++;
             notifyAll();
         }
     }
     public int take(){
         int ret=0;
         synchronized (this){
             while (size==0){
                 try {
                     wait();
                 } catch (InterruptedException e) {
                     break;
                 }
             }
             ret=items[head];
             head=(head+1)%items.length;
             size--;
             notifyAll();
         }
         return ret;
     }

    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue=new MyBlockingQueue();
        Thread customer=new Thread(()->{
            int i=10;
            while(i-->0){
                try {
                    int value=myBlockingQueue.take();
                    System.out.println("消费者消费了"+value+"此时元素个数是"+ myBlockingQueue.size);
                }
                catch ( Exception e){
                    e.printStackTrace();
                }
            }
        });
        customer.start();

        Thread producer=new Thread(new Runnable() {
            @Override
            public void run() {
                int i=10;
                while (i-->0){
                    try{
                         int random= (int)(Math.random()*1000);
                         myBlockingQueue.put(random);
                        System.out.println("生产者生产了"+random+"此时元素个数是"+ myBlockingQueue.size);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        });
        producer.start();

  }


}
class MyThread extends  Thread{

}

