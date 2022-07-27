package com.company;
//idea在本质上只是对电脑中现有文件的一种解释方式
import com.sun.jdi.PathSearchingVirtualMachine;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class 预编译和批处理 {
    public static String driver="com.mysql.cj.jdbc.Driver";
    public static String url="jdbc:mysql://localhost:3306/mydb?rewriteBatchStatements=true&serverTimezone=Asia/Shanghai";
    public static String user="root";
    public static String password ="123456";

    public static void main(String[] args) throws Exception{
        testAddBatch2();
       // testAddBatch1();
    }
    public static void testAddBatch1() throws SQLException {
       
           Connection connection=null;
           PreparedStatement preparedStatement =null;//这里要换成预编译对象
           String sql="select* from acconnt where username=? and password =?";
         try{
           Class.forName(driver);

           connection= DriverManager.getConnection(url,user,password);

           preparedStatement=connection.prepareStatement(sql);
           preparedStatement.setString(1,"zhangsan");
           preparedStatement.setString(2,"derder");
          ResultSet resultSet= preparedStatement.executeQuery();
             while(resultSet.next()){
                 System.out.println(resultSet.getInt(3));
             }
           //这种语句对象还是有update和query之分的
       }
       catch (Exception e){
           System.out.println(e.getMessage());
       }
       finally{
           if(null!=preparedStatement) {
               try {
                   preparedStatement.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
           connection.close();

       }
        
       //预编译之后需要干什么，预编译还可以动态的修改变量 弥补sql缺陷
        // 但是其本质都是计算机应有的，不应该作为什么的独特之事而处理

    }
    public static void testAddBatch2() throws Exception {
        Class.forName(driver);
        Connection connection=null;
        PreparedStatement preparedStatement =null;//这里要换成预编译对象
        String sql="insert into acconnt values(?,?,?)";
        connection=DriverManager.getConnection(url,user,password);
        preparedStatement=connection.prepareStatement(sql);
        //接下来是怎么进行批处理
        for(int i=1;i<=10000;i++){
            preparedStatement.setString(1,"lala");
            preparedStatement.setString(2,"huhuhu");
            preparedStatement.setInt(3,Integer.MAX_VALUE);
            preparedStatement.addBatch();
            if(i%1000==0){

                preparedStatement.executeBatch();
                preparedStatement.clearBatch();

            }
        }

    }
    public static void testAddBatch3() throws Exception {//设置事务回滚点

        LinkedList<Savepoint>list=new LinkedList<>();
        Connection connection=null;
        PreparedStatement preparedStatement =null;//这里要换成预编译对象
        String sql="insert into acconnt values(?,?,?)";
       try{
           Class.forName(driver);
           connection=DriverManager.getConnection(url,user,password);
           preparedStatement=connection.prepareStatement(sql);
           //接下来是怎么进行批处理
           for(int i=1;i<=10000;i++){
               connection.setAutoCommit(false);//反正每个语句都是开启事务，但是不提交事务
               preparedStatement.setString(1,"lala");
               preparedStatement.setString(2,"huhuhu");
               preparedStatement.setInt(3,Integer.MAX_VALUE);
               preparedStatement.addBatch();
               if(i%1000==0){

                   preparedStatement.executeBatch();
                   preparedStatement.clearBatch();
                   //应该在没有异常的数据处理玩之后才设置回滚点，这样子无异常数据会被完全提交
                   Savepoint savepoint=connection.setSavepoint();
                   list.addLast(savepoint);

               }
           }

       }catch (Exception e){
           try {
               connection.rollback(list.getLast());
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
       finally {
           try {
               connection.commit();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }
}
