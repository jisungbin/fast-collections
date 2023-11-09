## FastList ![fastlist](https://img.shields.io/maven-central/v/land.sungbin.fastlist/fastlist?style=flat-square)

Applies random access to the List operator.
Only use for collections that are created by code we control and are known to support random access.

```kotlin
dependencies {
  implementation("land.sungbin.fastlist:fastlist:${version}")
}
```

#### Support operators

Please see [functions.md](functions.md).

#### License

This code copied from...
- [ListUtils.kt (AOSP)](https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui-util/src/commonMain/kotlin/androidx/compose/ui/util/ListUtils.kt;l=1;drc=1ffdbfc04d55f940f18aeefe1bc47b026cc14026)
- [ListUtils.kt (AOSP)](https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:text/text/src/main/java/androidx/compose/ui/text/android/ListUtils.kt;l=1;drc=cc067e6742334053e8edf73307e3a99bb3304ed7)
- [ListUtils.kt (AOSP)](https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/runtime/runtime/src/commonMain/kotlin/androidx/compose/runtime/snapshots/ListUtils.kt;l=1;drc=d2cf9de8466c24bd18446cf336d72cbeaf80efee)

```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```