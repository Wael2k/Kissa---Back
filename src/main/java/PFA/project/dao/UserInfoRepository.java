package PFA.project.dao;

import PFA.project.dao.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> getByEmail(String email);
}
