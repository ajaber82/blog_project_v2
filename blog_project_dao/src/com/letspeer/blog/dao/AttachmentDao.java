package com.letspeer.blog.dao;

import java.util.List;

import com.letspeer.blog.model.Attachment;


public interface AttachmentDao extends BaseDao {
	
	
	public Integer addAttachment(Attachment attachmnet);

	public Attachment getAttachmentById(Integer id);

	public List<Attachment> getAttachments(Integer start, Integer count);

	public List<Attachment> getAttachments();

	public Boolean updateAttachment(Attachment attachment);

	public void deleteAttachment(Integer id);

}
