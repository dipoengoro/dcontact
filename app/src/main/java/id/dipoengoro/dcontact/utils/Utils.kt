package id.dipoengoro.dcontact.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import id.dipoengoro.dcontact.ContactApplication
import id.dipoengoro.dcontact.data.Contact
import id.dipoengoro.dcontact.data.ContactDetails
import id.dipoengoro.dcontact.ui.utils.ContactUiState
import java.io.IOException
import java.util.Locale

fun ContactDetails.toContact(): Contact = Contact(
    id = id,
    name = name,
    alias = alias,
    phoneNumber = phoneNumber,
    email = email,
    note = note,
    profilePicture = profilePicture,
    color = color,
    addedTime = addedTime,
    editedTime = editedTime,
    isDeleted = isDeleted,
    deletedTime = deletedTime
)

fun Contact.toContactDetails(): ContactDetails = ContactDetails(
    id = id.toLong(),
    name = name.toString(),
    alias = alias.toString(),
    phoneNumber = phoneNumber.toString(),
    email = email.toString(),
    note = note.toString(),
    profilePicture = profilePicture.toString(),
    color = color,
    addedTime = addedTime,
    editedTime = editedTime,
    isDeleted = isDeleted,
    deletedTime = deletedTime
)

fun ContactDetails.validateInput(): Boolean = with(this) {
    name.isNotEmpty() && phoneNumber.isNotEmpty()
}

fun CreationExtras.contactApplication(): ContactApplication =
    this[AndroidViewModelFactory.APPLICATION_KEY] as ContactApplication

suspend fun performDatabaseOperationSafely(databaseOperation: suspend () -> Unit) {
    try {
        databaseOperation.invoke()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun String.getInitial(): String = if (this.isNotEmpty()) {
    this.split(" ").let { listName ->
        when (listName.size) {
            1 -> listName.first().takeFirstAndUp()
            else -> listName.let {
                "${it.first().takeFirstAndUp()}${it.last().takeFirstAndUp()}"
            }
        }
    }
} else ""

fun String.takeFirstAndUp(): String = this.take(1).uppercase(Locale.getDefault())

fun Contact.intentChat(intent: (String) -> Unit): String =
    this.phoneNumber.replaceFirstChar { "62" }.apply {
        try {
            intent("whatsapp://send?phone=$this")
        } catch (e: Exception) {
            e.printStackTrace()
            "com.whatsapp".apply {
                try {
                    intent("market://details?id=$this@apply")
                } catch (e: android.content.ActivityNotFoundException) {
                    intent("https://play.google.com/store/apps/details?id=$this@apply")
                }
            }
        }
    }

fun Context.startActivity(uri: String): Unit =
    this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))