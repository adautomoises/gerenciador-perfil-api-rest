package br.com.adautomoises.gerenciador_perfil.Repository

import br.com.adautomoises.gerenciador_perfil.model.Entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun existsByName(name: String): Boolean
}