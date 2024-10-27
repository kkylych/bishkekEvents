package com.bishevents.service;
import com.bishevents.DTO.CategoryDTO;
import com.bishevents.Mapper.CategoryMapper;
import com.bishevents.entity.Category;
import com.bishevents.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(CategoryMapper::toDto);
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDto(savedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
