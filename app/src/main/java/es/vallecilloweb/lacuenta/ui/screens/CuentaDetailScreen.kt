package es.vallecilloweb.lacuenta.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import es.vallecilloweb.lacuenta.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuentaDetailScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable { navController.popBackStack() })},
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        },
        content = { padding -> CuentaDetail(padding)}
    )
}

@Composable
fun CuentaDetail(padding: PaddingValues){
    Column( modifier= Modifier.padding(padding).fillMaxWidth()) {
        Text("Prueba")
    }
}

@Composable
@Preview
fun preview(){
    CuentaDetailScreen(navController = rememberNavController())
}