package practica.practica_busqueda

import search_sort_comparative.algoritmos.binarySearch
import search_sort_comparative.algoritmos.clonarVector
import search_sort_comparative.algoritmos.quickSort

fun main(){
    val tamannoVector: Int =  10_000
    val vectorUno: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}
    val vectorDos: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}
    val vectorTres: IntArray = IntArray(tamannoVector) {(0..990_000_000).random()}

    //PRIMERO DEBEMOS CLONAR Y ORDENAR
    var vectorClonadoUno: IntArray = clonarVector(vectorUno)
    quickSort(vectorClonadoUno)
    var vectorClonadoDos: IntArray = clonarVector(vectorDos)
    quickSort(vectorClonadoDos)
    var vectorClonadoTres: IntArray = clonarVector(vectorTres)
    quickSort(vectorClonadoTres)

    estudioBusquedaBinaria(vectorClonadoUno, vectorClonadoDos, vectorClonadoTres)
}

fun estudioBusquedaBinaria(vector1: IntArray,vector2: IntArray ,vector3:IntArray) {
    var tiempoNanosegundosIncialVectorUno = 0L
    var tiempoNanosegundosFinalVectorUno = 0L
    var tiempoNanosegundosIncialVectorDos = 0L
    var tiempoNanosegundosFinalVectorDos = 0L
    var tiempoNanosegundosInicialVectorTres = 0L
    var tiempoNanosegundosFinalVectorTres = 0L

    tiempoNanosegundosIncialVectorUno = System.nanoTime()
    binarySearch(vector1,1)
    tiempoNanosegundosFinalVectorUno = System.nanoTime()

    tiempoNanosegundosIncialVectorDos = System.nanoTime()
    binarySearch(vector2,1)
    tiempoNanosegundosFinalVectorDos = System.nanoTime()

    tiempoNanosegundosInicialVectorTres = System.nanoTime()
    binarySearch(vector3,1)
    tiempoNanosegundosFinalVectorTres = System.nanoTime()

    val tiempoTardadoVectorUno: Int = (tiempoNanosegundosFinalVectorUno - tiempoNanosegundosIncialVectorUno).toInt()
    val tiempoTardadoVectorDos: Int = (tiempoNanosegundosFinalVectorDos - tiempoNanosegundosIncialVectorDos).toInt()
    val tiempoTardadoVectorTres: Int = (tiempoNanosegundosFinalVectorTres - tiempoNanosegundosInicialVectorTres).toInt()

    println("El tiempo de tres vectores aleatorios mediante una b??squeda binaria el valor 1 en nanosegundos:")
    println("Vector 1: $tiempoTardadoVectorUno ns")
    println("Vector 2: $tiempoTardadoVectorDos ns")
    println("Vector 3: $tiempoTardadoVectorTres ns")
    println("Siendo su media: ${(tiempoTardadoVectorUno + tiempoTardadoVectorDos + tiempoTardadoVectorTres) / 3} ns")
}