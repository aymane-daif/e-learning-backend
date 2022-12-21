package ma.ensa.userservice.repository;

import ma.ensa.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByUserId(Long user_id);
    Optional<User> findByNicknameOrEmail(String nickName, String email);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByNickname(String nickName);

}
