package r2d2Sondeo

const val MAX_CARGA: Int = 150

// Deberían ser 4min...
const val TIEMPO_RECOGIDA_ROBOT: Long = 200

// Deberia ser 2min...
const val TIEMPO_MOVIMIENTO_ROBOT: Long = 200

// Deberían ser 40min...
const val MAX_TIEMPO_SIMULACION: Long = 300000

const val TAMANNO_FILAS_MAPA: Int = 10
const val TAMANNO_COLUMNAS_MAPA: Int = 10


fun main() {
    simulacionRecogida()
}

fun simulacionRecogida() {
    var contadorTiempoSimulacion: Long = 0

    // Inicializamos Los Tres Mapas
    val matrizInicialReferencia: Array<CharArray> = Array(TAMANNO_FILAS_MAPA) { CharArray(TAMANNO_COLUMNAS_MAPA) }
    val matrizMateriales: Array<CharArray> = generarMateriales(matrizInicialReferencia)
    val matrizCantidades: Array<IntArray> = generarMatrizCantidades(matrizMateriales)

    imprimirMatrizChar(matrizInicialReferencia)
    println()

    // Posicion incial r2d2
    val posicionRobot: IntArray = intArrayOf(1, 1)
    var sorteoDireccion: Int = sortearDireccion()

    // Inicializo almacen robot
    //Primer indice = r, segundo = t, tercer = m, cuarto = o
    val almacenDeItems: IntArray = IntArray(4) { 0 }

    do {

        // Se moverá una vez hasta que encuentre algo o llegue al limite
        if (posicionRobot[1] == matrizInicialReferencia[0].size - 1
            || posicionRobot[0] == matrizInicialReferencia.size - 1
            || posicionRobot[1] == 0
            || posicionRobot[0] == 0
        ) {
            // Si llega al limite maximo de la matriz debe cambiar de direccionR2D2
            val sondeoDireccionNueva: Int = sortearDireccion()
            movimientoR2D2(matrizInicialReferencia, posicionRobot, sondeoDireccionNueva)
            sorteoDireccion = sondeoDireccionNueva
        } else {
            // En caso contrario sigue avanzando en una direccion, sin salirse de la matriz
            movimientoR2D2(matrizInicialReferencia, posicionRobot, sorteoDireccion)
        }

        // Por cada movimiento del robot tarda 2 min
        Thread.sleep(TIEMPO_MOVIMIENTO_ROBOT)
        contadorTiempoSimulacion += TIEMPO_MOVIMIENTO_ROBOT

        // Por cada recogida de objeto que será de 1 a 5kg, tardará 4min
        if (recogerItem(matrizInicialReferencia, matrizMateriales, matrizCantidades, posicionRobot, almacenDeItems)) {
            do {
                Thread.sleep(TIEMPO_RECOGIDA_ROBOT)
                contadorTiempoSimulacion += TIEMPO_RECOGIDA_ROBOT
            } while (recogerItem(
                    matrizInicialReferencia,
                    matrizMateriales,
                    matrizCantidades,
                    posicionRobot,
                    almacenDeItems
                )
            )
        }

        // Imprimiendo el recorrido y mapa del robot
        recorridoR2D2(matrizInicialReferencia, posicionRobot)
        imprimirMatrizChar(matrizInicialReferencia)
        println()

    } while (!isFullRobot(almacenDeItems) && contadorTiempoSimulacion < MAX_TIEMPO_SIMULACION)

    informeFinal(almacenDeItems)

}

fun isFullRobot(almacenDeItems: IntArray): Boolean {
    var cargaRobotActual: Int = 0
    for (i in almacenDeItems.indices) {
        cargaRobotActual += almacenDeItems[i]
    }

    if (cargaRobotActual >= MAX_CARGA) {
        return true
    }
    return false
}

fun informeFinal(almacenDeItems: IntArray) {
    println("R2D2 ha recogido ROCA con un total de: ${almacenDeItems[0]} kg")
    println("R2D2 ha recogido TIERRA con un total de: ${almacenDeItems[1]} kg")
    println("R2D2 ha recogido MINERALES con un total de: ${almacenDeItems[2]} kg")
    println("R2D2 ha recogido OTROS con un total de: ${almacenDeItems[3]} kg")

    var cargaRobotActual: Int = 0
    for (i in almacenDeItems.indices) {
        cargaRobotActual += almacenDeItems[i]
    }
    println("R2D2 ha recogido UN TOTAL de: $cargaRobotActual kg")

    if (isFullRobot(almacenDeItems)) {
        println("CUIDADO: R2D2 está en su límite de espacio de carga máxima siendo: $MAX_CARGA")
    }


    println("El mapa de los minerales recogidos:")
    // TODO: MAPA MINERALES QUE HA RECOGIDO
    println("El mapa de la cantidad recogida:")
    // TODO: MAPA CANTIDAD DE LOS MINERALES QUE HA RECOGIDO
}

fun recogerItem(
    matrizPrincipal: Array<CharArray>,
    matrizMateriales: Array<CharArray>,
    matrizCantidades: Array<IntArray>,
    posicionRobot: IntArray,
    almacenDeItems: IntArray
): Boolean {
    for (filas in matrizPrincipal.indices) {
        for (columnas in matrizPrincipal[filas].indices) {
            if (!isFullRobot(almacenDeItems)) {
                // Caso de encontrar roca
                if (matrizMateriales[filas][columnas] == 'r'
                    && filas == posicionRobot[0]
                    && columnas == posicionRobot[1]
                ) {
                    val cantidadKiloRecogeRobot: Int = sorteoRecogida()
                    if (cantidadKiloRecogeRobot <= matrizCantidades[filas][columnas]) {
                        almacenDeItems[0] += cantidadKiloRecogeRobot
                        matrizCantidades[filas][columnas] -= cantidadKiloRecogeRobot
                        println("R2D2 ha recogido ROCA con un peso de $cantidadKiloRecogeRobot kg")
                    }
                    if (matrizCantidades[filas][columnas] == 0) {
                        return false
                    }
                    return true
                }
                // Caso de encontrar tierra
                if (matrizMateriales[filas][columnas] == 't'
                    && filas == posicionRobot[0]
                    && columnas == posicionRobot[1]
                ) {
                    val cantidadKiloRecogeRobot: Int = sorteoRecogida()
                    if (cantidadKiloRecogeRobot <= matrizCantidades[filas][columnas]) {
                        almacenDeItems[1] += cantidadKiloRecogeRobot
                        matrizCantidades[filas][columnas] -= cantidadKiloRecogeRobot
                        println("R2D2 ha recogido TIERRA con un peso de $cantidadKiloRecogeRobot kg")
                    }
                    if (matrizCantidades[filas][columnas] == 0) {
                        return false
                    }
                    return true
                }
                // Caso de encontrar minerales
                if (matrizMateriales[filas][columnas] == 'm'
                    && filas == posicionRobot[0]
                    && columnas == posicionRobot[1]
                ) {
                    val cantidadKiloRecogeRobot: Int = sorteoRecogida()
                    if (cantidadKiloRecogeRobot <= matrizCantidades[filas][columnas]) {
                        almacenDeItems[2] += cantidadKiloRecogeRobot
                        matrizCantidades[filas][columnas] -= cantidadKiloRecogeRobot
                        println("R2D2 ha recogido MINERALES con un peso de $cantidadKiloRecogeRobot kg")
                    }
                    if (matrizCantidades[filas][columnas] == 0) {
                        return false
                    }
                    return true
                }
                // Caso de encontrar otros
                if (matrizMateriales[filas][columnas] == 'o'
                    && filas == posicionRobot[0]
                    && columnas == posicionRobot[1]
                ) {
                    val cantidadKiloRecogeRobot: Int = sorteoRecogida()
                    if (cantidadKiloRecogeRobot <= matrizCantidades[filas][columnas]) {
                        almacenDeItems[3] += cantidadKiloRecogeRobot
                        matrizCantidades[filas][columnas] -= cantidadKiloRecogeRobot
                        println("R2D2 ha recogido OTROS con un peso de $cantidadKiloRecogeRobot kg")
                    }
                    if (matrizCantidades[filas][columnas] == 0) {
                        return false
                    }
                    return true
                }
            }
        }
    }
    return false
}

// || matrizMateriales[filas][columnas] == 't'
//                        || matrizMateriales[filas][columnas] == 'm'
//                        || matrizMateriales[filas][columnas] == 'o'

fun sorteoRecogida(): Int {
    val sorteoRecogidaKilos: Int = (1..5).random()
    return sorteoRecogidaKilos
}

fun generarMatrizCantidades(matrizMateriales: Array<CharArray>): Array<IntArray> {
    val matrizCantidadesInicial: Array<CharArray> = matrizMateriales.clone()
    val matrizCantidadesNumerica: Array<IntArray> =
        Array(matrizCantidadesInicial.size) { IntArray(matrizCantidadesInicial[0].size) }

    // Primero debemos pasar la matriz a valores numericos donde haya materiales
    for (filas in 0 until matrizCantidadesInicial.size) {
        for (columnas in 0 until matrizCantidadesInicial[filas].size) {
            if (matrizCantidadesInicial[filas][columnas] == 'r'
                || matrizCantidadesInicial[filas][columnas] == 't'
                || matrizCantidadesInicial[filas][columnas] == 'm'
                || matrizCantidadesInicial[filas][columnas] == 'o'
            ) {
                matrizCantidadesNumerica[filas][columnas] = sorteoCantidad()
            } else {
                matrizCantidadesNumerica[filas][columnas] = 0
            }
        }
    }

    return matrizCantidadesNumerica
}

fun sorteoCantidad(): Int {
    val sorteoCantidadMaterial: Int = (10..25).random()
    return sorteoCantidadMaterial
}

fun generarMateriales(matriz: Array<CharArray>): Array<CharArray> {
    val matrizMateriales: Array<CharArray> = matriz.clone()

    for (filas in 0 until matrizMateriales.size) {
        for (columnas in 0 until matrizMateriales[filas].size) {
            if (matrizMateriales[filas][columnas] != 'R') {
                matrizMateriales[filas][columnas] = materialAleatorio()
            } else if (matrizMateriales[filas][columnas] == 'R') {
                matrizMateriales[filas][columnas] = 'x'
            }
        }
    }
    return matrizMateriales
}

fun materialAleatorio(): Char {
    val materialSorteo: Int = (1..10).random()
    if (materialSorteo == 1) {
        return 'r'
    }
    if (materialSorteo == 2) {
        return 't'
    }
    if (materialSorteo == 3) {
        return 'm'
    }
    if (materialSorteo == 4) {
        return 'o'
    }
    if (materialSorteo == 5
        || materialSorteo == 6
        || materialSorteo == 7
        || materialSorteo == 8
        || materialSorteo == 9
        || materialSorteo == 10
    ) {
        return 'x'
    }
    return '?'
}

fun recorridoR2D2(matriz: Array<CharArray>, posicionRobot: IntArray) {

    // Recorrido que lleva el robot
    for (filas in 0 until matriz.size) {
        for (columnas in 0 until matriz[filas].size) {
            if (matriz[filas][columnas] == 'R') {
                matriz[filas][columnas] = '.'
            }
        }
    }
    // Inserta nueva posicion del robot
    for (filas in 0 until matriz.size) {
        for (columnas in 0 until matriz[filas].size) {
            matriz[posicionRobot[0]][posicionRobot[1]] = 'R'
        }
    }
}

fun imprimirMatrizChar(matriz: Array<CharArray>) {

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

fun imprimirMatrizInt(matriz: Array<IntArray>) {

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

fun movimientoR2D2(matriz: Array<CharArray>, posicionRobot: IntArray, sorteoDireccion: Int) {
    // Decidimos a que dirección se moverá
    // 1 = Norte, 2 = Sur, 3 = Este, 4 = Oeste

    // Direccion Norte
    if (sorteoDireccion == 1 && posicionRobot[0] != 0) {
        posicionRobot[0] = posicionRobot[0] - 1
    }
    // Direccion Sur
    if (sorteoDireccion == 2 && posicionRobot[0] != matriz.size - 1) {
        posicionRobot[0] = posicionRobot[0] + 1
    }
    // Direccion Este
    if (sorteoDireccion == 3 && posicionRobot[1] != matriz[0].size - 1) {
        posicionRobot[1] = posicionRobot[1] + 1
    }
    // Direccion Oeste
    if (sorteoDireccion == 4 && posicionRobot[1] != 0) {
        posicionRobot[1] = posicionRobot[1] - 1
    }
}

fun sortearDireccion(): Int {
    return (1..4).random()
}