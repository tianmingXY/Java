package com.yydhy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * �ù��߿�������mysql��SQLServer��2005 2000��2008����Oracle���ݿ� ������ʹ�����ݿ�ѡ����Ӧ�����ݿ�����
 * Ĭ��ʹ��Oracle���ݿ����� ��main���Է������Բ��������Ƿ���ȷ
 * 
 * @author Administrator
 *
 */
public class DBUtil {
	// MYSQL ���ݿ�����������Ϣ
	// private static String driverclass = "com.mysql.jdbc.Driver";
	// private static String url =
	// "jdbc:mysql://localhost:3306/���ݿ�����?characterEncoding=utf8";
	// //characterEncoding=utf8��ֹ��������
	// private static String user = "root";
	// private static String password = "root";

	// sqlserver ���ݿ�����������Ϣ
	// private static String driverclass
	// ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// private static String url =
	// "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_movie";
	// private static String user = "sa";
	// private static String password = "123456";

	// Oracle ���ݿ�������Ϣ����
	private static String driverclass = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1522:orcl01";
	private static String user = "system";
	private static String password = "123456";
	// 1.��������

	static {
		try {
			Class.forName(driverclass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("������������!", e);
		}
	}
	// 2.��������

	/*
	 * ��ζ���һ���������ӵķ��� ����ֵ����:�Ƿ����������� ����У���������ͼ�Ϊ����ֵ���� �����б�:�������Ƿ��в�ȷ�������ݲ�������
	 * ����м�Ϊ����
	 * 
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	// 3.�ر�����
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("�ر����Ӵ���!", e);
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}
