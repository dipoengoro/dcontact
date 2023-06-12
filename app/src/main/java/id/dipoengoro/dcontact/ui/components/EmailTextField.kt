package id.dipoengoro.dcontact.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import id.dipoengoro.dcontact.ui.states.TextFieldState

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    label: String = "Email",
    textFieldState: TextFieldState,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email,
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
    onValueChange: (String) -> Unit = {
        textFieldState.text = it
    }
) = AppTextField(
    label = label,
    textFieldState = textFieldState,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    modifier = modifier,
    enabled = enabled,
    singleLine = singleLine,
    supportingText = supportingText,
    onValueChange = onValueChange
)