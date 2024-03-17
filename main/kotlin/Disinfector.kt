package com.sanyavertolet.handmadespring

import com.sanyavertolet.handmadespring.announcer.Announcer
import com.sanyavertolet.handmadespring.policeman.Policeman

class Disinfector {
    private val announcer: Announcer = ObjectFactory.getInstance().createObject(Announcer::class.java)
    private val policeman: Policeman = ObjectFactory.getInstance().createObject(Policeman::class.java)

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
