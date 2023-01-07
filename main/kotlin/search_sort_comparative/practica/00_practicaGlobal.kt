package practica

import search_sort_comparative.algoritmos.clonarVector
import search_sort_comparative.algoritmos.quickSort
import practica.practica_busqueda.*
import practica.practica_ordenacion.*

fun main(){
    //Únicamente cambia el valor tamannoVector para el estudio general
    val tamannoVector: Int = 100_000

    estudioGlobal(tamannoVector)

}
/**
 * CUIDADO con los valores que asignas al array = tamannoVector, ya que puede saturar
 * la capacidad de los procesos debido a la poca eficacia con vectores enormes
 * como en el caso del ordenamiento de burbuja...
 *
 * En el caso de las funciones estudioBusquedaLineal y estudioBusquedaBinaria,
 * tienen por defecto que hagan la búsqueda al valor = 1
 *
 * Este fichero es para realizar un caso global y echar un vistazo muy generico a todos los
 * metodos, ACONSEJO comentar aquellos métodos que no quieres estudiar, si quieres realizar
 * un estudio más individual, accede a las carpetas practica_busqueda y practica_ordenacion
 */



fun estudioGlobal(tamannoVector:Int){
    val vectorUno: IntArray = IntArray(tamannoVector) {(0..999_000_000).random()}
    val vectorDos: IntArray = IntArray(tamannoVector) {(0..999_000_000).random()}
    val vectorTres: IntArray = IntArray(tamannoVector) {(0..999_000_000).random()}

    var vectorClonadoUnoOrdenacionBurbuja: IntArray = clonarVector(vectorUno)
    var vectorClonadoDosOrdenacionBurbuja: IntArray = clonarVector(vectorDos)
    var vectorClonadoTresOrdenacionBurbuja: IntArray = clonarVector(vectorTres)

    var vectorClonadoUnoOrdenacionSelection: IntArray = clonarVector(vectorUno)
    var vectorClonadoDosOrdenacionSelection: IntArray = clonarVector(vectorDos)
    var vectorClonadoTresOrdenacionSelection: IntArray = clonarVector(vectorTres)

    var vectorClonadoUnoOrdenacionInsertion: IntArray = clonarVector(vectorUno)
    var vectorClonadoDosOrdenacionInsertion: IntArray = clonarVector(vectorDos)
    var vectorClonadoTresOrdenacionInsertion: IntArray = clonarVector(vectorTres)

    var vectorClonadoUnoOrdenacionShell: IntArray = clonarVector(vectorUno)
    var vectorClonadoDosOrdenacionShell: IntArray = clonarVector(vectorDos)
    var vectorClonadoTresOrdenacionShell: IntArray = clonarVector(vectorTres)

    var vectorClonadoUnoOrdenacionQuick: IntArray = clonarVector(vectorUno)
    var vectorClonadoDosOrdenacionQuick: IntArray = clonarVector(vectorDos)
    var vectorClonadoTresOrdenacionQuick: IntArray = clonarVector(vectorTres)

    var vectorClonadoUnoBusquedaLineal: IntArray = clonarVector(vectorUno)
    quickSort(vectorClonadoUnoBusquedaLineal)
    var vectorClonadoDosBusquedaLineal: IntArray = clonarVector(vectorDos)
    quickSort(vectorClonadoDosBusquedaLineal)
    var vectorClonadoTresBusquedaLineal: IntArray = clonarVector(vectorTres)
    quickSort(vectorClonadoTresBusquedaLineal)

    var vectorClonadoUnoBusquedaBinaria: IntArray = clonarVector(vectorUno)
    quickSort(vectorClonadoUnoBusquedaBinaria)
    var vectorClonadoDosBusquedaBinaria: IntArray = clonarVector(vectorDos)
    quickSort(vectorClonadoDosBusquedaBinaria)
    var vectorClonadoTresBusquedaBinaria: IntArray = clonarVector(vectorTres)
    quickSort(vectorClonadoTresBusquedaBinaria)

    estudioOrdenacionBurbuja(vectorClonadoUnoOrdenacionBurbuja, vectorClonadoDosOrdenacionBurbuja, vectorClonadoTresOrdenacionBurbuja)
    estudioOrdenacionSeleccion(vectorClonadoUnoOrdenacionSelection, vectorClonadoDosOrdenacionSelection, vectorClonadoTresOrdenacionSelection)
    estudioOrdenacionInsercion(vectorClonadoUnoOrdenacionInsertion, vectorClonadoDosOrdenacionInsertion, vectorClonadoTresOrdenacionInsertion)
    estudioOrdenacionShell(vectorClonadoUnoOrdenacionShell, vectorClonadoDosOrdenacionShell, vectorClonadoTresOrdenacionShell)
    estudioOrdenacionQuick(vectorClonadoUnoOrdenacionQuick, vectorClonadoDosOrdenacionQuick, vectorClonadoTresOrdenacionQuick)
    estudioBusquedaLineal(vectorClonadoUnoBusquedaLineal, vectorClonadoDosBusquedaLineal, vectorClonadoTresBusquedaLineal)
    estudioBusquedaBinaria(vectorClonadoUnoBusquedaBinaria, vectorClonadoDosBusquedaBinaria,vectorClonadoTresBusquedaBinaria,)
}
