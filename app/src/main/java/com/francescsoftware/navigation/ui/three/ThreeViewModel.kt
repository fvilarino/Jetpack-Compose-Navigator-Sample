package com.francescsoftware.navigation.ui.three

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.francescsoftware.navigation.navigation.NavigationThreeRoute
import com.francescsoftware.navigation.navigation.NavigationTwoRoute
import com.francescsoftware.navigation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThreeReturnData(
    val name: String,
    val value: Int,
) : Parcelable

@HiltViewModel
class ThreeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
) : ViewModel() {

    private val route = NavigationThreeRoute(
        savedStateHandle
    )
    private val _state = MutableStateFlow(
        ThreeState(
            option = route.option,
            confirm = route.confirm,
        )
    )
    val state: StateFlow<ThreeState> = _state.asStateFlow()

    fun onBackClick() {
        navigator.popBackStack()
    }

    fun onTwoClick(value: String) {
        navigator.navigate(
            NavigationTwoRoute(
                value = value
            )
        )
    }

    fun onSaveReturnDataClick() {
        navigator.saveData(
            ThreeReturnData(
                name = when (route.option) {
                    ThreeOptions.One -> "First"
                    ThreeOptions.Two -> "Second"
                    ThreeOptions.Three -> "Third"
                },
                value = route.option.name.fold(initial = 0) { acc, c ->
                    acc + c.code
                }
            )
        )
    }
}
