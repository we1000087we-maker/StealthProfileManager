package com.stealth.manager

import android.webkit.WebView
import android.webkit.WebViewClient

class StealthWebViewClient : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        view?.loadUrl("javascript:(function() {" +
                "    Object.defineProperty(navigator, 'webdriver', {get: () => false});" +
                "    Object.defineProperty(navigator, 'hardwareConcurrency', {get: () => 8});" +
                "    Object.defineProperty(navigator, 'deviceMemory', {get: () => 8});" +
                "    const originalGetImageData = CanvasRenderingContext2D.prototype.getImageData;" +
                "    CanvasRenderingContext2D.prototype.getImageData = function(x, y, w, h) {" +
                "        const imageData = originalGetImageData.call(this, x, y, w, h);" +
                "        for (let i = 0; i < imageData.data.length; i += 4) {" +
                "            imageData.data[i] += 1;" +
                "        }" +
                "        return imageData;" +
                "    };" +
                "})()")
    }
}
