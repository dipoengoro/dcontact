package id.dipoengoro.dcontact.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import id.dipoengoro.dcontact.data.ContactDetails
import id.dipoengoro.dcontact.ui.states.TextFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    label: String,
    textFieldState: TextFieldState,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    supportingText: @Composable () -> Unit = {},
    contactDetails: ContactDetails = ContactDetails(),
    onValueChange: (String) -> Unit = { textFieldState.text = it },
) {
    OutlinedTextField(
        value = textFieldState.text,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = typography.bodyMedium
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                textFieldState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused)
                    textFieldState.enableShowErrors()
            },
        textStyle = typography.bodyMedium,
        isError = textFieldState.showErrors(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else 3,
        supportingText = supportingText
    )
}