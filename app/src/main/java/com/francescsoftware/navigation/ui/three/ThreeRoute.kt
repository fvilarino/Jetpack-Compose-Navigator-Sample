package com.francescsoftware.navigation.ui.three

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
import com.francescsoftware.navigation.ui.common.TwoBlock
import com.francescsoftware.navigation.ui.theme.NavigationTheme
import com.francescsoftware.navigation.ui.theme.PhonePreview

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ThreeRoute(
    modifier: Modifier = Modifier,
    viewModel: ThreeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ThreeRoute(
        state = state,
        onTwoClick = viewModel::onTwoClick,
        onSaveClick = viewModel::onSaveReturnDataClick,
        onBackClick = viewModel::onBackClick,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ThreeRoute(
    state: ThreeState,
    onTwoClick: (String) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Three Screen") },
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
                    text = "Option:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 8.dp),
                )
                Text(
                    text = state.option.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Confirm:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 8.dp),
                )
                Text(
                    text = state.confirm.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
            Button(
                onClick = onSaveClick,
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                Text(
                    text = "Save return data"
                )
            }
            TwoBlock(
                onTwoClick = onTwoClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@PhonePreview
@Composable
private fun PreviewThreeRoute() {
    NavigationTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ThreeRoute(
                state = ThreeState(
                    option = ThreeOptions.Two,
                    confirm = true,
                ),
                onTwoClick = {},
                onSaveClick = {},
                onBackClick = {},
            )
        }
    }
}
