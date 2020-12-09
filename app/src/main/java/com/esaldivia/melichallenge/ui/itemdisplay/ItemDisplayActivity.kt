package com.esaldivia.melichallenge.ui.itemdisplay

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.utils.Constants
import kotlinx.android.synthetic.main.activity_item_display.*

class ItemDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_display)

        setupActionBar()
        setupRefresherLayout()
        setupWebView()
    }

    private fun setupRefresherLayout() {
        refreshLayout.setOnRefreshListener {
            webView.reload()
        }
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupWebView() {
        webView.webViewClient = setupWebClient()

        val settings = webView.settings
        settings.javaScriptEnabled = true

        webView.loadUrl(intent.getStringExtra(Constants.ITEM_URL_KEY)!!)
    }

    private fun setupWebClient(): WebViewClient {
        return object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                refreshLayout.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                refreshLayout.isRefreshing = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}