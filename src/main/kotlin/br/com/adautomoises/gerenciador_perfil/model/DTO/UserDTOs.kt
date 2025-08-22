package br.com.adautomoises.gerenciador_perfil.model.DTO

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserRequest(
    @field:NotBlank(message = "Login should not be empty")
    val login: String,
    @field:NotBlank(message = "Url should not be empty")
    val url: String
)

data class UserResponse(
    val id: Long,
    val login: String,
    val url: String
)

data class UserWithRolesResponse(
    val id: Long,
    val login: String,
    val url: String,
    val roles: Set<RoleResponse>
)