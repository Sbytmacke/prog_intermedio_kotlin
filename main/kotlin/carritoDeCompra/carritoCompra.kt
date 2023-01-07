package carritoDeCompra

fun main() {
    val matrizInicial: Array<Array<String>> = Array(11) { (Array(7) { "null" }) }

    // Para introducir correctamente en la posición de la fila siguiente en precioFinalConIva especifico
    var contadorFila: Int = 1

    // Se repite hasta que queramos models.carritoDeCompra.salir
    do {
        // Para ver como va nuestra matriz
        imprimirCarroCompraMatriz(matrizInicial)

        val producto: String = preguntarProducto()
        introducirObjetoEnFilaVacia(matrizInicial, producto)

        val precioUnitario: String = preguntarPrecioUnitario()
        introducirPrecioUnitarioEnFilaDelObjeto(matrizInicial, precioUnitario)

        val cantidadObjeto: String = preguntarCantidadObjeto()
        introducirCantidadEnFilaDelObjeto(matrizInicial, cantidadObjeto)

        val tipoIVA: String = preguntarTipoIVA()
        introducirTipoIVA(matrizInicial, tipoIVA)

        val precioFinal: String = calcularPrecioFinal(precioUnitario, cantidadObjeto)
        introducirPrecioFinalSinIVAEnFilaDelObjeto(matrizInicial, precioFinal)

        introducirPrecioFinalConIVAEnFilaDelObjeto(matrizInicial, precioFinal, tipoIVA, contadorFila)
        contadorFila += 1

        // Para ver como va nuestra matriz
        imprimirCarroCompraMatriz(matrizInicial)

        // Para models.carritoDeCompra.salir y validar en test necesito crear el parametro
        var salida: String = respuestaSalir()
    } while (!salir(salida))

}

fun respuestaSalir(): String {
    println("¿Quieres introducir más productos en tu cesta? (Y/N):")
    var entradaDecision: String = ""
    entradaDecision = readln()
    return entradaDecision
}

// Introducir más productos o terminar
fun salir(entradaDecision: String): Boolean {

    if (entradaDecision == "Y" || entradaDecision == "y") {
        return false
    }
    if (entradaDecision == "N" || entradaDecision == "n") {
        return true
    }
    return true
}

// Preguntar nombre del producto
fun preguntarProducto(): String {
    println("Cual es tu producto:")
    var producto: String = " "
    producto = readln()
    println()
    return producto
}

// Recorrer la matriz, en el vector del objeto y buscar en la primera posición vacía
// Vector del objeto = 0
fun introducirObjetoEnFilaVacia(matrizInicial: Array<Array<String>>, productoNuevo: String) {
    // Siempre será la columna cero, para ver si tenemos algún objeto
    val columnas: Int = 0
    var contadorLimiteFilas: Int = 0
    for (fila in 1 until 10) {
        if (matrizInicial[fila][columnas] == "null" && contadorLimiteFilas == 0) {
            // Introducir en la posición [0,0] de nuestra matriz, el nombre, siendo una cadena
            matrizInicial[fila][columnas] = productoNuevo
            // Contador para introducir el producto una vez únicamente
            contadorLimiteFilas += 1

        }
    }
}

// Preguntar precio unitario
fun preguntarPrecioUnitario(): String {
    println("Cual es el precio unitario del producto:")
    var precioUnitario: String = ""
    precioUnitario = readln()
    println()
    return precioUnitario
}

// Recorrer la matriz y buscar en la primera posición vacía
// Introducir en la posición [0,1] de nuestra matriz, el precio unitario, siendo un entero
fun introducirPrecioUnitarioEnFilaDelObjeto(matrizInicial: Array<Array<String>>, precioUnitario: String) {
    // Siempre será la columna uno, para ver si tenemos algún objeto
    val columnas: Int = 1
    var contadorLimite: Int = 0
    for (fila in 1 until 10) {
        if (matrizInicial[fila][columnas] == "null" && contadorLimite == 0) {
            // Introducir en la posición [0,0] de nuestra matriz, el nombre, siendo una cadena
            matrizInicial[fila][columnas] = "$precioUnitario€"
            // Contador para introducir el producto una vez únicamente
            contadorLimite += 1
        }
    }
}

// Preguntar cantidad
fun preguntarCantidadObjeto(): String {
    println("Cuantas veces has cogido el objeto (la suma total de todos los objetos dentro del carrito como máximo debe ser 10):")
    var cantidadObjeto: String = ""
    cantidadObjeto = readln()
    println()
    return cantidadObjeto
}

// Recorrer la matriz y buscar en la primera posición vacía
// Introducir en la posición [0,2] de nuestra matriz, la cantidad, siendo un entero
fun introducirCantidadEnFilaDelObjeto(matrizInicial: Array<Array<String>>, cantidadObjeto: String) {
    // Siempre será la columna dos, para ver si tenemos algún objeto
    val columnas: Int = 2
    var contadorLimite: Int = 0
    for (fila in 1 until 10) {
        if (matrizInicial[fila][columnas] == "null" && contadorLimite == 0) {
            // Introducir en la posición [0,0] de nuestra matriz, el nombre, siendo una cadena
            matrizInicial[fila][columnas] = cantidadObjeto
            // Contador para introducir el producto una vez únicamente
            contadorLimite += 1
        }
    }
}

// Preguntar tipo de IVA
fun preguntarTipoIVA(): String {
    println("Define su tipo de IVA (0 = sin IVA, 1 = IVA 10%, 2 = IVA 21%:")
    var tipoIVA: String = ""
    tipoIVA = readln()
    println()
    return tipoIVA
}

// Recorrer la matriz y buscar en la primera posición, siendo un entero
// Introducir en la posición [0,3] de nuestra matriz
fun introducirTipoIVA(matrizInicial: Array<Array<String>>, tipoIVA: String) {
    // Siempre será la columna tres, para ver si tenemos algún objeto
    val columnas: Int = 3
    var contadorLimite: Int = 0
    for (fila in 1 until 10) {
        if (matrizInicial[fila][columnas] == "null" && contadorLimite == 0) {
            // Introducir en la posición [0,0] de nuestra matriz, el nombre, siendo una cadena
            matrizInicial[fila][columnas] = tipoIVA
            // Contador para introducir el producto una vez únicamente
            contadorLimite += 1
        }
    }
}

// Ver el tipo de IVA, para calcular: (precio unitario*cantidad)*0,21) o (precio unitario*cantidad)*0,1), siendo
// Necestaremos el tipo del IVA para devolver finalmente el valor en función de su tipo


// Calcular precioFinal
fun calcularPrecioFinal(precioUnitario: String, cantidadObjeto: String): String {
    val precioFinalSinIVA: Double = precioUnitario.toDouble() * cantidadObjeto.toDouble()
    return precioFinalSinIVA.toString()
}

// SI el IVA es tipo sin IVA
// Recorrer la matriz y buscar en la primera posición vacía
// Introducir en la posición [0,4] de nuestra matriz, el precio unitario*cantidad, siendo un entero
fun introducirPrecioFinalSinIVAEnFilaDelObjeto(matrizInicial: Array<Array<String>>, precioFinal: String) {
    // Siempre será la columna cuatro, para ver si tenemos algún objeto
    val columnas: Int = 4
    var contadorLimite: Int = 0
    for (fila in 1 until 10) {
        if (matrizInicial[fila][columnas] == "null" && contadorLimite == 0) {
            // Introducir en la posición [0,0] de nuestra matriz, el nombre, siendo una cadena
            matrizInicial[fila][columnas] = "$precioFinal€"
            // Contador para introducir el producto una vez únicamente
            contadorLimite += 1
        }
    }
}

fun calcularReducidoIVA(precioFinal: String): String {
    val precioFinalReducidoIVA: Double = (precioFinal.toDouble() * 0.1) + precioFinal.toDouble()
    return precioFinalReducidoIVA.toString()
}

fun calcularEstandarIVA(precioFinal: String): String {
    val precioFinalEstandarIVA: Double = (precioFinal.toDouble() * 0.21) + precioFinal.toDouble()
    return precioFinalEstandarIVA.toString()
}

// SI el IVA es tipo sin IVA
// Recorrer la matriz y buscar en la primera posición vacía
fun introducirPrecioFinalConIVAEnFilaDelObjeto(
    matrizInicial: Array<Array<String>>,
    precioFinal: String,
    tipoIVA: String,
    contadorFila: Int
) {
    // Con IVA reducido del 10%
    // Introducir en la posición [0,5] de nuestra matriz
    val precioReducidoIVA: String = calcularReducidoIVA(precioFinal)
    if (tipoIVA == "1") {
        val columnas: Int = 5
        var contadorLimiteReducidoIVA: Int = 0
        // Iniciamos para poder introducirnos en el recorrido
        contadorLimiteReducidoIVA = 0
        for (fila in contadorFila until 10) {
            if (matrizInicial[fila][columnas] == "null" && contadorLimiteReducidoIVA == 0) {
                // Introducir en la posición [0,0] de nuestra matriz, el nombre, siendo una cadena
                matrizInicial[fila][columnas] = "$precioReducidoIVA€"
                // Contador para introducir el producto una vez únicamente
                contadorLimiteReducidoIVA += 1
            }
        }
    }
    // Con IVA estándar del 21%
    // Introducir en la posición [0,6] de nuestra matriz
    val precioFinalEstandarIVA: String = calcularEstandarIVA(precioFinal)
    if (tipoIVA == "2") {
        val columnas: Int = 6
        var contadorLimiteEstandarIVA: Int = 0

        // Iniciamos para poder introducirnos en el recorrido
        contadorLimiteEstandarIVA = 0
        for (fila in contadorFila until 10) {
            if (matrizInicial[fila][columnas] == "null" && contadorLimiteEstandarIVA == 0) {
                // Introducir en la posición [0,0] de nuestra matriz, el nombre, siendo una cadena
                matrizInicial[fila][columnas] = "$precioFinalEstandarIVA€"
                // Contador para introducir el producto una vez únicamente
                contadorLimiteEstandarIVA += 1
            }
        }
    }
}

// Imprimir matriz para ver como van los valores asignados
fun imprimirCarroCompraMatriz(matrizInicial: Array<Array<String>>) {

    for (columnas in matrizInicial[0].indices) {
        when (columnas) {
            0 -> {
                matrizInicial[0][columnas] = "Nombre Producto | "
                print(matrizInicial[0][columnas])
            }

            1 -> {
                matrizInicial[0][columnas] = "Precio Unitario | "
                print(matrizInicial[0][columnas])
            }

            2 -> {
                matrizInicial[0][columnas] = "Cantidad | "
                print(matrizInicial[0][columnas])
            }

            3 -> {
                matrizInicial[0][columnas] = "Tipo IVA (0 = Sin, 1 = 10%, 2 = 21%) | "
                print(matrizInicial[0][columnas])
            }

            4 -> {
                matrizInicial[0][columnas] = "Precio Final (Sin IVA) | "
                print(matrizInicial[0][columnas])
            }

            5 -> {
                matrizInicial[0][columnas] = "Precio Final (IVA Reducido) | "
                print(matrizInicial[0][columnas])
            }

            6 -> {
                matrizInicial[0][columnas] = "Precio Final (IVA Estándar)"
                print(matrizInicial[0][columnas])
            }
        }
    }
    println()

    for (filas in 1 until 10) {

        for (columnas in matrizInicial[filas].indices) {
            //if (matrizInicial[filas][columnas] != "null") {
            // Primera línea de atributos de nuestra tabla
            if (columnas == matrizInicial[filas].size - 1) {
                println("${matrizInicial[filas][columnas]} ")
            } else {
                print("${matrizInicial[filas][columnas]} ")
            }
            //}
        }
    }
    println()
}




