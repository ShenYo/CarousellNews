package com.carousell.base

import java.io.IOException
import java.nio.charset.Charset

/**
 * Description:
 *
 * @author Shenyo
 * @version 1.0, 2019/3/30
 */
 
 
class ResourceManager {

    fun loadJSONFromAsset(fileName : String): String {
        var json = ""
        try {
            val `is` = AppContext.INSTANCE.getAssets().open(fileName)
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