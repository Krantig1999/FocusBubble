package com.socialmedia.focusbubble.presentation

import android.accessibilityservice.AccessibilityService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class AppBlockerService : AccessibilityService() {

    private val blockedApps = listOf(
        "com.instagram.android",
        "com.youtube.android",
        "com.facebook.katana","com.whatsapp","com.linkedin.android"
    )


    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString()
            Log.d("Kranthi","please avoid using " + packageName)
            if (packageName in blockedApps) {
                if (packageName != null) {
                    showBlockScreen(packageName)
                }
            }
        }
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    private fun showBlockScreen(packageName: String) {
        val intent = Intent(this, BlockActivity::class.java).apply {
            putExtra("BLOCKED_APP", packageName)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

}