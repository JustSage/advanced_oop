package com.food.plants;

import com.food.EFoodType;
import com.food.Food;

/**
 * abstract class Plant extends abstract class Foods, handles food that are vegetables.
 */
public abstract class Plant extends Food {
	/**
	 * Plant constructor
	 */
	public Plant() { }

	/**
	 * @see com.food.IEdible getFoodtype()
	 * @return the food type of plant: Vegetable.
	 */
	@Override
	public EFoodType getFoodType() {
		return EFoodType.VEGETABLE;
	}
}
