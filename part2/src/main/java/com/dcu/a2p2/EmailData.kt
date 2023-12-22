package com.dcu.a2p2

import java.io.Serializable

data class EmailData(val to: String, val subject: String, val compose: String) : Serializable
