package com.diet;

import com.animals.Animal;
import com.food.IEdible;
import com.food.EFoodType;

/**
 * Plant eating animals.
 */
public class Herbivore implements IDiet {

    public Herbivore(){};

    @Override
    public double eat(Animal animal, IEdible food) {
        // eating veggies increase animal weight by 7%
        if (canEat(food.getFoodtype()) && !(animal == food)) {
            return (animal.getWeight() * 0.07);
        }
        return 0;
    }

    @Override
    public boolean canEat(EFoodType food) {
        // can eat VEGETABLE.
        return food == EFoodType.VEGETABLE;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "[Herbivore]";
    }
}
