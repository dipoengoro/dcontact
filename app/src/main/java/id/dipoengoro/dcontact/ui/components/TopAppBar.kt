package id.dipoengoro.dcontact.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import id.dipoengoro.dcontact.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_button)
                )
            }
        }
    )
}