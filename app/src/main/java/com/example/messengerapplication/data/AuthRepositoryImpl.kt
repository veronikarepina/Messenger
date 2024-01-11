package com.example.messengerapplication.data

import android.util.Log
import com.example.messengerapplication.base.Response
import com.example.messengerapplication.domain.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    private var userId: String = ""
    override fun authUser(email: String, password: String): Flow<Response<AuthResult>> {
        return flow {
            emit(Response.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Response.Success(result))
        }.catch {
            emit(Response.Error(it.message.toString()))
        }
    }

    override fun registerUser(
        userName: String,
        email: String,
        password: String
    ): Flow<Response<AuthResult>> {
        return flow {
            emit(Response.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        userId = firebaseAuth.currentUser?.uid ?: ""
                    }
                    else {
                        Log.e("MyLog", "error: ${task.exception?.message.toString()}")
                    }
                }.await()
            emit(Response.Success(result))
        }.catch {
            emit(Response.Error(it.message.toString()))
        }
    }
}