package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.Repository.RoleRepository
import br.com.adautomoises.gerenciador_perfil.Repository.UserRepository
import br.com.adautomoises.gerenciador_perfil.model.DTO.UserRoleRequest
import br.com.adautomoises.gerenciador_perfil.model.Entity.Role
import br.com.adautomoises.gerenciador_perfil.model.Entity.User
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.apache.coyote.BadRequestException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class ProfileManagementServiceTest {
    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var roleRepository: RoleRepository

    @InjectMockKs
    private lateinit var profileManagementService: ProfileManagementService

    @Test
    @DisplayName("Must throw BadRequestException if the role is already assigned to the user")
    fun `ascribeUserRole must throw an exception if the userRole exists`() {
        val request = UserRoleRequest(userId = 1L, roleId = 10L)
        val existingRole = Role(id = 10L, name = "ADMIN")
        val user = User(id = 1L, login = "testuser", url = "url.com")

        user.roles.add(existingRole)

        every { userRepository.findById(1L) } returns Optional.of(user)
        every { roleRepository.findById(10L) } returns Optional.of(existingRole)

        val exception = assertThrows<BadRequestException> {
            profileManagementService.ascribeUserRole(request)
        }

        Assertions.assertThat(exception.message).isEqualTo("The user already has this role")

        verify(exactly = 0) { userRepository.save(any()) }
    }
}