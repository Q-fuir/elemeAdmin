package elemeAdmin.dao;

import elemeAdmin.domain.Food;

import java.util.List;

public interface FoodDao {

    //通过商家编号查询食品
    public List<Food> findFoodByBusinessId(Integer businessId);

    //添加食品信息
    public Integer saveFood(Food food);

    //通过foodId删除食品
    public boolean removeFood(Integer foodId);

    //更新食品信息
    public boolean updateFood(Food food);

    //根据食品编号查询食品信息
    public Food getFoodByFoodId(Integer foodId);
}
