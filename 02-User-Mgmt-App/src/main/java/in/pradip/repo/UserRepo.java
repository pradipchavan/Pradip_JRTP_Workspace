package in.pradip.repo;

import in.pradip.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    public UserEntity findByEmailAndPwd(String email, String pwd);

    public UserEntity findByEmail(String email);

    public boolean existsByEmail(String emailId);
}
