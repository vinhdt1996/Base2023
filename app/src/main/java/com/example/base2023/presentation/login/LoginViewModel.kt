package com.example.base2023.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base2023.data.repository.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow().shareIn(viewModelScope, SharingStarted.Lazily)
    private suspend fun sendEvent(event: LoginUiEvent) = _uiEvent.send(event)
    private fun sendEventAsync(event: LoginUiEvent) = viewModelScope.launch { _uiEvent.send(event) }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> validateUserInput()
            is LoginEvent.Download -> download()
            is LoginEvent.UpdateEmail -> updateEmail(event.email)
            is LoginEvent.UpdatePassword -> updatePassword(event.password)
            is LoginEvent.ClearMessage -> clearMessage()
        }
    }

    private fun updateEmail(newEmail: String) {
        _uiState.update {
            it.copy(email = newEmail)
        }
    }

    private fun updatePassword(newPassword: String) {
        _uiState.update {
            it.copy(password = newPassword)
        }
    }

    //        https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3


    private fun download() {
//https://file-examples.com/storage/fefb234bc0648a3e7a1a47d/2017/11/file_example_MP3_700KB.mp3
        viewModelScope.launch {
            Log.d("vinnne", "launch")
            try {
                val response = downloadRepository.downloadFile("1-jai-shri-ram-ringtone.mp3")
                Log.d("vinnne", "responded")
                sendEvent(LoginUiEvent.Downloaded(response.byteStream()))
//                saveFile(response, "VinhNe.mp3")

            } catch (e: Exception) {
                Log.d("vinnne", "Exception")

            }
        }
    }

//    private fun saveFile(body: ResponseBody, pathWhereYouWantToSaveFile: String): String {
//        val myFile = File(
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
//            pathWhereYouWantToSaveFile
//        )
//        var input: InputStream? = null
//        try {
//            input = body.byteStream()
//            val fos = FileOutputStream(myFile.path)
//            fos.use { output ->
//                val buffer = ByteArray(4 * 1024) // or other buffer size
//                var read: Int
//                while (input.read(buffer).also { read = it } != -1) {
//                    output.write(buffer, 0, read)
//                }
//                output.flush()
//            }
//            Log.d("vinnne", "saved ${myFile.path}")
//
////            val values = ContentValues().apply {
////                put(MediaStore.Audio.Media.IS_RINGTONE, true)
////            }
////
////            val uri = MediaStore.Audio.Media.getContentUriForPath(
////                myFile
////                    .getAbsolutePath()
////            )
////            getContentResolver().delete(
////                uri,
////                (MediaStore.MediaColumns.DATA + "=\""
////                        + k.getAbsolutePath()) + "\"", null
////            )
////            val newUri: Uri = getContentResolver().insert(uri, values)
//
//            return myFile.path
//        } catch (e: Exception) {
//            Log.d("vinnne", "Exception")
//            Log.e("saveFile", e.toString())
//        } finally {
//            Log.d("vinnne", "saved finally")
//            input?.close()
//        }
//        Log.d("vinnne", "empty")
//        return ""
//    }

    private fun validateUserInput() {
        if (uiState.value.email.isBlank() || uiState.value.password.isBlank()) {
            _uiState.update {
                it.copy(userMessage = "Thông tin đăng nhập không được bỏ trống")
            }
            return
        }
        login()
    }

    private fun clearMessage() {
        _uiState.update {
            it.copy(userMessage = null)
        }
    }

    private fun login() {
        sendEventAsync(LoginUiEvent.LoginSuccess)
    }
}