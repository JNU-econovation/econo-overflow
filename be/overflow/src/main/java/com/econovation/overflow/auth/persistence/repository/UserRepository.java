package com.econovation.overflow.auth.persistence.repository;

import com.econovation.overflow.auth.persistence.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);

	Optional<UserEntity> findByEmail(String email);
}
