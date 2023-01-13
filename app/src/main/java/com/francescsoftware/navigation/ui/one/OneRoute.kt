package com.francescsoftware.navigation.ui.one

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.francescsoftware.navigation.ui.common.ThreeBlock
import com.francescsoftware.navigation.ui.common.TwoBlock
import com.francescsoftware.navigation.ui.theme.PhonePreview
import com.francescsoftware.navigation.ui.theme.NavigationTheme
import com.francescsoftware.navigation.ui.theme.TabletPreview
import com.francescsoftware.navigation.ui.three.ThreeOptions

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun OneRoute(
    modifier: Modifier = Modifier,
    viewModel: OneViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    OneRoute(
        state = state,
        onTwoClick = viewModel::onTwoClick,
        onThreeClick = viewModel::onThreeClick,
        modifier = modifier,
    )
}

@Composable
private fun OneRoute(
    state: OneState,
    onTwoClick: (String) -> Unit,
    onThreeClick: (ThreeOptions, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        val isLandscape = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE
        if (isLandscape) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                TwoBlock(
                    onTwoClick = onTwoClick,
                    modifier = Modifier.weight(1f),
                    label = "Navigate to Two asynchronously"
                )
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(DividerDefaults.Thickness)
                        .padding(vertical = 16.dp)
                )
                ThreeBlock(
                    onThreeClick = onThreeClick,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                TwoBlock(
                    onTwoClick = onTwoClick,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Navigate to Two asynchronously"
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                ThreeBlock(
                    onThreeClick = onThreeClick,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = .25f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@PhonePreview
@TabletPreview
@Composable
private fun PreviewOneRoute() {
    NavigationTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            OneRoute(
                state = OneState(isLoading = false),
                onTwoClick = {},
                onThreeClick = { _, _ -> },
            )
        }
    }
}
