package es.vallecilloweb.lacuenta.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.vallecilloweb.lacuenta.R
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCuentaScreen(navController: NavHostController, viewModel: CuentaViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable { navController.popBackStack() })
                },
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        },
        content = { padding -> AddCuentaForm(padding,viewModel,navController)}
    )
}

@Composable
fun AddCuentaForm(padding: PaddingValues,viewModel:CuentaViewModel,navController: NavHostController){
    var name by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        Modifier
            .padding(paddingValues = padding)
            .fillMaxWidth()) {
        Text(text="Nueva Cuenta", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text(text = "Nombre") },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            isError = !isValidCuentaName(name.text),
        )
        Button(onClick = {
            //Validacion del valor introducido, de al menos 3 caracteres
            if (isValidCuentaName(name.text)) {
                viewModel.onClickNewCuentaButton(navController, name.text)
            }
        }
        ) {
            Text(text = "Añadir")
        }
    }
}

fun isValidCuentaName(name:String):Boolean {
    return name.length >= 3
}
