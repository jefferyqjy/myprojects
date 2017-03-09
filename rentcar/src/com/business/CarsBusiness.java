package com.business;
import java.util.List;
import com.dao.CarsDAO;
import com.entity.CarsEntity;
public class CarsBusiness {
/** Spring IOC DAO 注入 Business * */
private CarsDAO carsDAO;
public CarsDAO getCarsDAO() { return carsDAO; }
public void setCarsDAO(CarsDAO carsDAO) { this.carsDAO = carsDAO; }
/** save * */
public void save(CarsEntity cars) {this.carsDAO.save(cars);}
/** update * */
public void update(CarsEntity cars) {this.carsDAO.update(cars);}
/** delete * */
public void delete(CarsEntity cars) {this.carsDAO.delete(cars);}
/** show * */
public List<CarsEntity> hot() {return carsDAO.hot();}
/** show * */
public List<CarsEntity> recommend() {return carsDAO.recommend();}
/** show * */
public List<CarsEntity> special() {return carsDAO.special();}
/** show * */
public List<CarsEntity> show() {return carsDAO.show();}
/** check * */
public List<CarsEntity> checkCarno(String name) {return this.carsDAO.queryByCarno(name);}
/** check * */
public List<CarsEntity> checkImage(String name) {return this.carsDAO.queryByImage(name);}
/** check * */
public List<CarsEntity> checkCateid(String name) {return this.carsDAO.queryByCateid(name);}
/** check * */
public List<CarsEntity> checkBrandid(String name) {return this.carsDAO.queryByBrandid(name);}
/** check * */
public List<CarsEntity> checkPrice(String name) {return this.carsDAO.queryByPrice(name);}
/** check * */
public List<CarsEntity> checkColour(String name) {return this.carsDAO.queryByColour(name);}
/** check * */
public List<CarsEntity> checkDegree(String name) {return this.carsDAO.queryByDegree(name);}
/** check * */
public List<CarsEntity> checkDisplacement(String name) {return this.carsDAO.queryByDisplacement(name);}
/** check * */
public List<CarsEntity> checkSpecial(String name) {return this.carsDAO.queryBySpecial(name);}
/** check * */
public List<CarsEntity> checkRecommend(String name) {return this.carsDAO.queryByRecommend(name);}
/** check * */
public List<CarsEntity> checkContents(String name) {return this.carsDAO.queryByContents(name);}
/** check * */
public List<CarsEntity> checkByLikeCarno(String name) {return this.carsDAO.queryLikeByCarno(name);}
/** check * */
public List<CarsEntity> checkByLikeImage(String name) {return this.carsDAO.queryLikeByImage(name);}
/** check * */
public List<CarsEntity> checkByLikeCateid(String name) {return this.carsDAO.queryLikeByCateid(name);}
/** check * */
public List<CarsEntity> checkByLikeBrandid(String name) {return this.carsDAO.queryLikeByBrandid(name);}
/** check * */
public List<CarsEntity> checkByLikePrice(String name) {return this.carsDAO.queryLikeByPrice(name);}
/** check * */
public List<CarsEntity> checkByLikeColour(String name) {return this.carsDAO.queryLikeByColour(name);}
/** check * */
public List<CarsEntity> checkByLikeDegree(String name) {return this.carsDAO.queryLikeByDegree(name);}
/** check * */
public List<CarsEntity> checkByLikeDisplacement(String name) {return this.carsDAO.queryLikeByDisplacement(name);}
/** check * */
public List<CarsEntity> checkByLikeSpecial(String name) {return this.carsDAO.queryLikeBySpecial(name);}
/** check * */
public List<CarsEntity> checkByLikeRecommend(String name) {return this.carsDAO.queryLikeByRecommend(name);}
/** check * */
public List<CarsEntity> checkByLikeContents(String name) {return this.carsDAO.queryLikeByContents(name);}
/** checkId * */
public CarsEntity checkId(String id) {return this.carsDAO.queryById(id);}
}

