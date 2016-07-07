package com.yydhy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 该工具可以连接mysql、SQLServer（2005 2000、2008）、Oracle数据库 根据所使用数据库选择相应的数据库驱动
 * 默认使用Oracle数据库连接 有main测试方法可以测试连接是否正确
 * 
 * @author Administrator
 *
 */
public class DBUtil {
	// MYSQL 数据库连接驱动信息
	// private static String driverclass = "com.mysql.jdbc.Driver";
	// private static String url =
	// "jdbc:mysql://localhost:3306/数据库名称?characterEncoding=utf8";
	// //characterEncoding=utf8防止中文乱码
	// private static String user = "root";
	// private static String password = "root";

	// sqlserver 数据库连接驱动信息
	// private static String driverclass
	// ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// private static String url =
	// "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_movie";
	// private static String user = "sa";
	// private static String password = "123456";

	// Oracle 数据库连接信息配置
	private static String driverclass = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1522:orcl01";
	private static String user = "system";
	private static String password = "123456";
	// 1.加载驱动

	static {
		try {
			Class.forName(driverclass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("加载驱动错误!", e);
		}
	}
	// 2.创建连接

	/*
	 * 如何定义一个创建连接的方法 返回值类型:是否有运算结果。 如果有，结果的类型即为返回值类型 参数列表:功能中是否有不确定的数据参与运算
	 * 如果有即为参数
	 * 
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	// 3.关闭连接
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("关闭连接错误!", e);
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}
