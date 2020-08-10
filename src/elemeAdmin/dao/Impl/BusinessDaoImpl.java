package elemeAdmin.dao.Impl;

import elemeAdmin.dao.BusinessDao;
import elemeAdmin.domain.Business;
import elemeAdmin.utils.JDBCUtils;
import elemeAdmin.view.Impl.BusinessViewImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoImpl implements BusinessDao {

    Connection connection=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    List<Business> list=null;


    /**
     * 根据商家名称和商家地点查询商家信息
     * @param businessName 商家名称
     * @param businessAddress 商家地址
     * @return 返回存储商家信息的 List集合
     */
    @Override
    public List<Business> findBusiness(String businessName, String businessAddress) {
         list= new ArrayList<>();
        StringBuffer sql=new StringBuffer("select * from business where 1=1");
        if(businessName!=null&& !businessName.equals("")){
            //传入了商家名
            sql.append(" and businessName like '%").append(businessName).append("%'");
        }
        if(businessAddress!=null&&!businessAddress.equals("")) {
            sql.append(" and businessAddress like '%").append(businessAddress).append("%'");
        }
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            pst =connection.prepareStatement(sql.toString());
            rs=pst.executeQuery();
            //查询ResultSet结果集
            while(rs.next()){
                Business b=new Business();
                b.setBusinessId(rs.getInt("businessId"));
                b.setBusinessName(rs.getString("businessName"));
                b.setBusinessAddress(rs.getString("businessAddress"));
                b.setBusinessExplain(rs.getString("businessExplain"));
                b.setStarPrice(rs.getDouble("starPrice"));
                b.setDeliveryPrice(rs.getDouble("deliveryPrice"));
                list.add(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,rs);
        }
        return list;
    }


    /**
     * 添加商家
     * @param businessName 商家名称
     * @param password 商家密码
     * @return 返回添加商家编号（businessId）
     */
    @Override
    public Integer saveBusiness(String businessName,String password) {
         Integer businessId=0;
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="insert into business (businessName,password) values(?,?)";
            //可以在prepareStatement中设置返回自增长列的值
            pst=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //给占位符赋值
            pst.setString(1,businessName);
            pst.setString(2,password);
            pst.executeUpdate();
            //获取自增长的列
            rs= pst.getGeneratedKeys();
            while (rs.next()){
                businessId=rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,rs);
        }
      return businessId;
    }


    /**
     * 删除商家
     * @param businessId 商家编号
     * @return  返回是否删除成功
     */
    @Override
    public boolean removeBusiness(Integer businessId) {
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="delete from business where businessId=?";
            pst =connection.prepareStatement(sql);
            //给占位符赋值
            pst.setInt(1,businessId);
            int row = pst.executeUpdate();
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
     * 修改商家信息
     * @param business 商家对象
     * @return 返回是否修改成功
     */
    @Override
    public boolean updateBusiness(Business business) {
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="update business set businessName =?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId=?";
            pst=connection.prepareStatement(sql);
            //给占位符赋值
            pst.setString(1,business.getBusinessName());
            pst.setString(2,business.getBusinessAddress());
            pst.setString(3,business.getBusinessExplain());
            pst.setDouble(4, business.getStarPrice());
            pst.setDouble(5, business.getDeliveryPrice());
            pst.setInt(6,business.getBusinessId());

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
     * 根据商家编号修改商家密码
     * @param businessId 商家编号
     * @param password 商家密码
     * @return 返回是否修改成功
     */
    @Override
    public boolean updatePasswordByBusinessId(Integer businessId,String password) {

        try{
            //获取连接
            connection = JDBCUtils.getConnection();
            //书写SQL语句
            String sql = "update business set password=? where businessId=?";
            pst = connection.prepareStatement(sql);
            //给占位符赋值
            pst.setString(1,password);
            pst.setInt(2,businessId);
            //执行SQL语句
            int row =pst.executeUpdate();
            //判断是否修改成功
            if(row>0){
                //修改成功
                return  true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,null);
        }

        //修改失败
        return false;
    }


    /**
     * 根据商家编号查询商家信息
     * @param businessId 商家编号
     * @return 返回商家对象
     */
    @Override
    public Business getBusinessByBusinessId(Integer businessId) {
        Business business =null;
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="select * from business where businessId=?";
            pst = connection.prepareStatement(sql);
            //给占位符赋值
            pst.setInt(1,businessId);
            //执行SQL语句
            rs=pst.executeQuery();
            while (rs.next()){
                business =new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setBusinessName(rs.getString("businessName"));
                business.setPassword(rs.getString("password"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,rs);
        }

        return business;
    }


    /**
     * 查询和商家编号和商家密码
     * @param businessId 商家编号
     * @param password 商家密码
     * @return 返回商家对象
     */
    @Override
    public Business getBusinessByBusinessIdByPassword(Integer businessId, String password) {
        Business business =null;
        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="select * from business where businessId=? and password=?";
            pst = connection.prepareStatement(sql);
            //给占位符赋值
            pst.setInt(1,businessId);
            pst.setString(2,password);
            //执行SQL语句
            rs=pst.executeQuery();
            while (rs.next()){
                business =new Business();
                business.setBusinessId(rs.getInt("businessId"));
                business.setBusinessName(rs.getString("businessName"));
                business.setPassword(rs.getString("password"));
                business.setBusinessAddress(rs.getString("businessAddress"));
                business.setBusinessExplain(rs.getString("businessExplain"));
                business.setStarPrice(rs.getDouble("starPrice"));
                business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,rs);
        }
        return business;
    }


    /**
     * 根据商家编号得到商家密码
     * @param businessId 商家摆好
     * @return 返回String
     */
    @Override
    public String getPasswordByBusinessId(Integer businessId) {

        String password= null;

        try{
            //获取连接
            connection =JDBCUtils.getConnection();
            //书写SQL语句
            String sql ="select password from business where businessId=?";
            pst = connection.prepareStatement(sql);
            //给占位符赋值
            pst.setInt(1,businessId);
            //执行SQL语句
            rs=pst.executeQuery();
            while (rs.next()){
               password=rs.getString("password");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,connection,rs);
        }


        return password;
    }


//    public static void main(String[] args) {
//        Business business=new BusinessDaoImpl().getBusinessByBusinessId(10001);
//        System.out.println(business);
//    }




}
