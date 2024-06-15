plugins {
  kotlin("jvm")
  id("com.google.devtools.ksp") version "2.0.0-1.0.22"
}

dependencies {
  implementation("com.google.devtools.ksp:symbol-processing-api:1.9.20-1.0.14")
  implementation("land.sungbin.gfm:gfm-dsl:1.0.0")

  implementation("com.google.auto.service:auto-service-annotations:1.1.1")
  ksp("dev.zacsweers.autoservice:auto-service-ksp:1.1.0")
}
