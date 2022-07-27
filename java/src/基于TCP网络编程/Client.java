package 基于TCP网络编程;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws IOException {
        Socket s=new Socket("localhost",8888);
        OutputStream os=s.getOutputStream();
        DataOutputStream dos=new DataOutputStream(os);

        //dos.writeUTF("你好");
        Scanner sc=new Scanner(System.in);//因为没有学EE所以没有界面？
        System.out.println("请输入账号");
        String str=sc.next();
        System.out.println("请输入密码");
        String str2=sc.next();

        InputStream is= s.getInputStream();
        DataInputStream dis=new DataInputStream(is);
        ObjectOutputStream oos=new ObjectOutputStream(os);
        oos.writeObject(new User(str,str2));
       //一次读一个UTF里面的内容
//        dos.close();
//        os.close();
//        s.close();
    }
}
class User implements Serializable {
    private static final long serialVersionUID = 9050691344308365540L;
    private String name;
    private String pwd;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }
}