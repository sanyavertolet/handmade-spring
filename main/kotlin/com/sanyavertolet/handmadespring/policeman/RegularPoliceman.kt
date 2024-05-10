package com.sanyavertolet.handmadespring.policeman

@Suppress("unused")
class RegularPoliceman : Policeman {
    override fun makePeopleLeaveRoom() = println("People should leave the room immediately!")
}
