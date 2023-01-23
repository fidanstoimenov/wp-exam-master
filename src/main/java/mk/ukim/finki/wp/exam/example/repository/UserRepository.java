package mk.ukim.finki.wp.exam.example.repository;

//treba da bide interfejs so nasleduva jd JPARepository
//za koja klasa se odnsuva i koj e tipot na nejzniniot primaren kluc

//import jdk.internal.org.objectweb.asm.Opcodes;
import mk.ukim.finki.wp.exam.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// cekor 10 -
//za koj entitet se odnesuva, i koj e tipot na nejzniniot primaren kluc
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

}
