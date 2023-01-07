package practica.practica_ordenacion

import search_sort_comparative.algoritmos.quickSort
fun main(){
    val tamannoVector: Int =  10_000
    val vectorUno: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}
    val vectorDos: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}
    val vectorTres: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}
    estudioOrdenacionQuick(vectorUno, vectorDos, vectorTres)
}

fun estudioOrdenacionQuick(vector1: IntArray,vector2: IntArray ,vector3:IntArray) {
    var tiempoMilisegundosIncialVectorUno = 0L
    var tiempoMilisegundosFinalVectorUno = 0L
    var tiempoMilisegundosIncialVectorDos = 0L
    var tiempoMilisegundosFinalVectorDos = 0L
    var tiempoMilisegundosInicialVectorTres = 0L
    var tiempoMilisegundosFinalVectorTres = 0L

    tiempoMilisegundosIncialVectorUno = System.currentTimeMillis()
    quickSort(vector1)
    tiempoMilisegundosFinalVectorUno = System.currentTimeMillis()

    tiempoMilisegundosIncialVectorDos = System.currentTimeMillis()
    quickSort(vector2)
    tiempoMilisegundosFinalVectorDos = System.currentTimeMillis()

    tiempoMilisegundosInicialVectorTres = System.currentTimeMillis()
    quickSort(vector3)
    tiempoMilisegundosFinalVectorTres = System.currentTimeMillis()

    val tiempoTardadoVectorUno: Int = (tiempoMilisegundosFinalVectorUno - tiempoMilisegundosIncialVectorUno).toInt()
    val tiempoTardadoVectorDos: Int = (tiempoMilisegundosFinalVectorDos - tiempoMilisegundosIncialVectorDos).toInt()
    val tiempoTardadoVectorTres: Int = (tiempoMilisegundosFinalVectorTres - tiempoMilisegundosInicialVectorTres).toInt()


    println("El tiempo de tres vectores aleatorios en ordenarlos con el método de Quick en milisegundos son:")
    println("Vector 1: $tiempoTardadoVectorUno ms")
    println("Vector 2: $tiempoTardadoVectorDos ms")
    println("Vector 3: $tiempoTardadoVectorTres ms")
    println("Siendo su media: ${(tiempoTardadoVectorUno + tiempoTardadoVectorDos + tiempoTardadoVectorTres) / 3} ms")
}
