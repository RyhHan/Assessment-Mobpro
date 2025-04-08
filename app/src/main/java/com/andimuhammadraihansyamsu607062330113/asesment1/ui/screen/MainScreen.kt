package com.andimuhammadraihansyamsu607062330113.asesment1.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andimuhammadraihansyamsu607062330113.asesment1.R
import com.andimuhammadraihansyamsu607062330113.asesment1.ui.theme.Asesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var berat by remember { mutableStateOf("") }
    var beratError by remember { mutableStateOf(false) }

    var tinggi by remember { mutableStateOf("") }
    var tinggiError by remember { mutableStateOf(false) }

    var usia by remember { mutableStateOf("") }
    var usiaError by remember { mutableStateOf(false) }

    var gender by remember { mutableStateOf("Pria") }
    var aktivitas by remember { mutableStateOf("Ringan") }

    var kaloriKonsumsi by remember { mutableStateOf("") }
    var kaloriKonsumsiError by remember { mutableStateOf(false) }

    var hasil by remember { mutableStateOf("") }
    var cekHasil by remember { mutableStateOf("") }

    var kaloriTotal by remember { mutableDoubleStateOf(0.0) }

    val aktivitasList = listOf("Sangat ringan", "Ringan", "Sedang", "Berat", "Sangat berat")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.input_title), style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text(text = stringResource(id = R.string.label_weight)) },
            trailingIcon = {IconPicker(beratError, "kg")},
            supportingText = { ErrorHint(beratError) },
            isError = beratError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = tinggi,
            onValueChange = { tinggi = it },
            label = { Text(text = stringResource(id = R.string.label_height)) },
            trailingIcon = {IconPicker(tinggiError, "cm")},
            supportingText = { ErrorHint(tinggiError) },
            isError = tinggiError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = usia,
            onValueChange = { usia = it },
            label = { Text(text = stringResource(id = R.string.label_age)) },
            trailingIcon = {IconPicker(usiaError, stringResource(id = R.string.year))},
            supportingText = { ErrorHint(usiaError) },
            isError = usiaError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Text(text = stringResource(id = R.string.label_gender), modifier = Modifier.padding(top = 16.dp))
        Row (modifier = Modifier.padding(top = 2.dp), verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = gender == "Pria", onClick = { gender = "Pria" })
            Text(text = stringResource(id = R.string.gender_male))
            RadioButton(selected = gender == "Wanita", onClick = { gender = "Wanita" })
            Text(text = stringResource(id = R.string.gender_female))
        }

        Text("Aktivitas Fisik:", modifier = Modifier.padding(top = 8.dp))
        DropdownMenuBox(options = aktivitasList, selected = aktivitas, onSelected = { aktivitas = it })

        OutlinedTextField(
            value = kaloriKonsumsi,
            onValueChange = { kaloriKonsumsi = it },
            label = { Text(text = stringResource(id = R.string.label_calories_consumed)) },
            trailingIcon = {IconPicker(kaloriKonsumsiError,"kcal")},
            supportingText = { ErrorHint(kaloriKonsumsiError) },
            isError = kaloriKonsumsiError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Button(
            onClick = {
                beratError = ((berat.isEmpty() || berat == "0" || (berat.toDoubleOrNull()
                    ?: 0.0) <= 0.0))
                tinggiError = ((tinggi.isEmpty() || tinggi == "0" || (tinggi.toDoubleOrNull()
                    ?: 0.0) <= 0.0))
                usiaError = ((usia.isEmpty() || usia == "0" || (usia.toIntOrNull() ?: 0) <= 0))
                kaloriKonsumsiError = ((kaloriKonsumsi.isEmpty() || kaloriKonsumsi == "0" || (kaloriKonsumsi.toDoubleOrNull()
                    ?: 0.0) <= 0.0))

                val b = berat.toDoubleOrNull()
                val t = tinggi.toDoubleOrNull()
                val u = usia.toIntOrNull()
                val konsumsi = kaloriKonsumsi.toDoubleOrNull()

                if (beratError || tinggiError || usiaError || kaloriKonsumsiError || b == null || t == null || u == null || konsumsi == null) {
                    return@Button
                } else {
                    kaloriTotal = hitungKalori(gender, b, t, u, aktivitas)

                    cekHasil = when {
                        konsumsi < kaloriTotal * 0.9 -> "less"
                        konsumsi > kaloriTotal * 1.1 -> "over"
                        else -> "good"
                    }

                    hasil = context.getString(
                        R.string.result_template,
                        kaloriTotal.toInt(),
                        konsumsi.toInt(),
                    )
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.btn_calculate))
        }

        if (hasil.isNotBlank()) {
            val imageResId = when (cekHasil) {
                "good" -> R.drawable.good
                "less" -> R.drawable.less
                else -> R.drawable.over
            }
            Column(
                modifier = Modifier.padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(
                        imageResId
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )

                val hasilResId = when (cekHasil) {
                    "good" -> R.string.status_normal
                    "less" -> R.string.status_low
                    else -> R.string.status_high
                }
                Text(
                    text = stringResource(id = hasilResId),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF1E88E5)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = hasil,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp).align(Alignment.CenterHorizontally)
                )

                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.getString(
                                R.string.share_template,
                                kaloriTotal.toInt(),
                                kaloriKonsumsi.toInt(),
                                context.getString(
                                    when (cekHasil) {
                                        "good" -> R.string.status_normal
                                        "less" -> R.string.status_low
                                        else -> R.string.status_high
                                    }
                                )
                            )
                        )
                    },
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Text(text = stringResource(id = R.string.btn_share))
                }
            }
        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Composable
fun IconPicker(isError: Boolean,unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }else{
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(text =  stringResource(id = R.string.error_invalid_input))
    }
}

fun hitungKalori(
    gender: String,
    berat: Double,
    tinggi: Double,
    usia: Int,
    aktivitas: String
): Double {
    val bmr = if (gender == "Pria") {
        66.5 + (13.75 * berat) + (5.003 * tinggi) - (6.75 * usia)
    } else {
        655.1 + (9.563 * berat) + (1.850 * tinggi) - (4.676 * usia)
    }

    val faktorAktivitas = when (aktivitas) {
        "Sangat ringan" -> 1.2
        "Ringan" -> 1.375
        "Sedang" -> 1.55
        "Berat" -> 1.725
        "Sangat berat" -> 1.9
        else -> 1.2
    }

    return bmr * faktorAktivitas
}

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuBox(
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(id = R.string.label_select)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Asesment1Theme {
        MainScreen()
    }
}