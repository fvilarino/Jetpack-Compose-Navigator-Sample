package com.francescsoftware.navigation.ui.two

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.francescsoftware.navigation.ui.common.ThreeBlock
import com.francescsoftware.navigation.ui.theme.NavigationTheme
import com.francescsoftware.navigation.ui.theme.PhonePreview
import com.francescsoftware.navigation.ui.three.ThreeOptions

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TwoRoute(
    modifier: Modifier = Modifier,
    viewModel: TwoViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TwoRoute(
        state = state,
        onThreeClick = viewModel::onThreeClick,
        onRestoreClick = viewModel::loadReturnedData,
        onBackClick = viewModel::onBackClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TwoRoute(
    state: TwoState,
    onThreeClick: (ThreeOptions, Boolean) -> Unit,
    onRestoreClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Two Screen") },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Argument:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 8.dp),
                )
                Text(
                    text = state.input,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
            ThreeBlock(
                onThreeClick = onThreeClick,
            )
            Button(
                onClick = onRestoreClick,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Retrieve returned data")
            }
            AnimatedVisibility(
                visible = state.returnedData != null,
                modifier = Modifier.padding(top = 16.dp),
            ) {
                Column {
                    Text(
                        text = "Name: " + state.returnedData?.name.orEmpty(),
                    )
                    Text(
                        text = "Value: " + state.returnedData?.value?.toString().orEmpty()
                    )
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun PreviewTwoRoute() {
    NavigationTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TwoRoute(
                state = TwoState(
                    input = "Sample input",
                    returnedData = null,
                ),
                onThreeClick = { _, _ -> },
                onRestoreClick = {},
                onBackClick = {},
            )
        }
    }
}
