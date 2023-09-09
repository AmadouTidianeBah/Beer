@file:OptIn(ExperimentalMaterial3Api::class)

package com.atb.beer.presentation.beer

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.atb.beer.domain.Beer
import com.atb.beer.presentation.beer.components.BeerItem

@Composable
fun BeerScreen(
    modifier: Modifier = Modifier,
    beers: LazyPagingItems<Beer>
) {
    Scaffold(
        topBar = {
             CenterAlignedTopAppBar(
                 title = {
                     Text(
                         text = "Beers",
                         style = MaterialTheme.typography.headlineMedium,
                         color = MaterialTheme.colorScheme.onPrimary
                    )
                 },
                 colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
             )
        },
        modifier = modifier
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (beers.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator()
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(beers.itemCount) {index ->
                    beers[index]?.let { BeerItem(beer = it, modifier = Modifier.padding(horizontal = 12.dp)) }
                }
            }

            if (beers.loadState.refresh is LoadState.Error) {
                Text(
                    text = "Error: " + (beers.loadState.refresh as LoadState.Error).error.message,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}