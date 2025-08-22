package br.com.adautomoises.gerenciador_perfil.model.Entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false, unique = true)
    val name: String,
    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    val users: MutableSet<User> = mutableSetOf()
) {
    override fun toString(): String {
        return "Role(id=$id, name='$name')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Role
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}