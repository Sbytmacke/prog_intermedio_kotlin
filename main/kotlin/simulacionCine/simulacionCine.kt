package simulacionCine

import simulacionCine.enum.EstadoButaca
import simulacionCine.enum.EstadoTicket
import simulacionCine.models.*

const val PRECIO_ESTANDAR: Double = 5.25
const val PRECIO_VIP: Double = 8.5
const val BUTACAS_FILA_MAX: Int = 7
const val BUTACAS_COLUMNA_MAX: Int = 7
// BB. DD. volátil de clientes con un tamaño fijo máximo
const val CLIENTES_MAX: Int = 15

fun main() {
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

    // Inicializo contenedor de tamaño fijo de clientes/usuarios
    val almacenClientes: Array<Cliente> = Array(CLIENTES_MAX) {
        Cliente(" ", " ", " ", " ", " ", " ",
            Ticket(EstadoTicket.INACTIVO, " ", " ", " ",
                Array(BUTACAS_FILA_MAX * BUTACAS_COLUMNA_MAX) { Butaca(EstadoButaca.LIBRE, "A", "0", false) }
            )
        )
    }

    // Mostrar los menús
    menuEleccionUsuario(cine,almacenClientes)
}

/**
 * Muestra un menú que permite al usuario iniciar sesión como administrador o como usuario.
 * Si el usuario elige comenzar sesión como administrador, se le pedirá la contraseña y se le
 * mostrará el menú para administradores.
 * Si elige comenzar sesión como usuario, se le mostrará el menú para usuarios.
 * Si elige salir, se finaliza el programa.
 *
 * @param cine Array de objetos de la clase Sala.
 * @param almacenClientes Array de objetos de la clase Cliente.
 */
fun menuEleccionUsuario(cine: Array<Sala>, almacenClientes: Array<Cliente>) {
    while(true) {
        val eleccion: Int = eleccionTipoUsuario("Iniciar sesión como Administrador, Usuario, o Salir del programa (admin/user/salir):")
        if (eleccion == 1) {
            do {
                val passCorrect: String = "1234"
                if (isPassCorrect(passCorrect)) {
                    falsoBorradoDeConsola()
                    menuAdmin(cine)
                    break
                }
            } while (repetir("¿Quieres volver a intentar introducir la contraseña? (S/N):"))
        }
        if (eleccion == 0) {
            falsoBorradoDeConsola()
            menuCliente(cine, almacenClientes)
        }
        if (eleccion == -1) {
            falsoBorradoDeConsola()
            print("Muchas gracias por disfrutar de CINES ANGEL! Nos volveremos a ver...")
            break
        }
    }
}

/**
 * Muestra un mensaje y espera a que el usuario ingrese "admin", "user" o "salir".
 * Si el usuario ingresa "admin", se retorna 1. Si ingresa "user", se retorna 0.
 * Si ingresa "salir", se retorna -1.
 * En caso contrario, se muestra un mensaje de error (temporal) sin romper la ejecución y se vuelve a intentar.
 *
 * @param mensaje Mensaje a mostrar al usuario.
 * @return 1 si el usuario ingresa "admin", 0 si ingresa "user", -1 si ingresa "salir".
 */
fun eleccionTipoUsuario(mensaje: String): Int {
    while (true) {
        portadaCinesAngel()
        println(mensaje)
        val entradaInicioSesion: String = readln().lowercase()
        if (entradaInicioSesion == "admin") {
            falsoBorradoDeConsola()
            return 1
        }
        if (entradaInicioSesion == "user") {
            falsoBorradoDeConsola()
            return 0
        }
        if (entradaInicioSesion == "salir") {
            falsoBorradoDeConsola()
            return -1
        }
        falsoBorradoDeConsola()
        println("Debe introducir (admin/user/salir)!")
        Thread.sleep(1750)
        falsoBorradoDeConsola()
    }
}

/**
 * Pide al usuario que introduzca "S" o "N" para confirmar o cancelar una acción.
 *
 * @param mensaje El mensaje a mostrar al usuario al preguntar por la confirmación o cancelación.
 * @return 'true' si el usuario introduce "S", 'false' si el usuario introduce "N".
 */
fun repetir(mensaje: String): Boolean {
    while (true) {
        println("")
        println(mensaje)
        val entradaUsuario: String = readln().lowercase()
        if (entradaUsuario == "s") {
            falsoBorradoDeConsola()
            return true
        }
        if (entradaUsuario == "n") {
            falsoBorradoDeConsola()
            return false
        }
        falsoBorradoDeConsola()
        println("Debe introducir (S/N)!")
        Thread.sleep(1500)
        falsoBorradoDeConsola()
    }
}

/**
 * Permite al usuario volver al menú principal.
 *
 * @return "true" si el usuario ha introducido "menu", "false" en caso contrario.
 */
fun volverAlMenu(): Boolean {
    println("Introduzca (menu), para volver al menú principal:")
    val entradaDireccionMenu: String = readln().lowercase()
    if (entradaDireccionMenu == "menu") {
        return true
    }
    return false
}



