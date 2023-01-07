package gameOfTheLife

const val TAMANNO_MAPA: Int = 7

// 1000 ms = 1 segundo
const val TIEMPO_ESPERA_NUEVA_POBLACION: Long = 1_000
const val LIMITE_TIEMPO_JUEGO: Long = 35_000
fun main() {
    gameOfTheLife()
}

fun gameOfTheLife() {
    val matrizInicial: Array<BooleanArray> = generarMatrizAleatoria()
    var contadorTiempo: Long = 0
    var juegoPerdido: Int = 0
    // Por si quiero estudiar una matriz definida:
//    val matrizInicial: Array<BooleanArray> = arrayOf(
//        booleanArrayOf(false, true, false),
//        booleanArrayOf(false, false, false),
//        booleanArrayOf(true, false, false)
//    )

    // Necesito hacer el bucle, hasta que termine el contador de tiempo, o hasta que se mueran todas las celulas
    do {
        imprimirMatriz(matrizInicial)
        juegoPerdido = siguientePoblacionDeMatriz(matrizInicial)
        println()

        Thread.sleep(TIEMPO_ESPERA_NUEVA_POBLACION)
        contadorTiempo += TIEMPO_ESPERA_NUEVA_POBLACION

    } while (juegoPerdido != 0 && contadorTiempo < LIMITE_TIEMPO_JUEGO)

    if (juegoPerdido != 0) {
        println("HAS GANADO")
        println("Quedan células viva")
    } else {
        println("HAS PERDIDO")
        println("No queda ninguna célula viva")
    }
}

fun generarMatrizAleatoria(): Array<BooleanArray> {
    val matrizInicial: Array<BooleanArray> = Array(TAMANNO_MAPA) { (BooleanArray(TAMANNO_MAPA)) }
    var sorteoBooleano: Int = 0
    for (filas in matrizInicial.indices) {
        for (columnas in matrizInicial[filas].indices) {
            sorteoBooleano = (0..1).random()
//            if (sorteoBooleano == 0) {
//                matrizInicial[filas][colunas] = true
//            } else {
//                matrizInicial[filas][colunas] = false
//            }
            matrizInicial[filas][columnas] = sorteoBooleano == 0
        }
    }
    return matrizInicial
}

// Devolverá si hemos perdido el juego, si es igual a 0 = hemos perdido
fun siguientePoblacionDeMatriz(matrizEscritura: Array<BooleanArray>): Int {
    // Debemos aplicar dobleBuffer, una matriz de lectura y otra de escritura clonándola
    val matrizLectura: Array<BooleanArray> = clonarMatriz(matrizEscritura)
    // Recorrido individual de cada célula
    var contadorCelulasVivasAlrededor: Int = 0
    // Si el contador es igual a cero habremos perdido
    var contadorCelulasVivasJuegoPerdido: Int = 0
    contadorCelulasVivasJuegoPerdido = 0
    for (filas in matrizLectura.indices) {
        for (columnas in matrizLectura[filas].indices) {
            // Reiniciamos el contador por siguiente casilla
            contadorCelulasVivasAlrededor = 0
            // Miramos alrededor de una celula, para contar si tiene celulas vivas alrededor
            if (filas - 1 >= 0 && columnas - 1 >= 0) {
                if (matrizLectura[filas - 1][columnas - 1]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }
            if (filas - 1 >= 0) {
                if (matrizLectura[filas - 1][columnas]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }
            if (filas - 1 >= 0 && columnas + 1 <= matrizLectura[filas].size - 1) {
                if (matrizLectura[filas - 1][columnas + 1]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }
            if (columnas - 1 >= 0) {
                if (matrizLectura[filas][columnas - 1]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }
            if (columnas + 1 <= matrizLectura[filas].size - 1) {
                if (matrizLectura[filas][columnas + 1]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }
            if (filas + 1 <= matrizLectura.size - 1 && columnas - 1 >= 0) {
                if (matrizLectura[filas + 1][columnas - 1]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }
            if (filas + 1 <= matrizLectura.size - 1) {
                if (matrizLectura[filas + 1][columnas]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }
            if (filas + 1 <= matrizLectura.size - 1 && columnas + 1 <= matrizLectura[filas].size - 1) {
                if (matrizLectura[filas + 1][columnas + 1]) {
                    contadorCelulasVivasAlrededor += 1
                }
            }

            // Cambiar el valor de la celula en la matriz de escritura
            if (matrizLectura[filas][columnas] && (contadorCelulasVivasAlrededor == 0 || contadorCelulasVivasAlrededor == 1 || contadorCelulasVivasAlrededor >= 4)) {
                // Si ocurre la celula muere
                matrizEscritura[filas][columnas] = false
            } else if (!matrizLectura[filas][columnas] && contadorCelulasVivasAlrededor == 3) {
                // Si ocurre la celula nace
                matrizEscritura[filas][columnas] = true
            }

            // Si existe true, en la posicion que recorremos de toda la matriz todavia existe celula
            if (matrizLectura[filas][columnas]) {
                contadorCelulasVivasJuegoPerdido += 1
            }
        }
    }
    return contadorCelulasVivasJuegoPerdido
}

fun imprimirMatriz(matriz: Array<BooleanArray>) {
    for (filas in 0 until matriz.size) {
        for (columnas in 0 until matriz[filas].size) {
            if (columnas == matriz[filas].size - 1) {
                println("${matriz[filas][columnas]} ")
            } else {
                print("${matriz[filas][columnas]} ")
            }
        }
    }
}

fun clonarMatriz(matrixLectura: Array<BooleanArray>): Array<BooleanArray> {
    val matrixEscritura = Array(matrixLectura.size) { BooleanArray(matrixLectura[0].size) }
    for (fila in matrixLectura.indices) {
        for (columna in matrixLectura[fila].indices) {
            matrixEscritura[fila][columna] = matrixLectura[fila][columna]
        }
    }
    return matrixEscritura
}

