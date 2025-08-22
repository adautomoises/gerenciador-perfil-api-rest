package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.Repository.RoleRepository
import br.com.adautomoises.gerenciador_perfil.Repository.UserRepository
import br.com.adautomoises.gerenciador_perfil.model.DTO.UserRoleRequest
import br.com.adautomoises.gerenciador_perfil.model.Entity.Role
import jakarta.transaction.Transactional
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service

@Service
class ProfileManagementService(private val userRepository: UserRepository, private val roleRepository: RoleRepository) {
    @Transactional
    fun ascribeUserRole(userRoleRequest: UserRoleRequest) {
        val user = userRepository.findById(userRoleRequest.userId).orElseThrow { RuntimeException("User not found") }
        val role = roleRepository.findById(userRoleRequest.roleId).orElseThrow { RuntimeException("Role not found") }

        val userRoleExists = user.roles.any {
            userRole: Role -> userRole.id == role.id
        }

        if (userRoleExists) {
            throw BadRequestException("The user already has this role")
        }

        user.roles.add(role)
        userRepository.save(user)
    }
}