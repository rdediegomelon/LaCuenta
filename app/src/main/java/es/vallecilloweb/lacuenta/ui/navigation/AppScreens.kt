package es.vallecilloweb.lacuenta.ui.navigation

sealed class AppScreens(val route:String) {

    object ListCuentasScreen: AppScreens("listcuentascreen")
    object CuentaDetailScreen: AppScreens("cuentadetailscreen")
    object AddCuentaScreen: AppScreens("addcuentascreen")
}