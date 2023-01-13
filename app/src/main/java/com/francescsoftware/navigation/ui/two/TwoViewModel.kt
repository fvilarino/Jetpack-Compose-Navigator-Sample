package com.francescsoftware.navigation.ui.two

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.francescsoftware.navigation.navigation.NavigationThreeRoute
import com.francescsoftware.navigation.navigation.NavigationTwoRoute
import com.francescsoftware.navigation.navigation.Navigator
import com.francescsoftware.navigation.ui.three.ThreeOptions
import com.francescsoftware.navigation.ui.three.ThreeReturnData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class TwoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
) : ViewModel() {

    private val route = NavigationTwoRoute(savedStateHandle)

    private val _state = MutableStateFlow(
        TwoState(
            input = route.value,
            returnedData = null,
        )
    )
    val state: StateFlow<TwoState> = _state.asStateFlow()

    fun onBackClick() {
        navigator.popBackStack()
    }

    fun onThreeClick(option: ThreeOptions, confirm: Boolean) {
        navigator.navigate(
            NavigationThreeRoute(
                option = option,
                confirm = confirm,
            )
        )
    }

    fun loadReturnedData() {
        val returnedData = navigator.restoreData<ThreeReturnData>()
        _state.update { state ->
            state.copy(
                returnedData = returnedData
            )
        }
    }
}
