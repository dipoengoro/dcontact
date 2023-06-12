package id.dipoengoro.dcontact.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import id.dipoengoro.dcontact.ui.screens.add.ContactAddDestination
import id.dipoengoro.dcontact.ui.screens.add.ContactAddScreen
import id.dipoengoro.dcontact.ui.screens.detail.ContactDetailDestination
import id.dipoengoro.dcontact.ui.screens.detail.ContactDetailScreen
import id.dipoengoro.dcontact.ui.screens.home.HomeDestination
import id.dipoengoro.dcontact.ui.screens.home.HomeScreen

@Composable
fun ContactNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(navigateToContactAdd = {
                navController.navigate(ContactAddDestination.route)
            }, navigateToContactDetail = {
                navController.navigate("${ContactDetailDestination.route}/$it")
            })
        }
        composable(route = ContactAddDestination.route) {
            ContactAddScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = ContactDetailDestination.routeWithArgs,
            arguments = listOf(
                navArgument(ContactDetailDestination.contactIdArg) {
                    type = NavType.LongType
                })
        ) { backStackEntry ->
            backStackEntry.arguments?.getLong(ContactDetailDestination.contactIdArg, 0)?.let {
                ContactDetailScreen(
                    contactId = it,
                    navigateToEditScreen = {},
                    navigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}