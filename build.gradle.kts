/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

allprojects {
    repositories {
        mavenLocal()

        google()
        jcenter()

        maven { url = uri("https://kotlin.bintray.com/kotlin") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx") }
        maven { url = uri("https://kotlin.bintray.com/ktor") }
        maven { url = uri("https://dl.bintray.com/icerockdev/moko") }
    }

    // workaround for https://youtrack.jetbrains.com/issue/KT-27170
    configurations.create("compileClasspath")
}

buildscript {
    repositories {
        println("Build script repo ${project.name}")
        maven { url = uri("$rootDir/gradle-plugin/build/localDevRepo") }
        maven { url = uri("https://dl.bintray.com/icerockdev/plugins") }

        mavenLocal()

        google()
        jcenter()
    }

    dependencies {
        println("Build script dep ${project.name}")
        classpath(Deps.Plugins.mokoNetwork)
    }
}

tasks.register("clean", Delete::class).configure {
    delete(rootProject.buildDir)
}
