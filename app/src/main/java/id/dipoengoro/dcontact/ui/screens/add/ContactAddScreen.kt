package id.dipoengoro.dcontact.ui.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.dipoengoro.dcontact.R
import id.dipoengoro.dcontact.data.color.ContactColor.pastelColors
import id.dipoengoro.dcontact.ui.components.ContactInputForm
import id.dipoengoro.dcontact.ui.components.ContactTopAppBar
import id.dipoengoro.dcontact.ui.navigation.NavigationDestination
import id.dipoengoro.dcontact.ui.utils.AppViewModelProvider
import id.dipoengoro.dcontact.ui.utils.toColor
import kotlinx.coroutines.launch

object ContactAddDestination : NavigationDestination {
    override val route: String
        get() = "contact_add"
    override val titleRes: Int
        get() = R.string.add_contact
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactAddScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: ContactAddViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ContactTopAppBar(
                title = stringResource(id = ContactAddDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
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
            ContactInputForm(
                contactDetails = viewModel.uiState.contactDetails,
                updateContactDetails = viewModel::updateUiState,
                buttonEnabled = viewModel.uiState.isEntryValid,
                enabled = true,
                buttonClick = {
                    coroutineScope.launch {
                        viewModel.saveContact()
                        navigateBack()
                    }
                }
            )
        }
    }
}