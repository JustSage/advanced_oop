package com.graphics;

import com.animals.Animal;

import java.util.ArrayList;

/**
 * AnimalModel represents the model used for the zoo.
 * it consists of array list with default set size.
 */
public class AnimalModel {
    /**
     * ArrayList of animals.
     */
    private ArrayList<Animal> animals;
    /**
     * integer value representing the maximum size of the animal array.
     */
    private static final int MAX_SIZE = 10;
    /**
     * boolean value indicating if the model is changed or not.
     */
    private boolean isChanged;

    /**
     * AnimalModel constructor.
     * initiating the array list and default state to false.
     */
    AnimalModel(){
        animals = new ArrayList<>();
        isChanged = false;
    }

    /**
     * addAnimal will add an animal if the current only if the current size does not exceed the maximum.
     * @param animal Animal object to add.
     * @return boolean value indicating if the animal addition was successful or not.
     */
    public boolean addAnimal(Animal animal){
        boolean isSuccess = false;
        if (animals.size() < MAX_SIZE){
            animals.add(animal);
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * removeAllAnimals assigns an empty array list.
     * the removal of the previous array list is handled via the garbage collector.
     */
    public void removeAllAnimals(){
        animals = new ArrayList<>();
    }

    /**
     * AnimalModel getter
     * @return ArrayList of Animals.
     */
    public ArrayList<Animal> getAnimalModel(){
        return animals;
    }

    /**
     * Model size getter.
     * @return integer representation of the animal model size.
     */
    public int getAnimalModelSize() {
        return animals.size();
    }

    /**
     * Animal names getter
     * @return String array of the animal names of all the animals in the animal model.
     */
    public String[] getAnimalNames() {
        String[] names = new String[animals.size()];

        for (int i = 0; i < getAnimalModelSize(); i++){
            names[i] = animals.get(i).toString();
        }

        return names;
    }


    /**
     * containsAnimalName evaluates if the animal model contains a given name or not.
     * @param name String representation of the animal name.
     * @return boolean value indicating if the animal name is contained in the animal model or not.
     */
    public boolean containsAnimalName(String name){
        for (Animal animal: animals){
            if (animal.getName().equals(name))
                return true;
        }
        return false;
    }

    /**
     * MAX_SIZE getter
     * @return integer value representing the model maximum size.
     */
    public static int getModelMaxSize() {
        return MAX_SIZE;
    }

    /**
     * isChanged getter.
     * @return boolean value indicating the state of isChanged.
     */
    public boolean getChangesState(){
        return isChanged;
    }

    /**
     * isChanged setter.
     * @param state boolean value of the model state.
     */
    public void setChangesState(boolean state){
        isChanged = state;
    }

}
