package ppdatabase;

import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args) {
        select();
        delect();
        update();
        insert();
    }

    /*
     * 创建连接
     * */
    static Connection getCon(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");                                                  //注册一个驱动，使用该驱动连接数据库
            String url = "jdbc:mysql://localhost:3306/pptry?serverTimezone=GMT%2B8&&useSSL=false";      //格式：“jdbc:mysql://IP地址:端口号/"数据库.name?serverTimezone=GMT%2B8”
            String user = "root";                                                       //用户名
            String password = "localhost";                                              //密码
            con = DriverManager.getConnection(url,user,password);                       //建立连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /*
     * select
     * */
    static void select(){
        String sql = "select * from user";                  //待执行sql语句
        PreparedStatement state = null;
        ResultSet rs = null;                                //结果集
        Connection con = getCon();                          //建立连接

        try {
            state = con.prepareStatement(sql);              //预编译
            rs = state.executeQuery();                      //发起请求
            int col = rs.getMetaData().getColumnCount();    //获取列数
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {                                          //释放资源，分3个try catch防止第一个close失败后影响到后面资源的释放
            try {
                if(rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(state != null) state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * delect
     * */
    static void delect(){
        String sql = "delete from user where id='" + 1 + "'";
        PreparedStatement state = null;
        int row;

        Connection con = getCon();                          //建立连接
        try {
            state = con.prepareStatement(sql);              //预编译
            row = state.executeUpdate();                    //执行增删改操作，返回收影响的行数
            System.out.println("resutl: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {                                          //释放资源，分3个try catch防止第一个close失败后影响到后面资源的释放
            try {
                if(state != null) state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * update
     * */
    static void update(){
        PreparedStatement state = null;
        String sql = "update user set username='" + "pengpeng" + "' where username='" + "pp" + "'";
        int row;

        Connection con = getCon();                          //建立连接
        try {
            state = con.prepareStatement(sql);              //预编译
            row = state.executeUpdate();                    //执行增删改操作，返回收影响的行数
            System.out.println("resutl: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {                                          //释放资源，分3个try catch防止第一个close失败后影响到后面资源的释放
            try {
                if(state != null) state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * insert
     * */
    static void insert(){
        String sql = "insert into user values(null,'pp',123);";
        PreparedStatement state = null;
        int row;
        Connection con = getCon();
        try {
            state = con.prepareStatement(sql);              //预编译
            row = state.executeUpdate();                    //执行增删改操作，返回收影响的行数
            System.out.println("result1:" + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {                                          //释放资源，分3个try catch防止第一个close失败后影响到后面资源的释放
            try {
                if(state != null) state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
