package id.dipoengoro.dcontact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import id.dipoengoro.dcontact.ui.ContactApp
import id.dipoengoro.dcontact.ui.theme.DContactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DContactTheme(darkTheme = false) {
                ContactApp()
            }
        }
    }
}