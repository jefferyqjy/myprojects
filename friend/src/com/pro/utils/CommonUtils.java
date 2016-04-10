/**
 *
 */
package com.pro.utils;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pro.constants.ErrorCodeConst;
import com.pro.security.MyUser;

/**
 * @author sezhao
 * 
 */
public class CommonUtils {

	public static boolean isEmptyList(List<?> list) {
		return list == null || list.size() == 0;
	}

	public static String getDetailMsg(String arg, String[] parameters) {
		if (arg == null || parameters == null || parameters.length < 1)
			return arg;

		String prefix = ErrorCodeConst.GHS_EXCEPTION_ERROR_REPLACE_PREFIX;
		String suffix = ErrorCodeConst.GHS_EXCEPTION_ERROR_REPLACE_SUFFIX;
		String replace = null;
		for (int i = 0, len = parameters.length; i < len; i++) {
			replace = prefix + i + suffix;
			arg = arg.replaceAll(replace, parameters[i]);
		}
		return arg;
	}

	public static String upperCaseFirst(String input) {
		if (input != null) {
			return input.substring(0, 1).toUpperCase() + input.substring(1);
		} else {
			return null;
		}

	}

	public static String lowerCaseFirst(String input) {
		if (input != null) {
			return input.substring(0, 1).toLowerCase() + input.substring(1);
		} else {
			return null;
		}

	}

	public static boolean isNullorBlank(String str) {
		if (str == null || "".equals(str.trim()))
			return true;
		else
			return false;
	}

	public static final String getCurrentUserName() throws AuthenticationException {
		String userName = "ANONYMOUS";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
			MyUser userDetail = (MyUser) token.getPrincipal();
			userName = userDetail.getUsername();
		}
		return userName;
	}
}
