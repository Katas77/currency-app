package com.example.currency_app.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.currency_app.viewmodel.CurrencyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel = viewModel()) {
    var selectedCurrency by remember { mutableStateOf<String?>(null) }

    val rate by viewModel.rate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Курсы валют", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            CurrencyButton(label = "Доллар", code = "USD") {
                selectedCurrency = "USD"
                viewModel.loadRate("USD")
            }
            CurrencyButton(label = "Евро", code = "EUR") {
                selectedCurrency = "EUR"
                viewModel.loadRate("EUR")
            }
            CurrencyButton(label = "Юань", code = "CNY") {
                selectedCurrency = "CNY"
                viewModel.loadRate("CNY")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (selectedCurrency != null) {
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Курс $selectedCurrency:", fontWeight = FontWeight.Bold)
                    Text(rate, fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun CurrencyButton(label: String, code: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(label)
    }
}