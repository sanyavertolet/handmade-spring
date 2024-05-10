package com.sanyavertolet.handmadespring.policeman;

@SuppressWarnings("unused")
public class AngryPoliceman implements Policeman {
    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("EVERYBODY GET OUT!!!");
    }
}
