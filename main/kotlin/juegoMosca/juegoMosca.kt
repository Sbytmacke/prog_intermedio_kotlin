package juegoMosca

const val TAMANNO_MAPA: Int = 2
const val INTENTOS: Int = 60

// TODO: Cuando sigue el bucle, parece que calcula mal la posicion de la mosca...
fun main() {
    var mapa: Mapa = Mapa(TAMANNO_MAPA)
    var vida: Int = INTENTOS
    var posicionActualMosca: Int = mapa.getPosicionMosca()
    mapa.imprimirMapa()

    do {
        println("")
        val entradaPlayer: Int = 1// readln().toInt() - 1

        if (posicionActualMosca == entradaPlayer) {
            println("Mosca capturada!")
            println("***HAS GANADO***")
            break
        }

        // Si damos cerca de la mosca, se moverá a un sitio distinto
        if (isMoscaAdyacente(posicionActualMosca, entradaPlayer)) {
            println("Casi das a la mosca!")
            println("Te quedan $vida vidas")
            println("")

            // Movemos a la mosca
            mapa = Mapa(TAMANNO_MAPA)
            posicionActualMosca = mapa.getPosicionMosca()
            mapa.imprimirMapa()
        } else {
            println("Has fallado!")
            println("Te quedan $vida vidas")
            println("")
            mapa.imprimirMapa()
        }

        vida -= 1
        if (vida == 0) {
            println("---HAS PERDIDO---")
        }

    } while (vida > 0)
}

fun isMoscaAdyacente(posicionActualMosca: Int, entradaPlayer: Int): Boolean {

    // Filtro no salirnos por la derecha
    if (entradaPlayer + 1 <= TAMANNO_MAPA - 1) {
        if (entradaPlayer + 1 == posicionActualMosca) {
            return true
        }
    }

    // Filtro no salirnos por la izquierda
    if (entradaPlayer - 1 >= 0) {
        if (entradaPlayer - 1 == posicionActualMosca) {
            return true
        }
    }

    return false
}




