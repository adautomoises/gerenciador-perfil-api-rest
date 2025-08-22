package br.com.adautomoises.gerenciador_perfil.Controller

import br.com.adautomoises.gerenciador_perfil.Service.TokenService
import br.com.adautomoises.gerenciador_perfil.model.DTO.AuthRequest
import br.com.adautomoises.gerenciador_perfil.model.DTO.AuthResponse
import br.com.adautomoises.gerenciador_perfil.model.Entity.AuthUser
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        val usernamePassword = UsernamePasswordAuthenticationToken(authRequest.login, authRequest.password)
        val auth = authenticationManager.authenticate(usernamePassword)
        val authUser = auth.principal as AuthUser
        val token = tokenService.generateToken(authUser)
        return ResponseEntity.ok(AuthResponse(token = token))
    }
}