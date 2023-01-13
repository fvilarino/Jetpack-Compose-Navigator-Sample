package com.francescsoftware.navigation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.francescsoftware.navigation.navigation.NavigationOneRoute
import com.francescsoftware.navigation.navigation.NavigationThreeRoute
import com.francescsoftware.navigation.navigation.NavigationTwoRoute
import com.francescsoftware.navigation.navigation.Navigator
import com.francescsoftware.navigation.ui.one.OneRoute
import com.francescsoftware.navigation.ui.theme.NavigationTheme
import com.francescsoftware.navigation.ui.three.ThreeRoute
import com.francescsoftware.navigation.ui.two.TwoRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    DisposableEffect(key1 = navController) {
                        navigator.setController(navController)
                        onDispose {
                            navigator.clear()
                        }
                    }
                    NavHost(
                        navController = navController,
                        startDestination = NavigationOneRoute.route,
                    ) {
                        composable(route = NavigationOneRoute.route) {
                            OneRoute(
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                        composable(route = NavigationTwoRoute.route) {
                            TwoRoute(
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                        composable(
                            route = NavigationThreeRoute.route,
                            arguments = NavigationThreeRoute.navArgs,
                        ) {
                            ThreeRoute(
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }
                }
            }
        }
    }
}
