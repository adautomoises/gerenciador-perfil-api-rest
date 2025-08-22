package br.com.adautomoises.gerenciador_perfil

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class GerenciadorPerfilApplication

fun main(args: Array<String>) {
	runApplication<GerenciadorPerfilApplication>(*args)
}
