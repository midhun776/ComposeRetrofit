package com.example.composeretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeretrofit.ViewModel.CreditCardViewModel
import com.example.composeretrofit.ui.theme.ComposeRetrofitTheme


class MainActivity : ComponentActivity() {
    private val viewModel: CreditCardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListCreditCard("Credit Card List",viewModel)
                }
            }
        }
    }

    @Composable
    private fun ListCreditCard(
        name: String,
        viewModel: CreditCardViewModel,
        modifier: Modifier = Modifier) {

        val creditCards by viewModel.creditCard.observeAsState(emptyList())

        LaunchedEffect(Unit) {
            viewModel.fetchCreditCards()
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            if (creditCards.isEmpty()) {
                Text(text = "Loading...")
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant)
            } else {
                LazyColumn( modifier =
                    Modifier.padding(all = 10.dp)
                ){
                    items(creditCards)
                    {
                        Card( shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp)
                                .captionBarPadding()
                                .height(190.dp)
                        ) {
                            Box {
                                Image(
                                    painter = painterResource(id = R.drawable.credit_card_bg),
                                    contentDescription = "Background Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()

                                )

                                Column {

                                    Row(
                                        Modifier.absolutePadding(top= 15.dp,left = 20.dp, right = 20.dp)
                                    ) {
                                        Text(text = "Debit Card",
                                            style = TextStyle(color = Color.White, fontSize = 16.sp))
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(text = it.credit_card_type,
                                            style = TextStyle(color = Color.White, fontSize = 16.sp))
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Image(painter = painterResource(id = R.drawable.chip),
                                        contentDescription = "Chip Image",
                                        Modifier.width(50.dp).absolutePadding(left = 20.dp))
                                    Text(text = it.credit_card_number,
                                        Modifier.absolutePadding(top = 10.dp, bottom = 10.dp, left = 20.dp, right = 20.dp),
                                        style = TextStyle(
                                            fontSize = 28.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Row {
                                        Column(
                                            Modifier.absolutePadding(left = 20.dp, right = 20.dp, bottom = 15.dp)
                                        ) {
                                            Text(text = "Account Holder",
                                                style = TextStyle(color = Color.White, fontSize = 16.sp))
                                            Text(text = it.name,
                                                style = TextStyle(color = Color.White, fontSize = 16.sp))
                                        }
                                        Spacer(modifier = Modifier.width(2.dp))
                                        Column(
                                            Modifier.absolutePadding(left = 20.dp, right = 20.dp, bottom = 15.dp)
                                        ) {
                                            Text(text = "Expiry Date",
                                                style = TextStyle(color = Color.White, fontSize = 16.sp))
                                            Text(text = it.credit_card_expiry_date,
                                                style = TextStyle(color = Color.White, fontSize = 16.sp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}