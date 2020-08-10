package elemeAdmin.view;

import elemeAdmin.domain.Business;

public interface BusinessDaoView {

    //查询所有商家
    public void findBusinessAll();

    //详细查询商家信息
    public void findBusiness();

    //新建商家
    public void saveBusiness();

    //删除不需要的商家
    public  void  removeBusiness();

    //商家登录
    public Business BusinessLogin();

    //商家编号修改商家信息
    public void editBusiness(Integer businessId);

    //根据商家编号修改商家密码
    public void updatePassword(Integer businessId);

    //根据商家编码查询商家信息
    public void showBusiness(Integer businessId);
}
