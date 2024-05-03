package es.vallecilloweb.lacuenta.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.vallecilloweb.lacuenta.R
import es.vallecilloweb.lacuenta.data.CuentaModel
import es.vallecilloweb.lacuenta.ui.theme.LaCuentaTheme
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel

class MainActivity : ComponentActivity() {


    private val _viewModel: CuentaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LaCuentaTheme {
                Draw(_viewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Draw(viewModel: CuentaViewModel){
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
            addButton(){viewModel.onClickAddCuentaButton()}
        },

        content = { padding -> List(padding,viewModel)

        }
    )
}

@Composable
fun List(padding: PaddingValues, viewModel: CuentaViewModel){

    val cuentas: List<CuentaModel> = viewModel.cuentas

    LazyColumn(modifier= Modifier
        .padding(padding)
        .fillMaxWidth()) {
            items(cuentas) {
                cuenta -> ListElement(title = cuenta.name, created = cuenta.dateCreated.toString(),peoplecount = cuenta.people.size )
            }
    }
}


@Composable
fun ListElement(title:String, created:String,peoplecount:Int){
    Column(
        Modifier
            .border(1.dp, Color.Black)
            .fillMaxWidth()
            .clickable { onClickListItem(title) }) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text (created)
        Text("${peoplecount} personas")
    }
}

@Composable
fun addButton(onClickAddCuentaButton: () -> Unit) {
    LargeFloatingActionButton(
        onClick = { onClickAddCuentaButton () },
        shape = CircleShape,
    ) {
        Icon(Icons.Filled.Add, "Large floating action button")
    }
}

/*@Preview
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
}*/

//FUNCIONES DE EVENTOS
private fun onClickListItem(title:String) {
    println("Pulsado $title")
}