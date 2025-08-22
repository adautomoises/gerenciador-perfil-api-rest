package br.com.adautomoises.gerenciador_perfil.model.DTO

import jakarta.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank(message = "Login should not be empty")
    val login: String,
    @field:NotBlank(message = "Password should not be empty")
    val password: String
)

data class AuthResponse(val token: String)