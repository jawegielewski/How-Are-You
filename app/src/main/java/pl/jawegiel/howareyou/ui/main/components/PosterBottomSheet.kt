package pl.jawegiel.howareyou.ui.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterBottomSheet(
    sheetState: SheetState,
    textValue: String,
    onTextChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onHideClick: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest, sheetState = sheetState
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Enter Details")
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = textValue, onValueChange = onTextChange, label = { Text("Your Input") })

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(visible = textValue.isNotEmpty()) {
                Button(onClick = onHideClick) {
                    Text("Submit & Hide")
                }
            }
        }
    }
}