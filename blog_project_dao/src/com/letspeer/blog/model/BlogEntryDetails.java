package com.letspeer.blog.model;

import java.util.HashMap;
import java.util.List;

public class BlogEntryDetails {

	private Integer id;
	private String blogTitle; 
	private String blogBody;
	private Integer catId;
	private Integer userId;
	private Boolean deleted;
	private Long createdTime;
	private String categoryName;
	private String firstName;
	private String lastName;
	private String profilePicture;
	private Integer commentsCount;
	//private HashMap<Integer, String> tags;
	
	private List<Tag> tags ;  

	public Integer getId() {
		return id;
	}
	
	

	public String getBlogTitle() {
		return blogTitle;
	}



	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}



	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/*public HashMap<Integer, String> getTags() {
		return tags;
	}

	public void setTags(HashMap<Integer, String> tags) {
		this.tags = tags;
	}*/
	
	

}
