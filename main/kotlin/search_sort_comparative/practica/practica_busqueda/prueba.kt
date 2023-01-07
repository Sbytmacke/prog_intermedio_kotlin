package practica.practica_busqueda

import search_sort_comparative.algoritmos.bubbleSort

fun main(){
        val vectorUno: IntArray = intArrayOf(0,1,9,3,4,5,6,7,8,2)
        val vectorDos: IntArray = intArrayOf(0,1,9,3,4,5,6,7,8,2)

        println("Vector 1: ${estudioOrdenacionBurbuja(vectorUno)} ns")
        println("Vector 2: ${estudioOrdenacionBurbuja(vectorDos)} ns")
}

fun estudioOrdenacionBurbuja(vector: IntArray): Long {

    val tiempoNanosegundosInicialVectorUno: Long = System.nanoTime()
    bubbleSort(vector)
    return System.nanoTime() - tiempoNanosegundosInicialVectorUno
}
