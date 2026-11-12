# Mars Photos · Android 15

Implementación del codelab [Cómo obtener datos de Internet](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?hl=es-419) en Kotlin y Jetpack Compose. Usa API 35 como SDK de compilación y destino.

1. Abre esta carpeta en Android Studio y sincroniza Gradle con Internet. Usa JDK 17 o 21 como Gradle JDK.
2. En Device Manager selecciona o crea un emulador con Android 15 (API 35). También puedes conectar un teléfono con Android 15.
3. Ejecuta la configuración `app`. La pantalla muestra la cantidad de registros devueltos por el endpoint oficial de Marte.
4. En el emulador, desactiva Wi-Fi y datos móviles y vuelve a abrir la app para comprobar el estado de error. Restablece la conexión y pulsa **Reintentar**.

Retrofit realiza la solicitud GET, kotlinx.serialization convierte el JSON a una lista de `MarsPhoto` y el `MarsViewModel` actualiza el estado de Compose.
