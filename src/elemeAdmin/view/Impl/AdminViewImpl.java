package elemeAdmin.view.Impl;

import elemeAdmin.dao.AdminDao;
import elemeAdmin.dao.Impl.AdminDaoImpl;
import elemeAdmin.domain.Admin;
import elemeAdmin.view.AdminView;

import java.util.Scanner;

public class AdminViewImpl implements AdminView {

   private Scanner input = new Scanner(System.in);

    @Override
    public Admin login() {

        System.out.println("请输入管理员的用户名：");
        String adminName=input.next();
        System.out.println("请输入管理员的密码：");
        String password =input.next();

        AdminDao dao = new AdminDaoImpl();

        return dao.getAdminByNameByPass(adminName,password);

    }
}
