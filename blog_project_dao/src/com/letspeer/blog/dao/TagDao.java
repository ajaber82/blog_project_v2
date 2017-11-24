package com.letspeer.blog.dao;

import java.util.List;

import com.letspeer.blog.model.Tag;


//test
public interface TagDao extends BaseDao {

	public Integer addTag(Tag tag);

	public Tag getTagById(Integer id);

	public List<Tag> getTags(Integer start, Integer count);

	public List<Tag> getTags();

	public Boolean updateTag(Tag tag);

	public void deleteTag(Integer id);

}
