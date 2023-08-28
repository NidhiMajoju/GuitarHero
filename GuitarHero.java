/*****************************************************************************
 *  Compilation:  javac GuitarHeroLite.java
 *  Execution:    java  GuitarHeroLite
 *  Dependencies: StdAudio.java StdDraw.java GuitarString.java
 *
 *  Plays two guitar strings (concert A and concert C) when the user
 *  types the lowercase letters 'a' and 'c', respectively in the 
 *  standard drawing window.
 *
 ****************************************************************************/
import java.util.*;
public class GuitarHero {

    public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString [] notes = new GuitarString[37];
        for (int i = 0; i<keyboard.length(); i++)
        {
            notes [i] = new GuitarString(440*Math.pow(1.05956, (i-24)));
        }
        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) <0)
                    continue;
                notes[keyboard.indexOf(key)].pluck();
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i<notes.length; i++)
            {
                sample += notes[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i<notes.length; i++)
            {
                notes[i].tic();
            }
        }
    }

}