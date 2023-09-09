package com.atb.beer.presentation.beer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.atb.beer.domain.Beer
import com.atb.beer.presentation.ui.theme.BeerTheme

@Composable
fun BeerItem(
    modifier: Modifier = Modifier,
    beer: Beer
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = beer.imageUrl,
                contentDescription = beer.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 120.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = beer.tagline,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = beer.description
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = beer.contributedBy,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "First Brewed: " + beer.firstBrewed,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }
}

@Preview
@Composable
fun BeerItemPre() {
    BeerTheme {
        BeerItem(
            beer = Beer(
                1,
                "Trashy Blonde",
                "You Know You Shouldn't",
                "A titillating, neurotic, peroxide punk of a Pale Ale. Combining attitude, style, substance, and a little bit of low self esteem for good measure; what would your mother say? The seductive lure of the sassy passion fruit hop proves too much to resist. All that is even before we get onto the fact that there are no additives, preservatives, pasteurization or strings attached. All wrapped up with the customary BrewDog bite and imaginative twist.",
                null,
                "04/2008",
                "Sam Mason <samjbmason>"
            )
        )
    }
}