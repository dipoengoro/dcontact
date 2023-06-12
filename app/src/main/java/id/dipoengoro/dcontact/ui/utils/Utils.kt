package id.dipoengoro.dcontact.ui.utils

import androidx.compose.ui.graphics.Color
import id.dipoengoro.dcontact.data.color.ContactColor.pastelColors
import android.graphics.Color as GColor

object Utils


fun String.toColor(): Color = Color(GColor.parseColor(this))

fun String.getColor(): Color {
    var color = Color(0xFFFFFFFF)
    for (pair in pastelColors) {
        if (pair.first == this) {
            color = pair.second.toColor()
            break
        }
    }
    return color
}