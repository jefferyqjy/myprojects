package com.business;
import java.util.List;
import com.dao.AdminDAO;
import com.entity.AdminEntity;
public class AdminBusiness {
/** Spring IOC DAO 注入 Business * */
private AdminDAO adminDAO;
public AdminDAO getAdminDAO() { return adminDAO; }
public void setAdminDAO(AdminDAO adminDAO) { this.adminDAO = adminDAO; }
/** save * */
public void save(AdminEntity admin) {this.adminDAO.save(admin);}
/** update * */
public void update(AdminEntity admin) {this.adminDAO.update(admin);}
/** delete * */
public void delete(AdminEntity admin) {this.adminDAO.delete(admin);}
/** show * */
public List<AdminEntity> show() {return adminDAO.show();}
/** check * */
public List<AdminEntity> checkUsername(String name) {return this.adminDAO.queryByUsername(name);}
/** check * */
public List<AdminEntity> checkPassword(String name) {return this.adminDAO.queryByPassword(name);}
/** check * */
public List<AdminEntity> checkRealname(String name) {return this.adminDAO.queryByRealname(name);}
/** check * */
public List<AdminEntity> checkContact(String name) {return this.adminDAO.queryByContact(name);}
/** check * */
public List<AdminEntity> checkByLikeUsername(String name) {return this.adminDAO.queryLikeByUsername(name);}
/** check * */
public List<AdminEntity> checkByLikePassword(String name) {return this.adminDAO.queryLikeByPassword(name);}
/** check * */
public List<AdminEntity> checkByLikeRealname(String name) {return this.adminDAO.queryLikeByRealname(name);}
/** check * */
public List<AdminEntity> checkByLikeContact(String name) {return this.adminDAO.queryLikeByContact(name);}
/** checkId * */
public AdminEntity checkId(String id) {return this.adminDAO.queryById(id);}
}

