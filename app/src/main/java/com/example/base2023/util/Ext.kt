package com.example.base2023.util

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.charset.Charset

fun Context.saveRingtones(stream: InputStream) {
    val resolver = contentResolver
    val current = System.currentTimeMillis()

    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "Vinh2$current.mp3")
        put(MediaStore.MediaColumns.TITLE, "Vinh2$current.mp3")
        put(MediaStore.MediaColumns.MIME_TYPE, "audio/MP3")
        put(MediaStore.MediaColumns.RELATIVE_PATH, "Ringtones/ringtonedownload")
        put(MediaStore.Audio.Media.ARTIST, "Madonna")
        put(MediaStore.Audio.Media.IS_RINGTONE, true)
        put(MediaStore.Audio.Media.IS_NOTIFICATION, false)
        put(MediaStore.Audio.Media.IS_ALARM, false)
        put(MediaStore.Audio.Media.IS_MUSIC, true)
    }

    val newUri = resolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValues)
    Log.d("vinnne", "newUri: $newUri")

    newUri?.let {
        val ops = resolver.openOutputStream(it)
        try {
            ops?.let { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (stream.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
                output.close()
                Log.d("vinnne", "Done")
            }
        } catch (e: Exception) {
            ops?.close()
        } finally {
            ops?.close()
        }

    }


//    val current = System.currentTimeMillis()
//    val myFile = File(
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
//        "VinhNew$current.mp3"
//    )
//    try {
//        val fos = FileOutputStream(myFile.path)
//        fos.use { output ->
//            val buffer = ByteArray(4 * 1024) // or other buffer size
//            var read: Int
//            while (stream.read(buffer).also { read = it } != -1) {
//                output.write(buffer, 0, read)
//            }
//            output.flush()
//        }
//        Log.d("vinnne", "saved ${myFile.path}")
//        val uri = MediaStore.Audio.Media.getContentUriForPath(myFile.absolutePath)!!
//        val values = ContentValues()
//
//        values.put(MediaStore.Audio.Media.DISPLAY_NAME, myFile.name)
//        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp3")
//        values.put(MediaStore.Audio.Media.IS_PENDING, 1)
//        contentResolver.insert(uri, values)
//        values.clear()
//        values.put(MediaStore.Audio.Media.IS_PENDING, 0)
//        contentResolver.update(uri, values, null, null)

//        val uri = Uri.fromFile(myFile)
//        val uri2 = getImageContentUri(context = this,myFile)!!
//        Log.d("vinnne","uri: $uri")
//        Log.d("vinnne","uri2: $uri2")
//        values.put(MediaStore.MediaColumns.DATA, myFile.absolutePath)
//        values.put(MediaStore.Audio.Media.IS_RINGTONE, true)
//        val successMediaStore = contentResolver.update(
//            uri2,
//            values,
//            null,
//            null
//        ) == 1
//        Log.d("vinnne", "successMediaStore $successMediaStore")
    //        val values = ContentValues().apply {
//            put(MediaStore.MediaColumns.DISPLAY_NAME, myFile.name)
//            put(MediaStore.MediaColumns.DATA, myFile.absolutePath)
//            put(MediaStore.MediaColumns.TITLE, myFile.name)
//            put(MediaStore.Audio.Media.DATE_ADDED, current)
//            put(MediaStore.MediaColumns.SIZE, 215454)
//            put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3")
//            put(MediaStore.Audio.Media.ARTIST, "Madonna")
//            put(MediaStore.Audio.Media.IS_RINGTONE, true)
//            put(MediaStore.Audio.Media.IS_NOTIFICATION, false)
//            put(MediaStore.Audio.Media.IS_ALARM, false)
//            put(MediaStore.Audio.Media.IS_MUSIC, false)
//        }
//    } catch (e: Exception) {
//        Log.d("vinnne", "Exception")
//        e.printStackTrace()
//    } finally {
//        Log.d("vinnne", "saved finally")
//        stream.close()
//    }
}

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