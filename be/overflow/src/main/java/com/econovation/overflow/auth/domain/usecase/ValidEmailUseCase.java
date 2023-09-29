package com.econovation.overflow.auth.domain.usecase;

import com.econovation.overflow.auth.domain.exception.DuplicateException;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
import com.econovation.overflow.auth.web.supplier.EmailSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidEmailUseCase {
	private final UserRepository userRepository;

	public void execute(EmailSupplier supplier) {
		boolean exists = userRepository.existsByEmail(supplier.getEmail());
		if (exists) {
			throw new DuplicateException("중복되는 이메일입니다");
		}
	}
}
