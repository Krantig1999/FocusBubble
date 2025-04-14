package com.socialmedia.focusbubble.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.socialmedia.focusbubble.R
import com.socialmedia.focusbubble.databinding.ActivityBlockBinding

class BlockActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get package name passed from service
        val packageName = intent.getStringExtra("BLOCKED_APP") ?: "Unknown App"
        val appName = getAppNameFromPackage(packageName)
        val motivation = getRandomMotivation()

        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("You tried opening:", color = Color.White, fontSize = 22.sp)
                    Text(packageName, color = Color.Red, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        motivation,
                        color = Color.LightGray,
                        fontSize = 18.sp,
                        lineHeight = 26.sp
                    )
                }
            }
        }
    }

    private fun getAppNameFromPackage(packageName: String): String {
        return try {
            val appInfo = packageManager.getApplicationInfo(packageName, 0)
            packageManager.getApplicationLabel(appInfo).toString()
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown App"
        }
    }

    private fun getRandomMotivation(): String {
        val quotes = listOf(
            "Discipline is choosing between what you want now and what you want most.",
            "Success is nothing more than a few simple disciplines, practiced every day.",
            "Don’t trade short-term pleasure for long-term progress.",
            "One focused hour is more powerful than ten distracted ones.",
            "Stay strong. Stay committed. You've got this."
        )
        return quotes.random()
    }

}