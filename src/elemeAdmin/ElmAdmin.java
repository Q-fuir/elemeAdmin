package elemeAdmin;

import elemeAdmin.dao.BusinessDao;
import elemeAdmin.dao.Impl.BusinessDaoImpl;
import elemeAdmin.domain.Admin;
import elemeAdmin.view.AdminView;
import elemeAdmin.view.BusinessDaoView;
import elemeAdmin.view.Impl.AdminViewImpl;
import elemeAdmin.view.Impl.BusinessViewImpl;

import java.util.Scanner;

public class ElmAdmin {

    public static void main(String[] args) {
        work();
    }

    public static void work(){
        Scanner input = new Scanner(System.in);

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么控制台版后台管理系统 V1.0\t\t\t\t|");
        System.out.println("---------------------------------------------------------------------------");

        //调用登陆方法
        AdminView adminView = new AdminViewImpl();
        Admin admin  =adminView.login();

        //调用查找所有商家方法
        BusinessDaoView businessDao=new BusinessViewImpl();


        if(admin!=null){
            int menuNumber=0;
            System.out.println("欢迎来到饿了么商家管理系统");
            //创建菜单
            while(menuNumber!=5){
            System.out.println("========= 1.所有商家列表=2.搜索商家=3.新建商家=4.删除商家=5.退出系统 =========");
            System.out.println("请选择相应的菜单编号");
            menuNumber= input.nextInt();

                switch (menuNumber){
                    case 1:
                        businessDao.findBusinessAll();
                        break;
                    case 2:
                        businessDao.findBusiness();
                        break;
                    case 3:
                        businessDao.saveBusiness();
                        break;
                    case 4:
                       businessDao.removeBusiness();
                        break;
                    case 5:
                        System.out.println("========= 欢迎下次光临饿了么系统 =========");
                        break;
                    default:
                        System.out.println("没有这个菜单项\n");


                }
            }

        }else {
            System.out.println("\n管理员名称或密码输入错误!\n");
        }

    }
}
