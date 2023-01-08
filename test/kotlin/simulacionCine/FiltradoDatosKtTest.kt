package simulacionCine

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import simulacionCine.models.Pelicula
import simulacionCine.models.Sala
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FiltradoDatosKtTest {

    @Test
    fun filtroMaxLetrasTest() {
        // Preparar los datos de prueba
        val cadenasValidasUno: String = "abc"
        val cadenasValidasDos: String = "abcde"
        val cadenasValidasTres: String = "abcdefghijklmnopqrst"
        val cadenasInvalidasUno: String = "1"
        val cadenasInvalidasDos: String = "abcd5"
        val cadenasInvalidasTres: String = "---"

        // Ejecutar la función a probar
        // Assert (verificar el resultado)
        assertAll(
            { assertTrue(filtroMaxLetras(cadenasValidasUno)) },
            { assertTrue(filtroMaxLetras(cadenasValidasDos)) },
            { assertTrue(filtroMaxLetras(cadenasValidasTres)) },
            { assertFalse(filtroMaxLetras(cadenasInvalidasUno)) },
            { assertFalse(filtroMaxLetras(cadenasInvalidasDos)) },
            { assertFalse(filtroMaxLetras(cadenasInvalidasTres)) }
        )
    }

    @Test
    fun filtroTarjetaCreditoTest() {
        // Preparar los datos de prueba
        val cadenasValidasUno: String = "1234567890123456"
        val cadenasInvalidasUno: String = "123456789012345"
        val cadenasInvalidasDos: String = "abcdefghijklmnño"
        val cadenasInvalidasTres: String = "---"

        // Ejecutar la función a probar
        // Assert (verificar el resultado)
        assertAll(
            { assertTrue(filtroTarjetaCredito(cadenasValidasUno)) },
            { assertFalse(filtroTarjetaCredito(cadenasInvalidasUno)) },
            { assertFalse(filtroTarjetaCredito(cadenasInvalidasDos)) },
            { assertFalse(filtroTarjetaCredito(cadenasInvalidasTres)) }
        )
    }

    @Test
    fun filtroEmailTest() {
        // Preparar los datos de prueba
        val cadenasValidasUno: String = "xxx@xx.xx"
        val cadenasInvalidasUno: String = "xxx@xx"
        val cadenasInvalidasDos: String = "xxxxx"
        val cadenasInvalidasTres: String = "xxxxx.xx"

        // Ejecutar la función a probar
        // Assert (verificar el resultado)
        assertAll(
            { assertTrue(filtroEmail(cadenasValidasUno)) },
            { assertFalse(filtroEmail(cadenasInvalidasUno)) },
            { assertFalse(filtroEmail(cadenasInvalidasDos)) },
            { assertFalse(filtroEmail(cadenasInvalidasTres)) }
        )
    }

    @Test
    fun filtroTelefonoTest() {
        // Preparar los datos de prueba
        val cadenasValidasUno: String = "665808210"
        val cadenasInvalidasUno: String = "66580821"
        val cadenasInvalidasDos: String = "66580821a"
        val cadenasInvalidasTres: String = "m6658082a"
        val cadenasInvalidasCuatro: String = "abcd"

        // Ejecutar la función a probar
        // Assert (verificar el resultado)
        assertAll(
            { assertTrue(filtroTelefono(cadenasValidasUno)) },
            { assertFalse(filtroTelefono(cadenasInvalidasUno)) },
            { assertFalse(filtroTelefono(cadenasInvalidasDos)) },
            { assertFalse(filtroTelefono(cadenasInvalidasTres)) },
            { assertFalse(filtroTelefono(cadenasInvalidasCuatro)) }
        )
    }

    @Test
    fun filtroDNITest() {
        // Preparar los datos de prueba
        val cadenasValidasUno: String = "47310216K"
        val cadenasValidasDos: String = "47310216k"
        val cadenasInvalidasUno: String = "47310216"
        val cadenasInvalidasDos: String = "7310216K"
        val cadenasInvalidasTres: String = "abcd"

        // Ejecutar la función a probar
        // Assert (verificar el resultado)
        assertAll(
            { assertTrue(filtroDNI(cadenasValidasUno)) },
            { assertTrue(filtroDNI(cadenasValidasDos)) },
            { assertFalse(filtroDNI(cadenasInvalidasUno)) },
            { assertFalse(filtroDNI(cadenasInvalidasDos)) },
            { assertFalse(filtroDNI(cadenasInvalidasTres)) }
        )
    }

    @Test
    fun filtroCantidadEntradasTest() {
        // Preparar los datos de prueba
        val tamannoMaxButacas: Int = BUTACAS_FILA_MAX* BUTACAS_COLUMNA_MAX
        val cadenasValidasUno: String = "1"
        val cadenasValidasDos: String = tamannoMaxButacas.toString()
        val cadenasInvalidasUno: String = (tamannoMaxButacas+1).toString()
        val cadenasInvalidasDos: String = "0"
        val cadenasInvalidasTres: String = "abcd"

        // Ejecutar la función a probar
        // Assert (verificar el resultado)
        assertAll(
            { assertTrue(filtroCantidadEntradas(cadenasValidasUno,tamannoMaxButacas)) },
            { assertTrue(filtroCantidadEntradas(cadenasValidasDos,tamannoMaxButacas)) },
            { assertFalse(filtroCantidadEntradas(cadenasInvalidasUno,tamannoMaxButacas)) },
            { assertFalse(filtroCantidadEntradas(cadenasInvalidasDos,tamannoMaxButacas)) },
            { assertFalse(filtroCantidadEntradas(cadenasInvalidasTres,tamannoMaxButacas)) }
        )
    }

    @Test
    fun filtradoButacasTest() {
        // Preparar los datos de prueba
        // Inicialización de las películas que dispongamos
        val catalogoPeliculas: Array<Pelicula> = arrayOf(
            Pelicula("Avatar 2", "2022", "James Cameron", "Ciencia Ficción"),
            Pelicula("El viaje de Chihiro", "2001", "Hayao Miyazaki", "Anime-Fantasia")
        )
        // Inicialización de las salas que dispongamos, con sus correspondientes butacas en estado libre por defecto
        val cine: Array<Sala> = arrayOf(
            Sala("001", "Sala 1", catalogoPeliculas[0], BUTACAS_FILA_MAX, BUTACAS_COLUMNA_MAX),
            Sala("002", "Sala 2", catalogoPeliculas[1], BUTACAS_FILA_MAX, BUTACAS_COLUMNA_MAX)
        )
        val idSala: String = "001"

        val cadenasValidasUno: String = "A1"
        val cadenasValidasDos: String = "G7"
        val cadenasInvalidasUno: String = "A0"
        val cadenasInvalidasDos: String = "G8"
        val cadenasInvalidasTres: String = "abcd"
        val cadenasInvalidasCuatro: String = "A"
        val cadenasInvalidasCinco: String = "1"

        // Ejecutar la función a probar
        // Assert (verificar el resultado)
        assertAll(
            { assertTrue(filtradoButacas(cine,idSala,cadenasValidasUno)) },
            { assertTrue(filtradoButacas(cine,idSala,cadenasValidasDos)) },
            { assertFalse(filtradoButacas(cine,idSala,cadenasInvalidasUno)) },
            { assertFalse(filtradoButacas(cine,idSala,cadenasInvalidasDos)) },
            { assertFalse(filtradoButacas(cine,idSala,cadenasInvalidasTres)) },
            { assertFalse(filtradoButacas(cine,idSala,cadenasInvalidasCuatro)) },
            { assertFalse(filtradoButacas(cine,idSala,cadenasInvalidasCinco)) }
        )
    }
}