package com.bishevents.Mapper;

import com.bishevents.DTO.CategoryDTO;
import com.bishevents.entity.Category;

public class CategoryMapper {
    public static CategoryDTO toDto(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.id());
        category.setName(categoryDTO.name());
        return category;
    }
}
