package id.dipoengoro.dcontact.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val alias: String = "",
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String,
    val email: String = "",
    val note: String = "",
    @ColumnInfo(name = "profile_picture")
    val profilePicture: String? = null,
    val color: String,
    @ColumnInfo(name = "add_time")
    val addedTime: Long,
    @ColumnInfo(name = "edit_time")
    val editedTime: Long,
    @ColumnInfo(name = "is_deleted")
    val isDeleted: Boolean = false,
    @ColumnInfo(name = "delete_time")
    val deletedTime: Long = 0,
)