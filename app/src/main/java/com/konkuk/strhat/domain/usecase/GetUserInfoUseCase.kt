package com.konkuk.strhat.domain.usecase

import com.konkuk.strhat.domain.entity.UserInfoModel
import com.konkuk.strhat.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(): Result<UserInfoModel> = userRepository.getUserInfo()
}