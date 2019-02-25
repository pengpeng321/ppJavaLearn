package ppdatabase;

import java.sql.*;

/**
 * 环境配置说明：需要安装mysql、下载 Connector/J、idea配置加载依赖包
 * mysql下载安装版本的会自动下载 Connector/J ，在目录C:\Program Files (x86)\MySQL 下
 * mysql下载解压版本的需要自己手动下载 Connector/J ，将里面的 jar 拷贝出来 即可
 *
 * 步骤：
 * // 连接对象            Connection con = null;
 * // 查询对象            PreparedStatement ps = null;
 * // 结果集对象          ResultSet rs = null;（仅仅在查询语句时需要）
 *
 * // 加载数据库驱动      Class.forName("com.mysql.cj.jdbc.Driver");
 * // 获取连接            con = DriverManager.getConnection(url,user,password);
 * // 获取statement       state = con.prepareStatement(sql);
 * // 查询 输出结果       rs = state.executeQuery();
 *
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
                    "&useSSL=FALSE&allowPublicKeyRetrieval=true";
            String user = "root";
            String password = "root";

            // 建立连接
            con = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
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
            // 获取查询对象
            state = con.prepareStatement(sql);
            // 获取结果存放在rs内
            rs = state.executeQuery();

            // 获取列数
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    // getString("username")也可以使用表头名;
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 释放资源，分3个try catch防止第一个close失败后影响到后面资源的释放
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
            // 释放资源，分2个try catch防止第一个close失败后影响到后面资源的释放
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
