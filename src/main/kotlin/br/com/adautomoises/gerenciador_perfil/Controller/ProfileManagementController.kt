package br.com.adautomoises.gerenciador_perfil.Controller

import br.com.adautomoises.gerenciador_perfil.Service.ProfileManagementService
import br.com.adautomoises.gerenciador_perfil.model.DTO.UserRoleRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/manage-profile")
class ProfileManagementController(private val profileManagementService: ProfileManagementService) {

    @PostMapping
    private fun ascribeUserRole(@Valid @RequestBody userRoleRequest: UserRoleRequest): ResponseEntity<Void> {
        profileManagementService.ascribeUserRole(userRoleRequest)
        return ResponseEntity.ok().build()
    }
}