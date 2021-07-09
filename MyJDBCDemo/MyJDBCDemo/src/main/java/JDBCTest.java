import java.sql.*;

/**
 * created by ZSAndroid on 2021/7/6
 * 描述：JDBC连接测试类
 *
 * @author
 * @creat 2021-07-06-16:36:07
 */
public class JDBCTest {
    private static String stringValueOne;
    private static String stringValueTwo;
    private static String stringValueThree;
    private static String stringValueFour;
    private static String stringValueFive;
    private static String stringValueSix;
    private static String stringValueSeven;

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1.注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbweb?useSSL=false", "root", "root");
            //3.获取数据库操作对象
            statement = connection.createStatement();
            //4.执行SQL
            //String sql = "select * from student";
            String sql = "select stu_id as id,stu_no,stu_name from student";
            // int = statement.executeUpdate(sql);//数据的操作语言：insert delete update
            // ResultSet = statement.executeQuery(sql);//专门执行DQL语句:select
            resultSet = statement.executeQuery(sql);
            //5.处理查询结果集
            //查询第一行
//            if (resultSet.next()) {
//                int intId = resultSet.getInt(1);
//                String strNo = resultSet.getString(2);
//                String strName = resultSet.getString(3);
//                System.out.println("id=" + intId + "学号=" + strNo + "姓名=" + strName);
//            }
//            //查询第二行
//            if (resultSet.next()) {
//                int intId = resultSet.getInt(1);
//                String strNo = resultSet.getString(2);
//                String strName = resultSet.getString(3);
//                System.out.println("id=" + intId + "学号=" + strNo + "姓名=" + strName);
//            }
            //while循环遍历结果集
            //next()光标指向的行没有数据就跳出当前while()循环，然后继续执行while下面的语句(没return时)
//            while (resultSet.next()) {
//                //getString()方法的特点 ：不管数据库中数据的类型是什么，都以String类型取出
//                //JDBC中所有的下标从1开始，不是从0开始
//                //getSting()中的1,2,3,4,5,6,7表示从当前行的几列取出数据 这种方式不健壮，不推荐使用
//                String intId = resultSet.getString(1);
//                String strNo = resultSet.getString(2);
//                String strName = resultSet.getString(3);
//                System.out.println("学生ID==" + intId + ",学号==" + strNo + "," + "姓名==" + strName);
//            }
            //while循环遍历结果集以getSting("列名")查询数据
            while (resultSet.next()) {
                //getString()方法的特点 ：不管数据库中数据的类型是什么，都以String类型取出
                //JDBC中所有的下标从1开始，不是从0开始
                //getString()中的1,2,3,4,5,6,7表示从当前行的几列取出数据 这种方式不健壮，不推荐使用
                //而列名指的是将需要执行的sql语句中排列的列名，而不是数据库表中的字段名。"select stu_id as id form student";
                String intId = resultSet.getString("id");
                String strNo = resultSet.getString("stu_no");
                String strName = resultSet.getString("stu_name");
                System.out.println("学生ID==" + intId + ",学号==" + strNo + "," + "姓名==" + strName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.关闭连接+释放资源
            if (resultSet != null) {//关闭结果集
                try {
                    resultSet.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (statement != null) {//关闭数据库操作对象
                try {
                    statement.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (connection != null) {//关数据库连接
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }
}
