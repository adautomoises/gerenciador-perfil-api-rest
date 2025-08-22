package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.Repository.UserRepository
import br.com.adautomoises.gerenciador_perfil.mapper.UserMapper
import br.com.adautomoises.gerenciador_perfil.model.DTO.UserResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val gitHubService: GitHubService) {

    @Transactional
    fun create(): List<UserResponse> {
        val users =
            gitHubService.getUsersGitHubApi().orElseThrow { NoSuchElementException("No users found") }
        return UserMapper.toResponse(userRepository.saveAll(UserMapper.toEntity(users)))
    }

    fun listUsers(): List<UserResponse> {
        return userRepository.findAll().map { UserMapper.toResponse(it) }
    }
}