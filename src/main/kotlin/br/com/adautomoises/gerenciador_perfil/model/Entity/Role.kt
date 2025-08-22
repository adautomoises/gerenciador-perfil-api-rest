package br.com.adautomoises.gerenciador_perfil.model.Entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false, unique = true)
    val name: String,
    @ManyToMany(mappedBy = "roles")
    val users: MutableSet<User> = mutableSetOf()
)