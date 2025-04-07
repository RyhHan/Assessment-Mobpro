package com.andimuhammadraihansyamsu607062330113.asesment1.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var berat by remember { mutableStateOf("") }
    var tinggi by remember { mutableStateOf("") }
    var usia by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Pria") }
    var aktivitas by remember { mutableStateOf("Ringan") }
    var kaloriKonsumsi by remember { mutableStateOf("") }
    var hasil by remember { mutableStateOf("") }

    val aktivitasList = listOf("Sangat ringan", "Ringan", "Sedang", "Berat", "Sangat berat")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Masukkan Data Anda", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text("Berat (kg)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = tinggi,
            onValueChange = { tinggi = it },
            label = { Text("Tinggi (cm)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        OutlinedTextField(
            value = usia,
            onValueChange = { usia = it },
            label = { Text("Usia (tahun)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Text("Jenis Kelamin:", modifier = Modifier.padding(top = 8.dp))
        Row {
            RadioButton(selected = gender == "Pria", onClick = { gender = "Pria" })
            Text("Pria", modifier = Modifier.padding(end = 16.dp))
            RadioButton(selected = gender == "Wanita", onClick = { gender = "Wanita" })
            Text("Wanita")
        }

        Text("Aktivitas Fisik:", modifier = Modifier.padding(top = 8.dp))
        DropdownMenuBox(options = aktivitasList, selected = aktivitas, onSelected = { aktivitas = it })

        OutlinedTextField(
            value = kaloriKonsumsi,
            onValueChange = { kaloriKonsumsi = it },
            label = { Text("Kalori yang Dikonsumsi Hari Ini (kkal)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        Button(
            onClick = {
                val b = berat.toDoubleOrNull()
                val t = tinggi.toDoubleOrNull()
                val u = usia.toIntOrNull()
                val konsumsi = kaloriKonsumsi.toDoubleOrNull()

                if (b == null || t == null || u == null || konsumsi == null) {
                    hasil = "Mohon isi semua data dengan benar!"
                } else {
                    val kaloriTotal = hitungKalori(gender, b, t, u, aktivitas)
                    val status = when {
                        konsumsi < kaloriTotal * 0.9 -> "❗Kalori Anda KURANG dari kebutuhan harian."
                        konsumsi > kaloriTotal * 1.1 -> "⚠️Kalori Anda BERLEBIHAN dari kebutuhan harian."
                        else -> "✅Kalori Anda SESUAI kebutuhan harian."
                    }

                    hasil = "Kebutuhan kalori harian Anda: ${kaloriTotal.toInt()} kkal\n" +
                            "Kalori yang dikonsumsi: ${konsumsi.toInt()} kkal\n\n$status"
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Hitung Kalori")
        }

        if (hasil.isNotBlank()) {
            Text(
                text = hasil,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
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

    // Faktor aktivitas
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
            label = { Text("Pilih") },
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