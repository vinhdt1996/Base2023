package com.example.base2023.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.base2023.R
import com.example.base2023.ui.theme.CustomTheme
import com.example.base2023.ui.theme.GrayAC

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    value: String,
    isPassword: Boolean = false,
    error: String? = null,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(!isPassword) }

    val trailingIconView = @Composable {
        val iconId = if (passwordVisible) R.drawable.ic_visibility
        else R.drawable.ic_visibility_off

        // Please provide localized description for accessibility services
        val description = if (passwordVisible) "Hide password" else "Show password"
        IconButton(onClick = {
            if (isPassword) {
                passwordVisible = !passwordVisible
            }
        }) {
            Icon(
                painter = painterResource(id = iconId),
                description,
                tint = CustomTheme.colors.textFieldIconTint
            )
        }
    }

    Column() {

        BasicTextField(modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(6.dp))
            .border(
                width = 1.dp, color = GrayAC
            ),
            maxLines = 1,
            value = value,
            onValueChange = onValueChange,
            textStyle = CustomTheme.typography.regular14,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = if (isPassword) keyboardOptions.copy(keyboardType = KeyboardType.Password) else keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.border(
                        border = BorderStroke(1.dp, GrayAC), shape = RoundedCornerShape(6.dp)
                    )
                ) {
                    HorizontalSpacer(16f)
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeHolder,
                                modifier = Modifier.fillMaxWidth(),
                                style = CustomTheme.typography.placeHolder
                            )
                        }
                        innerTextField()
                    }
                    if (isPassword) {
                        trailingIconView()
                    } else {
                        HorizontalSpacer(16f)
                    }
                }

            })

        if (error.isNullOrBlank().not()) {
            VerticalSpacer(height = 10f)
            Text(text = error.toString(), style = CustomTheme.typography.error)
        }
    }
}