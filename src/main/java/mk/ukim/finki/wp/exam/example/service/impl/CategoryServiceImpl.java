package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

// cekor 14 implements
@Service
public class CategoryServiceImpl implements CategoryService{

    // cekor 15 - zavisime od categoryrepository / se buni intellij odime vo CategoryRepository
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    //cekor 17
    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(InvalidCategoryIdException::new);
    }

    //cekor 18
    @Override
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    //cekor 19 - proveruvame dali go ima default construktor
    // cekor 20 - vo sledniot service productServiceImpl
    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return this.categoryRepository.save(category);
    }
}
