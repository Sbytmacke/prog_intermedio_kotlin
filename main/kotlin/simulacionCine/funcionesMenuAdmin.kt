package simulacionCine

import simulacionCine.enum.EstadoButaca
import simulacionCine.models.Butaca
import simulacionCine.models.Cliente
import simulacionCine.models.Sala

/**
 * Muestra un menú para el administrador.
 *
 * @param cine El cine del que se desea obtener información.
 * @param almacenClientes El almacén de clientes.
 */
fun menuAdmin(cine: Array<Sala>, almacenClientes: Array<Cliente>) {
    while (true) {
        portadaCinesAngel()
        println("(ADMIN) -> Selecciona la opción deseada: ")
        println("")
        println("1: Informe de butacas (libres, reservadas y ocupadas)")
        println("2: Recaudación total (caja final de las butacas compradas/ocupadas)")
        println("0: Salir")

        when (readln()) {
            // Admin
            "1" -> {
                falsoBorradoDeConsola()
                informeButacas(cine)
                while (!volverAlMenu()) {
                    // No saldremos del bucle hasta que introduzcamos "menu"
                }
                falsoBorradoDeConsola()
            }

            "2" -> {
                falsoBorradoDeConsola()
                informeRecaudacionTotal(cine)
                while (!volverAlMenu()) {
                    // No saldremos del bucle hasta que introduzcamos "menu"
                }
                falsoBorradoDeConsola()
            }
            // Salir
            "0" ->{
                falsoBorradoDeConsola()
                break
            }
        }
    }
}

/**
 * Muestra un informe de las butacas de una sala determinada.
 *
 * @param cine El cine del que se desea obtener información.
 */
fun informeButacas(cine: Array<Sala>){
    val almacenIDSalaElegida: Array<String> = Array<String> (1){" "}

    var salirBucle: Boolean = false
    while (!salirBucle) {
        mostrarCatalogo(cine)
        println("Introduzca el ID de la sala para representar un informe de su estado:")
        val entradaIDsala: String = readln()
        falsoBorradoDeConsola()

        // Filtro para elegir un ID existente
        for (i in cine.indices) {
            if (entradaIDsala == cine[i].id) {
                almacenIDSalaElegida[0] = entradaIDsala
                salirBucle = true
                break
            }
            if (i == cine.size - 1) {
                if (entradaIDsala != cine[i].id) {
                    println("ID inválido! Debe existir la sala en el CINE!")
                    Thread.sleep(2250)
                    falsoBorradoDeConsola()
                    break
                }
            }

        }

    }

    mostrarSala(cine, almacenIDSalaElegida[0])


    val salaElegida: Array<Sala> = Array<Sala> (1){cine[0]}
    for (i in cine.indices){
        if (cine[i].id == almacenIDSalaElegida[0]){
            salaElegida[0] = cine[i]
        }
    }

    val sala: Array<Array<Butaca>> = salaElegida[0].getMatrizSala()
    // Y a partir de ahí hacer informe: 1 SALA = 10 butacas compradas (2VIP y 8 NO VIP)

    var contadorButacasLibres: Int = 0
    var contadorButacasLibresVIP: Int = 0
    var contadorButacasReservadas: Int = 0
    var contadorButacasReservadasVIP: Int = 0
    var contadorButacasOcupadas: Int = 0
    var contadorButacasOcupadasVIP: Int = 0

    for (filas in sala.indices){
        for ( columnas in 0 until sala[filas].size){
            // Si es ESTÁNDAR libres
            if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.LIBRE && !sala[filas][columnas].getBooleanButacaVip()){
                contadorButacasLibres += 1
            }
            // Si es VIP libres
            if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.LIBRE && sala[filas][columnas].getBooleanButacaVip()){
                contadorButacasLibresVIP += 1
            }
            // Si es ESTÁNDAR reservadas
            if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.RESERVADO && !sala[filas][columnas].getBooleanButacaVip()){
                contadorButacasReservadas += 1
            }
            // Si es VIP reservadas
            if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.RESERVADO && sala[filas][columnas].getBooleanButacaVip()){
                contadorButacasReservadasVIP += 1
            }
            // Si es ESTÁNDAR ocupadas
            if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.OCUPADO && !sala[filas][columnas].getBooleanButacaVip()){
                contadorButacasOcupadas += 1
            }
            // Si es VIP ocupadas
            if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.OCUPADO && sala[filas][columnas].getBooleanButacaVip()){
                contadorButacasOcupadasVIP += 1
            }

        }
    }
    println("====================Informe SALA ${salaElegida[0].id}=======================")
    println("Butacas ESTÁNDAR libres -> ($contadorButacasLibres)")
    println("Butacas VIP libres ->  ($contadorButacasLibresVIP)")
    println("")
    println("Butacas ESTÁNDAR reservadas -> ($contadorButacasReservadas)")
    println("Butacas VIP reservadas ->  ($contadorButacasReservadasVIP)")
    println("")
    println("Butacas ESTÁNDAR ocupadas ->($contadorButacasOcupadas)")
    println("Butacas VIP ocupadas ->  ($contadorButacasOcupadasVIP)")
    println("=========================================================")
}

/**
 * Calcula la recaudación total del cine en función número de butacas ocupadas y su precio.
 *
 * @param cine Array de objetos Sala con las salas del cine
 */
fun informeRecaudacionTotal(cine: Array<Sala>){
    var contadorButacasVIP: Int = 0
    var contadorButacasNoVIP: Int = 0

    // Tenemos que recorrer cada sala del cine
    for (i in cine.indices){
        var sala: Array<Array<Butaca>> = cine[i].getMatrizSala()
        // Recorremos cada sala
        for ( filas in sala.indices){
            for ( columnas in 0 until sala[filas].size){
                // Si es VIP
                if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.OCUPADO && sala[filas][columnas].getBooleanButacaVip()){
                    contadorButacasVIP += 1
                }
                // Si no es VIP
                if (sala[filas][columnas].getEstadoButaca() == EstadoButaca.OCUPADO && !sala[filas][columnas].getBooleanButacaVip()){
                    contadorButacasNoVIP += 1
                }
            }
        }
    }

    val totalVIP: Double = contadorButacasVIP * PRECIO_VIP
    val totalNoVIP: Double = contadorButacasNoVIP* PRECIO_ESTANDAR
    val totalFinal: Double = totalVIP + totalNoVIP
    println("====================Informe de CAJA FINAL=======================")
    println("Recaudación butacas ESTÁNDAR ($contadorButacasNoVIP) -> $totalNoVIP€ ")
    println("Recaudación butacas VIP ($contadorButacasVIP) -> $totalVIP€ ")
    println("Recaudación TOTAL -> $totalFinal€ ")
    println("================================================================")
}

/**
 * Verifica si una contraseña es correcta.
 *
 * @param passCorrect La contraseña correcta a comparar.
 * @return "true" si la contraseña es correcta, "false" si no lo es.
 */
fun isPassCorrect(passCorrect:String): Boolean {
    while (true) {
        println("Introduzca la contraseña:")
        val entradaInicioSesion: String = readln()
        if (entradaInicioSesion == passCorrect) {
            falsoBorradoDeConsola()
            return true
        }else{
            println("Contraseña incorrecta!")
            Thread.sleep(1500)
            falsoBorradoDeConsola()
            return false
        }
    }
}