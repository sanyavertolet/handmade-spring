package com.sanyavertolet.handmadespring;

import com.sanyavertolet.handmadespring.annotations.InjectByType;
import com.sanyavertolet.handmadespring.annotations.Timed;
import com.sanyavertolet.handmadespring.announcer.Announcer;
import com.sanyavertolet.handmadespring.policeman.Policeman;

@Timed
public class Disinfector {
    @InjectByType
    @SuppressWarnings("unused")
    private Announcer announcer;
    @InjectByType
    @SuppressWarnings("unused")
    private Policeman policeman;

    void startDisinfection(Room room) {
        announcer.announce("Disinfection is starting...");
        policeman.makePeopleLeaveRoom();
        disinfect(room);
        announcer.announce("Disinfection is finished!");
    }

    private void disinfect(Room room) {
        System.out.println("Cleaning the room " + room.name + "...");
    }
}
