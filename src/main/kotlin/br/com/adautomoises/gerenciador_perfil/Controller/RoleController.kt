package br.com.adautomoises.gerenciador_perfil.Controller

import br.com.adautomoises.gerenciador_perfil.Service.RoleService
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleRequest
import br.com.adautomoises.gerenciador_perfil.model.DTO.RoleResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(private val roleService: RoleService) {

    @PostMapping
    private fun createRole(@Valid @RequestBody roleRequest: RoleRequest): ResponseEntity<RoleResponse> {
        return ResponseEntity(roleService.create(roleRequest), HttpStatus.CREATED)
    }

    @GetMapping
    private fun listRoles(): ResponseEntity<List<RoleResponse>> {
        return ResponseEntity.ok(roleService.listRoles())
    }
}