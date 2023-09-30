package com.econovation.overflow.auth.domain.usecase;

import com.econovation.overflow.auth.domain.exception.DuplicateException;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
import com.econovation.overflow.auth.web.supplier.NicknameSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidNicknameUseCase {
	private final UserRepository userRepository;

	public void execute(NicknameSupplier supplier) {
		boolean exists = userRepository.existsByNickname(supplier.getNickname());
		if (exists) {
			throw new DuplicateException("중복되는 닉네임입니다");
		}
	}
}
