package elemeAdmin.view.Impl;

import elemeAdmin.dao.AdminDao;
import elemeAdmin.dao.BusinessDao;
import elemeAdmin.dao.Impl.AdminDaoImpl;
import elemeAdmin.dao.Impl.BusinessDaoImpl;
import elemeAdmin.domain.Business;
import elemeAdmin.view.BusinessDaoView;

import java.util.List;
import java.util.Scanner;

public class BusinessViewImpl implements BusinessDaoView {

   private Scanner input =new Scanner(System.in);
   private BusinessDao dao =new BusinessDaoImpl();

    /**
     * 查询所有商家信息
     */
    @Override
    public void findBusinessAll() {
        List<Business> list =dao.findBusiness(null,null);
//        System.out.println("商家编号\t\t\t商家名称\t\t\t商家地址\t\t\t商家介绍\t\t\t起始价格\t\t\t配送价格");
        for(Business b:list){
            System.out.println(b);
        }
    }

    /**
     * 查找商家详细信息
     */
    @Override
    public void findBusiness() {
        String BusinessAddress="";
        String  BusinessName="";
        String inputStr="";
        System.out.println("是否需要输入商家名称关键词（y/n）");
        inputStr=input.next();
        if(inputStr.equalsIgnoreCase("y")){
            System.out.println("请输入商家名称关键词（y/n）");
              BusinessName =input.next();
        }

        System.out.println("是否需要输入商家地址关键词（y/n）");
        inputStr=input.next();
        if(inputStr.equalsIgnoreCase("y")){
            System.out.println("请输入商家地址关键词（y/n）");
             BusinessAddress=input.next();
        }
        List<Business> list =dao.findBusiness(BusinessName,BusinessAddress);
        for(Business b:list){
            System.out.println("商家编号："+b.getBusinessId()+"  商家名称"+b.getBusinessName()+"  商家地址："+b.getBusinessAddress()+"  起始价格："+b.getStarPrice()+"  配送价格："+b.getDeliveryPrice());
        }




    }

    /**
     * 新增商家
     */
    @Override
    public void saveBusiness() {

        String businessName="";
        String password="";
        System.out.println("请输入商家名称");
        businessName=input.next();

        int businessId=dao.saveBusiness(businessName,password);
        if(businessId>0){
                System.out.println("新建商家成功！！  商家编号是："+businessName);
        }else {
            System.out.println("新建商家失败！！请重新新建");
        }

    }


    /**
     * 删除商家
     */
    @Override
    public void removeBusiness() {
            Integer businessId=0;
        System.out.println("请输入要删除的商家编号");
        businessId =input.nextInt();
        if(businessId!=null){
            System.out.println("确认要删除（y/n）");
            String enterInput =input.next();
            if(enterInput.equalsIgnoreCase("y")){
                dao.removeBusiness(businessId);
                System.out.println("删除商家成功");
            }else {
                System.out.println("删除商家失败");
            }
        }else {
            System.out.println("请输入要删除的商家编号");
        }

    }

    /**
     * 商家登录
     * @return  返回Business对象
     */
    @Override
    public Business BusinessLogin() {

        System.out.println("请输入商家的编号：");
        Integer businessId=input.nextInt();
        System.out.println("请输入商家的密码：");
        String password =input.next();
        return dao.getBusinessByBusinessIdByPassword(businessId,password);
    }

    /**
     * 修改商家信息
     * @param businessId  商家编号
     */
    @Override
    public void editBusiness(Integer businessId) {
            //先查询，再修改
        Business business =dao.getBusinessByBusinessId(businessId);
        System.out.println(business);


        Business business1 = new Business();
        System.out.println("是否修改商家名称（y/n）");
       String inputStr =input.next();
        if(inputStr.equalsIgnoreCase("y")){
            business1.setBusinessName(input.next());
        }
        System.out.println("是否修改商家地址（y/n）");
        if(inputStr.equalsIgnoreCase("y")){
            business1.setBusinessAddress(input.next());
        }
        System.out.println("是否修改商家介绍（y/n）");
        if(inputStr.equalsIgnoreCase("y")){
            business1.setBusinessExplain(input.next());
        }
        System.out.println("是否修改起始价格（y/n）");
        if(inputStr.equalsIgnoreCase("y")){
            business1.setStarPrice(input.nextDouble());
        }
        System.out.println("是否修改运费价格价格（y/n）");
        if(inputStr.equalsIgnoreCase("y")){
            business1.setDeliveryPrice(input.nextDouble());
        }

         boolean flag =dao.updateBusiness(business1);
        if(flag){
            System.out.println("修改商家信息成功");
        }else {
            System.out.println("修改商家信息失败");
        }


    }


    /**
     * 修改商家密码
     * @param businessId  商家编号
     */
    @Override
    public void updatePassword(Integer businessId) {

        System.out.println("是否修改商家密码");
        String inputStr=input.next();
        if(inputStr.equalsIgnoreCase("y")){
            System.out.println("请输入旧密码");
            String olderPassword=input.next();
            if(olderPassword.equals(dao.getPasswordByBusinessId(businessId))) {
                System.out.println("请输入新的密码");
                String newPassword = input.next();
                System.out.println("请再次输入新的密码");
                String secondNewPassword = input.next();
                if (newPassword.equals(secondNewPassword)) {
                    boolean flag = dao.updatePasswordByBusinessId(businessId, newPassword);
                    if (flag) {
                        System.out.println("修改密码成功");
                    } else {
                        System.out.println("修改密码失败");
                    }
                }
            }
        }

    }

    /**
     * 根据商家编号查询商家信息
     * @param businessId 商家编号
     */
    @Override
    public void showBusiness(Integer businessId) {
        Business business=dao.getBusinessByBusinessId(businessId);
        System.out.println(business);
    }


}
