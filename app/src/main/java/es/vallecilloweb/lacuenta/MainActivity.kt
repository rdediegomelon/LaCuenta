package es.vallecilloweb.lacuenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import es.vallecilloweb.lacuenta.model.CuentaModel
import es.vallecilloweb.lacuenta.ui.theme.LaCuentaTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {

    //Temporal - Crear lista de cuentas
    val cuentas:MutableList<CuentaModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Temporal - Para llenar la lista de cuentas
        for (i in 1..9) {
            cuentas.add(CuentaModel("Cuenta $i"))
        }

        setContent {
            LaCuentaTheme {
                /*// A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }*/
                Draw(cuentas)
            }
        }
    }
}

//FUNCIONES DE DISEÑO

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    TopAppBar(title = { Text("Hola Mundo") })
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}*/

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    HolaMundoTheme {
        Greeting("Android")
    }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Draw(cuentas:MutableList<CuentaModel>){
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
            addButton()
        },

        content = { padding -> List(padding,cuentas)

        }
    )
}

@Composable
fun List(padding: PaddingValues, cuentas:MutableList<CuentaModel>){
    Column(modifier= Modifier
        .padding(padding)
        .fillMaxWidth()) {
        for (cuenta in cuentas){
            ListElement(title = cuenta.name, content = cuenta.dateCreated.toString())
        }
    }
}

@Composable
fun ListElement(title:String, content:String){
    Column(
        Modifier
            .border(1.dp, Color.Black)
            .fillMaxWidth()
            .clickable { onClickListItem(title) }) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text (content)
    }
}

@Composable
fun addButton(){
    LargeFloatingActionButton(
        onClick = { null },
        shape = CircleShape,
    ) {
        Icon(Icons.Filled.Add, "Large floating action button")
    }
}

@Preview
@Composable
fun Preview(){



    //Temporal - Para llenar la lista de cuentas

    val cuentasp:MutableList<CuentaModel> = mutableListOf()

    for (i in 1..9) {
        cuentasp.add(CuentaModel("Cuenta $i"))
    }

    LaCuentaTheme {
        Draw(cuentasp)
    }
}

//FUNCIONES DE EVENTOS
private fun onClickListItem(title:String) {
    println("Pulsado $title")
}