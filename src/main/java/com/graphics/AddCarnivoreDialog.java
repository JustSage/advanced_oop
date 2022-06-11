package com.graphics;

/**
 * AddCarnivoreDialog is an extension of AddAnimalDialog
 * opens a dialog with Carnivores only.
 */
public class AddCarnivoreDialog extends AddAnimalDialog {
    /**
     * String array of carnivore animal types.
     */
    private static final String[] types = {"Lion"};

    /**
     * AddCarnivoreDialog constructor.
     * passes the animal types string array and the factory type to the constructor of
     * AddAnimalDialog.
     * @param model AnimalModel object, the animal container.
     * @param zooPanel ZooPanel object, the zoo panel.
     * @see AddAnimalDialog
     */
    public AddCarnivoreDialog(AnimalModel model, ZooPanel zooPanel) {
        super(model, zooPanel, types, "Carnivore");
    }
}
