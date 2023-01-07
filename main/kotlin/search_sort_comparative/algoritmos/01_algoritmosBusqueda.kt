package search_sort_comparative.algoritmos

/**
* Método de búsqueda lineal o secuencial
*/
fun linearSearch(array: IntArray, value: Int): Int {
    for (i in array.indices) {
        if (array[i] == value) {
            return i
        }
    }
    return -1
}

/**
 * Método de búsqueda binaria recursiva
 */

fun binarySearchGeneral(array: IntArray, value: Int, left: Int, right: Int): Int {
    if (left > right) {
        return -1
    }
    val middle = (left + right) / 2
    return when {
        array[middle] == value -> middle
        array[middle] > value -> binarySearchGeneral(array, value, left, middle - 1)
        else -> binarySearchGeneral(array, value, middle + 1, right)
    }
}

fun binarySearch(array: IntArray, value: Int): Int {
    return binarySearchGeneral(array, value, 0, array.size - 1)
}