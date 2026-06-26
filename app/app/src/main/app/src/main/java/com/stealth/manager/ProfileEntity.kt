package com.stealth.manager

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val proxyHost: String,
    val proxyPort: Int,
    val proxyUser: String,
    val proxyPass: String,
    val deviceModel: String,
    val hardwareConcurrency: Int,
    val userAgent: String
)
