package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidProductIdException;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.repository.ProductRepository;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//cekor 20 implementiranje na site metodi

@Service
public class ProductServiceImpl implements ProductService {

    //zavisi od
    private final ProductRepository productRepository;

    //zavisi i od categoryrepository cekor 22
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    // odi vo productrepository interface

    //se implementira listall findbyid create
    @Override
    public List<Product> listAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(InvalidProductIdException::new);
    }

    //kreira product
    @Override
    public Product create(String name, Double price, Integer quantity, List<Long> categoryIds) {
        List<Category> categories = this.categoryRepository.findAllById(categoryIds);
        Product product = new Product(name,price,quantity,categories);
        return this.productRepository.save(product);
    }

    //kreira update
    @Override
    public Product update(Long id, String name, Double price, Integer quantity, List<Long> categoryIds) {
        Product product = this.findById(id);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        List<Category> categories = this.categoryRepository.findAllById(categoryIds);
        product.setCategories(categories);
        return this.productRepository.save(product);
    }

    @Override
    public Product delete(Long id) {
        Product product = this.findById(id);
        this.productRepository.delete(product);
        return product;
    }

    // od tuka vo DataInitializer cekor 23

    // za kraj - napocetok vrakja prazna lista return new ArrayList<>();
    //TODO implement

    @Override
    public List<Product> listProductsByNameAndCategory(String name, Long categoryId) {
        //TODO: implement it
        Category category = categoryId != null ? this.categoryRepository.findById(categoryId).orElse((Category)null) : null;
        String nameLike = "%"+name+"%";
        if(name != null && category != null) {
            return this.productRepository.findAllByNameLikeAndCategoriesContaining(nameLike, category);
        } else if (name != null) {
            return this.productRepository.findAllByNameLike(nameLike);
        } else if (category != null) {
            return this.productRepository.findAllByCategoriesContaining(category);
        } else {
            return this.productRepository.findAll();
        }

        }
    }

