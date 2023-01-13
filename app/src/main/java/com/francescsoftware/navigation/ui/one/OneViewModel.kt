package com.francescsoftware.navigation.ui.one

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francescsoftware.navigation.navigation.NavigationThreeRoute
import com.francescsoftware.navigation.navigation.NavigationTwoRoute
import com.francescsoftware.navigation.navigation.Navigator
import com.francescsoftware.navigation.ui.three.ThreeOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class OneViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {

    private val _state = MutableStateFlow(
        OneState(
            isLoading = false,
        )
    )

    val state: StateFlow<OneState> = _state.asStateFlow()

    fun onTwoClick(value: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            doWork()
            _state.update { it.copy(isLoading = false) }
            navigator.navigate(
                NavigationTwoRoute(
                    value = value
                )
            )
        }
    }

    fun onThreeClick(threeOptions: ThreeOptions, confirmed: Boolean) {
        navigator.navigate(
            NavigationThreeRoute(
                option = threeOptions,
                confirm = confirmed,
            )
        )
    }

    private suspend fun doWork() {
        delay(500L + Random.nextLong(1000L))
    }
}
