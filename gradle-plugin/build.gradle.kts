/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    `kotlin-dsl`
    `maven-publish`
    maven
}

repositories {
    mavenLocal()

    jcenter()
    google()
}

dependencies {
    implementation(Deps.Libs.Jvm.openApiGenerator)

    compileOnly(Deps.Plugins.kotlin)
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

tasks.named<Upload>("uploadArchives") {
    repositories.withGroovyBuilder {
        "mavenDeployer" {
            "repository"("url" to uri("$rootDir/gradle-plugin/build/localDevRepo"))
            "pom" {
                setProperty("version", Versions.Libs.MultiPlatform.mokoNetwork)
                setProperty("artifactId", "network-generator")
                setProperty("groupId", "dev.icerock.moko")
            }
        }
    }
}

publishing {
    repositories.maven("https://api.bintray.com/maven/icerockdev/plugins/moko-network-generator/;publish=1") {
        name = "bintray"

        credentials {
            username = System.getProperty("BINTRAY_USER")
            password = System.getProperty("BINTRAY_KEY")
        }
    }

    publications {
        register("plugin", MavenPublication::class) {
            groupId = "dev.icerock.moko"
            artifactId = "network-generator"
            version = Versions.Libs.MultiPlatform.mokoNetwork

            from(components["java"])
        }
    }
}
