package id.dipoengoro.dcontact.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.dipoengoro.dcontact.ui.navigation.ContactNavHost

@Composable
fun ContactApp(navController: NavHostController = rememberNavController()) {
    ContactNavHost(navController = navController)
}