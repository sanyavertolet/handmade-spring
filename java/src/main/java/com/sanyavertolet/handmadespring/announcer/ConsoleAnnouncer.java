package com.sanyavertolet.handmadespring.announcer;

import com.sanyavertolet.handmadespring.annotations.InjectByType;
import com.sanyavertolet.handmadespring.annotations.Singleton;
import com.sanyavertolet.handmadespring.annotations.Timed;
import com.sanyavertolet.handmadespring.recommendator.Recommendator;
import com.sanyavertolet.handmadespring.ObjectFactory;

@SuppressWarnings("unused")
public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;

    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
