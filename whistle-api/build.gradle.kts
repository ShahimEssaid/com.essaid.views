plugins {
    id("java-library")
    id("maven-publish")
    // https://github.com/gradle-nexus/publish-plugin#user-content-applying-the-plugin
}

version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("ossrh"){
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {

//    runtimeOnly("")

    //api(libs.views)
    api(project(":api"))
    api(libs.whistle)
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    withSourcesJar()
}


publishing {
    publications {
        create<MavenPublication>("whistle-api") {
//            groupId = "com.essaid.views.whistle"
//            artifactId = "api"
//            version = "0.0.1-SNAPSHOT"

            from(components["java"])
        }
    }
}


tasks.test {
    useJUnitPlatform()
}
