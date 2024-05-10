package com.sanyavertolet.handmadespring.policeman;

import com.sanyavertolet.handmadespring.annotations.InjectByType;
import com.sanyavertolet.handmadespring.recommendator.Recommendator;

import javax.annotation.PostConstruct;

@SuppressWarnings("unused")
public class RegularPoliceman implements Policeman {
    @InjectByType
    private Recommendator recommendator;

    @PostConstruct
    public void init() {
        System.out.println(recommendator.getClass());
    }

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println("People should leave the room immediately!");
        recommendator.recommend();
    }
}
