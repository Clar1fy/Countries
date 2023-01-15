
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

        maven { url = uri("https://jitpack.io") }
    }
}

enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("countries")
        maven { url = uri("https://jitpack.io") }
    }
    versionCatalogs {
        create("config") {
            from(files("gradle/config.versions.toml"))
        }
        create("options") {
            from(files("gradle/options.versions.toml"))
        }
    }
}

rootProject.name = "Countries"
include(":app",":features")
include(":common")
include(":core")
include(":features:main")
include(":features:main:domain")
include(":features:main:data")
