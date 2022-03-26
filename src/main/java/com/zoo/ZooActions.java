package com.zoo;

import com.animals.*;
import com.food.IEdible;

public class ZooActions
{

    private static Animal findAnimalInstance(Object animal) {
        if (animal instanceof Lion)
            return (Lion) animal;
        if (animal instanceof Bear)
            return (Bear) animal;
        if (animal instanceof Giraffe)
            return (Giraffe) animal;
        if (animal instanceof Elephant)
            return (Elephant) animal;
        if (animal instanceof Turtle)
            return (Turtle) animal;
        return null;
    }



    public static boolean eat(Object animal, IEdible food){
        Animal creature = findAnimalInstance(animal);
        return creature.eat(food);
    }


    public static void main(String[] args) {

        
    }

}
