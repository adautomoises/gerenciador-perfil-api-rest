package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.Repository.AuthUserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(
    private val authUserRepository: AuthUserRepository
) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        return authUserRepository.findByLogin(username)
            ?: throw UsernameNotFoundException("User '$username' not found")
    }
}