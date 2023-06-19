package com.example.base2023.presentation.login

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Audio.AudioColumns
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.base2023.presentation.common.CustomAlertDialog
import com.example.base2023.presentation.common.CustomOutlinedTextField
import com.example.base2023.presentation.common.PrimaryButton
import com.example.base2023.presentation.common.VerticalSpacer
import com.example.base2023.util.LoginTopAppBar
import com.example.base2023.util.saveRingtones
import com.example.base2023.vo.DialogData
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.nio.charset.Charset
import java.security.AccessController.getContext


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit, viewModel: LoginViewModel, context: Context = LocalContext.current
) {
    Scaffold(topBar = { LoginTopAppBar() }) { padding ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LoginContent(
            email = uiState.email,
            password = uiState.password,
            onEvent = viewModel::onEvent,
            modifier = Modifier.padding(padding)
        )
        uiState.userMessage?.let {
            CustomAlertDialog(
                data = DialogData(textConfirm = "Close",
                    textTitle = "Error",
                    textMessage = it,
                    onConfirmClick = { viewModel.onEvent(LoginEvent.ClearMessage) })
            )
        }
        LaunchedEffect(key1 = Unit) {
            viewModel.uiEvent.collect() { event ->
                when (event) {
                    is LoginUiEvent.LoginSuccess -> onLoginSuccess()
                    is LoginUiEvent.Downloaded -> context.saveRingtones(event.stream)
                }
            }
        }
    }
}

@Composable
private fun LoginContent(
    email: String,
    password: String,
    onEvent: (LoginEvent) -> Unit,
    context: Context = LocalContext.current,
    scope: CoroutineScope = rememberCoroutineScope(),
    modifier: Modifier = Modifier
) {

    var firstFile by remember { mutableStateOf<File?>(null) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomOutlinedTextField(
            value = email,
            onValueChange = { onEvent.invoke(LoginEvent.UpdateEmail(it)) },
            placeHolder = "Email"
        )
        VerticalSpacer(height = 16f)
        CustomOutlinedTextField(
            value = password,
            onValueChange = { onEvent.invoke(LoginEvent.UpdatePassword(it)) },
            placeHolder = "Password"
        )
        VerticalSpacer(height = 16f)
        PrimaryButton(label = "Login", onClick = { onEvent.invoke(LoginEvent.Login) })
        PrimaryButton(label = "Download", onClick = {
            onEvent.invoke(LoginEvent.Download)
//            val request =
//                DownloadManager.Request(Uri.parse("https://file-examples.com/storage/fefb234bc0648a3e7a1a47d/2017/11/file_example_MP3_700KB.mp3"))
//            request.setDescription("Downloading")
//            request.setMimeType("audio/MP3")
//            request.setTitle("file_example_MP3_700KB")
//            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//            request.setDestinationInExternalPublicDir(
//                Environment.DIRECTORY_RINGTONES, "/file_example_MP3_700KB.mp3"
//            )
//            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
//            manager?.enqueue(request)
        })
        PrimaryButton(label = "Update metadata", onClick = {

//            val resolver = context.contentResolver
//            val contentValues = ContentValues().apply {
//                put(MediaStore.MediaColumns.DISPLAY_NAME, "doc.txt")
//                put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
//                put(MediaStore.MediaColumns.RELATIVE_PATH, "Documents")
//            }
//
//            val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
//
//            uri?.let {
//                resolver.openOutputStream(it).use {
//                    // Write file
//                    it?.write("line1".toByteArray(Charset.defaultCharset()))
//                    it?.write("line2".toByteArray(Charset.defaultCharset()))
//                    it?.close()
//                }
//
//            }

//            firstFile?.let { chosenFile ->
//                Log.d("vinnne", "chosenFile ${chosenFile.absolutePath}")
//
//                val values = ContentValues()
//                values.put(MediaStore.MediaColumns.DISPLAY_NAME, chosenFile.getName())
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    values.put(MediaStore.Audio.Media.RELATIVE_PATH, chosenFile.getAbsolutePath())
//                } else {
//                    values.put(MediaStore.MediaColumns.DATA, chosenFile.getAbsolutePath())
//                }
//                values.put(MediaStore.MediaColumns.TITLE, chosenFile.getName())
//                values.put(MediaStore.MediaColumns.SIZE, chosenFile.length())
//                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3")
//                values.put(AudioColumns.ARTIST, "Vinh")
//                values.put(AudioColumns.IS_RINGTONE, true)
//                values.put(AudioColumns.IS_NOTIFICATION, false)
//                values.put(AudioColumns.IS_ALARM, false)
//                values.put(AudioColumns.IS_MUSIC, true)
//                val contentUri =
//                    FileProvider.getUriForFile(context, context.packageName + ".provider", chosenFile)
//                Log.d("vinnne", "contentUri: " + contentUri.toString())
//                val newUri = context.contentResolver.insert(contentUri,values)
//                Log.d("vinnne", "newUri: " + newUri.toString())
//
//            }
        })
        PrimaryButton(label = "Set ringtone", onClick = {
            firstFile?.let {
                if (Settings.System.canWrite(context)) {
                    Log.d("vinnne", "permission granted already")
                    RingtoneManager.setActualDefaultRingtoneUri(
                        context,
                        RingtoneManager.TYPE_RINGTONE,
                        Uri.fromFile(it)
                    )
                } else {
                    Log.d("vinnne", "request permission")
                    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                    intent.data = Uri.parse("package:" + context.packageName)
                    context.startActivity(intent)
                }
            }
        })
    }
    LaunchedEffect(key1 = Unit) {
        val folder = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES)
                .toString() + "/ringtonedownload"
        )
        Log.d("vinnne", "folder path: ${folder.absolutePath}")

        if (folder.exists()) {
            val list = folder.listFiles()
            Log.d("vinnne", "size ${list?.size}")
            firstFile = list?.firstOrNull()
            Log.d("vinnne", "firstFile ${firstFile?.absolutePath}")

            list?.forEach {
                Log.d("vinnne", it.name)
            }
            return@LaunchedEffect
        }
        Log.d("vinnne", "not exist")
    }
}