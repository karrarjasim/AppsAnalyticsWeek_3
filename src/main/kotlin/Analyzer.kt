import kotlin.math.roundToInt

class Analyzer() {

    fun topTenAppInstall(appDataList: List<App>): List<String>? =
        if (appDataList.isNotEmpty())
            appDataList.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .map { data -> data.appName }
                .toSet()
                .take(10)
        else null

    fun findOldestApp(apps: List<App>): String? =
        if (apps.isNotEmpty()) {apps.sortedBy { it.updatedDate }[0].appName} else null

    fun percentageAppsRunningOnAndroid9(apps: List<App>): String?{
        if (apps.isEmpty()) return null
        apps.forEach { it ->
            if (it.requiresAndroid.contains("9 and up"))
            return String.format("%.1f", 100.0 * apps.count {
                it.requiresAndroid == "9 and up"
            } / apps.size)
        }
        return null
    }

    fun getPercentageAppsRunningOnSpecificVersion(apps: List<App>, version:String): Double =
        if (apps.isNotEmpty())
            calculatePercentage(
                apps.filter { it.requiresAndroid.contains(version,true)}.size,
                apps.size)
        else -1.0

    fun getPercentageOfCategory(apps: List<App>,categoryName:String):Double =
        if (apps.isNotEmpty())
            calculatePercentage(
                apps.filter { it.category.contains(categoryName, true) }.size,
                apps.size)
        else -1.0

    private fun calculatePercentage(dividend:Int, divisor:Int):Double =
        if (dividend!=0){
            String.format("%.1f", 100.0 * dividend.div(divisor.toDouble())).toDouble()
        } else -1.0

    fun getLargestApp(apps: List<App>,size:Int):List<String>?{
        if (apps.isNotEmpty()) {
            val list = mutableMapOf<App,Long>()

             apps.filterNot { it.size.contains("Varies", true) }
                 .apply {
                    onEach {
                       list[it] = convertToNumber(it.size)
                    }
                 }
            return list.toList().sortedByDescending { (_, value) -> value}.toMap()
                .keys.map { it-> it.appName }.toList().take(size)
        }
        return null
    }

    private fun convertToNumber(size:String): Long {
        when {
            size.endsWith("k", true)->{
                return size.substring(0,size.indexOf("k")).toLong()
            }
            size.endsWith("M", true)->{
                return (size.substring(0,size.indexOf("M")).toDouble()* 1000).toLong()
            }
            size.endsWith("G", true)->{
                return (size.substring(0,size.indexOf("G")).toDouble()* 1000000).toLong()
            }
        }
        return 0
    }
}