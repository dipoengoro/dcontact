package id.dipoengoro.dcontact.ui.utils

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import id.dipoengoro.dcontact.ui.screens.add.ContactAddViewModel
import id.dipoengoro.dcontact.ui.screens.detail.ContactDetailViewModel
import id.dipoengoro.dcontact.ui.screens.home.ContactHomeViewModel
import id.dipoengoro.dcontact.utils.contactApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ContactHomeViewModel(
                contactApplication().container.contactsRepository
            )
        }
        initializer {
            ContactAddViewModel(
                contactApplication().container.contactsRepository
            )
        }
        initializer {
            ContactDetailViewModel(
                contactApplication().container.contactsRepository
            )
        }
    }
}