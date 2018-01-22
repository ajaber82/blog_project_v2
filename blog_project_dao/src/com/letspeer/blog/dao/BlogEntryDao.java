package com.letspeer.blog.dao;

import java.util.List;

import com.letspeer.blog.model.BlogEntry;
import com.letspeer.blog.model.BlogEntryDetails;

public interface BlogEntryDao extends BaseDao {
	
	
	public Integer addBlogEntry(BlogEntry blogEntry);

	public BlogEntry getBlogEntryById(Integer id);

	public List<BlogEntry> getBlogEntries(Integer start, Integer count);

	public List<BlogEntry> getBlogEntries();

	public Boolean updateBlogEntry(BlogEntry blogEntry);

	public void deleteBlogEntry(Integer id);
	
	public BlogEntryDetails getBlogEntryDetailes(Integer id);
	

}
