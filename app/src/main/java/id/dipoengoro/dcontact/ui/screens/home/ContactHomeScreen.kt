package id.dipoengoro.dcontact.ui.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.dipoengoro.dcontact.R
import id.dipoengoro.dcontact.data.Contact
import id.dipoengoro.dcontact.ui.components.ContactTopAppBar
import id.dipoengoro.dcontact.ui.navigation.NavigationDestination
import id.dipoengoro.dcontact.ui.theme.green_whatsapp
import id.dipoengoro.dcontact.ui.theme.md_theme_dark_errorContainer
import id.dipoengoro.dcontact.ui.utils.AppViewModelProvider
import id.dipoengoro.dcontact.ui.utils.getColor
import id.dipoengoro.dcontact.utils.getInitial
import id.dipoengoro.dcontact.utils.intentChat
import id.dipoengoro.dcontact.utils.startActivity
import kotlinx.coroutines.launch


object HomeDestination : NavigationDestination {
    override val route: String
        get() = "home"
    override val titleRes: Int
        get() = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToContactAdd: () -> Unit,
    navigateToContactDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ContactHomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val homeUiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            ContactTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToContactAdd) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add contact")
            }
        }
    ) { innerPadding ->
        var selectedIndex by remember { mutableStateOf(-1) }
        LazyColumn(
            modifier = modifier.padding(16.dp),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(
                homeUiState.contactList,
                key = { index, _: Contact -> index }) { index, contact ->
                val context = LocalContext.current
                val canExpand = selectedIndex == index
                val coroutineScope = rememberCoroutineScope()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = { navigateToContactDetail(contact.id) }
                        )
                        .then(
                            if (canExpand)
                                Modifier
                                    .border(
                                        width = 1.dp,
                                        color = colorScheme.inverseSurface,
                                        shape = ShapeDefaults.Small
                                    )
                            else Modifier
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(contact.color.getColor())
                                .size(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = contact.name.getInitial(),
                                style = typography.titleLarge.copy(
                                    fontWeight = FontWeight.Thin,
                                    color = colorScheme.surface,
                                    fontSize = 24.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            modifier = Modifier.weight(4f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = if (contact.alias.isNotEmpty()) "${contact.name} (${contact.alias})"
                                else contact.name,
                                style = typography.titleMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            if (contact.note.isNotEmpty())
                                Text(
                                    text = contact.note,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = typography.bodySmall
                                )
                        }
                        IconButton(
                            onClick = {
                                selectedIndex = if (selectedIndex == index) -1 else index
                            },
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                imageVector = if (canExpand) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = if (canExpand) "Show less" else "Show more"
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                            .then(
                                if (canExpand)
                                    Modifier
                                else
                                    Modifier
                                        .height(0.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        OutlinedButton(
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.softDeleteContact(contact)
                                }
                            },
                            border = BorderStroke(1.dp, md_theme_dark_errorContainer),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = colorScheme.errorContainer,
                                contentColor = colorScheme.onErrorContainer
                            )
                        ) {
                            Text(text = "Delete")
                        }
                        Button(
                            onClick = { contact.intentChat { context.startActivity(it) } },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = green_whatsapp,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Chat")
                        }
                    }
                }
            }
        }
    }
}