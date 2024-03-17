package com.sanyavertolet.handmadespring

import com.sanyavertolet.handmadespring.announcer.Announcer
import com.sanyavertolet.handmadespring.announcer.ConsoleAnnouncer
import com.sanyavertolet.handmadespring.policeman.Policeman
import com.sanyavertolet.handmadespring.policeman.RegularPoliceman

class Disinfector {
    private val announcer: Announcer = ConsoleAnnouncer()
    private val policeman: Policeman = RegularPoliceman()

    fun start(room: Room) {
        announcer.announce("Disinfection is starting...")
        policeman.makePeopleLeaveRoom()
        disinfect(room)
        announcer.announce("Disinfection is finished!")
    }

    private fun disinfect(room: Room) {
        println("Cleaning the room ${room.name}...")
    }
}
