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

        apps.let { it ->
            if (it.isNotEmpty() && categoryName.isNotEmpty()) {
                converter.apply {
                    return calculatePercentage(
                        it.count { it.category.contains(categoryName.trim(), true) },
                        it.size
                    )
                }
            }
        }
        return -1.0
    }


    fun getLargestApp(apps: List<App>, size: Int): List<App>? {
        apps.let {
            if (it.isNotEmpty() && size <= it.size) {
                return it.sortedByDescending { it -> it.size.convertToByte() }.take(size)
                    }
            return null
        }
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
       apps.let {
        if (it.isEmpty() || companyName.isEmpty() || companyName.isBlank()) {
            return null
        }
        val list = it.filter { it.company.lowercase().contains(companyName.lowercase()) }
        return getLargestApp(list, numberOfApps)
    } }
}
