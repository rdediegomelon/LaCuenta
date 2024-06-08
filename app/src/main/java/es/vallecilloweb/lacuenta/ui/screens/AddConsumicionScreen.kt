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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import es.vallecilloweb.lacuenta.R
import es.vallecilloweb.lacuenta.ui.viewmodel.CuentaViewModel
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddConsumicionScreen(navController: NavHostController, viewModel: CuentaViewModel){
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
        content = { padding -> AddConsumicionForm(padding,viewModel,navController)}
    )
}

@Composable
fun AddConsumicionForm(padding: PaddingValues,viewModel:CuentaViewModel,navController: NavHostController){
    var person by remember { mutableStateOf(TextFieldValue("")) }
    var product by remember { mutableStateOf(TextFieldValue("")) }
    var cost by remember { mutableStateOf(TextFieldValue("")) }
    var quantity by remember { mutableStateOf(TextFieldValue("")) }


    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")


    Column(
        Modifier
            .padding(paddingValues = padding)
            .fillMaxWidth()) {
        Text(text="Nueva Consumición", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text="${viewModel.cuentaDetail.value?.name} ${dateFormat.format(viewModel.cuentaDetail.value?.dateCreated)}", fontSize = 20.sp)
        OutlinedTextField(
            value = person,
            onValueChange = {
                person = it
            },
            label = { Text(text = "Persona") },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            isError = !isValidConsumicionPersona(person.text),
        )
        OutlinedTextField(
            value = product,
            onValueChange = {
                product = it
            },
            label = { Text(text = "Producto") },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            isError = !isValidConsumicionProduct(product.text),
        )
        OutlinedTextField(
            value = cost,
            onValueChange = {
                cost = it
            },
            label = { Text(text = "Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = !isValidConsumicionCost(cost.text),
        )
        OutlinedTextField(
            value = quantity,
            onValueChange = {
                quantity = it
            },
            label = { Text(text = "Cantidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = !isValidConsumicionQuantity(quantity.text),
        )
        Button(onClick = {
            //Validacion de los valores introducidos
            if (isValidConsumicionPersona(person.text) && isValidConsumicionProduct(product.text) &&
                isValidConsumicionCost(cost.text) && isValidConsumicionQuantity(quantity.text)) {
                    viewModel.onClickNewConsumicionButton(navController,person.text,product.text,cost.text,quantity.text)
            }
        }
        ) {
            Text(text = "Añadir")
        }
    }
}

fun isValidConsumicionPersona(persona:String):Boolean {
    return persona.length >= 3
}

fun isValidConsumicionProduct(product:String):Boolean {
    return product.length >= 3
}

fun isValidConsumicionCost(cost:String):Boolean {
    val costf: Float? =cost.toFloatOrNull()
    if (costf!=null){
        return costf>0
    }
    else {
        return false
    }
}

fun isValidConsumicionQuantity(quantity:String):Boolean {
    val quantityi: Int? =quantity.toIntOrNull()
    if (quantityi!=null){
        return quantityi>0
    }
    else {
        return false
    }
}