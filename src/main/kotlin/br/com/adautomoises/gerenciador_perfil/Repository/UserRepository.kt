package br.com.adautomoises.gerenciador_perfil.Repository

import br.com.adautomoises.gerenciador_perfil.model.Entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>