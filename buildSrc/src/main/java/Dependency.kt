object Dependency {
    object Version {
        const val kotlin = "1.7.0"

        const val retrofit = "2.9.0"
        const val okHttp = "4.10.0"
        const val moshi = "1.13.0"
    }

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
    const val retrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:${Version.retrofit}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
    const val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Version.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
}