package com.business;
import java.util.List;
import com.dao.BrandDAO;
import com.entity.BrandEntity;
public class BrandBusiness {
/** Spring IOC DAO 注入 Business * */
private BrandDAO brandDAO;
public BrandDAO getBrandDAO() { return brandDAO; }
public void setBrandDAO(BrandDAO brandDAO) { this.brandDAO = brandDAO; }
/** save * */
public void save(BrandEntity brand) {this.brandDAO.save(brand);}
/** update * */
public void update(BrandEntity brand) {this.brandDAO.update(brand);}
/** delete * */
public void delete(BrandEntity brand) {this.brandDAO.delete(brand);}
/** show * */
public List<BrandEntity> show() {return brandDAO.show();}
/** check * */
public List<BrandEntity> checkBrandname(String name) {return this.brandDAO.queryByBrandname(name);}
/** check * */
public List<BrandEntity> checkImage(String name) {return this.brandDAO.queryByImage(name);}
/** check * */
public List<BrandEntity> checkByLikeBrandname(String name) {return this.brandDAO.queryLikeByBrandname(name);}
/** check * */
public List<BrandEntity> checkByLikeImage(String name) {return this.brandDAO.queryLikeByImage(name);}
/** checkId * */
public BrandEntity checkId(String id) {return this.brandDAO.queryById(id);}
}

