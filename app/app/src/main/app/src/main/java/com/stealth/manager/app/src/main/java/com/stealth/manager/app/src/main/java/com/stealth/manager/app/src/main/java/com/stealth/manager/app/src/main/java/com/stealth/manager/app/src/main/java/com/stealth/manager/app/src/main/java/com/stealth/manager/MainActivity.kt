package com.stealth.manager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private val profileList = mutableListOf<ProfileEntity>()
    private lateinit var adapter: ProfilesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        db = AppDatabase.getInstance(this)
        
        val recyclerView = findViewById<RecyclerView>(R.id.profilesRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProfilesAdapter(profileList) { profile ->
            val intent = Intent(this, StealthWebViewActivity::class.java)
            intent.putExtra("PROFILE", profile)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        
        findViewById<FloatingActionButton>(R.id.addProfileBtn).setOnClickListener {
            showAddProfileDialog()
        }
        
        loadProfiles()
    }
    
    private fun loadProfiles() {
        db.profileDao().getAllProfiles().observe(this) { list ->
            profileList.clear()
            profileList.addAll(list)
            adapter.notifyDataSetChanged()
        }
    }
    
    private fun showAddProfileDialog() {
        // هنا هتضيف كود الـ Dialog لعمل Insert
        // مثال سريع (افتراضي)
        val profile = ProfileEntity(
            name = "US Proxy 1",
            proxyHost = "us.proxy.com",
            proxyPort = 1080,
            proxyUser = "user123",
            proxyPass = "pass123",
            deviceModel = "SM-G998B",
            hardwareConcurrency = 8,
            userAgent = "Mozilla/5.0 (Linux; Android 13; SM-G998B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
        )
        GlobalScope.launch { db.profileDao().insert(profile) }
    }
}
