package br.com.adautomoises.gerenciador_perfil.mapper

import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleRequest
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleResponse
import br.com.adautomoises.gerenciador_perfil.model.Entity.Role

object RoleMapper {
    fun toEntity(dto: RoleRequest): Role {
        return Role(name = dto.name)
    }

    fun toResponse(entity: Role): RoleResponse {
        return RoleResponse(id = entity.id, name = entity.name)
    }
}