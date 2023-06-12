package id.dipoengoro.dcontact.data.container

import android.content.Context
import id.dipoengoro.dcontact.data.database.ContactDatabase
import id.dipoengoro.dcontact.data.repository.ContactsRepository
import id.dipoengoro.dcontact.data.repository.offline.OfflineContactsRepository

interface AppContainer {
    val contactsRepository: ContactsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val contactsRepository: ContactsRepository by lazy {
        OfflineContactsRepository(ContactDatabase.getDatabase(context).contactDao())
    }
}