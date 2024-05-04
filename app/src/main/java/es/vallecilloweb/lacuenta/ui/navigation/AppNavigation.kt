package es.vallecilloweb.lacuenta.ui.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.vallecilloweb.lacuenta.ui.screens.ListCuentasScreen
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel
import androidx.activity.viewModels



@Composable
fun AppNavigation(cuentaViewModel: CuentaViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.ListCuentasScreen.route){
        composable(AppScreens.ListCuentasScreen.route){
            ListCuentasScreen(viewModel = cuentaViewModel , navController = navController)
        }
    }
}