package mk.ukim.finki.wp.exam.example.config;

import mk.ukim.finki.wp.exam.example.model.Role;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import mk.ukim.finki.wp.exam.example.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//cekor 23
@Component // za da povikame nesto po startuvanjeto na app - anotirame kako component - autoware
public class DataInitializer {

    public static final String ADMIN = "admin";

    private final UserService userService;

    private final CategoryService categoryService;

    private final ProductService productService;

    public DataInitializer(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostConstruct // da se anotira so postconstruct
    public void initData() {
        User admin = this.userService.create(ADMIN, ADMIN, Role.ROLE_ADMIN);

        for (int i = 1; i < 6; i++) {
            this.categoryService.create("Category " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.productService.create("Product " + i, 20.9 * i, i, Stream.of(1L, i % 5L + 1).collect(Collectors.toList()));
        }
    }
}
/// moze da se proba dali e se implementirano kako sto treba
// da se povrze so bazata localhost:8080/h2 pass od application.properties
// test serviceInit moze da se proba
//cekor 24 - productcontroller vo web delot