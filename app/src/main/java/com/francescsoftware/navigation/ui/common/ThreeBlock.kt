package com.francescsoftware.navigation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.francescsoftware.navigation.ui.theme.NavigationTheme
import com.francescsoftware.navigation.ui.theme.PhonePreview
import com.francescsoftware.navigation.ui.three.ThreeOptions

@Composable
fun ThreeBlock(
    onThreeClick: (ThreeOptions, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var threeOption by rememberSaveable {
        mutableStateOf(ThreeOptions.One)
    }
    var confirmed by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ThreeOptions.values().forEach { option ->
                    ThreeRadioOption(
                        label = option.name,
                        isChecked = option == threeOption,
                        onSelect = { threeOption = option },
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Confirm option",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
                Checkbox(
                    checked = confirmed,
                    onCheckedChange = {
                        confirmed = !confirmed
                    },
                )
            }

        }
        Button(
            onClick = { onThreeClick(threeOption, confirmed) },
            modifier = Modifier.padding(all = 16.dp),
        ) {
            Text(text = "Navigate to Three")
        }
    }
}

@Composable
fun ThreeRadioOption(
    label: String,
    isChecked: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .selectable(
                selected = isChecked,
                onClick = onSelect,
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = isChecked,
            onClick = onSelect,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@PhonePreview
@Composable
private fun PreviewThreeBlock() {
    NavigationTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ThreeBlock(
                onThreeClick = { _, _ -> }
            )
        }
    }
}
