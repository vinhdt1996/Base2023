package com.example.base2023.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

fun TextStyle.alignCenter(): TextStyle = this.copy(textAlign = TextAlign.Center)

fun Modifier.clickableEffect(
    onClick: () -> Unit
) = composed(inspectorInfo = debugInspectorInfo {
    name = "clickable"
    properties["onClick"] = onClick
}) {
    Modifier.clickable(onClick = onClick,
        indication = rememberRipple(bounded = true),
        interactionSource = remember { MutableInteractionSource() })
}

fun Modifier.clickableNoEffect(
    onClick: () -> Unit
) = composed(inspectorInfo = debugInspectorInfo {
    name = "clickable"
    properties["onClick"] = onClick
}) {
    Modifier.clickable(
        onClick = onClick,
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
    )
}