package com.business;
import java.util.List;
import com.dao.ArticleDAO;
import com.entity.ArticleEntity;
public class ArticleBusiness {
/** Spring IOC DAO 注入 Business * */
private ArticleDAO articleDAO;
public ArticleDAO getArticleDAO() { return articleDAO; }
public void setArticleDAO(ArticleDAO articleDAO) { this.articleDAO = articleDAO; }
/** save * */
public void save(ArticleEntity article) {this.articleDAO.save(article);}
/** update * */
public void update(ArticleEntity article) {this.articleDAO.update(article);}
/** delete * */
public void delete(ArticleEntity article) {this.articleDAO.delete(article);}
/** show * */
public List<ArticleEntity> show() {return articleDAO.show();}
/** check * */
public List<ArticleEntity> checkTitle(String name) {return this.articleDAO.queryByTitle(name);}
/** check * */
public List<ArticleEntity> checkContents(String name) {return this.articleDAO.queryByContents(name);}
/** check * */
public List<ArticleEntity> checkAddtime(String name) {return this.articleDAO.queryByAddtime(name);}
/** check * */
public List<ArticleEntity> checkByLikeTitle(String name) {return this.articleDAO.queryLikeByTitle(name);}
/** check * */
public List<ArticleEntity> checkByLikeContents(String name) {return this.articleDAO.queryLikeByContents(name);}
/** check * */
public List<ArticleEntity> checkByLikeAddtime(String name) {return this.articleDAO.queryLikeByAddtime(name);}
/** checkId * */
public ArticleEntity checkId(String id) {return this.articleDAO.queryById(id);}
}

