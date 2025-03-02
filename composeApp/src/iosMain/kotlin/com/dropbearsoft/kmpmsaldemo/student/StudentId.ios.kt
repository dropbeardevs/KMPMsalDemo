package com.dropbearsoft.kmpmsaldemo.student

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*

class IosStudentId : StudentId {
    override suspend fun getStudentId(token: String) {
        val client = HttpClient() {
//            install(Auth) {
//                bearer {
//                    token;
//                }
//            }
        }

        val response: HttpResponse = client.request("http://localhost:5258/StudentInfo/GetStudentNumber") {
            method = HttpMethod.Get
            headers {
                append(HttpHeaders.Authorization, "Bearer: $token")
            }
        }
    }
}