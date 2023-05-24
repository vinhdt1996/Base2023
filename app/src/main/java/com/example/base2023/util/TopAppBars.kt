package com.example.base2023.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.base2023.R
import com.example.base2023.ui.theme.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopAppBar() {
    Surface(shadowElevation = 2.dp) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.login),
                    style = CustomTheme.typography.bold14.alignCenter(),
                    modifier = Modifier.fillMaxWidth()
                )
            }, modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun LoginTopAppBarPreview() {
    CustomTheme() {
        Surface {
            LoginTopAppBar()
        }
    }
}