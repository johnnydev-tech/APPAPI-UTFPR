package com.example.minhaprimeiraapi.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class PrettyJsonLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)

        // Intercepta e faz logging da resposta
        val rawJson = response.body?.string() ?: ""

        try {
            // Usa Gson para formatar o JSON
            val gson = GsonBuilder().setPrettyPrinting().create()
            val jsonElement = gson.fromJson(rawJson, Any::class.java)
            val prettyJson = gson.toJson(jsonElement)

            println("Formatted JSON Response:\n$prettyJson")

            // Reconstrói o corpo da resposta para ser retornado
            return response.newBuilder()
                .body(prettyJson.toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        } catch (e: Exception) {
            // Se não conseguir fazer o parsing do JSON, loga a resposta original
            println("Raw Response:\n$rawJson")
            return response.newBuilder()
                .body(rawJson.toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        }
    }
}