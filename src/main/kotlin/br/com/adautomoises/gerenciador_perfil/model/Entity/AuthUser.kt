package br.com.adautomoises.gerenciador_perfil.model.Entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "auth_user")
class AuthUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    private var login: String,

    @Column(nullable = false)
    private var password: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User

) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.roles.map { SimpleGrantedAuthority("ROLE_${it.name.uppercase()}") }.toMutableList()
    }

    override fun getPassword(): String = this.password
    override fun getUsername(): String = this.login
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}