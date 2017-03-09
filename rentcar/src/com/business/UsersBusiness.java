package com.business;
import java.util.List;
import com.dao.UsersDAO;
import com.entity.UsersEntity;
public class UsersBusiness {
/** Spring IOC DAO 注入 Business * */
private UsersDAO usersDAO;
public UsersDAO getUsersDAO() { return usersDAO; }
public void setUsersDAO(UsersDAO usersDAO) { this.usersDAO = usersDAO; }
/** save * */
public void save(UsersEntity users) {this.usersDAO.save(users);}
/** update * */
public void update(UsersEntity users) {this.usersDAO.update(users);}
/** delete * */
public void delete(UsersEntity users) {this.usersDAO.delete(users);}
/** show * */
public List<UsersEntity> show() {return usersDAO.show();}
/** check * */
public List<UsersEntity> checkUsername(String name) {return this.usersDAO.queryByUsername(name);}
/** check * */
public List<UsersEntity> checkPassword(String name) {return this.usersDAO.queryByPassword(name);}
/** check * */
public List<UsersEntity> checkRealname(String name) {return this.usersDAO.queryByRealname(name);}
/** check * */
public List<UsersEntity> checkSex(String name) {return this.usersDAO.queryBySex(name);}
/** check * */
public List<UsersEntity> checkBirthday(String name) {return this.usersDAO.queryByBirthday(name);}
/** check * */
public List<UsersEntity> checkIdcard(String name) {return this.usersDAO.queryByIdcard(name);}
/** check * */
public List<UsersEntity> checkContact(String name) {return this.usersDAO.queryByContact(name);}
/** check * */
public List<UsersEntity> checkAddress(String name) {return this.usersDAO.queryByAddress(name);}
/** check * */
public List<UsersEntity> checkByLikeUsername(String name) {return this.usersDAO.queryLikeByUsername(name);}
/** check * */
public List<UsersEntity> checkByLikePassword(String name) {return this.usersDAO.queryLikeByPassword(name);}
/** check * */
public List<UsersEntity> checkByLikeRealname(String name) {return this.usersDAO.queryLikeByRealname(name);}
/** check * */
public List<UsersEntity> checkByLikeSex(String name) {return this.usersDAO.queryLikeBySex(name);}
/** check * */
public List<UsersEntity> checkByLikeBirthday(String name) {return this.usersDAO.queryLikeByBirthday(name);}
/** check * */
public List<UsersEntity> checkByLikeIdcard(String name) {return this.usersDAO.queryLikeByIdcard(name);}
/** check * */
public List<UsersEntity> checkByLikeContact(String name) {return this.usersDAO.queryLikeByContact(name);}
/** check * */
public List<UsersEntity> checkByLikeAddress(String name) {return this.usersDAO.queryLikeByAddress(name);}
/** checkId * */
public UsersEntity checkId(String id) {return this.usersDAO.queryById(id);}
}

