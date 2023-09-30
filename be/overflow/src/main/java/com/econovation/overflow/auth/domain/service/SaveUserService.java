package com.econovation.overflow.auth.domain.service;

import com.econovation.overflow.auth.domain.model.UserModel;
import com.econovation.overflow.auth.domain.usecase.ValidEmailUseCase;
import com.econovation.overflow.auth.domain.usecase.ValidNicknameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveUserService {
	private final ValidEmailUseCase validEmailUseCase;
	private final ValidNicknameUseCase validNicknameUseCase;

	public void execute(UserModel userModel) {
		validEmailUseCase.execute(userModel);
		validNicknameUseCase.execute(userModel);
	}
}
