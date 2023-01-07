package search_sort_comparative.algoritmos

import practica.practica_busqueda.estudioOrdenacionBurbuja

fun main(){
    val tamannosVector: IntArray = intArrayOf(1_000,5_000,10_000,15_000,20_000)//25_000,30_000,35_000,40_000,45_000,50_000,55_000,60_000,65_000,70_000,75_000,80_000,85_000,90_000,100_000)
    val vectorUno: IntArray = IntArray(retornarTamannos(tamannosVector)) {(0..990_000_000).random()}
    val vectorDos: IntArray = IntArray(retornarTamannos(tamannosVector)) {(0..990_000_000).random()}
    mediaTiempo(tamannosVector,vectorUno,vectorDos)
}

fun retornarTamannos(tamannosVector: IntArray):Int {
    for (element in tamannosVector) {
       return element.toInt()
    }
    return -1
}

fun mediaTiempo(tamannoVector: IntArray, vectorUno: IntArray, vectorDos: IntArray){

    for ( i in 0..tamannoVector.size){
        val tiempoVectorUno = estudioOrdenacionBurbuja(vectorUno)
        val tiempoVectorDos = estudioOrdenacionBurbuja(vectorDos)
        val media = (tiempoVectorUno+tiempoVectorDos)/2
        print(media)
    }
}
