package com.sanyavertolet.handmadespring

fun main() {
    println("Starting app")
    val room = Room()
    val disinfector = Disinfector()
    disinfector.start(room)
}
