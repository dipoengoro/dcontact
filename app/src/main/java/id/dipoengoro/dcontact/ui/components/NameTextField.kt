package id.dipoengoro.dcontact.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import id.dipoengoro.dcontact.ui.states.NameState
import id.dipoengoro.dcontact.ui.states.TextFieldState

@Composable
fun NameTextField(
    modifier: Modifier = Modifier,
    label: String = "Name",
    textFieldState: TextFieldState,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text,
        capitalization = KeyboardCapitalization.Words
    ),
    enabled: Boolean = true,
    singleLine: Boolean = true,
    onImeAction: () -> Unit = {},
    keyboardActions: KeyboardActions = KeyboardActions(
        onNext = { onImeAction() }
    ),
    supportingText: @Composable () -> Unit = {
        textFieldState.getError()?.let { Text(text = it) }
    },
    onValueChange: (String) -> Unit = { textFieldState.text = it }
) = AppTextField(
    label = label,
    textFieldState = textFieldState,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    enabled = enabled,
    singleLine = singleLine,
    modifier = modifier,
    supportingText = supportingText,
    onValueChange = onValueChange
)