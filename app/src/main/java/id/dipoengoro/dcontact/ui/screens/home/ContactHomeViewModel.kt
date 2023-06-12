package id.dipoengoro.dcontact.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.dipoengoro.dcontact.data.Contact
import id.dipoengoro.dcontact.data.ContactDetails
import id.dipoengoro.dcontact.data.color.ContactColor
import id.dipoengoro.dcontact.data.repository.ContactsRepository
import id.dipoengoro.dcontact.ui.utils.ContactUiState
import id.dipoengoro.dcontact.utils.performDatabaseOperationSafely
import id.dipoengoro.dcontact.utils.toContact
import id.dipoengoro.dcontact.utils.validateInput
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ContactHomeViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {
    val uiState: StateFlow<HomeUiState> =
        contactsRepository.getAllContacts().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_DURATION),
                initialValue = HomeUiState()
            )

    suspend fun softDeleteContact(contact: Contact) {
        val currentTime = System.currentTimeMillis()
        performDatabaseOperationSafely {
            contactsRepository.deleteContact(contact.id, currentTime)
        }
    }

    companion object {
        private val TIMEOUT_DURATION = 5.seconds
    }
}

data class HomeUiState(
    val contactList: List<Contact> = listOf()
)