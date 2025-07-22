package com.example.currency_app.viewmodel
import androidx.lifecycle.ViewModel

import com.example.currency_app.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    // Временное хранилище пользователей
    private val users = mutableListOf<User>()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private var currentUser: User? = null

    fun register(username: String, password: String): Boolean {
        // Проверка, есть ли уже такой пользователь
        if (users.any { it.username == username }) {
            return false // пользователь уже существует
        }
        // Регистрация нового пользователя
        users.add(User(username, password))
        return true
    }

    fun login(username: String, password: String): Boolean {
        val user = users.find { it.username == username && it.password == password }
        return if (user != null) {
            currentUser = user
            _isAuthenticated.value = true
            true
        } else {
            false
        }
    }

    fun logout() {
        currentUser = null
        _isAuthenticated.value = false
    }
}