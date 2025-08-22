package br.com.adautomoises.gerenciador_perfil.model.DTO

import jakarta.validation.constraints.NotNull

data class UserRoleRequest(
    @field:NotNull(message = "User's id should not be empty")
    val userId: Long,
    @field:NotNull(message = "Role's id should not be empty")
    val roleId: Long
)