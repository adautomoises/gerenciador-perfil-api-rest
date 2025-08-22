package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.model.DTO.UserRequest
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import java.util.*

@Service
class GitHubService() {
    fun getUsersGitHubApi(): Optional<List<UserRequest>> {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://api.github.com/users").build()
        val response = client.newCall(request).execute()

        if (!response.isSuccessful) return Optional.empty()

        val bodyString = response.body()?.string()
        if (bodyString.isNullOrBlank()) {
            return Optional.empty()
        }
        val objectMapper = ObjectMapper().registerKotlinModule()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return Optional.of(objectMapper.readValue(bodyString, object : TypeReference<List<UserRequest>>() {}))
    }
}