@file:Suppress("UnstableApiUsage")

rootProject.name = "FastList"

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

buildCache {
  local {
    removeUnusedEntriesAfterDays = 7
  }
}
