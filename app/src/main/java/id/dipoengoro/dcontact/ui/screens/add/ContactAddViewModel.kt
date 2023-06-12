package id.dipoengoro.dcontact.ui.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import id.dipoengoro.dcontact.data.ContactDetails
import id.dipoengoro.dcontact.data.color.ContactColor
import id.dipoengoro.dcontact.data.repository.ContactsRepository
import id.dipoengoro.dcontact.ui.utils.ContactUiState
import id.dipoengoro.dcontact.utils.performDatabaseOperationSafely
import id.dipoengoro.dcontact.utils.toContact
import id.dipoengoro.dcontact.utils.validateInput

class ContactAddViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {
    var uiState by mutableStateOf(ContactUiState())
        private set

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