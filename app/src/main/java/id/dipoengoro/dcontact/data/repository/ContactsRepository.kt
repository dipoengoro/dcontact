package id.dipoengoro.dcontact.data.repository

import id.dipoengoro.dcontact.data.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getAllContacts(): Flow<List<Contact>>
    fun getContact(contactId: Long): Flow<Contact>
    suspend fun insertContact(contact: Contact)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contactId: Long, deleteTime: Long)
}