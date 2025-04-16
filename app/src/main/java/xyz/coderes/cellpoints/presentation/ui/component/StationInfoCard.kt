package xyz.coderes.cellpoints.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.coderes.cellpoints.domain.model.Station


@Composable
fun StationInfoCard(station: Station,onClose:()-> Unit) {
    Card(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier.clickable { onClose() }) {

            Column(modifier = Modifier.padding(16.dp)) {
                Text("ID: ${station.cellId}")
                Text("Type: ${station.rat}")
                Text("Lat: ${station.lat}, Lng: ${station.lon}")
            }
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                modifier = Modifier.padding(16.dp).align(Alignment.TopEnd)
            )
        }
    }
}