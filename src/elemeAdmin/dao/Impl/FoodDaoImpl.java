package elemeAdmin.dao.Impl;

import elemeAdmin.dao.FoodDao;
import elemeAdmin.domain.Food;
import elemeAdmin.utils.JDBCUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FoodDaoImpl implements FoodDao {

    Connection connection=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    List<Food> list=null;


    /**
     * 根据商家编号查找食品信息
     * @param businessId 商品编号
     * @return 返回List<Food>集合
     */
    @Override
    public List<Food> findFoodByBusinessId(Integer businessId) {
        list = new ArrayList<>();
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="select * from food where businessId=?";
            pst=connection.prepareStatement(sql);
            //占位符赋值
            pst.setInt(1,businessId);
            rs= pst.executeQuery();
            //处理结果集
            while (rs.next()){
                Food food =new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodPrice(rs.getDouble("foodPrice"));
                food.setBusinessId(rs.getInt("businessId"));
                list.add(food);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,rs);
        }

        return list;
    }


    /**
     * 添加食品
     * @param food Food对象
     * @return 返回
     */
    @Override
    public Integer saveFood(Food food) {
            Integer foodId=0;
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql = "insert into food values(null,?,?,?,?)";
            pst=connection.prepareStatement(sql);
            //给占位符赋值
            pst.setString(1,food.getFoodName());
            pst.setString(2,food.getFoodExplain());
            pst.setDouble(3,food.getFoodPrice());
            pst.setInt(4,food.getBusinessId());
           foodId=pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,null);
        }

        return foodId;
    }


    /**
     * 删除食品
     * @param foodId 食品编号
     * @return  返回boolean
     */
    @Override
    public boolean removeFood(Integer foodId) {

        try{
            //获取连接
            connection = JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="delete from food where foodId=?";
            pst= connection.prepareStatement(sql);
            //给占位符赋值
            pst.setInt(1,foodId);
            int row =pst.executeUpdate();
            //判断是否删除成功
            if(row>0){
                //删除成功
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,null);
        }


        return false;
    }

    /**
     * 修改食品信息
     * @param food  Food对象
     * @return  返回boolean
     */
    @Override
    public boolean updateFood(Food food) {

        try{
            //获取连接
            connection = JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="update food set foodName=?,foodEsplain=?,foodPrice=?,businessId=?";
            pst= connection.prepareStatement(sql);
            pst.setString(1,food.getFoodName());
            pst.setString(2,food.getFoodExplain());
            pst.setDouble(3,food.getFoodPrice());
            pst.setInt(4,food.getBusinessId());
            int row =pst.executeUpdate();
            if(row>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,null);
        }

        return false;
    }

    /**
     * 根据食品编号查询食品信息
     * @param foodId 食品编号
     * @return 返回Food 对象
     */
    @Override
    public Food getFoodByFoodId(Integer foodId) {

        Food food =null;
        try{
            //获取连接
            connection = JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="select *  from food where foodId=?";
            pst= connection.prepareStatement(sql);
            pst.setInt(1,foodId);
            rs=pst.executeQuery();
            while(rs.next()){
                food =new Food();
                food.setFoodId(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setBusinessId(rs.getInt("businessId"));
                food.setFoodExplain(rs.getString("foodExplain"));
                food.setFoodPrice(rs.getDouble("foodPrice"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,rs);
        }

        return food;
    }
}
