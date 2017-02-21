package com.team7;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class of static methods accessible by the game engine
 * Used to generate different characteristics and game effects
 */
public class ProbabilityGenerator {

    //determines whether an event will occur using Java's random() method (double between 0 - 0.9999)
    public static boolean willOccur(double percentageOfEventOccuring){
        return Math.random() < percentageOfEventOccuring;
    }

    //returns a random int number between the min and max parameters provided
    public static int randomInteger(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}