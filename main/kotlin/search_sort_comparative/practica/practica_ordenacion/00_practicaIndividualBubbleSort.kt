package practica.practica_ordenacion

import search_sort_comparative.algoritmos.bubbleSort

fun main(){
    val tamannoVector: Int =  10_000
    val vectorUno: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}
    val vectorDos: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}
    val vectorTres: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}

    estudioOrdenacionBurbuja(vectorUno, vectorDos, vectorTres)
}

fun estudioOrdenacionBurbuja(vectorUno: IntArray, vectorDos: IntArray, vectorTres: IntArray) {
    var tiempoMilisegundosIncialVectorUno = 0L
    var tiempoMilisegundosFinalVectorUno = 0L
    var tiempoMilisegundosIncialVectorDos = 0L
    var tiempoMilisegundosFinalVectorDos = 0L
    var tiempoMilisegundosInicialVectorTres = 0L
    var tiempoMilisegundosFinalVectorTres = 0L

    tiempoMilisegundosIncialVectorUno = System.currentTimeMillis()
    bubbleSort(vectorUno)
    tiempoMilisegundosFinalVectorUno = System.currentTimeMillis()

    tiempoMilisegundosIncialVectorDos = System.currentTimeMillis()
    bubbleSort(vectorDos)
    tiempoMilisegundosFinalVectorDos = System.currentTimeMillis()

    tiempoMilisegundosInicialVectorTres = System.currentTimeMillis()
    bubbleSort(vectorTres)
    tiempoMilisegundosFinalVectorTres = System.currentTimeMillis()

    val tiempoTardadoVectorUno: Int = (tiempoMilisegundosFinalVectorUno - tiempoMilisegundosIncialVectorUno).toInt()
    val tiempoTardadoVectorDos: Int = (tiempoMilisegundosFinalVectorDos - tiempoMilisegundosIncialVectorDos).toInt()
    val tiempoTardadoVectorTres: Int = (tiempoMilisegundosFinalVectorTres - tiempoMilisegundosInicialVectorTres).toInt()

    println("El tiempo de tres vectores aleatorios en ordenarlos con el método de seleccion en milisegundos son:")
    println("Vector 1: $tiempoTardadoVectorUno ms")
    println("Vector 2: $tiempoTardadoVectorDos ms")
    println("Vector 3: $tiempoTardadoVectorTres ms")
    println("Siendo su media: ${(tiempoTardadoVectorUno + tiempoTardadoVectorDos + tiempoTardadoVectorTres) / 3} ms")
}
