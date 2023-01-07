package simulacionCine

import simulacionCine.models.Sala

/**
 * Muestra información detallada del cine y sus salas.
 *
 * @param cine El cine a mostrar.
 */
fun mostrarCine(cine: Array<Sala>) {
    portadaCinesAngel()
    for (i in cine.indices) {
        println("=======--- SALA -> ${cine[i].id}---=======")
        println(cine[i])
        println("========--- PANTALLA ---========")
        cine[i].imprimirMatrizButacas()
        println("")
    }
}

/**
 * Muestra un catálogo de películas disponibles en el cine.
 *
 * @param cine El cine del que se desea obtener el catálogo de películas.
 */
fun mostrarCatalogo(cine: Array<Sala>) {
    for (element in cine) {
        println(element)
        println("")
    }
}

/**
 * Muestra información detallada de una sala en particular.
 *
 * @param cine El cine al que pertenece la sala.
 * @param idSala El ID de la sala a mostrar.
 */
fun mostrarSala(cine: Array<Sala>, idSala: String) {
    portadaCinesAngel()
    println("=======--- SALA -> $idSala---=======")
    println("========--- PANTALLA ---========")
    for (i in cine.indices) {
        if (cine[i].id == idSala) {
            cine[i].imprimirMatrizButacas()
        }
    }
    println("")
}

/**
 * Muestra la portada del cine.
 */
fun portadaCinesAngel() {
    val red = "\u001b[31m"
    val reset = "\u001b[0m"
    println("$red======================$reset")
    println("  ***CINES ANGEL***")
    println("Precio = 5,25€/persona")
    println(" VIP = 8,5€/persona")
    println("$red======================$reset")
    println("")
}

/**
 * Crea una sensación de borrado de la consola.
 * Mediante una impresión de varias líneas en blanco.
 */
fun falsoBorradoDeConsola() {
    // Para crear una senación de borrado en consola
    for (i in 0 until 40) {
        println("")
    }
}

