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
import es.vallecilloweb.lacuenta.data.CuentaModel
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCuentasScreen(navController:NavHostController, viewModel: CuentaViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                //colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Yellow),
                title = { Text(stringResource(id = R.string.app_name)) },
                /*navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás" )
                    }
                }*/
            )
        },
        floatingActionButton = {
            ListCuentasAddButton(){viewModel.onClickAddCuentaButton()}
        },

        content = { padding -> ListCuentas(padding,viewModel)

        }
    )
}

@Composable
fun ListCuentas(padding: PaddingValues, viewModel: CuentaViewModel){

    val cuentas: List<CuentaModel> = viewModel.cuentas

    LazyColumn(modifier= Modifier
        .padding(padding)
        .fillMaxWidth()) {
        items(cuentas) {
                cuenta -> ListCuentasElement(title = cuenta.name, created = cuenta.dateCreated!!,peoplecount = cuenta.people.size,total=cuenta.calculateTotal() )
        }
    }
}


@Composable
fun ListCuentasElement(title:String, created: LocalDateTime, peoplecount:Int, total:Float){
    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Column(
        Modifier
            .border(1.dp, Color.Black)
            .fillMaxWidth()
            .clickable { onClickListCuentasItem(title) }) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text (dateFormat.format(created))
        if (peoplecount!=1)
            Text("${peoplecount} personas")
        else
            Text("${peoplecount} persona")
        Text("GASTADO: ${total} €")
    }
}

@Composable
fun ListCuentasAddButton(onClickAddCuentaButton: () -> Unit) {
    LargeFloatingActionButton(
        onClick = { onClickAddCuentaButton () },
        shape = CircleShape,
    ) {
        Icon(Icons.Filled.Add, "Large floating action button")
    }
}

//FUNCIONES DE EVENTOS
private fun onClickListCuentasItem(title:String) {
    println("Pulsado $title")
}