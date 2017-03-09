package com.business;
import java.util.List;
import com.dao.OrdersDAO;
import com.entity.OrdersEntity;
public class OrdersBusiness {
/** Spring IOC DAO 注入 Business * */
private OrdersDAO ordersDAO;
public OrdersDAO getOrdersDAO() { return ordersDAO; }
public void setOrdersDAO(OrdersDAO ordersDAO) { this.ordersDAO = ordersDAO; }
/** save * */
public void save(OrdersEntity orders) {this.ordersDAO.save(orders);}
/** update * */
public void update(OrdersEntity orders) {this.ordersDAO.update(orders);}
/** delete * */
public void delete(OrdersEntity orders) {this.ordersDAO.delete(orders);}
/** show * */
public List<OrdersEntity> show() {return ordersDAO.show();}
/** check * */
public List<OrdersEntity> checkOrdercode(String name) {return this.ordersDAO.queryByOrdercode(name);}
/** check * */
public List<OrdersEntity> checkUsersid(String name) {return this.ordersDAO.queryByUsersid(name);}
/** check * */
public List<OrdersEntity> checkCarsid(String name) {return this.ordersDAO.queryByCarsid(name);}
/** check * */
public List<OrdersEntity> checkThestart(String name) {return this.ordersDAO.queryByThestart(name);}
/** check * */
public List<OrdersEntity> checkTheend(String name) {return this.ordersDAO.queryByTheend(name);}
/** check * */
public List<OrdersEntity> checkPlace(String name) {return this.ordersDAO.queryByPlace(name);}
/** check * */
public List<OrdersEntity> checkAddress(String name) {return this.ordersDAO.queryByAddress(name);}
/** check * */
public List<OrdersEntity> checkAddtime(String name) {return this.ordersDAO.queryByAddtime(name);}
/** check * */
public List<OrdersEntity> checkStatus(String name) {return this.ordersDAO.queryByStatus(name);}
/** check * */
public List<OrdersEntity> checkByLikeOrdercode(String name) {return this.ordersDAO.queryLikeByOrdercode(name);}
/** check * */
public List<OrdersEntity> checkByLikeUsersid(String name) {return this.ordersDAO.queryLikeByUsersid(name);}
/** check * */
public List<OrdersEntity> checkByLikeCarsid(String name) {return this.ordersDAO.queryLikeByCarsid(name);}
/** check * */
public List<OrdersEntity> checkByLikeThestart(String name) {return this.ordersDAO.queryLikeByThestart(name);}
/** check * */
public List<OrdersEntity> checkByLikeTheend(String name) {return this.ordersDAO.queryLikeByTheend(name);}
/** check * */
public List<OrdersEntity> checkByLikePlace(String name) {return this.ordersDAO.queryLikeByPlace(name);}
/** check * */
public List<OrdersEntity> checkByLikeAddress(String name) {return this.ordersDAO.queryLikeByAddress(name);}
/** check * */
public List<OrdersEntity> checkByLikeAddtime(String name) {return this.ordersDAO.queryLikeByAddtime(name);}
/** check * */
public List<OrdersEntity> checkByLikeStatus(String name) {return this.ordersDAO.queryLikeByStatus(name);}
/** checkId * */
public OrdersEntity checkId(String id) {return this.ordersDAO.queryById(id);}
}

