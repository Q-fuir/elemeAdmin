package elemeAdmin.dao;

import elemeAdmin.domain.Business;

import java.util.List;

public interface BusinessDao {

    //显示所有商家列表 可选businessName,businessAddress
    public List<Business> findBusiness(String businessName , String businessAddress);

    //添加商家姓名，密码
    public Integer saveBusiness(String businessName,String password);

    //通过businessId删除商家
    public boolean removeBusiness(Integer businessId);

    //更新商家信息
    public boolean updateBusiness(Business business);

    //根据商家编号更改商家密码
    public boolean updatePasswordByBusinessId(Integer businessId,String password);

    //根据商家编号查询商家信息
    public Business getBusinessByBusinessId(Integer businessId);

    //根据商家编号和密码查询商家信息
    public Business getBusinessByBusinessIdByPassword(Integer businessId,String password);

    //根据商家编号查询商家密码
    public String  getPasswordByBusinessId(Integer businessId);
}
