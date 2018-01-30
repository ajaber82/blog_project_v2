package com.letspeer.blog.dao;

import java.util.List;


import com.letspeer.blog.model.Category;

public interface CategoryDao extends BaseDao {
	public Integer addCategory(Category category);

	public Category getCategoryById(Integer id);

	public List<Category> getCategories(Integer start, Integer count);

	public List<Category> getCategories();

	public Boolean updateCategory(Category category);

	public void deleteCategory(Integer id);
	
	
	
}
