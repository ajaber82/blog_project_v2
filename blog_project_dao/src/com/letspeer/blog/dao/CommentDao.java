package com.letspeer.blog.dao;

import java.util.List;

import com.letspeer.blog.model.Comment;;

public interface CommentDao extends BaseDao {
	
	public Integer addComment(Comment comment);

	public Comment getCommentById(Integer id);

	public List<Comment> getComments(Integer start, Integer count);

	public List<Comment> getComments();

	public Boolean updateComment(Comment comment);

	public void deleteComment(Integer id);
	

}
