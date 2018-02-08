package com.letspeer.blog.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.letspeer.blog.dao.BlogEntryDao;

import com.letspeer.blog.model.BlogEntry;
import com.letspeer.blog.model.BlogEntryDetails;
import com.letspeer.blog.model.Tag;

public class BlogEntryDaoImpl implements BlogEntryDao {

	private String dbUrl;
	private String dbUser;
	private String dbUserPwd;
	private Connection connection;

	public BlogEntryDaoImpl(String dbUrl, String dbUser, String dbUserPwd) {
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbUserPwd = dbUserPwd;
	}

	@Override
	public void connectDb() {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(dbUrl, dbUser, dbUserPwd);
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public void disconnectDb() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Integer addBlogEntry(BlogEntry blogEntry) {
		ResultSet rs = null;
		try {
			String stmt = "INSERT INTO blog_entries "
					+ "(`blog_title`,`blog_body`,`cat_id`,`user_id`,`created_time`,`deleted`) VALUES" + "(?,?,?,?,?,?)";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, blogEntry.getBlogTitle());
			pStmt.setString(2, blogEntry.getBlogBody());
			pStmt.setInt(3, blogEntry.getCatId());
			pStmt.setInt(4, blogEntry.getUserId());
			if(blogEntry.getCreatedTime()!=null) {
				pStmt.setLong(5, blogEntry.getCreatedTime());
			}else {
				pStmt.setNull(5, Types.BIGINT);
			}

			if (blogEntry.getDeleted()) {
				pStmt.setString(6, "1");
			} else {
				pStmt.setString(6, "0");
			}
			pStmt.execute();
			int autoIncKeyFromApi = -1;

			rs = pStmt.getGeneratedKeys();

			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
				return autoIncKeyFromApi;
			} else {
				return -1;
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			disconnectDb();
		}

		return -1;
	}

	@Override
	public BlogEntry getBlogEntryById(Integer id) {
		ResultSet result = null;
		try {
			String query = "SELECT * FROM blog_entries WHERE id=? AND deleted='0'";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			result = pStmt.executeQuery();
			if (result.next()) {
				BlogEntry blogEntry = new BlogEntry();
				blogEntry.setBlogTitle(result.getString("blog_title"));
				blogEntry.setDeleted(result.getString("deleted").equals('0') ? false : true);
				blogEntry.setBlogBody(result.getString("blog_body"));
				blogEntry.setCatId(result.getInt("cat_id"));
				blogEntry.setId(result.getInt("id"));
				blogEntry.setUserId(result.getInt("user_id"));
				blogEntry.setCreatedTime(result.getLong("created_time"));
				return blogEntry;

			} else {
				return null;
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			disconnectDb();
		}

		return null;
	}

	@Override
	public List<BlogEntry> getBlogEntries(Integer start, Integer count) {
		ResultSet result = null;
		List<BlogEntry> ls = new ArrayList<>();
		try {
			String query = "SELECT * FROM blog_entries WHERE deleted='0' LIMIT ?,?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, start);
			pStmt.setInt(2, count);
			result = pStmt.executeQuery();
			while (result.next()) {
				BlogEntry blogEntry = new BlogEntry();
				blogEntry.setBlogTitle(result.getString("blog_title"));
				blogEntry.setDeleted(result.getString("deleted").equals('0') ? false : true);
				blogEntry.setBlogBody(result.getString("blog_body"));
				blogEntry.setCatId(result.getInt("cat_id"));
				blogEntry.setId(result.getInt("id"));
				blogEntry.setUserId(result.getInt("user_id"));
				blogEntry.setCreatedTime(result.getLong("created_time"));
				ls.add(blogEntry);
			}

			return ls;

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			disconnectDb();
		}

		return null;
	}

	@Override
	public List<BlogEntry> getBlogEntries() {

		return getBlogEntries(0, 1000);
	}

	@Override
	public Boolean updateBlogEntry(BlogEntry blogEntry) {
		try {

			String stmt = "UPDATE blog_entries SET "
					+ "blog_title=? ,blog_body=?,cat_id=? ,user_id=? ,created_time=?,deleted=? " + "WHERE id=?";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(stmt);
			pStmt.setString(1, blogEntry.getBlogTitle());
			pStmt.setString(2, blogEntry.getBlogBody());
			pStmt.setInt(3, blogEntry.getCatId());
			pStmt.setInt(4, blogEntry.getUserId());
			
			if(blogEntry.getCreatedTime()!=null) {
				pStmt.setLong(5, blogEntry.getCreatedTime());
			}else {
				pStmt.setNull(5, Types.BIGINT);
			}
			
			if (blogEntry.getDeleted()) {
				pStmt.setString(6, "1");
			} else {
				pStmt.setString(6, "0");
			}
			pStmt.setInt(7, blogEntry.getId());
			Boolean result = pStmt.execute();
			return result;
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			disconnectDb();
		}

		return false;
	}

	@Override
	public void deleteBlogEntry(Integer id) {
		BlogEntry be = getBlogEntryById(id);
		be.setDeleted(true);
		updateBlogEntry(be);

	}

	@Override
	public BlogEntryDetails getBlogEntryDetailes(Integer id) {
		ResultSet result = null;
		BlogEntryDetails bed=null;
		try {
			String query = "SELECT be.id,  " + 
		            "       be.blog_title, " +
					"       be.blog_body,  " + 
					"       be.cat_id,  " + 
					"       be.user_id,  " + 
					"       be.deleted,  " + 
					"       be.created_time,  " + 
					"       c.category_name,  " + 
					"       u.first_name,  " + 
					"       u.last_name,  " + 
					"       u.profile_picture,  " + 
					"       Count(com.id)                 AS comments_count,  " + 
					"       Group_concat(outer1.tag_id)   tag_ids,  " + 
					"       Group_concat(outer1.tag_name) tag_names  " + 
					"FROM   blog_entries AS be  " + 
					"       LEFT OUTER JOIN comments com  " + 
					"                    ON com.blog_id = be.id  " + 
					"                       AND be.deleted = '0'  " + 
					"       LEFT OUTER JOIN (SELECT ref.blog_id,  " + 
					"                               ref.tag_id,  " + 
					"                               tg.tag_name  " + 
					"                        FROM   blog_entries_tags_ref ref,  " + 
					"                               tags tg,  " + 
					"                               blog_entries bee  " + 
					"                        WHERE  bee.id = ref.blog_id  " + 
					"                               AND tg.id = ref.tag_id) AS outer1  " + 
					"                    ON outer1.blog_id = be.id  " + 
					"       INNER JOIN categories c  " + 
					"               ON be.cat_id = c.id  " + 
					"       INNER JOIN users u  " + 
					"               ON be.user_id = u.id  " + 
					"WHERE  be.id = ? " + 
					"GROUP  BY be.id;  ";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			
			result = pStmt.executeQuery();
			while (result.next()) {
				 bed = new BlogEntryDetails();
		         bed.setBlogBody(result.getString("blog_body"));
		         bed.setBlogTitle(result.getString("blog_title"));
		         bed.setCategoryName(result.getString("category_name"));
		         bed.setCatId(result.getInt("cat_id"));
		         bed.setCommentsCount(result.getInt("comments_count"));
		         bed.setCreatedTime(result.getLong("created_time"));
		         bed.setDeleted(result.getString("deleted").equals('0') ? false : true);
		         bed.setFirstName(result.getString("first_name"));
		         bed.setId(result.getInt("id"));
		         bed.setLastName(result.getString("last_name"));
		         bed.setProfilePicture(result.getString("profile_picture"));
		         String tagIds= result.getString("tag_ids") == null ? "":result.getString("tag_ids");
		         String tagNames = result.getString("tag_names") == null ? "":result.getString("tag_names");
		         List<Tag> ls = new ArrayList<Tag>() ; 
		         
		         if(tagIds!="" && tagNames!="") {
		        	 String[] lsIds = tagIds.split(",") ; 
		        	 String [] lsNames = tagNames.split(",") ; 
		        	 
//		        	 HashMap<Integer, String> ht = new HashMap<>() ; 
//		        	 
//		        	 for(int i = 0 ; i<lsIds.length; i++) {
//		        		 ht.put(Integer.parseInt(lsIds[i]), lsNames[i]) ; 
//		        	 }
//		        	 
//		        	 bed.setTags(ht);
		        	 
		        	
		        	 
		        	 for(int i=0; i<lsIds.length; i++) {
		        		 Tag tag = new Tag() ; 
		        		 tag.setId(Integer.parseInt(lsIds[i]));
		        		 tag.setTagName(lsNames[i]);
		        		 ls.add(tag) ; 
		        	 } 
		        	 	        	 
		         }
		         bed.setTags(ls);
		         
		         bed.setUserId(result.getInt("user_id"));
		         break;
		         
		         
			}

			return bed;

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			disconnectDb();
		}

		return bed;
	}

	@Override
	public List<BlogEntryDetails> getBlogByCategory(Integer id) {
		ResultSet result = null;
		
		List<BlogEntryDetails> blogs = new ArrayList<>();
		try {
			String query = "SELECT be.id,  " + 
		            "       be.blog_title, " +
					"       be.blog_body,  " + 
					"       be.cat_id,  " + 
					"       be.user_id,  " + 
					"       be.deleted,  " + 
					"       be.created_time,  " + 
					"       c.category_name,  " + 
					"       u.first_name,  " + 
					"       u.last_name,  " + 
					"       u.profile_picture,  " + 
					"       Count(com.id)                 AS comments_count,  " + 
					"       Group_concat(outer1.tag_id)   tag_ids,  " + 
					"       Group_concat(outer1.tag_name) tag_names  " + 
					"FROM   blog_entries AS be  " + 
					"       LEFT OUTER JOIN comments com  " + 
					"                    ON com.blog_id = be.id  " + 
					"                       AND be.deleted = '0'  " + 
					"       LEFT OUTER JOIN (SELECT ref.blog_id,  " + 
					"                               ref.tag_id,  " + 
					"                               tg.tag_name  " + 
					"                        FROM   blog_entries_tags_ref ref,  " + 
					"                               tags tg,  " + 
					"                               blog_entries bee  " + 
					"                        WHERE  bee.id = ref.blog_id  " + 
					"                               AND tg.id = ref.tag_id) AS outer1  " + 
					"                    ON outer1.blog_id = be.id  " + 
					"       INNER JOIN categories c  " + 
					"               ON be.cat_id = c.id  " + 
					"       INNER JOIN users u  " + 
					"               ON be.user_id = u.id  " + 
					"WHERE  be.cat_id = ? " + 
					"GROUP  BY be.id;  ";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			
			result = pStmt.executeQuery();
			while (result.next()) {
				 BlogEntryDetails bbc = new BlogEntryDetails();
		         bbc.setBlogBody(result.getString("blog_body"));
		         bbc.setBlogTitle(result.getString("blog_title"));
		         bbc.setCategoryName(result.getString("category_name"));
		         bbc.setCatId(result.getInt("cat_id"));
		         bbc.setCommentsCount(result.getInt("comments_count"));
		         bbc.setCreatedTime(result.getLong("created_time"));
		         bbc.setDeleted(result.getString("deleted").equals('0') ? false : true);
		         bbc.setFirstName(result.getString("first_name"));
		         bbc.setId(result.getInt("id"));
		         bbc.setLastName(result.getString("last_name"));
		         bbc.setProfilePicture(result.getString("profile_picture"));
		         String tagIds= result.getString("tag_ids") == null ? "":result.getString("tag_ids");
		         String tagNames = result.getString("tag_names") == null ? "":result.getString("tag_names");
		         List<Tag> ls = new ArrayList<Tag>() ; 
		         
		         if(tagIds!="" && tagNames!="") {
		        	 String[] lsIds = tagIds.split(",") ; 
		        	 String [] lsNames = tagNames.split(",") ; 
		        	         	 
		        	
		        	 
		        	 for(int i=0; i<lsIds.length; i++) {
		        		 Tag tag = new Tag() ; 
		        		 tag.setId(Integer.parseInt(lsIds[i]));
		        		 tag.setTagName(lsNames[i]);
		        		 ls.add(tag) ; 
		        	 } 
		        	 	        	 
		         }
		         bbc.setTags(ls);
		         
		         bbc.setUserId(result.getInt("user_id"));
		         blogs.add(bbc);
		         
		         
		         
			}

			return blogs;

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			disconnectDb();
		}

		return blogs;
	}
	
	@Override
	public List<BlogEntryDetails> getBlogByUser(Integer id) {
		ResultSet result = null;
		List<BlogEntryDetails> blogs = new ArrayList<>();
		try {
			String query = "SELECT be.id,  " + 
		            "       be.blog_title, " +
					"       be.blog_body,  " + 
					"       be.cat_id,  " + 
					"       be.user_id,  " + 
					"       be.deleted,  " + 
					"       be.created_time,  " + 
					"       c.category_name,  " + 
					"       u.first_name,  " + 
					"       u.last_name,  " + 
					"       u.profile_picture,  " + 
					"       Count(com.id)                 AS comments_count,  " + 
					"       Group_concat(outer1.tag_id)   tag_ids,  " + 
					"       Group_concat(outer1.tag_name) tag_names  " + 
					"FROM   blog_entries AS be  " + 
					"       LEFT OUTER JOIN comments com  " + 
					"                    ON com.blog_id = be.id  " + 
					"                       AND be.deleted = '0'  " + 
					"       LEFT OUTER JOIN (SELECT ref.blog_id,  " + 
					"                               ref.tag_id,  " + 
					"                               tg.tag_name  " + 
					"                        FROM   blog_entries_tags_ref ref,  " + 
					"                               tags tg,  " + 
					"                               blog_entries bee  " + 
					"                        WHERE  bee.id = ref.blog_id  " + 
					"                               AND tg.id = ref.tag_id) AS outer1  " + 
					"                    ON outer1.blog_id = be.id  " + 
					"       INNER JOIN categories c  " + 
					"               ON be.cat_id = c.id  " + 
					"       INNER JOIN users u  " + 
					"               ON be.user_id = u.id  " + 
					"WHERE  be.user_id = ? " + 
					"GROUP  BY be.id;  ";
			connectDb();
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			
			result = pStmt.executeQuery();
			while (result.next()) {
				 BlogEntryDetails bbc = new BlogEntryDetails();
		         bbc.setBlogBody(result.getString("blog_body"));
		         bbc.setBlogTitle(result.getString("blog_title"));
		         bbc.setCategoryName(result.getString("category_name"));
		         bbc.setCatId(result.getInt("cat_id"));
		         bbc.setCommentsCount(result.getInt("comments_count"));
		         bbc.setCreatedTime(result.getLong("created_time"));
		         bbc.setDeleted(result.getString("deleted").equals('0') ? false : true);
		         bbc.setFirstName(result.getString("first_name"));
		         bbc.setId(result.getInt("id"));
		         bbc.setLastName(result.getString("last_name"));
		         bbc.setProfilePicture(result.getString("profile_picture"));
		         String tagIds= result.getString("tag_ids") == null ? "":result.getString("tag_ids");
		         String tagNames = result.getString("tag_names") == null ? "":result.getString("tag_names");
		         List<Tag> ls = new ArrayList<Tag>() ; 
		         
		         if(tagIds!="" && tagNames!="") {
		        	 String[] lsIds = tagIds.split(",") ; 
		        	 String [] lsNames = tagNames.split(",") ; 
		        	         	 
		        	
		        	 
		        	 for(int i=0; i<lsIds.length; i++) {
		        		 Tag tag = new Tag() ; 
		        		 tag.setId(Integer.parseInt(lsIds[i]));
		        		 tag.setTagName(lsNames[i]);
		        		 ls.add(tag) ; 
		        	 } 
		        	 	        	 
		         }
		         bbc.setTags(ls);
		         
		         bbc.setUserId(result.getInt("user_id"));
		         blogs.add(bbc);
		         
		         
		         
			}

			return blogs;

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			disconnectDb();
		}

		return blogs;
	}


}
