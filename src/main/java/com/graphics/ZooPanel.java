package com.graphics;

import com.animals.Animal;
import com.food.Food;
import com.food.Meat;
import com.food.plants.Cabbage;
import com.food.plants.Lettuce;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ZooPanel extends JPanel implements ActionListener {
    private JPanel actionPanel;
    private JDialog addAnimalDialog;
    private JDialog moveAnimalDialog;
    private InfoTableDialog infoTable;
    private AnimalModel model;
    private Food food = null;
    private BufferedImage savana;
    private static int totalEatCount = 0;

    public ZooPanel() {
        model = new AnimalModel();
        actionPanel = new JPanel();

        // instantiating buttons
        JButton addAnimal = new JButton("Add Animal");
        JButton moveAnimal = new JButton("Move Animal");
        JButton clear = new JButton("Clear");
        JButton food = new JButton("Food");
        JButton info = new JButton("Info");
        JButton exit = new JButton("Exit");

        // adding to action listener
        addAnimal.addActionListener(this);
        moveAnimal.addActionListener(this);
        clear.addActionListener(this);
        food.addActionListener(this);
        info.addActionListener(this);
        exit.addActionListener(this);

        // adding buttons to panel
        actionPanel.add(addAnimal);
        actionPanel.add(moveAnimal);
        actionPanel.add(clear);
        actionPanel.add(food);
        actionPanel.add(info);
        actionPanel.add(exit);

        // setting properties
        Font courier = new Font("Courier", Font.PLAIN, 14);
        TitledBorder actionBorder = BorderFactory.createTitledBorder("Select Option:");

        actionBorder.setTitleFont(courier);
        actionBorder.setTitleColor(Color.blue);

        actionPanel.setBorder(actionBorder);

        this.setLayout(new BorderLayout());
        this.add(actionPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(savana, 0, 0,this);

        if (food != null)
            food.drawObject(g);

        for (Animal animal : model.getModel()) {
            animal.drawObject(g);
        }
    }

    //    private void setPanelBackground()
    private void createFoodDialog() {
        String[] options = {"Lettuce", "Cabbage", "Meat"};
        int result = JOptionPane.showOptionDialog(null,//parent container of JOptionPane
                "What food would you like to add?",
                "Add Food Dialog",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,//do not use a custom Icon
                options,//the titles of buttons
                null);

        switch (result) {
            case 0 -> setPanelFood(new Lettuce());
            case 1 -> setPanelFood(new Cabbage());
            case 2 -> setPanelFood(new Meat());
        }
    }

    public boolean setPanelFood(Food food) {
        boolean isSuccess = false;
        if (food != null){
            this.food = food;
            this.food.setPan(this);
            isSuccess = true;
        } else {
            this.food = null;
        }
        return isSuccess;
    }

    public boolean isChange(){
        for (Animal animal : model.getModel()){
            if (animal.getChanges()){
                //checkEatAnimal();
                animal.setChanges(false);
                return true;
            }
        }
        return false;
    }

    public  void checkEatAnimal(){
        for (int i = 0; i < model.getModelSize(); i++){
            Animal predator = model.getModel().get(i);
            for (int j = i + 1; j < model.getModelSize(); j++) {
                Animal prey = model.getModel().get(j);
                if (predator.calcDistance(prey.getLocation()) <= Animal.getEatDistance()){
                    if (predator.eat(prey)) {
                        setTotalEatCount(getTotalEatCount() - prey.getEatCount());
                        model.getModel().remove(j);
                        j--;
                        updateEatCount(predator);
                        infoTable.updateTable();
                    }
                    else if (prey.eat(predator)){
                        setTotalEatCount(getTotalEatCount() - predator.getEatCount());
                        model.getModel().remove(i);
                        i--;
                        updateEatCount(prey);
                        infoTable.updateTable();
                    }
                }
            }
        }
    }

    public void updateEatCount(Animal animal){
        animal.eatInc();
        totalEatCountInc();
    }

    // TODO; Need to add checkEatAnimal to manageZoo.
    public void manageZoo() {
        if (isChange()) {
            repaint();
        }
        checkEatAnimal();
    }

    public static void totalEatCountInc(){
        totalEatCount++;
    }

    public static void setTotalEatCount(int count){
        totalEatCount = count;
    }

    public static int getTotalEatCount() {
        return totalEatCount;
    }

    public Food getFood(){
        return food;
    }


    public void setImageBackground(){
        try {
            savana = ImageIO.read(new File("src/main/resources/assignment2_pictures/background_images/savanna.png"));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGreenBackground(){
        savana = null;
        this.setBackground(Color.green);
        repaint();
    }

    public void setNoBackground(){
        savana = null;
        this.setBackground(null);
        repaint();
    }

    public InfoTableDialog getInfoTable() {
        return infoTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Add Animal" -> {
                addAnimalDialog = new AddAnimalDialog(model,this);
            }
            case "Move Animal" -> {
                if (model.getModelSize() > 0) {
                    moveAnimalDialog = new MoveAnimalDialog(model, this);
                } else {
                    String message = "Zoo is currently empty!";
                    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            case "Clear" -> {
                model.removeAllAnimals();
                setTotalEatCount(0);
                infoTable.setIsOpen(false);
                repaint();
            }
            case "Food" -> {
                createFoodDialog();
                repaint();
                System.out.println("Food pressed!");
            }
            case "Info" -> {
                //creating animals details list
                if (model.getModelSize() > 0) {
                    if (!InfoTableDialog.getIsOpen()){
                        infoTable = new InfoTableDialog(model);
                        infoTable.setIsOpen(true);
                    }
                } else {
                    String message = "Zoo is currently empty!";
                    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            case "Exit" -> {
                System.exit(1);
            }
        }
    }
}
