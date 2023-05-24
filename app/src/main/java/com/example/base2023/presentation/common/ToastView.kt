package com.example.base2023.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.base2023.ui.theme.BorderToast
import com.example.base2023.ui.theme.CustomTheme
import com.example.base2023.vo.ToastData

@Composable
fun BoxScope.ToastView(toastData: ToastData?) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = CustomTheme.colors.toastBackGround
        ),
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .offset(y = (-100).dp),
        border = BorderStroke(1.dp, BorderToast),
    ) {
//        AnimatedVisibility(
//            visible = toastData != null,
//        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)
        ) {
            toastData?.iconId?.let { id ->
                Icon(
                    painter = painterResource(id = id),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                HorizontalSpacer(width = 8f)
            }
            Text(
                text = toastData?.message ?: "", style = CustomTheme.typography.regular14
            )
//            }
        }
    }
}