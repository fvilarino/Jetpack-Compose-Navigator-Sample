package com.francescsoftware.navigation.navigation

import android.os.Parcelable
import androidx.navigation.NavController
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

private const val DefaultKey = "nav_data"

interface Navigator {
    fun setController(navController: NavController)
    fun navigate(route: NavigationRoute)
    fun popBackStack()
    fun <T : Parcelable> saveData(data: T, key: String = DefaultKey)
    fun <T : Parcelable> restoreData(key: String = DefaultKey): T?
    fun clear()
}

class NavigatorImpl @Inject constructor() : Navigator {

    private var navController: NavController? = null

    override fun setController(navController: NavController) {
        this.navController = navController
    }

    override fun navigate(route: NavigationRoute) {
        navController?.navigate(
            route.buildRoute()
        )
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }

    override fun <T : Parcelable> saveData(data: T, key: String) {
        navController?.previousBackStackEntry?.savedStateHandle?.set(
            key,
            data
        )
    }

    override fun <T : Parcelable> restoreData(key: String): T? =
        navController?.currentBackStackEntry?.savedStateHandle?.get<T>(key)

    override fun clear() {
        navController = null
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {
    @Binds
    @Singleton
    fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}
