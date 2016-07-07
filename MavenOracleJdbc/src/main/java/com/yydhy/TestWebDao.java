package com.yydhy;

import java.sql.SQLException;

import org.junit.Test;

public class TestWebDao {
	@Test
	public void test01() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.showUser();
	}
	@Test
	public void test02() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.fenYe(1, 3);
	}
	@Test
	public void test022() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.fenYe(2, 3);
	}
	@Test
	public void alias_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.alias_query();
	}
	@Test
	public void view_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.view_query();
	}
	@Test
	public void link_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.link_query();
	}
	@Test
	public void concat_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.concat_query();
	}
	@Test
	public void innerJoin_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.innerJoin_query();
	}
	@Test
	public void leftJoin_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.leftJoin_query();
	}
	@Test
	public void rightJoin_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.rightJoin_query();
	}
	@Test
	public void fullJoin_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.fullJoin_query();
	}
	@Test
	public void union_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.union_query();
	}
	@Test
	public void union_query2() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.union_query2();
	}
	@Test
	public void avg_query() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.avg_query();
	}
	@Test
	public void acg_query2() throws SQLException {
		WebDao webDao=new WebDao();
		webDao.acg_query2();
	}
	public static void main(String[] args) throws SQLException {
		WebDao webDao=new WebDao();
		webDao.innerJoin_query();
	}
	
}
