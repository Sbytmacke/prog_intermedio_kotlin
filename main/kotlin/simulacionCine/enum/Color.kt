package simulacionCine.enum

enum class Color(val color: String) {
    BLACK("\u001b[30m"),
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    MAGENTA("\u001b[35m"),
    CYAN("\u001b[36m"),
    WHITE("\u001b[37m"),
    LIGHT_BLACK("\u001b[1;30m"),
    LIGHT_RED("\u001b[1;31m"),
    LIGHT_GREEN("\u001b[1;32m"),
    LIGHT_YELLOW("\u001b[1;33m"),
    LIGHT_BLUE("\u001b[1;34m"),
    LIGHT_MAGENTA("\u001b[1;35m"),
    LIGHT_CYAN("\u001b[1;36m"),
    LIGHT_WHITE("\u001b[1;37m"),
    RESET("\u001b[0m")
}
