package id.dipoengoro.dcontact.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import id.dipoengoro.dcontact.data.ContactDetails
import id.dipoengoro.dcontact.ui.states.AliasState
import id.dipoengoro.dcontact.ui.states.EmailState
import id.dipoengoro.dcontact.ui.states.NameState
import id.dipoengoro.dcontact.ui.states.NoteState
import id.dipoengoro.dcontact.ui.states.PhoneNumberState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContactInputForm(
    contactDetails: ContactDetails,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonClick: () -> Unit = {},
    buttonEnabled: Boolean = false,
    updateContactDetails: (ContactDetails) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val nameState = remember { NameState(initialValue = contactDetails.name) }
        val aliasState = remember { AliasState(initialValue = contactDetails.alias) }
        val phoneNumberState = remember { PhoneNumberState(initialValue = contactDetails.phoneNumber) }
        val emailState = remember { EmailState(initialValue = contactDetails.email) }
        val noteState = remember { NoteState(initialValue = contactDetails.note) }

/**        OutlinedTextField(
//            value = contactDetails.name,
//            onValueChange = {
//                onValueChange(contactDetails.copy(name = it))
//            },
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Next,
//                capitalization = KeyboardCapitalization.Words
//            ),
//            keyboardActions = KeyboardActions(onNext = {
//                focusManager.moveFocus(FocusDirection.Down)
//            }),
//            label = { Text(text = "Name") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
        ) **/
        NameTextField(
            textFieldState = nameState,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            },
            onValueChange = {
                nameState.text = it.trim()
                updateContactDetails(contactDetails.copy(name = nameState.text))
            },
            enabled = enabled
        )
/**        OutlinedTextField(
//            value = contactDetails.alias,
//            onValueChange = {
//                onValueChange(contactDetails.copy(alias = it))
//            },
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Next,
//                capitalization = KeyboardCapitalization.Words
//            ),
//            keyboardActions = KeyboardActions(onNext = {
//                focusManager.moveFocus(FocusDirection.Down)
//            }),
//            label = { Text(text = "Name alias") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
        ) **/
        AliasTextField(
            textFieldState = aliasState,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            },
            onValueChange = {
                aliasState.text = it.trim()
                updateContactDetails(contactDetails.copy(alias = aliasState.text))
            },
            enabled = enabled
        )
/**        OutlinedTextField(
//            value = contactDetails.phoneNumber,
//            onValueChange = {
//                if (it.isEmpty() or it.matches(Regex("^[0-9]+$")))
//                    onValueChange(contactDetails.copy(phoneNumber = it))
//            },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Decimal,
//                imeAction = ImeAction.Next
//            ),
//            keyboardActions = KeyboardActions(onNext = {
//                focusManager.moveFocus(FocusDirection.Down)
//            }),
//            label = { Text(text = "Phone number") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        ) **/
        PhoneNumberTextField(
            textFieldState = phoneNumberState,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            },
            onValueChange = {
                if (it.isEmpty() or PhoneNumberState.checkPhoneNumberValidation(it)) {
                    phoneNumberState.text = it
                    updateContactDetails(contactDetails.copy(phoneNumber = phoneNumberState.text))
                }
            },
            enabled = enabled
        )
/**        OutlinedTextField(
//            value = contactDetails.email,
//            onValueChange = {
//                onValueChange(contactDetails.copy(email = it))
//            },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Next
//            ),
//            keyboardActions = KeyboardActions(onNext = {
//                focusManager.moveFocus(FocusDirection.Down)
//            }),
//            label = { Text(text = "Email") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        ) **/

        EmailTextField(
            textFieldState = emailState,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            },
            onValueChange = {
                emailState.text = it
                updateContactDetails(contactDetails.copy(email = emailState.text))
            },
            enabled = enabled
        )
/**        OutlinedTextField(
//            value = contactDetails.note,
//            onValueChange = {
//                onValueChange(contactDetails.copy(note = it))
//            },
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Done,
//                capitalization = KeyboardCapitalization.Sentences
//            ),
//            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
//            label = { Text(text = "Note") },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = false,
//            maxLines = 5
//        ) **/
        NoteTextField(
            textFieldState = noteState,
            onImeAction = {
                keyboardController?.hide()
            },
            onValueChange = {
                noteState.text = it
                updateContactDetails(contactDetails.copy(note = noteState.text))
            },
            enabled = enabled
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = buttonClick,
            enabled = buttonEnabled,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}