package com.letspeer.blog.model;

public class BlogEntry {

	private Integer id;
	private String blogTitle;
	private String blogBody;
	private Integer catId;
	private Integer userId;
	private Long createdTime;
	private Boolean deleted;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogBody() {
		return blogBody;
	}
	public void setBlogBody(String blogBody) {
		this.blogBody = blogBody;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	
}
