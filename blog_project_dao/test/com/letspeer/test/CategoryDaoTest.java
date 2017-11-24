package com.letspeer.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.letspeer.blog.dao.CategoryDao;

import com.letspeer.blog.dao.impl.CategoryDaoImpl;

import com.letspeer.blog.model.Category;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDaoTest {

	static int categoryId;

	@Test
	public void test1_AddCategory() {
		try {

			Category category = new Category();
			category.setCategoryName("news");
			category.setDeleted(false);

			CategoryDao dao = new CategoryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false",
					"root", "12345678");

			int res = dao.addCategory(category);
			// System.out.println(res);
			categoryId = res;
			assertTrue(res > -1);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test2_GetCategoryById() {
		try {
			// System.out.println(userId);
			Category c = null;
			CategoryDao dao = new CategoryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false",
					"root", "12345678");
			c = dao.getCategoryById(categoryId);
			assertNotNull(c);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test3_GetCategory() {

		try {

			CategoryDao dao = new CategoryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false",
					"root", "12345678");
			List<Category> ls = dao.getCategories(0, 100);
			assertNotNull(ls);
			assertTrue(ls.size() >= 0);

		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	@Test
	public void test4_Update() {

		try {
			Category c = new Category();

			c.setId(categoryId);
			c.setCategoryName("sport");

			c.setDeleted(false);

			CategoryDao dao = new CategoryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false",
					"root", "12345678");

			Boolean result = dao.updateCategory(c);
			assertFalse(result);

			c = dao.getCategoryById(categoryId);
			assertNotNull(c);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test5_deleteCategory() {
		CategoryDao dao = new CategoryDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false",
				"root", "12345678");
		dao.deleteCategory(categoryId);
		Category c = dao.getCategoryById(categoryId);

		assertNull(c);

	}

}
