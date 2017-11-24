package com.letspeer.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.letspeer.blog.dao.AttachmentDao;

import com.letspeer.blog.dao.impl.AttachmentDaoImpl;

import com.letspeer.blog.model.Attachment;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AttachmentDaoTest {

	static int attachmentId;

	@Test
	public void test1_AddAttachment() {
		try {

			Attachment attachment = new Attachment();
			attachment.setFileBath("xxxxxxxx");
			attachment.setBlogId(1);
			attachment.setDeleted(false);

			AttachmentDao dao = new AttachmentDaoImpl(
					"jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root", "12345678");

			int res = dao.addAttachment(attachment);
			attachmentId = res;
			assertTrue(res > -1);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test2_GetAttachmentById() {
		try {
			// System.out.println(userId);
			Attachment a = null;
			AttachmentDao dao = new AttachmentDaoImpl(
					"jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root", "12345678");
			a = dao.getAttachmentById(attachmentId);
			assertNotNull(a);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test3_GetAttachment() {

		try {

			AttachmentDao dao = new AttachmentDaoImpl(
					"jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root", "12345678");
			List<Attachment> ls = dao.getAttachments(0, 100);
			assertNotNull(ls);
			assertTrue(ls.size() >= 0);

		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	@Test
	public void test4_Update() {

		try {
			Attachment a = new Attachment();

			a.setId(attachmentId);
			a.setFileBath("zzzzz");
			a.setBlogId(1);

			a.setDeleted(false);

			AttachmentDao dao = new AttachmentDaoImpl(
					"jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false", "root", "12345678");

			Boolean result = dao.updateAttachment(a);
			assertFalse(result);

			a = dao.getAttachmentById(attachmentId);
			assertNotNull(a);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Test
	public void test5_deleteAttachment() {
		AttachmentDao dao = new AttachmentDaoImpl("jdbc:mysql://127.0.0.1:3306/blog_db?autoReconnect=true&useSSL=false",
				"root", "12345678");
		dao.deleteAttachment(attachmentId);
		Attachment a = dao.getAttachmentById(attachmentId);

		assertNull(a);

	}

}
