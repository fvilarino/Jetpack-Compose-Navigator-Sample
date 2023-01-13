package com.francescsoftware.navigation.ui.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(widthDp = 420)
@Preview(widthDp = 420, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class PhonePreview

@Preview(device = Devices.NEXUS_9)
@Preview(device = Devices.NEXUS_9, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class TabletPreview
