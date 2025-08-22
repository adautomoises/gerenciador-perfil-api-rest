package br.com.adautomoises.gerenciador_perfil.security

import br.com.adautomoises.gerenciador_perfil.Repository.AuthUserRepository
import br.com.adautomoises.gerenciador_perfil.Service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val tokenService: TokenService,
    private val authUserRepository: AuthUserRepository
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recoverToken(request)
        if (token != null) {
            val login = tokenService.validateToken(token)
            if (login != null) {
                val authUser = authUserRepository.findByLogin(login)
                if (authUser != null) {
                    val authentication = UsernamePasswordAuthenticationToken(authUser, null, authUser.authorities)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")
        return authHeader?.replace("Bearer ", "")
    }
}