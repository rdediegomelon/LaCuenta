package es.vallecilloweb.lacuenta.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import es.vallecilloweb.lacuenta.R
import es.vallecilloweb.lacuenta.data.ConsumicionModel
import es.vallecilloweb.lacuenta.data.CuentaModel
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel
import java.time.format.DateTimeFormatter

//TODO Agrupar consumiciones con el mismo nombre
//TODO Actualizar lista de consumiciones automáticamente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuentaDetailScreen(navController: NavHostController,viewModel: CuentaViewModel){
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
        floatingActionButton = {
            CuentaDetailAddButton(){viewModel.onClickAddConsumicionButton()}
        },
        content = { padding -> CuentaDetail(padding,viewModel)}
    )
}

@Composable
fun CuentaDetail(padding: PaddingValues,viewModel: CuentaViewModel){
    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    //Este column pinta todos los detalles de una cuenta, contiene el nombre de la cuenta, la fecha, y la lista de personas y el detalle de cada persona dentro
    Column( modifier= Modifier
        .padding(padding)
        .fillMaxWidth()) {
        Text(viewModel.cuentaDetail.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text (dateFormat.format(viewModel.cuentaDetail.dateCreated))
        Text("TOTAL: ${viewModel.cuentaDetail.calculateTotal()} €")
        if (viewModel.cuentaDetail.consumiciones.isEmpty()){
            Text("La cuenta está vacía, debes añadir nuevas consumiciones")
        }
        else {
            //Se pinta en una lista el contenido de la cuenta (persona y detalle)
            CuentalDetailListElements(padding,viewModel.cuentaDetail)
        }
    }
}

@Composable
fun CuentalDetailListElements(padding: PaddingValues,cuenta:CuentaModel){
    //Este LazyColumn pinta el detalle de la cuenta persona a persona, pinta el nombre de la persona y su total y la lista de consumiciones
    LazyColumn(modifier= Modifier
        .border(1.dp, Color.Black)
        .fillMaxWidth()) {
        //Convierte el Map en una lista de valores nombre,lista de consumiciones, y por cada elemento llama a la función composable
        items(cuenta.consumiciones.toList()) {
            element -> CuentaDetailListElements(element,cuenta.calculateTotal(element.first))
        }

    }
}

@Composable
fun CuentaDetailListElements(list:Pair<String,MutableList<ConsumicionModel>>,total:Float){

    Row() {
        Icon(Icons.Default.AccountCircle, contentDescription = "AccountCircle")
        Text(fontSize = 18.sp, fontWeight = FontWeight.Bold, text = "${list.first}")
    }
    Row() {
        Icon(Icons.Default.Edit, contentDescription = "Edit")
        Text(fontSize = 16.sp, fontWeight = FontWeight.Bold, text = "TOTAL: ${total} €")
    }
    //Este Column pinta la lista de consumiciones de cada persona
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for (consumicion in list.second){
            CuentaDetailElement(consumicion = consumicion)
        }
    }
}

@Composable
fun CuentaDetailElement(consumicion:ConsumicionModel){
    Row () {
        Icon(Icons.Default.Add, contentDescription = "Add")
        Text("${consumicion.quantity} x ${consumicion.name} (${consumicion.cost} €/u) -> ${consumicion.cost * consumicion.quantity} €")
    }
}

@Composable
fun CuentaDetailAddButton(onClickAddCuentaDetailButton: () -> Unit) {
    LargeFloatingActionButton(
        onClick = { onClickAddCuentaDetailButton () },
        shape = CircleShape,
    ) {
        Icon(Icons.Filled.ShoppingCart, "Large floating action button")
    }
}