package com.letspeer.blog.model;


public class Attachment {
	
	private Integer id;
	private Boolean deleted;
	private Integer blogId;
	private String fileBath;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getFileBath() {
		return fileBath;
	}
	public void setFileBath(String fileBath) {
		this.fileBath = fileBath;
	}
	

}
