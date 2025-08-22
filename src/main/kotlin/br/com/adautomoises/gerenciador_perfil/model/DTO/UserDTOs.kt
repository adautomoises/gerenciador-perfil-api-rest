package br.com.adautomoises.gerenciador_perfil.model.DTO

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserRequest(
    @field:NotBlank(message = "O login não pode ser vazio")
    val login: String,
    @field:NotBlank(message = "O url não pode ser vazio")
    val url: String
)

data class UserResponse(
    val id: Long,
    val login: String,
    val url: String
)