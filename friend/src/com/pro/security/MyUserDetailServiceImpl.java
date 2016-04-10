package com.pro.security;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailServiceImpl implements UserDetailsService {

	private static final String QUERY_SQL = "SELECT A.USERNAME AS USER_NAME, B.NAME AS ROLE_NAME FROM COM_PRO_USER A, COM_PRO_ROLE B, COM_PRO_USER_ROLE C WHERE A.LOGINNAME=? AND A.ID = C.USER_ID AND B.ID = C.ROLE_ID";
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		MyUser user = new MyUser();
		try {
			List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(QUERY_SQL, userName);
			user.setUserId(userName);
			if (rows != null && rows.size() > 0) {
				user.setEnabled(true);
				user.setUsername((String) rows.get(0).get("USER_NAME"));
				for (Map<String, Object> row : rows) {
					user.addAuthoritie(new MyGrantedAuthority((String) row.get("ROLE_NAME")));
				}
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException(userName);
		}
		return user;
	}

}
