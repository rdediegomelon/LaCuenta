package es.vallecilloweb.lacuenta.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import es.vallecilloweb.lacuenta.ui.navigation.AppNavigation
import es.vallecilloweb.lacuenta.ui.screens.ListCuentasScreen
import es.vallecilloweb.lacuenta.ui.theme.LaCuentaTheme
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel

class MainActivity : ComponentActivity() {

    private val _cuentaViewModel: CuentaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LaCuentaTheme {
                AppNavigation(_cuentaViewModel)
            }
        }
    }
}


