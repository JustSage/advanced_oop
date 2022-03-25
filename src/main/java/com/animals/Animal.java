package main.java.com.animals;

import main.java.com.diet.IDiet;
import main.java.com.food.IEdible;
import main.java.com.mobility.Mobile;
import main.java.com.mobility.Point;
import main.java.com.utilities.MessageUtility;

public abstract class Animal extends Mobile implements IEdible {
    private String name;
    private double weight;
    private IDiet diet;

    public Animal(String name, Point location) {
        super(location);
        setName(name);
        setWeight(weight);
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public abstract void makeSound();

    public boolean eat(IEdible food) {
        boolean isSuccess = false;
        double updatedWeight = getDiet().eat(this, food);
        if (updatedWeight != 0) {
            makeSound();
            setWeight(getWeight() + updatedWeight);
            isSuccess = true;
        }
        MessageUtility.logBooleanFunction(getName(), "eat", food, isSuccess);
        return isSuccess;
    }

    public boolean setName(String name) {
        boolean isSuccess = false;
        if (name.matches("[a-zA-Z]+")) {
            this.name = name;
            isSuccess = true;
        }
        MessageUtility.logSetter(getName(), "setName", name, isSuccess);
        return isSuccess;
    }

    public String getName() {
        MessageUtility.logGetter(name, "getName", name);
        return name;
    }

    public double getWeight() {
        MessageUtility.logGetter(getName(), "getWeight", weight);
        return weight;
    }

    public boolean setWeight(double weight) {
        boolean isSuccess = false;
        if (weight > 0) {
            this.weight = weight;
            isSuccess = true;
        }
        MessageUtility.logSetter(getName(), "setWeight", getWeight(), isSuccess);
        return isSuccess;
    }

    public boolean setDiet(IDiet diet) {
        boolean isSuccess = true;
        this.diet = diet;
        MessageUtility.logSetter(getName(), "setDiet", getDiet(), isSuccess);
        return isSuccess;
    }

    public IDiet getDiet() {
        MessageUtility.logGetter(getName(), "getDiet", diet);
        return diet;
    }
}