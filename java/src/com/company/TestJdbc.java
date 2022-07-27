package com.company;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//导入依赖

/**
 *
 * @Author :Chen Baixi
 * @Description: jdbc代码练习
 */
public class TestJdbc {
    public static void main(String[] args) {
       List<Emp>list=testQuery();
       for(Emp e:list){
           System.out.println(e);
       }
    }
    public static List<Emp> testQuery(){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        List<Emp>list=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //3 获得连接
            /*
            user:用户名
            password：密码
            url
             1 协议 jdbc：mysql
             2 ip localhost/107.0.0.1
             3 端口号 3306
             4数据库名字 java102；


             */
            String url="jdbc:mysql://localhost:3306/mydb?serverTimezone=Asia/Shanghai";
            String user="root";
            String password="123456";
            connection=DriverManager.getConnection(url,user,password);
            statement=connection.createStatement();
            String sql="select * from emp;";
            // String sql="insert into dept values(default,'教学部','北京')";
            resultSet=statement.executeQuery(sql);
            list=new ArrayList<>(10);
            while(resultSet.next()){
                int empno=resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                int mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");
                double sal= resultSet.getDouble("sal");
                double comm= resultSet.getDouble("comm");
                int deptno= resultSet.getInt("deptno");
                Emp emp=new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
                list.add(emp);
            }



        } catch (Exception e) {
            System.out.println("第一部分出错了");
            System.out.println(e.getMessage());
        }
        finally {
            if(statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

 return list;
        }

    }
    }

//成员变量的初始化是调用构造函数是初始化的



