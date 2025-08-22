package br.com.adautomoises.gerenciador_perfil.Controller

import br.com.adautomoises.gerenciador_perfil.Service.UserService
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleResponse
import br.com.adautomoises.gerenciador_perfil.model.DTO.UserWithRolesResponse
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(UserController::class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var userService: UserService

    @Test
    @WithMockUser
    @DisplayName("GET /users must show list of users and returns status code 200 Ok")
    fun listUsersWithRoles() {
        val usersResponse = listOf(
            UserWithRolesResponse(1, "user1", "url1", setOf(RoleResponse(10, "ADMIN")))
        )
        every { userService.listUsersWithRoles() } returns usersResponse

        mockMvc.get("/users/roles")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].login") { value("user1") }
                jsonPath("$[0].roles[0].name") { value("ADMIN") }
            }
    }
}