package br.com.adautomoises.gerenciador_perfil.model.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RoleRequest(
    @field:NotBlank(message = "Role's name should not be empty")
    @field:Size(min = 3, max = 50, message = "Role's name should have between 3 and 50 characters")
    val name: String
)

data class RoleResponse(
    val id: Long,
    val name: String
)