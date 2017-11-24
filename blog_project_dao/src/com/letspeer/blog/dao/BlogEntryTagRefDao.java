package com.letspeer.blog.dao;

import java.util.List;

import com.letspeer.blog.model.BlogEntryTagRef;

public interface BlogEntryTagRefDao extends BaseDao{
	

	public Integer addBlogEntryTagRef(BlogEntryTagRef blogEntryTagRef);

	public BlogEntryTagRef getBlogEntryTagRefById(Integer id);

	public List<BlogEntryTagRef> getBlogEntriesTagRef(Integer start, Integer count);

	public List<BlogEntryTagRef> getBlogEntriesTagRef();

	public Boolean updateBlogEntryTagRef(BlogEntryTagRef blogEntryTagRef);

	public void deleteBlogEntryTagRef(Integer id);
	
	

}
