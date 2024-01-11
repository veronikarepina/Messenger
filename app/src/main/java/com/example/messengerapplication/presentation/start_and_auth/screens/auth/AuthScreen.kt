package com.example.messengerapplication.presentation.start_and_auth.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.messengerapplication.R
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.event.AuthUiEvent
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.state.AuthUiState
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.viewModel.AuthViewModel
import com.example.messengerapplication.presentation.start_and_auth.screens.auth.viewModel.AuthViewModelEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreen(
    startMainActivity: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    
    val uiState by viewModel.uiState.collectAsState()

    val userName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordConfirm = remember { mutableStateOf("") }
    
    LaunchedEffect(key1 = null) {
        viewModel.vmEvent.collectLatest {
            when(it) {
                is AuthViewModelEvent.SuccessfulAuthEvent -> startMainActivity.invoke()
                is AuthViewModelEvent.ErrorAuthEvent -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        MainTitle(
            text = when (uiState) {
                is AuthUiState.SignIn -> stringResource(id = R.string.sign_in)
                is AuthUiState.SignUp-> stringResource(id = R.string.sign_up) 
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        if (uiState == AuthUiState.SignUp()) {
            TextFieldItem(
                placeholder = stringResource(id = R.string.user_name), 
                value = userName.value, 
                fieldState = userName,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(15.dp))
        }

        TextFieldItem(
            placeholder = stringResource(id = R.string.email),
            value = email.value, 
            fieldState = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextFieldItem(
            placeholder = stringResource(id = R.string.password),
            value = password.value, 
            fieldState = password,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (uiState == AuthUiState.SignUp()) {
            Spacer(modifier = Modifier.height(15.dp))

            TextFieldItem(
                placeholder = stringResource(id = R.string.confirm_password),
                value = passwordConfirm.value, 
                fieldState = passwordConfirm,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        ChangeAuthAndRegister(
            title = when (uiState) {
                is AuthUiState.SignIn -> stringResource(id = R.string.have_not_account)
                is AuthUiState.SignUp -> stringResource(id = R.string.have_account) },
            buttonText = when (uiState) {
                is AuthUiState.SignIn -> stringResource(id = R.string.sign_up)
                is AuthUiState.SignUp -> stringResource(id = R.string.sign_in) },
            onClick = {
                viewModel.postUiEvent(AuthUiEvent.ChangeUiStateEvent)
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        MainButton(
            text = when (uiState) {
                is AuthUiState.SignIn -> stringResource(id = R.string.sign_in)
                is AuthUiState.SignUp -> stringResource(id = R.string.sign_up) },
            onClick = {
                when (uiState) {
                    is AuthUiState.SignIn -> viewModel.postUiEvent(AuthUiEvent.AuthUserEvent(
                        email = email.value,
                        password = password.value
                    ))
                    is AuthUiState.SignUp -> viewModel.postUiEvent(AuthUiEvent.RegisterUserEvent(
                        userName = userName.value,
                        email = email.value,
                        password = password.value,
                        passwordConfirm = passwordConfirm.value
                    ))
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun MainTitle(text: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        color = colorResource(id = R.color.black)
    )
}

@Composable
private fun TextFieldItem(
    placeholder: String,
    value: String,
    fieldState: MutableState<String>,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = value,
        onValueChange = { fieldState.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 30.dp)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.main_color),
                shape = RoundedCornerShape(10.dp)
            ),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 12.sp,
                color = colorResource(id = R.color.black)
            )
        },
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color.Black,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun ChangeAuthAndRegister(
    title: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = title,
            color = colorResource(id = R.color.black),
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = buttonText,
            color = colorResource(id = R.color.black),
            fontSize = 15.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(enabled = true, onClick = onClick))
    }
}

@Composable
private fun MainButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.main_color)
        )
        ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = colorResource(id = R.color.black)
        )
    }
}
