package search_sort_comparative.algoritmos

/**
 * Método de ordenación de burbuja O(n²)
 */
fun bubbleSort(vector: IntArray) {
    for (i in 0 until vector.size - 1) {
        for (j in 0 until vector.size - 1 - i) {
            if (vector[j] > vector[j + 1]) {
                val aux = vector[j]
                vector[j] = vector[j + 1]
                vector[j + 1] = aux
            }
        }
    }
}

/**
 * Método de ordenación por selección O(n²)
 */
fun selectionSort(array: IntArray) {
    for (i in 0 until array.size - 1) {
        var min = i
        for (j in i + 1 until array.size) {
            if (array[j] < array[min]) {
                min = j
            }
        }
        val aux = array[i]
        array[i] = array[min]
        array[min] = aux
    }
}

/**
 * Método de ordenación por inserción O(n²)
 */
fun insertionSort(array: IntArray) {
    for (i in 1 until array.size) {
        var j = i
        while (j > 0 && array[j] < array[j - 1]) {
            val aux = array[j]
            array[j] = array[j - 1]
            array[j - 1] = aux
            j--
        }
    }
}

fun shellSort(array: IntArray) {
    var h = 1
    while (h < array.size / 3) {
        h = 3 * h + 1
    }
    while (h >= 1) {
        for (i in h until array.size) {
            var j = i
            while (j >= h && array[j] < array[j - h]) {
                val aux = array[j]
                array[j] = array[j - h]
                array[j - h] = aux
                j -= h
            }
        }
        h /= 3
    }
}

fun pivot(array: IntArray, left: Int, right: Int): Int {
    var i = left
    var j = right
    val pivot = array[left]
    while (i < j) {
        while (array[i] <= pivot && i < j) {
            i++
        }
        while (array[j] > pivot) {
            j--
        }
        if (i < j) {
            val aux = array[i]
            array[i] = array[j]
            array[j] = aux
        }
    }
    array[left] = array[j]
    array[j] = pivot
    return j
}

fun quickSortGeneral(array: IntArray, left: Int, right: Int) {
    val piv: Int
    if (left < right) {
        piv = pivot(array, left, right)
        quickSortGeneral(array, left, piv - 1)
        quickSortGeneral(array, piv + 1, right)
    }
}

/**
 * Método de ordenación QuickSort
 */
fun quickSort(array: IntArray) {
    quickSortGeneral(array, 0, array.size - 1)
}
