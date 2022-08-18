plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")

    id("org.openapi.generator") version "6.0.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependency.retrofit)
    implementation(Dependency.retrofitConverterMoshi)
    implementation(Dependency.retrofitConverterScalars)
    implementation(Dependency.okHttpLoggingInterceptor)

    implementation(Dependency.moshi)
    implementation(Dependency.moshiAdapters)
    implementation(Dependency.moshiKotlin)
}

// API名
val apiName = "petstore"

// ビルド先ディレクトリ
val buildApiDir = "$buildDir/openApiGenerator/$apiName"

// 自動生成先のパッケージ名
val basePackage = "com.example.api"

fun String.packageToDir() = replace('.', '/')

task<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("generate") {
    doFirst {
        delete(file(buildApiDir))
    }

    // OpenAPI Generatorのオプションを指定
    generatorName.set("kotlin")
    library.set("jvm-retrofit2")
    inputSpec.set("$rootDir/specs/petstore.yaml")
    outputDir.set(buildApiDir)
    packageName.set(basePackage)
    apiPackage.set("$basePackage.$apiName.api")
    modelPackage.set("$basePackage.$apiName.model")
    configOptions.set(mapOf(
        "dateLibrary" to "java8",
        "useCoroutines" to "true"
    ))
    generateApiTests.set(false)
}

task<Copy>("copy") {
    val dirFrom = "$buildApiDir/src/main/kotlin/${basePackage.packageToDir()}"
    val dirInto = "$projectDir/src/main/java/${basePackage.packageToDir()}"

    doFirst {
        delete(file(dirInto))
    }

    dependsOn("generate")
    from(dirFrom)
    into(dirInto)
}

task("buildApi") {
    dependsOn("generate", "copy")
}