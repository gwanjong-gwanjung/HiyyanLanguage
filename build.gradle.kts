plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(
            shade.map {
                if (it.isDirectory)
                    it
                else
                    zipTree(it)
            }
        )
        this.manifest.attributes(Pair("Main-Class", "me.gwanjong.gwanjung.MainKt"))
    }
}


tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "me.gwanjong.gwanjung.MainKt"
    }
}
