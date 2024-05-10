package com.sanyavertolet.handmadespring.announcer

@Suppress("unused")
class ConsoleAnnouncer : Announcer {
    override fun announce(message: String) = println(message)
}
