package com.example.messengerapplication.presentation.main.inner_screens.profile.viewmodel

import com.example.messengerapplication.base.BaseViewModel
import com.example.messengerapplication.presentation.main.inner_screens.profile.event.ProfileUiEvent
import com.example.messengerapplication.presentation.main.inner_screens.profile.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

): BaseViewModel<ProfileUiState, ProfileUiEvent, ProfileViewModelEvent>() {

    override val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(ProfileUiState(""))
    override val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    override val _vmEvent: MutableSharedFlow<ProfileViewModelEvent> = MutableSharedFlow()
    override val vmEvent: SharedFlow<ProfileViewModelEvent> = _vmEvent.asSharedFlow()


    override fun postUiEvent(event: ProfileUiEvent) {

    }
}