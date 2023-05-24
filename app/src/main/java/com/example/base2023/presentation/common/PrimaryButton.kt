package com.example.base2023.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.base2023.ui.theme.CustomTheme
import com.example.base2023.ui.theme.GrayAC
import com.example.base2023.ui.theme.WhiteFC
import com.example.base2023.ui.theme.WhiteFC40


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    iconId: Int? = null
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomTheme.colors.primary,
            disabledContainerColor = CustomTheme.colors.inactive
        )
    ) {
        iconId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = if (enabled) WhiteFC else if (CustomTheme.colors.darkTheme) WhiteFC40 else GrayAC,
            )
            HorizontalSpacer(width = 12f)
        }

        Text(
            textAlign = TextAlign.Center,
            text = label,
            style = CustomTheme.typography.buttonPrimary.copy(if (enabled) WhiteFC else if (CustomTheme.colors.darkTheme) WhiteFC40 else GrayAC)
        )
    }
}