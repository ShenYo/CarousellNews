package com.carousell.challenge.util

import com.carousell.challenge.base.AppContext
import org.koin.standalone.KoinComponent
import java.io.IOException
import java.nio.charset.Charset

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/30
 */
 
 
class ResourceManager: KoinComponent {

    fun loadJSONFromAsset(fileName : String): String {
        var json = ""
        try {
            val `is` = AppContext.INSTANCE.assets.open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return json
        }

        return json
    }
}