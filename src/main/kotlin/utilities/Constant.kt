package utilities

object Constant {
    object ColumnIndex {
        const val APP_NAME = 0
        const val COMPANY = 1
        const val CATEGORY = 2
        const val UPDATE_DATE = 3
        const val SIZE = 4
        const val INSTALLS = 5
        const val CURRENT_VERSION = 6
        const val REQUIRED_ANDROID = 7
    }
    object JsonKey {
        const val APP_NAME_KEY = "appName"
        const val COMPANY_KEY = "company"
        const val CATEGORY_KEY = "category"
        const val UPDATE_DATE_KEY = "updated"
        const val SIZE_KEY = "size"
        const val INSTALLS_KEY = "installs"
        const val CURRENT_VERSION_KEY ="currentVersion"
        const val REQUIRED_ANDROID_KEY = "requiresAndroid"
    }

    const val CSV_FILE_NAME = "google_play.csv"
    const val JSON_FILE_NAME = "google_play.json"
    const val DATE_FORMAT = "MMMM dd yyyy"
    const val KILO_BYTE = 1024.0
}

