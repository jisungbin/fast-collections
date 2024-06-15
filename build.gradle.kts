plugins {
  kotlin("jvm") version "2.0.0"
  id("com.vanniktech.maven.publish") version "0.28.0"
  // id("com.google.devtools.ksp") version "2.0.0-1.0.22"
}

kotlin {
  explicitApi()
}

dependencies {
  // ksp(project(":ksp"))
}