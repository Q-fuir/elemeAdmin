package elemeAdmin.dao.Impl;

import elemeAdmin.dao.AdminDao;
import elemeAdmin.domain.Admin;
import elemeAdmin.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AdminDaoImpl implements AdminDao {

    private Connection conn=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;


    /**
     * 查找管理密码
     * @param adminName 管理名称
     * @param password  管理密码
     * @return  返回Admin对象
     */
    @Override
    public Admin getAdminByNameByPass(String adminName, String password) {
        Admin admin = new Admin();
        try{
            conn= JDBCUtils.getConnection();
            String sql ="select * from admin where adminName=? and password=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,adminName);
            pst.setString(2,password);
            rs=pst.executeQuery();
            while(rs.next()){
              admin.setAdminName(rs.getNString("adminName"));
              admin.setPassword(rs.getNString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pst,conn,rs);
        }

        return admin;
    }


//    public static void main(String[] args) {
//        Admin admin=new AdminDaoImpl().getAdminByNameByPass("王磊","123");
//        System.out.println(admin);
//    }
}
