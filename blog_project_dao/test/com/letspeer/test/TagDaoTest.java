package com.letspeer.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.letspeer.blog.dao.TagDao;
import com.letspeer.blog.dao.impl.TagDaoImpl;
import com.letspeer.blog.model.Tag;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

//test


public class TagDaoTest {

	static int tagId;

	@Test
	public void test1_AddTag() {
		try {

			Tag tag = new Tag();
			tag.setTagName("Abdallah");
			tag.setDeleted(false);

			TagDao dao = new TagDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root",
					"12345678");

			int res = dao.addTag(tag);
			tagId = res;
			assertTrue(res > -1);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test2_GetTagById() {
		try {

			Tag t = null;
			TagDao dao = new TagDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root",
					"12345678");
			t = dao.getTagById(tagId);
			assertNotNull(t);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test3_GetTag() {

		try {

			TagDao dao = new TagDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root",
					"12345678");
			List<Tag> ls = dao.getTags(0, 100);
			assertNotNull(ls);
			assertTrue(ls.size() >= 0);

		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	@Test
	public void test4_Update() {

		try {
			Tag t = new Tag();

			t.setId(tagId);
			t.setTagName("Jaber");

			t.setDeleted(false);

			TagDao dao = new TagDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root",
					"12345678");

			Boolean result = dao.updateTag(t);
			assertFalse(result);

			t = dao.getTagById(tagId);
			assertNotNull(t);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test5_deleteTag() {
		TagDao dao = new TagDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root",
				"12345678");
		dao.deleteTag(tagId);
		Tag t = dao.getTagById(tagId);

		assertNull(t);

	}

}
