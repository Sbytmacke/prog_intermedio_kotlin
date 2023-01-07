package macAdressMenu

// TODO: mucha identación en la función menuMac()
fun main() {
    //val ejemploMacEntrada: String = "91:75:1a:ec:9a:c7"
    menuMac()
}

fun menuMac() {

    var listadoMacs: Array<String> = Array(10) { "vacio" }

    do {
        println("MENU MAC:")
        println("(1) Listar Mac")
        println("(2) Encontrar Mac")
        println("(3) Añadir Mac")
        println("(4) Borrar Mac")
        println("(5) Modificar Mac")
        println("(0) Salir")

        var entradaUsuario: String = "-1"
        entradaUsuario = readln()

        // Filtro numérico del 0-5 del menú
        if (!filtroEntradaMenu(entradaUsuario)) {
            println("Debes introducir un valor entre (0-5)")
        }

        if (entradaUsuario == "1") {
            listarVector(listadoMacs)
        }
        if (entradaUsuario == "2") {
            do {
                println("Introduce una Mac para encontrar:")
                val entradaToSearchMac: String = readln()

                // Filtro numérico del 0-hasta el final de la lista...
                if (!filtradoMac(entradaToSearchMac)) {
                    println("Debes introducir un valor de MAC válido con valores entre [0-9][a-f], ejemplo: 91:75:1a:ec:9a:c7")
                }
                if (filtradoMac(entradaToSearchMac)) {
                    val resultadoBusqueda: Int = encontrarMac(entradaToSearchMac, listadoMacs)
                    if (resultadoBusqueda != -1) {
                        println("La mac existe en el listado con posición: $resultadoBusqueda")
                    }
                    if (resultadoBusqueda == -1) {
                        println("No existe la mac en el listado")
                    }
                }
            } while (!filtradoMac(entradaToSearchMac))
        }
        if (entradaUsuario == "3") {
            // Filtro de MAC
            do {
                println("Introduce una Mac para almacenar:")
                val entradaMac: String = readln()
                if (!filtradoMac(entradaMac)) {
                    println("Debes introducir un valor de MAC válido con valores entre [0-9][a-f], ejemplo: 91:75:1a:ec:9a:c7")
                }
                if (filtradoMac(entradaMac)) {
                    listadoMacs = insertMacToVector(entradaMac, listadoMacs)
                }
            } while (!filtradoMac(entradaMac))
        }
        if (entradaUsuario == "4") {
            // Filtro numérico del 0-hasta el final de la lista...
            do {
                println("Introduce la posición de la Mac que quieras eliminar:")
                val entradaIndiceToDelete: String = readln()
                if (!filtradoIndiceVector(entradaIndiceToDelete, listadoMacs)) {
                    println("Debes introducir un valor entre [0-${listadoMacs.size - 1}]")
                }
                if (filtradoIndiceVector(entradaIndiceToDelete, listadoMacs)) {
                    listadoMacs = deleteMacOfVector(entradaIndiceToDelete, listadoMacs)
                }
            } while (!filtradoIndiceVector(entradaIndiceToDelete, listadoMacs))

        }
        if (entradaUsuario == "5") {
            do {
                println("Introduce la posición de la Mac que quieras modificar:")
                // Filtro numérico del 0-hasta el final de la lista...
                val entradaIndice: String = readln()
                if (!filtradoIndiceVector(entradaIndice, listadoMacs)) {
                    println("Debes introducir un valor entre [0-${listadoMacs.size - 1}]")
                }
                if (filtradoIndiceVector(entradaIndice, listadoMacs)) {
                    modificarMacOfVector(entradaIndice, listadoMacs)
                }
            } while (!filtradoIndiceVector(entradaIndice, listadoMacs))
        }

    } while (entradaUsuario != "0" || !filtroEntradaMenu(entradaUsuario))
}

fun filtradoMac(entradaMac: String): Boolean {
// Expresión regular
    val regex = Regex("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\$")
    if (!regex.matches(entradaMac)) {
        return false
    }
    return true
}

fun filtradoIndiceVector(entradaToSearchMac: String, vector: Array<String>): Boolean {
    // Expresión regular
    val regex = Regex("[0-${vector.size - 1}]")
    if (!regex.matches(entradaToSearchMac)) {
        return false
    }
    return true
}

fun filtroEntradaMenu(entradaUsuario: String): Boolean {
    // Expresión regular
    val regex = Regex("[0-5]")
    if (!regex.matches(entradaUsuario)) {
        return false
    }
    return true
}

fun modificarMacOfVector(entradaIndice: String, listadoMacs: Array<String>) {
    val entrada: Int = entradaIndice.toInt()
    println("Mac actual: ${listadoMacs[entrada]}")
    do {
        print("Mac modificada:")
        val nuevaMac: String = readln()
        if (!filtradoMac(nuevaMac)) {
            println("Debes introducir un valor de MAC válido con valores entre [0-9][a-f], ejemplo: 91:75:1a:ec:9a:c7")
        }
        if (filtradoMac(nuevaMac)) {
            listadoMacs[entrada] = nuevaMac
            println("MAC MODIFICADA CORRECTAMENTE")
        }
    } while (!filtradoMac(nuevaMac))
    // No hace falta devolver ningún vector, ya que pasa por referencia el vector al cambiar solo un dato
}

fun encontrarMac(entradaToSearchMac: String, listadoMacs: Array<String>): Int {
    for (i in listadoMacs.indices) {
        if (entradaToSearchMac == listadoMacs[i]) {
            return i
        }
    }
    return -1
}

// Una vez insertemos hay que recalcular el tamanno
fun insertMacToVector(mac: String, listadoMac: Array<String>): Array<String> {
    // Se insertará la mac en la primera posicion vacía que haya, al estar incializado el vector de cadenas, será "vacio"
    for (i in listadoMac.indices) {
        if (listadoMac[i] == "vacio") {
            listadoMac[i] = mac
            // Salir una vez lo introduzcamos para no repetir en el vector el valor
            break
        }
    }
    println("MAC INSERTADA CORRECTAMENTE")
    // Recalculamos el tamaño del vector
    return recalcularVector(listadoMac)
}

// Borramos seleccionando el indice, por tanto habra que listar previamente para saber cual borrar
// Una vez borremos hay que recalcular el tamaño
fun deleteMacOfVector(indexToDelete: String, listadoMac: Array<String>): Array<String> {
    val indice: Int = indexToDelete.toInt()
    listadoMac[indice] = "vacio"
    println("MAC ELIMINADA CORRECTAMENTE")
    // Recalculamos
    return recalcularVector(listadoMac)
}

// TODO: mejorar  el recalculamiento del tammanoNuevoVector, ya que no es del todo correcto, aunque nunca dará error, ya que está creando más espacios en nuiestro vector d elo debido, únicamente un error de optmización
// Recalcular vector en cada acción que realizamos de agregar o eliminar macAdress
fun recalcularVector(vector: Array<String>): Array<String> {
    // Si queremos cambiar el valor a recalcular únicamente cambiamos en porcentajeRecalculado
    val porcentajeLibreDelVector: Double = 0.2
    // Ej: 10 - 0.2*10 = 8, esto servirá para que a partir de 9 posiciones ocupadas aumentamos el vector un 20% más
    val limiteAumentoVector: Double = vector.size - porcentajeLibreDelVector * vector.size

    var posicionesOcupadas: Double = 0.0
    for (i in 0 until vector.size) {
        if (vector[i] != "vacio") {
            posicionesOcupadas += 1
        }
    }
    // Definir un tamaño de vector
    var tammanoNuevoVector: Int = vector.size
    // En el caso de que haya 9 posiciones ocupadas en nuestro vector de 10, es decir ya no hay un 20% de espacio
    if (posicionesOcupadas > limiteAumentoVector) {
        // Aumentamos para llegar al 20% más de espacio
        tammanoNuevoVector = ((porcentajeLibreDelVector * vector.size) + vector.size).toInt()
    }

    val nuevoVectorRecalculado: Array<String> = Array<String>(tammanoNuevoVector) { "vacio" }
    // Introducimos los valores del anterior vector al nuevo
    for (i in vector.indices) {
        nuevoVectorRecalculado[i] = vector[i]
    }

    return nuevoVectorRecalculado
}

// Imprimir/Listar en consola el vector
fun listarVector(vector: Array<String>) {
    for (i in vector.indices) {
        println("Mac ${i}º: ${vector[i]} ")
    }
    println()
}