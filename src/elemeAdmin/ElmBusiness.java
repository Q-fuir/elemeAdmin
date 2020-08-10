package elemeAdmin;


import elemeAdmin.domain.Business;
import elemeAdmin.view.BusinessDaoView;
import elemeAdmin.view.FoodView;
import elemeAdmin.view.Impl.BusinessViewImpl;
import elemeAdmin.view.Impl.FoodViewImpl;

import java.util.Scanner;

public class ElmBusiness {

    public static void work(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("|\t\t\t\t饿了么后台管理系统 V1.0\t\t\t\t|");
        System.out.println("---------------------------------------------------------------------------");


        //调用商家登录
        BusinessDaoView businessDaoView = new BusinessViewImpl();
        Business business =businessDaoView.BusinessLogin();

        if(business!=null){
            int menu=0;
            while (menu!=5){
                System.out.println("\n======= 一级菜单（商家管理）1.查看商家信息=2.修改商家信息=3.更新密码=4.所属商品管理=5.退出系统=======");
                System.out.println("请输入你的选择：");
                menu = scanner.nextInt();

                switch (menu){
                    case 1:
                        businessDaoView.showBusiness(business.getBusinessId());
                        break;
                    case 2:
                       businessDaoView.editBusiness(business.getBusinessId());
                        break;
                    case 3:
                        businessDaoView.updatePassword(business.getBusinessId());
                        break;
                    case 4:
                           new ElmBusiness().foodManager(business.getBusinessId());
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

    private   void foodManager(Integer businessId){
        FoodView foodView =new FoodViewImpl();

        Scanner scanner =new Scanner(System.in);

            int menu=0;
            while (menu!=5){
                System.out.println("\n======= 二级菜单（食品管理）1.查看食品列表=2.新增食品=3.修改食品=4.删除食品=5.返回一级菜单 =======");
                System.out.println("请输入你的选择：");
                menu = scanner.nextInt();

                switch (menu){
                    case 1:
                        foodView.showFood(businessId);
                        break;
                    case 2:
                        foodView.saveFood(businessId);
                        break;
                    case 3:
                        foodView.updateFood(businessId);
                        break;
                    case 4:
                        foodView.removeFood(businessId);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("没有这个菜单项\n");
                }
            }
        }

    public static void main(String[] args) {
        work();
    }
}











