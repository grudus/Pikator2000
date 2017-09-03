package com.grudus.pikator2000

object DriverDetector {
    private val DRIVERS = mapOf(
            "Windows" to "chromedriver",
            "Mac OS X" to "chromedriver-mac"
    )

    fun detectFileName(): String? {
        val prop = System.getProperty("os.name")

        return DRIVERS.entries.find { prop.startsWith(it.key) }?.value
    }
}
