package uk.ac.solent.emacamera
class image(i: Long, n: String, l: String, a: Long, d: Long, t: Long) {
    val id: Long
    val name: String
    val loc: String
    val album: Long
    val date: Long
    val time: Long

    init {
        id = i
        name = n
        loc = l
        album = a
        date = d
        time = t
    }
}