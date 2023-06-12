package id.dipoengoro.dcontact.ui.utils

import id.dipoengoro.dcontact.data.ContactDetails

data class ContactUiState(
    val contactDetails: ContactDetails = ContactDetails(),
    val isEntryValid: Boolean = false,
)