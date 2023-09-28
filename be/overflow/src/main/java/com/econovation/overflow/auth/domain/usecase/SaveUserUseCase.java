package com.econovation.overflow.auth.domain.usecase;

import com.econovation.overflow.auth.domain.model.UserModel;
import com.econovation.overflow.auth.domain.model.converter.UserModelConverter;
import com.econovation.overflow.auth.domain.service.SaveUserService;
import com.econovation.overflow.auth.infra.converter.UserEntityConverter;
import com.econovation.overflow.auth.infra.repository.UserRepository;
import com.econovation.overflow.auth.web.dto.request.SaveUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaveUserUseCase {
	private final UserRepository userRepository;
	private final UserModelConverter modelConverter;
	private final UserEntityConverter entityConverter;
	private final SaveUserService saveUserService;

	@Transactional
	public void execute(SaveUserRequest request) {
		UserModel userModel = modelConverter.from(request);
		saveUserService.execute(userModel);
		userRepository.save(entityConverter.toEntity(userModel));
	}
}
