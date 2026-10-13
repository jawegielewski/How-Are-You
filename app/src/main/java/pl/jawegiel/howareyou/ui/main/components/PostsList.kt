package pl.jawegiel.howareyou.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.jawegiel.howareyou.data.model.Post

@Composable
fun PostsList(posts: List<Post>, modifier: Modifier, innerPadding: PaddingValues) {

    LazyColumn(
        modifier = modifier.padding(innerPadding), contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        items(items = posts, key = { it.id.toString() }) { message ->
            PosterCard(message)
        }
    }
}