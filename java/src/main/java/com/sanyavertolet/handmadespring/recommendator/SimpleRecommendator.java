package com.sanyavertolet.handmadespring.recommendator;

import com.sanyavertolet.handmadespring.annotations.InjectProperty;
import com.sanyavertolet.handmadespring.annotations.Singleton;
import com.sanyavertolet.handmadespring.annotations.Timed;

@SuppressWarnings("unused")
@Singleton
public class SimpleRecommendator implements Recommendator {
    @InjectProperty
    private String adsProductName;

    public SimpleRecommendator() {
        System.out.println(SimpleRecommendator.class.getSimpleName() + " was created");
    }

    @Override
    public void recommend() {
        System.out.println("Ads: drink " + adsProductName + "!");
    }
}
