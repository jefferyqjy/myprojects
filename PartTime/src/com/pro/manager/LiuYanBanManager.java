package com.pro.manager;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.base.sys.dao.IBaseDAO;
import com.base.pagination.util.DefaultQueryCondition;
import com.base.pagination.util.Page;
import com.pro.entity.LiuYanBan;

	@Service
	public class LiuYanBanManager{
		@Resource private IBaseDAO liuYanBanDAOImpl;

		public void add(LiuYanBan entity) throws Exception {
			try {
				this.liuYanBanDAOImpl.add(entity);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public boolean isExist(String key, String value) {
			List list = this.liuYanBanDAOImpl.getViaHql("from LiuYanBan where "+key+"='"+value+"'");
			return (list != null && list.size() > 0) ? true : false;
		}

		public List<LiuYanBan> queryAll() {
			return this.liuYanBanDAOImpl.getAll();
		}

		public void deleteViaId(Integer id) {
			this.liuYanBanDAOImpl.delete(id);
		}

		public List queryByHql(String hql) {
			return this.liuYanBanDAOImpl.getViaHql(hql);
		}

		public LiuYanBan queryById(Integer id) {
			return (LiuYanBan)this.liuYanBanDAOImpl.getById(id);
		}

		public LiuYanBan querySingleRecordViaKey(String key, String value) {
			List list = this.liuYanBanDAOImpl.getViaHql("from LiuYanBan where "+key+"='"+value+"'");
		if(list != null && list.size() > 0){
			return (LiuYanBan)this.liuYanBanDAOImpl.getViaHql("from LiuYanBan where "+key+"='"+value+"'").get(0);
		}else{
		return null;
		}
		}
		public void update(LiuYanBan entity) {
			this.liuYanBanDAOImpl.update(entity);
		}

		public Page<LiuYanBan> getRecords(DefaultQueryCondition condition) {
			return this.liuYanBanDAOImpl.getRecord(condition);
		}

	}