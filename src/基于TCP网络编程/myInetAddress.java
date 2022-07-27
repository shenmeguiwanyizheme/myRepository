package 基于TCP网络编程;

import java.net.InetAddress;

public class myInetAddress {
    public static void main(String[] args) throws Exception {
        InetAddress ia=InetAddress.getByName("192.168.3.54");
        System.out.println(ia);
        System.out.println(ia.getHostName());
        InetAddress ia5=InetAddress.getByName("com.mashibing.com");
        System.out.println(ia5.getHostName());
        System.out.println(ia5.getHostAddress());
    }
    public  void eat(){}

}

