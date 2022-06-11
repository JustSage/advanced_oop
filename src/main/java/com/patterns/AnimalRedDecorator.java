package com.patterns;

import com.animals.Animal;

/**
 * AnimalRedDecorator decorates the Animals with the red color.
 */
public class AnimalRedDecorator extends AnimalColorDecorator {

    /**
     * AnimalRedDecorator constructor.
     * @param animal the animal to decorate.
     */
    public AnimalRedDecorator(Animal animal) {
        super(animal);
    }

    /**
     * decorate the animal with the red color.
     * @return the Animal decorated object with the red color.
     */
    @Override
    public Animal decorateAnimal() {
        setColor("RED");
        return getAnimal();
    }
}

