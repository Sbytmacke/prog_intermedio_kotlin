package search_sort_comparative.algoritmos

fun main(){
    val vector: IntArray = intArrayOf(0,1,2,3)
    val vectorClonado = clonarVector(vector)
   for (i in vectorClonado){
       println(i)
   }
}

fun clonarVector(vector: IntArray):IntArray {
    val vectorClonado: IntArray = IntArray(vector.size)
    for(i in vector.indices){
        vectorClonado[i] = vector[i]
    }
    return vectorClonado
}