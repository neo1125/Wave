package com.neo.wave.test.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CheckBoxText(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onCheckedChange(!checked)
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )
        Text(text)
    }
}