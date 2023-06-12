package id.dipoengoro.dcontact.data.repository.offline

import id.dipoengoro.dcontact.data.Contact
import id.dipoengoro.dcontact.data.database.ContactDao
import id.dipoengoro.dcontact.data.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class OfflineContactsRepository(private val contactDao: ContactDao) : ContactsRepository {
    override fun getAllContacts(): Flow<List<Contact>> = contactDao.getAllContacts()

    override fun getContact(contactId: Long): Flow<Contact> = contactDao.getContact(contactId)

    override suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    override suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    override suspend fun deleteContact(contactId: Long, deleteTime: Long) =
        contactDao.deleteContact(contactId, deleteTime)
}