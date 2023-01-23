package mk.ukim.finki.wp.exam.example.repository;

import mk.ukim.finki.wp.exam.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//cekor 16 interface extends jparepository tipot na identifikatorot
// nazad vo service impl (userseerviceimpl)
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
