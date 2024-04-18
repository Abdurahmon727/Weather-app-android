import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeapp.core.extensions.H
import com.example.composeapp.features.home.data.models.forecast.Hour


@Composable
fun HourlyReport(
    title: String,
    hours: List<Hour>
) {
    if (hours.isEmpty()) return
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title, modifier = Modifier.padding(start = 16.dp))
        6.H()
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(hours) { item ->
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Green)
                        .padding(vertical = 10.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${item.tempC} \u2103",
                    )
                    AsyncImage(
                        modifier = Modifier.size(50.dp),
                        model = "https:${item.condition.icon}",
                        contentDescription = ""
                    )
                    Text(text = item.time.split(" ")[1])
                }
            }
        }
    }
}