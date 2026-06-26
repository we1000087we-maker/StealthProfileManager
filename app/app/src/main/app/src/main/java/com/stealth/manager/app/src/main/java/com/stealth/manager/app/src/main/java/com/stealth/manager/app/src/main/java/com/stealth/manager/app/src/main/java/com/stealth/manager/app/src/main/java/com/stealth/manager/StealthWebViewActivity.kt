package com.stealth.manager

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class StealthWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.webView)
        val profile = intent.getSerializableExtra("PROFILE") as ProfileEntity

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setUserAgentString(profile.userAgent)
        }

        ProxyManager.setupWebViewProxy(webView, profile.proxyHost, profile.proxyPort, profile.proxyUser, profile.proxyPass)

        webView.webViewClient = StealthWebViewClient()
        webView.loadUrl("https://example.com") // ضع هنا رابط العرض
    }
}
