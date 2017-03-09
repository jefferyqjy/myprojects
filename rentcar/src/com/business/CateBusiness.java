package com.business;
import java.util.List;
import com.dao.CateDAO;
import com.entity.CateEntity;
public class CateBusiness {
/** Spring IOC DAO 注入 Business * */
private CateDAO cateDAO;
public CateDAO getCateDAO() { return cateDAO; }
public void setCateDAO(CateDAO cateDAO) { this.cateDAO = cateDAO; }
/** save * */
public void save(CateEntity cate) {this.cateDAO.save(cate);}
/** update * */
public void update(CateEntity cate) {this.cateDAO.update(cate);}
/** delete * */
public void delete(CateEntity cate) {this.cateDAO.delete(cate);}
/** show * */
public List<CateEntity> show() {return cateDAO.show();}
/** check * */
public List<CateEntity> checkCatename(String name) {return this.cateDAO.queryByCatename(name);}
/** check * */
public List<CateEntity> checkByLikeCatename(String name) {return this.cateDAO.queryLikeByCatename(name);}
/** checkId * */
public CateEntity checkId(String id) {return this.cateDAO.queryById(id);}
}

