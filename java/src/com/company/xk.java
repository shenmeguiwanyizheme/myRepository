package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class xk implements Comparable<xk> {
    public static void main(String[] args) {
//       try{
//           throw new RuntimeException("异常发生");
//       }
//       catch (Exception e){
//           System.out.println(e.getMessage());
//           System.out.println("已检测到异常");
//       }
//       finally {
//           System.out.println("finally语句被执行");
//       }
//        System.out.println("执行了catch和finally之后若没有return和exit仍执行剩下语句");
//    }
        Queue<String> queue = new LinkedList<>();
        System.out.println(queue.size());
        queue.add(null);
        System.out.println(queue.size());
    }

    @Override
    public int compareTo(xk o) {
        return (int) (Math.random() - Math.random());
    }
}
