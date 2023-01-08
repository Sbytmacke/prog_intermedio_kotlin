package juegoMosca

class Mapa(private val tamannoMapa: Int) {
    private val vectorMapa: Array<Boolean> = Array(tamannoMapa) { false }

    init {
        // Insertar la mosca en una posición aleatoria
        insertarMoscaAleatoriamente(vectorMapa)
    }

    fun getPosicionMosca(): Int {
        for (i in vectorMapa.indices) {
            if (vectorMapa[i]) {
                return i
            }
        }
        // En caso de dar error...
        return -100
    }

    // TODO: esta función no realiza lo que realmente quiero: que nunca se repita la posición anterior de la mosca
    private fun insertarMoscaAleatoriamente(vectorMapa: Array<Boolean>) {

        // Obligamos que se mueve la mosca a una posición nueva
        val indiceMaxMapa: Int = vectorMapa.size - 1
        while (true) {
            var anterioriPosicionMosca: Int = -1
            for (i in vectorMapa.indices) {
                if (vectorMapa[i]) {
                    anterioriPosicionMosca = i
                }
            }

            val posicionAleatoriaMapa: Int = (0..indiceMaxMapa).random()
            if (posicionAleatoriaMapa != anterioriPosicionMosca) {
                vectorMapa[posicionAleatoriaMapa] = true
                break
            }
        }
    }

    fun imprimirMapa() {
        for (element in vectorMapa) {
            print(" $element")
        }
    }
}