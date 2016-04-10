package com.pro.security;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class MyAuthenticationProvider implements AuthenticationProvider {

	private static final String QUERY_SQL_VALIDATE = "SELECT COUNT(1) FROM COM_PRO_USER WHERE LOGINNAME=? AND PASSWORD=?";
	private static final String QUERY_SQL_GRANT = "SELECT A.USERNAME AS USER_NAME, B.NAME AS ROLE_NAME FROM COM_PRO_USER A, COM_PRO_ROLE B, COM_PRO_USER_ROLE C WHERE A.LOGINNAME=? AND A.ID = C.USER_ID AND B.ID = C.ROLE_ID";
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
				"Only UsernamePasswordAuthenticationToken is supported");

		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;

		String userName = userToken.getName();

		if (!StringUtils.hasLength(userName)) {
			throw new BadCredentialsException("Empty Username");
		}

		String password = (String) authentication.getCredentials();
		if (this.jdbcTemplate.queryForInt(QUERY_SQL_VALIDATE, userName, password) < 1) {
			throw new BadCredentialsException("Error name and password");
		}
		MyUser userDetail = new MyUser();
		try {
			List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(QUERY_SQL_GRANT, userName);
			userDetail.setUserId(userName);
			if (rows != null && rows.size() > 0) {
				userDetail.setEnabled(true);
				userDetail.setUsername((String) rows.get(0).get("USER_NAME"));
				for (Map<String, Object> row : rows) {
					userDetail.addAuthoritie(new MyGrantedAuthority((String) row.get("ROLE_NAME")));
				}
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException(userName);
		}
		UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetail, password,
				userDetail.getAuthorities());
		user.setDetails(userToken.getDetails());
		return user;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
