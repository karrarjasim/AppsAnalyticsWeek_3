import model.App
import utilities.Converter
import utilities.convertToByte
import java.math.BigDecimal

class Analyzer (private val converter:Converter) {


    fun findNumberOfAppsByCompanyName(apps: List<App>, companyName: String): Int {
        return if (apps.isNotEmpty() && companyName.isNotEmpty()) {
            apps.count { it.company.contains(companyName.trim(), true) }
        } else {
            return -1
        }
    }

    fun findOldestApp(apps: List<App>): App? {
        apps.let {
            if(it.isEmpty()) return null
            return it.minByOrNull {it.updatedDate}
        }
    }


    fun getPercentageAppsRunningOnSpecificVersion(apps: List<App>, version: Double): Double {
        converter.apply {
            return calculatePercentage(
                apps.count { it.requiresAndroid != null && it.requiresAndroid == version },
                apps.size
            )
        }
    }

    fun getPercentageOfCategory(apps: List<App>, categoryName: String): Double {
        if (apps.isNotEmpty() && categoryName.isNotEmpty()) {
            return converter.calculatePercentage(
                apps.count { it.category.contains(categoryName.trim(), true) },
                apps.size
            )
        }
        return -1.0

    }


    fun getLargestApp(apps: List<App>, size: Int): List<App>? {
        if (apps.isNotEmpty() && size <= apps.size) {
            val list = mutableMapOf<App, BigDecimal>()

            apps.filterNot { it.size.contains("Varies", true) }
                .apply {
                    onEach {


                        val value = (it.size).convertToByte()

                        if (value != null) {
                            list[it] = value
                        }
                    }
                }
            return list.toList().sortedByDescending { (_, value) -> value }.toMap()
                .keys.map { it -> it }.toList().take(size)
        }
        return null
    }

    fun topNAppInstall(apps: List<App>, numberOfApps: Int): List<App>? {
        apps.let {
            if(it.isEmpty() || numberOfApps <= 0) return null
            return  it.asSequence()
                .sortedByDescending { dataSorted -> dataSorted.installs }
                .take(numberOfApps)
                .toList()
        }

    }



    fun getLargestAppsSizeByCompanyName(apps: List<App>, companyName: String, numberOfApps: Int): List<App>? {
        if (apps.isEmpty() || companyName.isEmpty() || companyName.isBlank()) {
            return null
        }
        val list = apps.filter { it.company.lowercase().contains(companyName.lowercase()) }
        return getLargestApp(list, numberOfApps)
    }
}
