package br.com.adautomoises.gerenciador_perfil.mapper

import br.com.adautomoises.gerenciador_perfil.model.DTO.UserRequest
import br.com.adautomoises.gerenciador_perfil.model.DTO.UserResponse
import br.com.adautomoises.gerenciador_perfil.model.Entity.User

object UserMapper {
    fun toEntity(dto: UserRequest): User {
        return User(login = dto.login, url = dto.url)
    }

    fun toEntity(dtos: List<UserRequest>): List<User> {
        return dtos.map { User(login = it.login, url = it.url) }
    }

    fun toResponse(entity: User): UserResponse {
        return UserResponse(id = entity.id, login = entity.login, url = entity.url)
    }

    fun toResponse(entities: List<User>): List<UserResponse> {
        return entities.map { UserResponse(id = it.id, login = it.login, url = it.url) }
    }
}