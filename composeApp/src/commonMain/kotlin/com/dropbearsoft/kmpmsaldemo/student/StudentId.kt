package com.dropbearsoft.kmpmsaldemo.student

interface StudentId {
    suspend fun getStudentId(token: String)
}