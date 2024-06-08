package es.vallecilloweb.lacuenta.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.vallecilloweb.lacuenta.R
import es.vallecilloweb.lacuenta.data.entities.CuentaModel
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCuentasScreen(navController:NavHostController, viewModel: CuentaViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        },
        floatingActionButton = {
            ListCuentasAddButton(navController){viewModel.onClickAddCuentaButton(navController)}
        },

        content = { padding -> ListCuentas(padding,navController,viewModel)

        }
    )
}

@Composable
fun ListCuentas(padding: PaddingValues, navController:NavHostController,viewModel: CuentaViewModel){

    val cuentas: List<CuentaModel> = viewModel.cuentas

    if (cuentas.isEmpty()){
        Column( modifier= Modifier
            .padding(padding)
            .fillMaxWidth()) {
                Text("Crea tu primera cuenta",fontWeight = FontWeight.Bold,fontSize = 20.sp)
        }
    }
    else {
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            items(cuentas) { cuenta ->
                ListCuentasElement(navController, viewModel, cuenta)
            }
        }
    }
}


@Composable
fun ListCuentasElement(navController:NavHostController,viewModel: CuentaViewModel,cuenta: CuentaModel){
    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Column(
        Modifier
            .border(1.dp, Color.Black)
            .fillMaxWidth()
            .clickable { viewModel.onClickCuentaDetail(navController,cuenta) }) {
        Text(cuenta.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text (dateFormat.format(cuenta.dateCreated))
        if (cuenta.consumiciones.size!=1)
            Text("${cuenta.consumiciones.size} personas")
        else
            Text("${cuenta.consumiciones.size} persona")
        Text("TOTAL: ${cuenta.calculateTotal()} €")
    }
}

@Composable
fun ListCuentasAddButton(navController:NavHostController, onClickAddCuentaButton: (navController:NavHostController) -> Unit) {
    LargeFloatingActionButton(
        onClick = { onClickAddCuentaButton (navController) },
        shape = CircleShape,
    ) {
        Icon(Icons.Filled.Add, "Large floating action button")
    }
}
