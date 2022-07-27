package 基于TCP网络编程;
import java.io.*;
import java.lang.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss=new ServerSocket(8888);
        Socket s=ss.accept();//这是一个阻塞方法，等待客户端的数据，什么时候接收到数据，什么时候继续向下执行
        //接收到以后以一个socket进行返回，这个socket就是客户端发来的那个socket
        //服务端的socket需要使用serversocket的接受方法，ip地址是运行该程序的地址
        //所以只需要表示自己占用了哪个端口号就可以了
      //程序员能感受到的就是已经被封装好的流
        InputStream in=s.getInputStream();
        DataInputStream din=new DataInputStream(in);
        String str=din.readUTF();
        System.out.println("客户端发来的数据为"+str);
        OutputStream os=s.getOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.writeUTF("我已经收到你的消息啦");
        dos.writeUTF("我已经收到你的消息啦");
            try{
                while (true){
                 s=ss.accept();//原来是接受客户端发来的socket呀哈哈哈
                  new ServerThread(s).start();
                }
            }
         catch (Exception e){
             din.close();
             in.close();
             s.close();
             ss.close();
         }

        //先开服务器
    }
}


 class ServerThread extends Thread {//线程：专门处理客户端的请求
     InputStream is = null;
     ObjectInputStream ois = null;
     OutputStream os = null;
     DataOutputStream dos = null;
     Socket s = null;

     public ServerThread(Socket s) {
         this.s = s;
     }

     @Override
     public void run() {
         try {
             //2.等着客户端发来的信息：
             is = s.getInputStream();
             ois = new ObjectInputStream(is);
             //4.读取客户端发来的数据：
             User user = (User) (ois.readObject());
             //对对象进行验证：
             boolean flag = false;
             if (user.getName().equals("娜娜") && user.getPwd().equals("123123")) {
                 flag = true;
             }
             //向客户端输出结果：---》操作流---》输出流
             os = s.getOutputStream();
             dos = new DataOutputStream(os);
             dos.writeBoolean(flag);
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
         } finally {
             try {
                 if (dos != null) {
                     dos.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             try {
                 if (os != null) {
                     os.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             try {
                 if (ois != null) {
                     ois.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             try {
                 if (is != null) {
                     is.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
 }