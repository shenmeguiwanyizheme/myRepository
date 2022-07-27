package testDao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class baseDao {
    private static String driver ="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user="root";
    private static String password="123456";
    private static  Connection connection=null;
   private static PreparedStatement preparedStatement=null;
   private static ResultSet resultSet=null;
  private  static  List list=null;
  public static int baseUpdate(String sql,Object ...args){

      int rows=0;
      try{
          Class.forName(driver);
          connection= DriverManager.getConnection(url,user,password);
          preparedStatement=connection.prepareStatement(sql);
          for(int i=0;i<args.length;i++){
              preparedStatement.setObject(i+1,args[i]);
          }//接下来干啥
          preparedStatement.executeUpdate();

      }
      catch (Exception e){
          System.out.println(e.getMessage());
      }
      finally {
          if(null != preparedStatement){
              try {
                  preparedStatement.close();
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
          if(null != connection){
              try {
                  connection.close();
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }
      return rows;
      }
     public static List  Query(Class clazz, String sql,Object...args){
         try {
            list=new LinkedList();
             Class.forName(driver);//这个方法是说可以加载某个类？//所以其实一个代码运行的时候加载的是用到的类，并不是所有的类？


             connection = DriverManager.getConnection(url, user, password);
             preparedStatement = connection.prepareStatement(sql);
             for(int i=0;i<args.length;i++){
                 preparedStatement.setObject(i+1,args[i]);
             }
             resultSet=preparedStatement.executeQuery();
             //执行其实没有什么问题,因为sql代码不可能是用户去写，所以实现类肯定会把是哪个表写出来
             //关键是怎么封装结果集而已
             while(resultSet.next()){
                 Field[]fields=clazz.getDeclaredFields();
                 Object o=clazz.getConstructor().newInstance();
                 for(Field field:fields){
                     field.setAccessible(true);
                     field.set(o,resultSet.getObject(field.getName()));
                     //这个字段的设置某一个对象的该字段的值，且值为结果集中
                 }//都可以被从外部设置属性，动态加载类就是语言灵活性的体现
                 list.add(o);
             }
         }catch (Exception e){
             System.out.println("chucuole");
         }
         finally {
                   try{
                       preparedStatement.close();
                       connection.close();
                   }
                   catch (Exception e){
                       e.printStackTrace();
                   }
         }
         return list;
     }
  }


