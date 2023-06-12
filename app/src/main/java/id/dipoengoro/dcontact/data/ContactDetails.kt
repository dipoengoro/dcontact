package id.dipoengoro.dcontact.data

import androidx.room.ColumnInfo
import id.dipoengoro.dcontact.data.Contact

data class ContactDetails(
    val id: Long = 0,
    val name: String = "",
    val alias: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val note: String = "",
    val profilePicture: String? = null,
    val color: String = "",
    val addedTime: Long = 0,
    val editedTime: Long = 0,
    val isDeleted: Boolean = false,
    val deletedTime: Long = 0,
)