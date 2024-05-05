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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
            //Por cada persona, se pinta la lista con el detalle de su cuenta
            for (entry in viewModel.cuentaDetail.consumiciones.entries){
                //TODO este for se debe eliminar al usa el LazyColumn
                CuentalDetailListElements(padding,entry,viewModel.cuentaDetail.calculateTotal(entry.key))
            }
        }
    }
}

@Composable
fun CuentalDetailListElements(padding: PaddingValues,entry:MutableMap.MutableEntry<String,MutableList<ConsumicionModel>>,total:Float){
    //Este column pinta el detalle de la cuenta de una persona, pinta el nombre de la persona y su total y la lista de consumiciones
    //TODO esto debe ser un LazyColumn
    Column(modifier= Modifier
        .border(1.dp, Color.Black)
        .fillMaxWidth()) {
        Text(fontSize = 18.sp, fontWeight = FontWeight.Bold, text = "${entry.key}")
        Text(fontSize = 16.sp, fontWeight = FontWeight.Bold,text = "TOTAL: ${total} €")
        //Este LazyColumn pinta la lista de consumiciones de cada persona
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //A items se le pasa una lista, y dentro ejecuta la acción por cada elemento de la lista. Ese elemento se le pasa a la función composable para que lo muestre
            items(entry.value) { consumicion ->
                CuentaDetailElement(consumicion)
            }
        }
    }
}

@Composable
fun CuentaDetailElement(consumicion:ConsumicionModel){
    Text("${consumicion.quantity} x ${consumicion.name} (${consumicion.cost} €/u) -> ${consumicion.cost * consumicion.quantity} €")
}