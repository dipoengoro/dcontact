package id.dipoengoro.dcontact.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.dipoengoro.dcontact.R
import id.dipoengoro.dcontact.data.Contact
import id.dipoengoro.dcontact.data.ContactDetails
import id.dipoengoro.dcontact.ui.components.ContactInputForm
import id.dipoengoro.dcontact.ui.components.ContactTopAppBar
import id.dipoengoro.dcontact.ui.navigation.NavigationDestination
import id.dipoengoro.dcontact.ui.utils.AppViewModelProvider
import id.dipoengoro.dcontact.utils.toContact
import id.dipoengoro.dcontact.utils.toContactDetails
import kotlinx.coroutines.launch

object ContactDetailDestination : NavigationDestination {
    override val route: String
        get() = "contact_detail"
    override val titleRes: Int
        get() = R.string.detail_contact
    const val contactIdArg = "contactId"
    val routeWithArgs = "$route/{$contactIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    navigateToEditScreen: (Long) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    contactId: Long = 0,
    viewModel: ContactDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Log.d("Detail", "ContactDetailScreen: $contactId")

    val contact = viewModel.getContact(contactId).collectAsState(initial = ContactDetails().toContact())

    Log.d("Detail", "ContactDetailScreen: $contact")

//    Log.d("Detail", "ContactDetailScreen: $contactDetails")

    Scaffold(
        topBar = {
            ContactTopAppBar(
                title = stringResource(id = ContactDetailDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            val contactDetails = remember { mutableStateOf(contact.value.toContactDetails()) }
            ContactInputForm(
//                contactDetails = viewModel.uiState.contactDetails,
                contactDetails =contactDetails.component1(),
                updateContactDetails = viewModel::updateUiState,
//                buttonEnabled = viewModel.uiState.isEntryValid,
                buttonEnabled = false,
                buttonClick = {
                    coroutineScope.launch {
                        viewModel.saveContact()
                        navigateBack()
                    }
                },
                enabled = false
            )
        }
    }
}