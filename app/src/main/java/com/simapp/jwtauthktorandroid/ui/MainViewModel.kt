package com.simapp.jwtauthktorandroid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simapp.jwtauthktorandroid.auth.AuthRepository
import com.simapp.jwtauthktorandroid.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    var state by mutableStateOf(AuthState())

    private val resulChannel = Channel<AuthResult<Unit>>()
    val authResults = resulChannel.receiveAsFlow()

    init {
        authenticate()
    }
    fun onEvent(event: AuthUiEvent) {
        when(event) {
            is AuthUiEvent.SignInUsernameChanged -> {
             state = state.copy(signInUsername = event.value)
            }
            is AuthUiEvent.SignInPasswordChanged -> {
                state = state.copy(signInPassword = event.value)
            }
            is AuthUiEvent.SignIn -> {
             signIn()
            }

            is AuthUiEvent.SignUpUsernameChanged -> {
                state = state.copy(signUpUsername = event.value)
            }

            is AuthUiEvent.SignUpPasswordChanged -> {
                state = state.copy(signUpPassword = event.value)
            }

            is AuthUiEvent.SignUp -> {
            signUp()
            }
        }
    }


    private fun signUp(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signUp(
                username = state.signUpUsername,
                password = state.signUpPassword
            )

            resulChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun signIn(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signIn(
                username = state.signInUsername,
                password = state.signInPassword
            )

            resulChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }


    private fun authenticate(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.authenticate()

            resulChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}