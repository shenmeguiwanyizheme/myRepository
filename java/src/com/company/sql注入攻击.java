package com.company;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class sql注入攻击 {
    private static String driver ="com.mysql.cj.jdbc.Driver";

    private static String url="jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user="root";
    private static String password="123456";
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=sc.next();
        System.out.println("请输入密码");
        String pwd =sc.next();
        Account account = getAccount(username, pwd);
        System.out.println(null!= account?"登录成功":"登录失败");
        sc.close();
    }
    public static Account getAccount(String username,String pwd){
        Connection connection = null;
        Statement statement=null;
        ResultSet resultSet=null;
        Account account =null;
        try{

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user,password);
            statement = connection.createStatement();
            String sql="select * from aconnt where username='"+username+"' and password='"+pwd+"';";
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){

                String usernamea = resultSet.getString("username");
                String pwda = resultSet.getString("password");
                double money = resultSet.getDouble("money");
                account=new Account(usernamea,pwda,money);
                System.out.println(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != resultSet){
                try {
                    resultSet.close();
                } catch (SQLException e) {e.printStackTrace();
                }
            }
            if(null != statement){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return account;
    }
}
 class Account implements Serializable {
     private int aid;
     private String username;
     private String password;
     private double money;
     public Account(String username,String password,double  money){

         this.username=username;
         this.password=password;
         this.money=money;

     }
     public String  toString(){
         return null;
     }//重写的方法权限都必须越大
 }