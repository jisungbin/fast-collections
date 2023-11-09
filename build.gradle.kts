plugins {
  kotlin("jvm") version "1.9.20"
  id("com.vanniktech.maven.publish") version "0.25.3"
  // id("com.google.devtools.ksp") version "1.9.20-1.0.14"
}

kotlin {
  explicitApi()
}

dependencies {
  // ksp(project(":ksp"))
}