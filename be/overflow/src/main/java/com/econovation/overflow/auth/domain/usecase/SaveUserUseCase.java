package com.econovation.overflow.auth.domain.usecase;

import com.econovation.overflow.auth.domain.dto.request.SaveUserRequest;
import com.econovation.overflow.auth.domain.model.UserModel;
import com.econovation.overflow.auth.domain.model.converter.UserModelConverter;
import com.econovation.overflow.auth.persistence.converter.UserEntityConverter;
import com.econovation.overflow.auth.persistence.repository.UserRepository;
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
	private final ValidEmailUseCase validEmailUseCase;
	private final ValidNicknameUseCase validNicknameUseCase;

	@Transactional
	public void execute(SaveUserRequest request) {
		UserModel userModel = modelConverter.from(request);
		valid(userModel);
		userRepository.save(entityConverter.toEntity(userModel));
	}

	public void valid(UserModel userModel) {
		validEmailUseCase.execute(userModel);
		validNicknameUseCase.execute(userModel);
	}
}
