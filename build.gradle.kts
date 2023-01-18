import org.jetbrains.kotlin.gradle.tasks.KotlinCompile



plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("kapt") version "1.6.21"
}


noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
    annotation("com.example.demokot.NoArgAndAllOpen")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
    annotation("com.example.demokot.NoArgAndAllOpen")
}



group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {

    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")

    //spring boot
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //postgresql
    runtimeOnly("org.postgresql:postgresql")

    //query dsl https://mvnrepository.com/artifact/com.querydsl/querydsl-apt
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
    implementation("com.querydsl:querydsl-jpa")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
    implementation("org.springdoc:springdoc-openapi-webmvc-core:1.6.9")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.9") // spring doc
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.9") // spring doc
    implementation("org.springdoc:springdoc-openapi-webflux-core:1.6.9") // spring doc
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.9") // spring doc
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
