package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.Repository.RoleRepository
import br.com.adautomoises.gerenciador_perfil.mapper.RoleMapper
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleRequest
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {

    @Transactional
    fun create(roleRequest: RoleRequest): RoleResponse {
        return RoleMapper.toResponse(roleRepository.save(RoleMapper.toEntity(roleRequest)))
    }

    fun listRoles(): List<RoleResponse> {
        return roleRepository.findAll().map { RoleMapper.toResponse(it) }
    }
}