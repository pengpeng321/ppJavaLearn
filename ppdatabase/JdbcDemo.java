package ppdatabase;

import java.sql.*;

/**
 * 测试JDBC操作
 */
public class JdbcDemo {
    public static void main(String[] args) {
        select();
        delect();
        update();
        insert();
    }

    /**
     * 创建连接
     * @return Connection
     */
    static Connection getCon(){
        Connection con = null;
        try {
            // 注册一个驱动，使用该驱动连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 初始化，与数据库连接所需要的参数
            String url = "jdbc:mysql://localhost:3306/pptry?serverTimezone=GMT%2B8" +
                    "&&useSSL=FALSE&&allowPublicKeyRetrieval=true";
            String user = "root";
            String password = "localhost";

            // 建立连接
            con = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * select
     */
    static void select(){
        String sql = "select * from user";
        PreparedStatement state = null;
        ResultSet rs = null;

        // 建立连接
        Connection con = getCon();

        try {
            // 预编译
            state = con.prepareStatement(sql);
            // 发起请求，结果存放在rs内
            rs = state.executeQuery();
            // 获取列数
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 释放资源，分3个try catch防止第一个close失败后影响到后面资源的释放
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

    /**
     * delect
     */
    static void delect(){
        String sql = "delete from user where id='" + 1 + "'";
        PreparedStatement state = null;
        int row;

        // 建立连接
        Connection con = getCon();
        try {
            // 预编译
            state = con.prepareStatement(sql);
            // 执行增删改操作，返回受影响的行数
            row = state.executeUpdate();
            System.out.println("受影响的行数: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 释放资源，分2个try catch防止第一个close失败后影响到后面资源的释放
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

    /**
     * update
     */
    static void update(){
        PreparedStatement state = null;
        String sql = "update user set username='" + "pengpeng" + "' where username='" + "pp" + "'";
        int row;

        // 建立连接
        Connection con = getCon();
        try {
            // 预编译
            state = con.prepareStatement(sql);
            // 执行增删改操作，返回收影响的行数
            row = state.executeUpdate();
            System.out.println("resutl: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 释放资源，分2个try catch防止第一个close失败后影响到后面资源的释放
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

    /**
     * insert
     */
    static void insert(){
        String sql = "insert into user values(null,'pp',123);";
        PreparedStatement state = null;
        int row;

        // 建立连接
        Connection con = getCon();
        try {
            // 预编译
            state = con.prepareStatement(sql);
            // 执行增删改操作，返回收影响的行数
            row = state.executeUpdate();
            System.out.println("result1:" + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 释放资源，分3个try catch防止第一个close失败后影响到后面资源的释放
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
