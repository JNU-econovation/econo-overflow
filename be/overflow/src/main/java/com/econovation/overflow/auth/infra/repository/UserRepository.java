package com.econovation.overflow.auth.infra.repository;

import com.econovation.overflow.auth.infra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByEmail(String email);

	boolean existsByNickname(String nickname);
}
