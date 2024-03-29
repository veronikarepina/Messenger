package com.example.messengerapplication.presentation.start_and_auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.messengerapplication.presentation.MainActivity
import com.example.messengerapplication.ui.theme.MessengerApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity: FragmentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessengerApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    navHostController = rememberNavController()
                    StartNavGraph(navHostController = navHostController) {
                        openMain()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun openMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}