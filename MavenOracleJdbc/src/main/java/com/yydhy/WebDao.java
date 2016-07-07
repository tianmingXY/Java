package com.yydhy;

import java.sql.*;

public class WebDao {
	private static Connection conn;
	private PreparedStatement pstat;
	static {// 静态代码块加载数据库连接
		try {
			conn = DBUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 简单查询表中数据
	 * 
	 * @throws SQLException
	 */
	public void showUser() throws SQLException {
		try {
			String sql = "select * from Websites";
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int alexa = rs.getInt("alexa");
				String name = rs.getString("name");
				String url = rs.getString("url");
				String country = rs.getString("country");
				System.out.println("id:" + id + "\t name:" + name + "\t url:" + url + "\t country:" + country
						+ "\t alexa:" + alexa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 简单查询表中数据
	 * 
	 * @throws SQLException
	 */
	public void alias_query() throws SQLException {
		try {
			String sql = "select id as WebsitesId,name as Websitesname  from Websites";
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("WebsitesId");
				String name = rs.getString("Websitesname");
				System.out.println("id:" + id + "\t name:" + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 我们把四个列（name、url、alexa 和 country）结合在一起，并创建一个名为 "site_info" 的别名
	 * 
	 * @throws SQLException
	 */
	public void concat_query() throws SQLException {
		try {
			String sql = "SELECT name, concat(url,country) AS site_info FROM Websites ";
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String site_info = rs.getString("site_info");
				System.out.println("name:" + name + "\t site_info:" + site_info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 两表关联查询
	 * 
	 * @throws SQLException
	 */
	public void link_query() throws SQLException {
		try {
			// String sql = "SELECT w.name as wname , w.url as wurl , a.count as
			// acournt , a.date1 as adate "
			// + "FROM Websites w, access_log a "
			// + "WHERE a.site_id=w.id and w.name='菜鸟教程'";
			String sql = "select w.id as wid,w.name as wname , w.url as wurl, u.count as acount, u.date1"
					+ " as adate from Websites  w, access_log  u where w.id = u.site_id  ";
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				String wname = rs.getString("wname");
				String acournt = rs.getString("acount");
				String wurl = rs.getString("wurl");
				String adate = rs.getString("adate");
				System.out
						.println("wname:" + wname + "\t acournt:" + acournt + "\t wurl:" + wurl + "\t adate:" + adate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 视图查询
	 * 
	 * @throws SQLException
	 */
	public void view_query() throws SQLException {
		try {
			// create view testView as
			// select w.id as wid,w.name as wname , w.url as wurl, u.count as
			// acount, u.date1
			// as adate from Websites w, access_log u where w.id = u.site_id ;
			// String sql = "select * from testview";
			String sql = "select * from testview where acount>100";
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				String wname = rs.getString("wname");
				String acournt = rs.getString("acount");
				String wurl = rs.getString("wurl");
				String adate = rs.getString("adate");
				System.out
						.println("wname:" + wname + "\t acournt:" + acournt + "\t wurl:" + wurl + "\t adate:" + adate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 两表关联查询 注释：INNER JOIN 与 JOIN 是相同的。
	 * 
	 * @throws SQLException
	 */
	public void innerJoin_query() throws SQLException {
		try {
			String sql = "SELECT ROWNUM,Websites.url as wurl, Websites.name as wname, "
					+ "access_log.count as acount, access_log.date1 as  adate "
					+ " FROM Websites INNER JOIN access_log ON Websites.id=access_log.site_id ";
			String sql1 = "select ROWNUM,w.url as wurl,w.name  wname , a.count as acount,a.date1 "
					+ "as adate from access_log a  inner join  Websites w on w.id = a.site_id ";
			String sql2 = "select ROWNUM,w.url as wurl,w.name  wname , a.count as acount,a.date1 "
					+ "as adate from  Websites w inner join access_log a   on w.id = a.site_id ";
			String sql3 = "select ROWNUM,w.url as wurl,w.name  wname , a.count as acount,a.date1 "
					+ "as adate from  Websites w  join access_log a   on w.id = a.site_id ";
			pstat = conn.prepareStatement(sql3);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("ROWNUM");
				String wname = rs.getString("wname");
				String acournt = rs.getString("acount");
				String wurl = rs.getString("wurl");
				String adate = rs.getString("adate");
				System.out.println(
						rownum + " wname:" + wname + "\t acournt:" + acournt + "\t wurl:" + wurl + "\t adate:" + adate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 左连接 注释：在某些数据库中，LEFT JOIN 称为 LEFT OUTER JOIN。 注释：LEFT JOIN
	 * 关键字从左表（Websites）返回所有的行，即使右表（access_log）中没有匹配。
	 * 
	 * @throws SQLException
	 */
	public void leftJoin_query() throws SQLException {
		try {
			String sql3 = "select ROWNUM,w.url as wurl,w.name  wname , a.count as acount,"
					+ "a.date1 as adate from  Websites w left join access_log a   on w.id = a.site_id ";
			pstat = conn.prepareStatement(sql3);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("ROWNUM");
				String wname = rs.getString("wname");
				String acournt = rs.getString("acount");
				String wurl = rs.getString("wurl");
				String adate = rs.getString("adate");
				System.out.println(
						rownum + " wname:" + wname + "\t acournt:" + acournt + "\t wurl:" + wurl + "\t adate:" + adate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 1 wname:Google acournt:45 wurl: https://www.google.cm/
		// adate:2016-05-10
		// 2 wname:菜鸟教程 acournt:100 wurl:http://www.runoob.com/ adate:2016-05-13
		// 3 wname:Google acournt:230 wurl: https://www.google.cm/
		// adate:2016-05-14
		// 4 wname:淘宝 acournt:10 wurl:https://www.taobao.com/ adate:2016-05-14
		// 5 wname:Facebook acournt:205 wurl: https://www.facebook.com/
		// adate:2016-05-14
		// 6 wname:微博 acournt:13 wurl: http://weibo.com/ adate:2016-05-15
		// 7 wname:菜鸟教程 acournt:220 wurl:http://www.runoob.com/ adate:2016-05-15
		// 8 wname:Facebook acournt:545 wurl: https://www.facebook.com/
		// adate:2016-05-16
		// 9 wname:菜鸟教程 acournt:201 wurl:http://www.runoob.com/ adate:2016-05-17
		// 10 wname:天明源代码 acournt:null wurl:www.yydhy.com adate:null

	}

	/**
	 * 右连接 注释：在某些数据库中，LEFT JOIN 称为 LEFT OUTER JOIN。
	 * 
	 * @throws SQLException
	 */
	public void rightJoin_query() throws SQLException {
		try {
			String sql3 = "select ROWNUM,w.url as wurl,w.name  wname , a.count as acount,a.date1 as adate"
					+ " from  Websites w right join access_log a   on w.id = a.site_id ";
			pstat = conn.prepareStatement(sql3);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("ROWNUM");
				String wname = rs.getString("wname");
				String acournt = rs.getString("acount");
				String wurl = rs.getString("wurl");
				String adate = rs.getString("adate");
				System.out.println(
						rownum + " wname:" + wname + "\t acournt:" + acournt + "\t wurl:" + wurl + "\t adate:" + adate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1 wname:Facebook acournt:545 wurl: https://www.facebook.com/
		// adate:2016-05-16
		// 2 wname:Facebook acournt:205 wurl: https://www.facebook.com/
		// adate:2016-05-14
		// 3 wname:Google acournt:230 wurl: https://www.google.cm/
		// adate:2016-05-14
		// 4 wname:Google acournt:45 wurl: https://www.google.cm/
		// adate:2016-05-10
		// 5 wname:淘宝 acournt:10 wurl:https://www.taobao.com/ adate:2016-05-14
		// 6 wname:菜鸟教程 acournt:201 wurl:http://www.runoob.com/ adate:2016-05-17
		// 7 wname:菜鸟教程 acournt:220 wurl:http://www.runoob.com/ adate:2016-05-15
		// 8 wname:菜鸟教程 acournt:100 wurl:http://www.runoob.com/ adate:2016-05-13
		// 9 wname:微博 acournt:13 wurl: http://weibo.com/ adate:2016-05-15
		// 10 wname:null acournt:45 wurl:null adate:2016-05-10

	}

	/**
	 * SQL FULL OUTER JOIN 关键字 FULL OUTER JOIN
	 * 关键字只要左表（table1）和右表（table2）其中一个表中存在匹配，则返回行. FULL OUTER JOIN 关键字结合了 LEFT
	 * JOIN 和 RIGHT JOIN 的结果。 注释：FULL OUTER JOIN
	 * 关键字返回左表（Websites）和右表（access_log）中所有的行。如果 "Websites" 表中的行在 "access_log"
	 * 中没有匹配或者 "access_log" 表中的行在 "Websites" 表中没有匹配，也会列出这些行。
	 * 
	 * @throws SQLException
	 */
	public void fullJoin_query() throws SQLException {
		try {
			String sql3 = "select ROWNUM,w.url as wurl,w.name  wname , a.count as acount,a.date1 as adate"
					+ " from  Websites w full join access_log a   on w.id = a.site_id ";
			pstat = conn.prepareStatement(sql3);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("ROWNUM");
				String wname = rs.getString("wname");
				String acournt = rs.getString("acount");
				String wurl = rs.getString("wurl");
				String adate = rs.getString("adate");
				System.out.println(
						rownum + " wname:" + wname + "\t acournt:" + acournt + "\t wurl:" + wurl + "\t adate:" + adate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1 wname:Google acournt:45 wurl: https://www.google.cm/
		// adate:2016-05-10
		// 2 wname:菜鸟教程 acournt:100 wurl:http://www.runoob.com/ adate:2016-05-13
		// 3 wname:Google acournt:230 wurl: https://www.google.cm/
		// adate:2016-05-14
		// 4 wname:淘宝 acournt:10 wurl:https://www.taobao.com/ adate:2016-05-14
		// 5 wname:Facebook acournt:205 wurl: https://www.facebook.com/
		// adate:2016-05-14
		// 6 wname:微博 acournt:13 wurl: http://weibo.com/ adate:2016-05-15
		// 7 wname:菜鸟教程 acournt:220 wurl:http://www.runoob.com/ adate:2016-05-15
		// 8 wname:Facebook acournt:545 wurl: https://www.facebook.com/
		// adate:2016-05-16
		// 9 wname:菜鸟教程 acournt:201 wurl:http://www.runoob.com/ adate:2016-05-17
		// 10 wname:null acournt:45 wurl:null adate:2016-05-10
		// 11 wname:天明源代码 acournt:null wurl:www.yydhy.com adate:null

	}

	/**
	 * 分页查询Oracle表中数据库
	 * 
	 * @param page
	 *            页数 （第一页 1 第二页 2 ）
	 * @param pagesize
	 *            （每页显示记录数）
	 * @throws SQLException
	 */
	public void fenYe(int page, int pagesize) throws SQLException {
		try {

			int end = page * pagesize;
			int start = (page - 1) * pagesize + 1;
			String sql = " select * from  (select A.* , ROWNUM as RN from  (select * from Websites order by id desc) A where ROWNUM <= "
					+ end + " ) where RN>=" + start;
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int alexa = rs.getInt("alexa");
				String name = rs.getString("name");
				String url = rs.getString("url");
				String country = rs.getString("country");
				System.err.println("id:" + id + "\t name:" + name + "\t url:" + url + "\t country:" + country
						+ "\t alexa:" + alexa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * SQL UNION 操作符合并两个或多个 SELECT 语句的结果。 UNION 操作符用于合并两个或多个 SELECT 语句的结果集。
	 * 请注意，UNION 内部的每个 SELECT 语句必须拥有相同数量的列。列也必须拥有相似的数据类型。 同时，每个 SELECT
	 * 语句中的列的顺序必须相同。 注释：默认地，UNION 操作符选取不同的值。如果允许重复的值，请使用 UNION ALL。
	 * 
	 * @throws SQLException
	 */
	public void union_query() throws SQLException {
		try {
			String sql3 = "SELECT country FROM Websites UNION  SELECT country FROM apps ORDER BY country";// 允许重复
			String sql31 = "SELECT country FROM Websites UNION  ALL SELECT country FROM apps ORDER BY country";// 允许重复
			pstat = conn.prepareStatement(sql31);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				String country = rs.getString("country");
				System.out.println(" country:" + country);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void union_query2() throws SQLException {
		try {
			String sql31 = "SELECT country, url as names  FROM Websites WHERE country='CN' UNION ALL SELECT "
					+ "country,url as names FROM apps WHERE country='CN' ORDER BY country";
			pstat = conn.prepareStatement(sql31);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				String country = rs.getString("country");
				String names = rs.getString("names");
				System.out.println(" country:" + country + "   names=" + names);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 求平均值
	 * 
	 * @throws SQLException
	 */
	public void avg_query() throws SQLException {
		try {
			String sql = "SELECT AVG(count) AS CountAverage FROM access_log ";
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			rs.next();//游标下移动一次
			int CountAverage = rs.getInt("CountAverage");
			System.out.println(" CountAverage:" + CountAverage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有统计高于平均值的信息
	 * @throws SQLException
	 */
	public void acg_query2() throws SQLException {
		try {
			String sql31 = "SELECT site_id, count FROM access_log WHERE count > (SELECT AVG(count) FROM access_log)";
			//下面的 SQL 语句计算 "access_log" 表中 "site_id"=3 的总访问量：
			//SELECT COUNT(count) AS nums FROM access_log WHERE site_id=3;
			//SQL 语句计算 "access_log" 表中总记录数：
			//SELECT COUNT(*) AS nums FROM access_log;
			//SQL 语句计算 "access_log" 表中不同 site_id 的记录数：
			//SELECT COUNT(DISTINCT site_id) AS nums FROM access_log;
			pstat = conn.prepareStatement(sql31);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				int site_id = rs.getInt("site_id");
				int count = rs.getInt("count");
				System.out.println(" site_id:" + site_id + "   count=" + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
