package simulacionCine.models

import simulacionCine.enum.EstadoButaca

// Clase que representa una sala de cine.
class Sala(var id: String, private var nombre: String, var pelicula: Pelicula, private var tamannoFila: Int, private var tamannoColumna: Int) {
    // La mantengo fuera del init para que todas las funciones tengan acceso a la matriz
    private val matrizButacas: Array<Array<Butaca>>

    /**
     * Inicializa la matriz de butacas de la sala con butacas en estado Libre,
     * y su correspondiente posición.
     * También asigna aleatoriamente butacas VIP en la matriz.
     */
    init {
        val butacaMain: Butaca = Butaca(EstadoButaca.LIBRE, "A", "0",false)
        matrizButacas = Array(tamannoFila) { Array(tamannoColumna) { butacaMain } }
        generarButacasVIP()
    }

    /**
     * Devuelve la matriz de butacas de la sala.
     * @return La matriz de butacas de la sala.
     */
    fun getMatrizSala(): Array<Array<Butaca>>{
        return matrizButacas
    }

    /**
     * Asigna aleatoriamente butacas VIP en la matriz de butacas de la sala.
     */
    private fun generarButacasVIP(){
        // 2 de cada 5 butacas son VIP
        val cantidadTotalButacas: Int = tamannoFila * tamannoColumna
        val butacasVip: Int = ((cantidadTotalButacas / 5)*2)
        val probabilidadButacaVip: Int = 40

        // Si no hay el total de butacasVIP, en nuestra SALA, el bucle infinito asignará hasta que haya el número de VIP deseado
        while(!verificarCalculoButacasVip(butacasVip)){
            // Asignamos por defecto todas libres con su posición correspondiente
            asignarPosicionesButacasPorDefecto()

            for (filas in matrizButacas.indices) {
                for (columnas in 0 until matrizButacas[filas].size) {
                    val sorteoVip: Int = (1..100).random()
                    if ( sorteoVip <= probabilidadButacaVip){
                        matrizButacas[filas][columnas].setBooleanButacaVip(true)
                    }
                }
            }
        }
    }

    /**
     * Comprueba si se han asignado el número correcto de butacas VIP en la matriz de butacas de la sala.
     * @param butacasVipLimite El número de butacas VIP que debe haber en la sala.
     * @return 'true' si se han asignado el número correcto de butacas VIP, 'false' en caso contrario.
     */
    private fun verificarCalculoButacasVip(butacasVipLimite: Int): Boolean {
        var contadorButacasVip: Int = 0

        for (filas in matrizButacas.indices) {
            for (columnas in 0 until matrizButacas[filas].size) {
                if (matrizButacas[filas][columnas].getBooleanButacaVip()){
                    contadorButacasVip += 1
                }
            }
        }
        if (contadorButacasVip == butacasVipLimite){
            return true
        }
        return false
    }

    /**
     * Devuelve el estado de la butaca especificada en la entrada.
     * @param posicionButaca La posición de la butaca, en formato "FilaColumna",
     * donde Fila es una letra mayúscula del abecedario,
     * y Columna es un número entero.
     * @return El estado de la butaca especificada.
     */
    fun getEstadoButacaEspecifica(posicionButaca: String): EstadoButaca {

        val filaButaca: Char = posicionButaca[0]
        val columnaButaca: Int = (posicionButaca.substring(1, 2).toInt())

        val abecedario: String = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        val filaButacaEnNumero: IntArray = IntArray(1)

        var contadorFila: Int = 0
        for (i in 0 until abecedario.length) {
            if (abecedario[i] == filaButaca) {
                filaButacaEnNumero[0] = contadorFila
            }
            contadorFila += 1
        }

        return matrizButacas[filaButacaEnNumero[0]][columnaButaca - 1].getEstadoButaca()
    }

    /**
     * Devuelve si la butaca especificada en la entrada es VIP o no.
     * @param entradaButacaPosicion La posición de la butaca, en formato "FilaColumna",
     * donde Fila es una letra mayúscula del abecedario,
     * y Columna es un número entero.
     * @return 'true' si la butaca es VIP, 'false' en caso contrario.
     */
    fun getBooleanButacaVipDesdeSala(entradaButacaPosicion: String): Boolean{
        val columnaButaca: Int = (entradaButacaPosicion.substring(1, 2).toInt())
        val filaButaca: Char = entradaButacaPosicion[0]

        val abecedario: String = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        val filaButacaEnNumero: IntArray = IntArray(1)

        var contadorFila: Int = 0
        for (element in abecedario) {
            if (element == filaButaca) {
                filaButacaEnNumero[0] = contadorFila
            }
            contadorFila += 1
        }
        return matrizButacas[filaButacaEnNumero[0]][columnaButaca - 1].getBooleanButacaVip()
    }

    /**
     * Devuelve la cantidad máxima de filas de butacas en la sala.
     * @return El número máximo de filas de butacas en la sala.
     */
    fun cantidaMaxFilas(): Int {
        return matrizButacas.size
    }

    /**
     * Devuelve la cantidad máxima de columnas de butacas en la sala.
     * @return El número máximo de columnas de butacas en la sala.
     */
    fun cantidaMaxColumnas(): Int {
        return matrizButacas[0].size
    }

    /**
     * Devuelve la cantidad total de butacas en la sala.
     * @return El número total de butacas en la sala.
     */
    fun cantidadButacasTotal(): Int {
        var contadorButacas: Int = 0
        for (element in matrizButacas) {
            for (columnas in 0 until element.size) {
                contadorButacas += 1
            }
        }
        return contadorButacas
    }

    /**
     * Libera la butaca especificada en la entrada.
     * @param entradaButacaPosicion La posición de la butaca a liberar,
     * en formato "FilaColumna", donde Fila es una letra mayúscula del abecedario,
     * y Columna es un número entero.
     */
    fun liberarButaca(entradaButacaPosicion: String) {
        // Conversión muy fea...

        val columnaButaca: Int = (entradaButacaPosicion.substring(1, 2).toInt())
        val filaButaca: Char = entradaButacaPosicion[0]

        val abecedario: String = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        val filaButacaEnNumero: IntArray = IntArray(1)

        var contadorFila: Int = 0
        for (element in abecedario) {
            if (element == filaButaca) {
                filaButacaEnNumero[0] = contadorFila
            }
            contadorFila += 1
        }

        val butacaActualizada: Butaca =
            Butaca(EstadoButaca.LIBRE, "$filaButaca", "$columnaButaca", matrizButacas[filaButacaEnNumero[0]][columnaButaca - 1].getBooleanButacaVip())
        // En la posición donde se encontraba la butaca introducimos la nueva libre
        matrizButacas[filaButacaEnNumero[0]][columnaButaca - 1] = butacaActualizada
    }

    /**
     * Reserva la butaca especificada en la entrada.
     * @param entradaButacaPosicion La posición de la butaca a reservar,
     * en formato "FilaColumna", donde Fila es una letra mayúscula del abecedario,
     * y Columna es un número entero.
     */
    fun reservarButaca(entradaButacaPosicion: String) {
        // Conversión muy fea...

        val filaButacaReservada: Char = entradaButacaPosicion[0]
        val columnaButacaReservada: Int = (entradaButacaPosicion.substring(1, 2).toInt())

        val abecedario: String = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        val filaButacaEnNumero: IntArray = IntArray(1)

        var contadorFila: Int = 0
        for (i in 0 until abecedario.length) {
            if (abecedario[i] == filaButacaReservada) {
                filaButacaEnNumero[0] = contadorFila
            }
            contadorFila += 1
        }

        val butacaActualizada: Butaca =
            Butaca(EstadoButaca.RESERVADO, "$filaButacaReservada", "$columnaButacaReservada",matrizButacas[filaButacaEnNumero[0]][columnaButacaReservada - 1].getBooleanButacaVip())
        // En la posición donde se encontraba la butaca introducimos la nueva reservada
        matrizButacas[filaButacaEnNumero[0]][columnaButacaReservada - 1] = butacaActualizada
    }

    /**
     * Ocupa la butaca especificada en la entrada.
     * @param entradaButacaPosicion La posición de la butaca a ocupar,
     * en formato "FilaColumna", donde Fila es una letra mayúscula del abecedario,
     * y Columna es un número entero.
     */
    fun ocuparButaca(entradaButacaPosicion: String) {
        // Conversión muy fea...

        val columnaButacaOcupada: Int = (entradaButacaPosicion.substring(1, 2).toInt())
        val filaButacaOcupada: Char = entradaButacaPosicion[0]

        val abecedario: String = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        val filaButacaEnNumero: IntArray = IntArray(1)

        var contadorFila: Int = 0
        for (i in 0 until abecedario.length) {
            if (abecedario[i] == filaButacaOcupada) {
                filaButacaEnNumero[0] = contadorFila
            }
            contadorFila += 1
        }

        val butacaActualizada: Butaca =
            Butaca(EstadoButaca.OCUPADO, "$filaButacaOcupada", "$columnaButacaOcupada",matrizButacas[filaButacaEnNumero[0]][columnaButacaOcupada - 1].getBooleanButacaVip())
        // En la posición donde se encontraba la butaca introducimos la nueva ocupada
        matrizButacas[filaButacaEnNumero[0]][columnaButacaOcupada - 1] = butacaActualizada
    }

    /**
     * Asigna las posiciones de las butacas de la sala.
     */
    private fun asignarPosicionesButacasPorDefecto() {
        val abecedario: String = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"

        // Introducimos las butacas con su correspondiente posición
        for (filas in matrizButacas.indices) {
            for (columnas in 0 until matrizButacas[filas].size) {
                val butacaPorDefecto: Butaca = Butaca(EstadoButaca.LIBRE, "${abecedario[filas]}", "${columnas + 1}",false)
                matrizButacas[filas][columnas] = butacaPorDefecto
            }
        }
    }

    /**
     * Muestra por pantalla la matriz de butacas de la sala.
     */
    fun imprimirMatrizButacas() {
        val purple = "\u001b[35m"
        val reset = "\u001b[0m"
        for (filas in matrizButacas.indices) {
            for (columnas in 0 until matrizButacas[filas].size) {
                if (columnas == matrizButacas[filas].size - 1) {
                    if (matrizButacas[filas][columnas].getBooleanButacaVip()){
                        println("$purple${matrizButacas[filas][columnas]}$reset ")
                    }else{
                        println("${matrizButacas[filas][columnas]} ")
                    }
                } else {
                    if (matrizButacas[filas][columnas].getBooleanButacaVip()) {
                        print("$purple${matrizButacas[filas][columnas]}$reset ")
                    }else{
                        print("${matrizButacas[filas][columnas]} ")
                    }
                }
            }
        }
        println("----------------------------------")
        println("LEYENDA: L -> (libre), R -> (reservado), O -> (ocupado), ${purple}MORADO${reset} -> (VIP)")
        println("")
    }

    /**
     * Devuelve una representación en forma de cadena del objeto sala.
     * @return Representación en forma de cadena del objeto sala.
     */
    override fun toString(): String {
        return "SALA -> (id='$id', nombre='$nombre') " +
                "\n$pelicula"
    }
}