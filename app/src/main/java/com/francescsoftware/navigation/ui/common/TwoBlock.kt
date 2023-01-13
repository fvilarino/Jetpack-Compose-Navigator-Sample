package com.francescsoftware.navigation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.francescsoftware.navigation.ui.theme.NavigationTheme
import com.francescsoftware.navigation.ui.theme.PhonePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoBlock(
    onTwoClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Navigate to Two",
) {
    var twoText by rememberSaveable {
        mutableStateOf("")
    }
    val navTwoEnabled by remember {
        derivedStateOf { twoText.isNotEmpty() }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = twoText,
            onValueChange = { twoText = it },
            label = { Text("2nd screen argument") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
        )
        Button(
            onClick = { onTwoClick(twoText) },
            enabled = navTwoEnabled,
            modifier = Modifier.padding(all = 16.dp),
        ) {
            Text(text = label)
        }
    }
}

@PhonePreview
@Composable
private fun PreviewTwoBlock() {
    NavigationTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TwoBlock(
                onTwoClick = {}
            )
        }
    }
}
