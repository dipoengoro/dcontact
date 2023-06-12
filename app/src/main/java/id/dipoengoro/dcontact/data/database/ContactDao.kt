package id.dipoengoro.dcontact.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.dipoengoro.dcontact.data.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contact: Contact)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE is_deleted = 0 ORDER BY name ASC")
    fun getAllContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE id = :contactId")
    fun getContact(contactId: Long): Flow<Contact>

    @Query("UPDATE contacts SET is_deleted = 1, delete_time = :deletedTime WHERE id = :contactId")
    suspend fun deleteContact(contactId: Long, deletedTime: Long)
}