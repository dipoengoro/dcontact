package id.dipoengoro.dcontact.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import id.dipoengoro.dcontact.data.Contact
import id.dipoengoro.dcontact.data.ContactDetails
import id.dipoengoro.dcontact.data.color.ContactColor
import id.dipoengoro.dcontact.data.repository.ContactsRepository
import id.dipoengoro.dcontact.ui.utils.ContactUiState
import id.dipoengoro.dcontact.utils.performDatabaseOperationSafely
import id.dipoengoro.dcontact.utils.toContact
import id.dipoengoro.dcontact.utils.validateInput
import kotlinx.coroutines.flow.Flow

/**
class ContactDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val contactsRepository: ContactsRepository,
) : ViewModel() {

    private val contactId: Long =
        checkNotNull(savedStateHandle[ContactDetailDestination.contactIdArg])

    private val currentContact = contactsRepository.getContact(contactId)
    private fun collectContactDetails(): ContactDetails {
        var contactDetails = ContactDetails()
        viewModelScope.launch {
            val defferedContactDetails = async { currentContact.first().toContactDetails() }
            contactDetails = defferedContactDetails.await()
        }
        return contactDetails
    }

    var uiState by mutableStateOf(
        ContactUiState(
            contactDetails = collectContactDetails(),
            isEntryValid = true
        )
    )
        private set

    fun updateUiState(contactDetails: ContactDetails) {
        uiState = ContactUiState(contactDetails = contactDetails)
    }

    suspend fun saveContact() {
        if (uiState.contactDetails.validateInput()) {
            val currentTime = System.currentTimeMillis()
            performDatabaseOperationSafely {
                contactsRepository.updateContact(
                    uiState.contactDetails.copy(
                        editedTime = currentTime
                    ).toContact()
                )
            }
        }
    }

    companion object {
        private val TIMEOUT_DURATION = 5.seconds
    }
}
**/

class ContactDetailViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {
    var uiState by mutableStateOf(ContactUiState())
        private set

    fun getContact(id: Long): Flow<Contact> {
        return contactsRepository.getContact(id)
    }

    fun updateUiState(contactDetails: ContactDetails) {
        uiState = ContactUiState(
            contactDetails = contactDetails,
            isEntryValid = contactDetails.validateInput()
        )
    }

    suspend fun saveContact() {
        if (uiState.contactDetails.validateInput()) {
            val currentTime = System.currentTimeMillis()
            val currentColor = ContactColor.pastelColors.random().first
            performDatabaseOperationSafely {
                contactsRepository.insertContact(
                    uiState.contactDetails.copy(
                        color = currentColor,
                        addedTime = currentTime,
                        editedTime = currentTime
                    ).toContact()
                )
            }
        }
    }
}