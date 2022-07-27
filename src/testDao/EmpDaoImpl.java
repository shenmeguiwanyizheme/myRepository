package testDao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//为了解决接口的抽象方法不可以是static的问题，需要在控制类直接弄一个对象
interface EmpDao{
      int addEmp(Emp emp);
      int deleteEmp(Integer  empno);
      int updateEmp(Emp emp);
      List<Emp> findALL();
}
 public class EmpDaoImpl extends  baseDao implements EmpDao{


    @Override
   public int addEmp(Emp emp){
        String sql="insert into Emp values(default,?,?,?,?,?,?,?)";
        return baseDao.baseUpdate(sql,emp.ename,emp.job,emp.mgr,emp.hiredate,emp.sal,emp.comm,emp.deptno);
    }
    public int deleteEmp(Integer empno){
        String sql="delete from emp where empno=?";
        return baseDao.baseUpdate(sql,empno);//直接写成Integer，不搞什么自动装箱了

//删除语句是不用*的，因为必须全部删除
    }
  public   int updateEmp(Emp emp){
      String sql="update emp set ename =? ,job=?, mgr =?,hiredate =?,sal=?,comm=?,deptno=? where empno =?";
      return baseUpdate(sql, emp.ename,emp.job,emp.mgr,emp.hiredate,emp.sal,emp.comm,emp.deptno);
  }
    public   List<Emp> findALL(){
        String sql="select * from Emp ";
        return Query(Emp.class,sql);//卧槽这是上层调用，baseDao是下层实现，牛P
    }
}
//那如果实现类的方法不可以是静态的，那么接下来咱们该怎么写呢，必须创建一个实体类对象嘛？
class Emp  implements Serializable {
   public Integer empno;
  public String ename;
   public String job;
   public Integer mgr;
    public Date hiredate;
 public Double sal;
   public Double comm;
   public Integer deptno;
  public Emp(){

  }
    public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    //利用Object可变参数和一些设置Object的方法完成两个类之间的转换
}