package elemeAdmin.dao;

import elemeAdmin.domain.Admin;

public interface AdminDao {
    public Admin getAdminByNameByPass(String adminName,String password);
}
