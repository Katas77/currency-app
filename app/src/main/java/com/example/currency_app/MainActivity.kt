package com.example.currency_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currency_app.screen.CurrencyScreen

import com.example.currency_app.screen.AuthScreen
import com.example.currency_app.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Получаем ViewModel для управления авторизацией
                val authViewModel: AuthViewModel = viewModel()
                val isAuth by authViewModel.isAuthenticated.collectAsState()

                if (isAuth) {
                    // Если пользователь авторизован, показываем основной экран
                    CurrencyScreen()
                } else {
                    // Иначе показываем экран входа/регистрации
                    AuthScreen(
                        viewModel = authViewModel,
                        onAuthSuccess = {  }
                    )
                }
            }
        }
    }
}
