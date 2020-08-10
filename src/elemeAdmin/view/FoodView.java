package elemeAdmin.view;

import elemeAdmin.domain.Food;

import java.util.List;

public interface FoodView {

    public List<Food> showFood(Integer businessId);
    public void saveFood(Integer businessId);
    public void updateFood(Integer businessId);
    public void removeFood(Integer businessId);
}
