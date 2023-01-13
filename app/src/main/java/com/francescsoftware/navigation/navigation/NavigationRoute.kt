package com.francescsoftware.navigation.navigation

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.francescsoftware.navigation.ui.three.ThreeOptions

interface NavigationRoute {
    fun buildRoute(): String
}

object NavigationOneRoute : NavigationRoute {
    override fun buildRoute(): String = route

    private const val root = "one"
    const val route = root
}

data class NavigationTwoRoute(
    val value: String,
) : NavigationRoute {
    constructor(savedStateHandle: SavedStateHandle) : this(
        value = requireNotNull(savedStateHandle.get<String>(inputArg))
    )

    override fun buildRoute(): String = "$root/$value"

    companion object {
        private const val root = "two"
        private const val inputArg = "input"
        const val route = "$root/{$inputArg}"
    }
}

data class NavigationThreeRoute(
    val option: ThreeOptions,
    val confirm: Boolean,
) : NavigationRoute {
    constructor(savedStateHandle: SavedStateHandle) : this(
        option = requireNotNull(savedStateHandle.get<ThreeOptions>(optionArg)),
        confirm = savedStateHandle.get<Boolean>(confirmArg) == true,
    )

    override fun buildRoute(): String = "$root/${option.serialize()}/$confirm"

    private class ThreeRouteParamType : NavType<ThreeOptions>(isNullableAllowed = false) {
        override val name: String
            get() = "ThreeOptions"

        override fun get(bundle: Bundle, key: String): ThreeOptions? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, ThreeOptions::class.java)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelable(key)
            }

        override fun parseValue(value: String): ThreeOptions = ThreeOptions.deserialize(value)

        override fun put(bundle: Bundle, key: String, value: ThreeOptions) {
            bundle.putParcelable(key, value)
        }
    }

    companion object {
        private const val root = "three"
        private const val optionArg = "option"
        private const val confirmArg = "confirm"
        const val route = "$root/{$optionArg}/{$confirmArg}"
        val navArgs = listOf(
            navArgument(name = optionArg) {
                type = ThreeRouteParamType()
            },
            navArgument(name = confirmArg) {
                type = NavType.BoolType
            },
        )
    }
}
