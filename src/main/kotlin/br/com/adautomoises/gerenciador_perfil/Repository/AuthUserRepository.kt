package br.com.adautomoises.gerenciador_perfil.Repository

import br.com.adautomoises.gerenciador_perfil.model.Entity.AuthUser
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthUserRepository : JpaRepository<AuthUser, Long> {
    @EntityGraph(attributePaths = ["user.roles"])
    fun findByLogin(login: String): AuthUser?
}