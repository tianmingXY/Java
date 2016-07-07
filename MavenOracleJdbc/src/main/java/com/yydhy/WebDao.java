package com.yydhy;

import java.sql.*;

public class WebDao {
	private static Connection conn;
	private PreparedStatement pstat;
	static {// ��̬�����������ݿ�����
		try {
			conn = DBUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �򵥲�ѯ��������
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
	 * �򵥲�ѯ��������
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
	 * ���ǰ��ĸ��У�name��url��alexa �� country�������һ�𣬲�����һ����Ϊ "site_info" �ı���
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
	 * ����������ѯ
	 * 
	 * @throws SQLException
	 */
	public void link_query() throws SQLException {
		try {
			// String sql = "SELECT w.name as wname , w.url as wurl , a.count as
			// acournt , a.date1 as adate "
			// + "FROM Websites w, access_log a "
			// + "WHERE a.site_id=w.id and w.name='����̳�'";
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
	 * ��ͼ��ѯ
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
	 * ����������ѯ ע�ͣ�INNER JOIN �� JOIN ����ͬ�ġ�
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
	 * ������ ע�ͣ���ĳЩ���ݿ��У�LEFT JOIN ��Ϊ LEFT OUTER JOIN�� ע�ͣ�LEFT JOIN
	 * �ؼ��ִ������Websites���������е��У���ʹ�ұ���access_log����û��ƥ�䡣
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
		// 2 wname:����̳� acournt:100 wurl:http://www.runoob.com/ adate:2016-05-13
		// 3 wname:Google acournt:230 wurl: https://www.google.cm/
		// adate:2016-05-14
		// 4 wname:�Ա� acournt:10 wurl:https://www.taobao.com/ adate:2016-05-14
		// 5 wname:Facebook acournt:205 wurl: https://www.facebook.com/
		// adate:2016-05-14
		// 6 wname:΢�� acournt:13 wurl: http://weibo.com/ adate:2016-05-15
		// 7 wname:����̳� acournt:220 wurl:http://www.runoob.com/ adate:2016-05-15
		// 8 wname:Facebook acournt:545 wurl: https://www.facebook.com/
		// adate:2016-05-16
		// 9 wname:����̳� acournt:201 wurl:http://www.runoob.com/ adate:2016-05-17
		// 10 wname:����Դ���� acournt:null wurl:www.yydhy.com adate:null

	}

	/**
	 * ������ ע�ͣ���ĳЩ���ݿ��У�LEFT JOIN ��Ϊ LEFT OUTER JOIN��
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
		// 5 wname:�Ա� acournt:10 wurl:https://www.taobao.com/ adate:2016-05-14
		// 6 wname:����̳� acournt:201 wurl:http://www.runoob.com/ adate:2016-05-17
		// 7 wname:����̳� acournt:220 wurl:http://www.runoob.com/ adate:2016-05-15
		// 8 wname:����̳� acournt:100 wurl:http://www.runoob.com/ adate:2016-05-13
		// 9 wname:΢�� acournt:13 wurl: http://weibo.com/ adate:2016-05-15
		// 10 wname:null acournt:45 wurl:null adate:2016-05-10

	}

	/**
	 * SQL FULL OUTER JOIN �ؼ��� FULL OUTER JOIN
	 * �ؼ���ֻҪ�����table1�����ұ���table2������һ�����д���ƥ�䣬�򷵻���. FULL OUTER JOIN �ؼ��ֽ���� LEFT
	 * JOIN �� RIGHT JOIN �Ľ���� ע�ͣ�FULL OUTER JOIN
	 * �ؼ��ַ��������Websites�����ұ���access_log�������е��С���� "Websites" ���е����� "access_log"
	 * ��û��ƥ����� "access_log" ���е����� "Websites" ����û��ƥ�䣬Ҳ���г���Щ�С�
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
		// 2 wname:����̳� acournt:100 wurl:http://www.runoob.com/ adate:2016-05-13
		// 3 wname:Google acournt:230 wurl: https://www.google.cm/
		// adate:2016-05-14
		// 4 wname:�Ա� acournt:10 wurl:https://www.taobao.com/ adate:2016-05-14
		// 5 wname:Facebook acournt:205 wurl: https://www.facebook.com/
		// adate:2016-05-14
		// 6 wname:΢�� acournt:13 wurl: http://weibo.com/ adate:2016-05-15
		// 7 wname:����̳� acournt:220 wurl:http://www.runoob.com/ adate:2016-05-15
		// 8 wname:Facebook acournt:545 wurl: https://www.facebook.com/
		// adate:2016-05-16
		// 9 wname:����̳� acournt:201 wurl:http://www.runoob.com/ adate:2016-05-17
		// 10 wname:null acournt:45 wurl:null adate:2016-05-10
		// 11 wname:����Դ���� acournt:null wurl:www.yydhy.com adate:null

	}

	/**
	 * ��ҳ��ѯOracle�������ݿ�
	 * 
	 * @param page
	 *            ҳ�� ����һҳ 1 �ڶ�ҳ 2 ��
	 * @param pagesize
	 *            ��ÿҳ��ʾ��¼����
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
	 * SQL UNION �������ϲ��������� SELECT ���Ľ���� UNION ���������ںϲ��������� SELECT ���Ľ������
	 * ��ע�⣬UNION �ڲ���ÿ�� SELECT ������ӵ����ͬ�������С���Ҳ����ӵ�����Ƶ��������͡� ͬʱ��ÿ�� SELECT
	 * ����е��е�˳�������ͬ�� ע�ͣ�Ĭ�ϵأ�UNION ������ѡȡ��ͬ��ֵ����������ظ���ֵ����ʹ�� UNION ALL��
	 * 
	 * @throws SQLException
	 */
	public void union_query() throws SQLException {
		try {
			String sql3 = "SELECT country FROM Websites UNION  SELECT country FROM apps ORDER BY country";// �����ظ�
			String sql31 = "SELECT country FROM Websites UNION  ALL SELECT country FROM apps ORDER BY country";// �����ظ�
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
	 * ��ƽ��ֵ
	 * 
	 * @throws SQLException
	 */
	public void avg_query() throws SQLException {
		try {
			String sql = "SELECT AVG(count) AS CountAverage FROM access_log ";
			pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			rs.next();//�α����ƶ�һ��
			int CountAverage = rs.getInt("CountAverage");
			System.out.println(" CountAverage:" + CountAverage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ����ͳ�Ƹ���ƽ��ֵ����Ϣ
	 * @throws SQLException
	 */
	public void acg_query2() throws SQLException {
		try {
			String sql31 = "SELECT site_id, count FROM access_log WHERE count > (SELECT AVG(count) FROM access_log)";
			//����� SQL ������ "access_log" ���� "site_id"=3 ���ܷ�������
			//SELECT COUNT(count) AS nums FROM access_log WHERE site_id=3;
			//SQL ������ "access_log" �����ܼ�¼����
			//SELECT COUNT(*) AS nums FROM access_log;
			//SQL ������ "access_log" ���в�ͬ site_id �ļ�¼����
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