package com.example.messengerapplication.domain

import com.example.messengerapplication.base.Response
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun authUser(email: String, password: String): Flow<Response<AuthResult>>
    fun registerUser(userName: String, email: String, password: String): Flow<Response<AuthResult>>
}