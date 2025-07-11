package com.example.currencyconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyconvertor.ui.theme.CurrencyConvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            CurrencyConvertorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                }
            }
        }
    }
}

//create custom composable
@Composable
fun UnitConverter (){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }

    fun convertUnits(){
        // check the value or return default value by elvish operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value ).roundToInt()/ 100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // here all the UI elements will be stacked below each other
        Text("Unit Converter", modifier = Modifier.padding(5.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue= it
                convertUnits()
            },
            label = {Text("Enter Value")},
            placeholder = {Text("Enter Value")}

            )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            // here all the UI elements will be stacked beside each other
            Box{
                Button(onClick = {
                    iExpanded = !iExpanded
                }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down" )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {
                    iExpanded = !iExpanded
                }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                         iExpanded = !iExpanded
                         inputUnit = "Centimeters"
                         conversionFactor.value = 0.01
                         convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Meters"
                            conversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            iExpanded = !iExpanded
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = {
                    oExpanded = !oExpanded
                }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down" )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {
                    oExpanded = !oExpanded
                }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {
                            oExpanded = !oExpanded
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {
                            oExpanded = !oExpanded
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            oExpanded = !oExpanded
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters")},
                        onClick = {
                            oExpanded = !oExpanded
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text("Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium
            )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}