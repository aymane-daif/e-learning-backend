package ma.ensa.userservice.repository;

import ma.ensa.userservice.entity.Role;
import ma.ensa.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    List<User> findAllByRole(Role role);
    Optional<User> findByUserId(Long user_id);
}
