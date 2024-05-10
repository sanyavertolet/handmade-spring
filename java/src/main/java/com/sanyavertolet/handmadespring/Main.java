package com.sanyavertolet.handmadespring;

import com.sanyavertolet.handmadespring.policeman.Policeman;
import com.sanyavertolet.handmadespring.policeman.RegularPoliceman;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting...");

        Room room = new Room("1");
        ApplicationContext context = Application.run("com.sanyavertolet", new HashMap<>(Map.of(Policeman.class, RegularPoliceman.class)));
        Disinfector disinfector = context.getObject(Disinfector.class);
        disinfector.startDisinfection(room);
    }
}
