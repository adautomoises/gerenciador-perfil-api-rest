package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.Repository.RoleRepository
import br.com.adautomoises.gerenciador_perfil.mapper.RoleMapper
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleRequest
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleResponse
import jakarta.transaction.Transactional
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {

    @Transactional
    fun create(roleRequest: RoleRequest): RoleResponse {
        if (roleRepository.existsByName(roleRequest.name.uppercase())) {
            throw BadRequestException("The role '${roleRequest.name.uppercase()}' already exists")
        }

        val role = RoleMapper.toEntity(roleRequest)
        val savedRole = roleRepository.save(role)
        return RoleMapper.toResponse(savedRole)
    }

    fun listRoles(): List<RoleResponse> {
        return roleRepository.findAll().map { RoleMapper.toResponse(it) }
    }
}