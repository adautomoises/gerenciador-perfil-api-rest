package br.com.adautomoises.gerenciador_perfil.Controller

import br.com.adautomoises.gerenciador_perfil.Service.UserService
import br.com.adautomoises.gerenciador_perfil.model.DTO.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @PostMapping
    private fun createUsers(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity(userService.create(), HttpStatus.CREATED)
    }

    @GetMapping
    private fun listUsers(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity.ok(userService.listUsers())
    }
}