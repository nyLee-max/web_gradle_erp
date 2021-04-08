package web_gradle_erp.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import web_gradle_erp.dto.Title;
import web_gradle_erp.util.JdbcUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoImplTest {
	private static Connection con;
	private TitleDaoImpl dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		con = JdbcUtil.getConnection();
	}


	@Before
	public void setUp() throws Exception {
		dao = TitleDaoImpl.getInstance();
		dao.setCon(con);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}


	@Test
	public void test04SelectTitleByAll() {
		System.out.printf("%s()%n", "testSelectTitleByAll");
		List<Title> titleList = dao.selectTitleByAll();
		Assert.assertNotNull(titleList);
		
		for(Title t: titleList) {
			System.out.println(t);
		}
	}

	@Test
	public void test05SelectTitleByNo() {
		System.out.printf("%s()%n", "testSelectTitleByNo");
		Title title = new Title(5);
		Title searchTitle = dao.selectTitleByNo(title);
		Assert.assertNotNull(searchTitle);
		System.out.println(searchTitle);
	}

	@Test
	public void test01InsertTitle() {
		System.out.printf("%s()%n","testInsertTitle");
		Title newTitle = new Title(6, "인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test02UpdateTitle() {
		System.out.printf("%s()%n", "testUpdateTitle");
		Title newTitle = new Title(6, "계약직");
		int res = dao.updateTitle(newTitle);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test03DeleteTitle() {
		System.out.printf("%s()%n","testDeleteTitle");
		Title newTitle = new Title(6);
		int res = dao.deleteTitle(newTitle);
		dao.selectTitleByAll().stream().forEach(System.out::println);
	}

}
