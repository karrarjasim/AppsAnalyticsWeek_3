package parser

import Interface.DataSource
import model.App
import org.json.JSONArray
import org.json.JSONObject
import utilities.Converter
import java.io.File

class JsonParser(private val fileName: String,private  val  converter: Converter) :DataSource {
    override
    fun getAllApps(): List<App> {
       val jsonFile= File(fileName)
        val jsonString=jsonFile.readText()
        val jsonArray=JSONArray(jsonString)
        val appList = mutableListOf<App>()
        jsonArray.forEach{
            val jsonObject =JSONObject(it.toString())
            appList.add(converter.convertJsonToApp(jsonObject))
        }
        return  appList
    }
}