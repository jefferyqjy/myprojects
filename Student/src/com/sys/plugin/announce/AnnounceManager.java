package com.sys.plugin.announce;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sys.common.IBaseDAO;
import com.sys.web.fenye.util.DefaultQueryCondition;
import com.sys.web.fenye.util.Page;

	@Service
	public class AnnounceManager{
		@Resource private IBaseDAO announceDAOImpl;

		public void insert(Announce pro) throws Exception {
			try {
				this.announceDAOImpl.add(pro);
			} catch(Exception e) {
				throw new Exception("添加失败");
			}
		}

		public List<Announce> queryAll() {
			return this.announceDAOImpl.getAll();
		}
		
		public List<Announce> queryByHQL(String hql) {
			return this.announceDAOImpl.getViaHql(hql);
		}

		public void deleteViaId(Integer id) {
			this.announceDAOImpl.delete(id);
		}

		public void update(Announce pro) {
			this.announceDAOImpl.update(pro);
		}
		
		public Announce queryById(Integer id) {
			return (Announce)this.announceDAOImpl.getById(id);
		}

		public Page<Announce> getRecords(DefaultQueryCondition condition) {
			return this.announceDAOImpl.getRecord(condition);
		}

	}