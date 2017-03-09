package com.business;
import java.util.List;
import com.dao.TopicDAO;
import com.entity.TopicEntity;
public class TopicBusiness {
/** Spring IOC DAO 注入 Business * */
private TopicDAO topicDAO;
public TopicDAO getTopicDAO() { return topicDAO; }
public void setTopicDAO(TopicDAO topicDAO) { this.topicDAO = topicDAO; }
/** save * */
public void save(TopicEntity topic) {this.topicDAO.save(topic);}
/** update * */
public void update(TopicEntity topic) {this.topicDAO.update(topic);}
/** delete * */
public void delete(TopicEntity topic) {this.topicDAO.delete(topic);}
/** show * */
public List<TopicEntity> show() {return topicDAO.show();}
/** check * */
public List<TopicEntity> checkUsersid(String name) {return this.topicDAO.queryByUsersid(name);}
/** check * */
public List<TopicEntity> checkCarsid(String name) {return this.topicDAO.queryByCarsid(name);}
/** check * */
public List<TopicEntity> checkNum(String name) {return this.topicDAO.queryByNum(name);}
/** check * */
public List<TopicEntity> checkContents(String name) {return this.topicDAO.queryByContents(name);}
/** check * */
public List<TopicEntity> checkAddtime(String name) {return this.topicDAO.queryByAddtime(name);}
/** check * */
public List<TopicEntity> checkByLikeUsersid(String name) {return this.topicDAO.queryLikeByUsersid(name);}
/** check * */
public List<TopicEntity> checkByLikeCarsid(String name) {return this.topicDAO.queryLikeByCarsid(name);}
/** check * */
public List<TopicEntity> checkByLikeNum(String name) {return this.topicDAO.queryLikeByNum(name);}
/** check * */
public List<TopicEntity> checkByLikeContents(String name) {return this.topicDAO.queryLikeByContents(name);}
/** check * */
public List<TopicEntity> checkByLikeAddtime(String name) {return this.topicDAO.queryLikeByAddtime(name);}
/** checkId * */
public TopicEntity checkId(String id) {return this.topicDAO.queryById(id);}
}

