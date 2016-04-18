package com.base.common.util;

public class Const {

	public interface Session {
		public static final String ADMIN_USER_NAME = "ses_admin_username";
		public static final String ADMIN_USER_TYPE = "ses_admin_usertype";
		public static final String FRONT_USER_NAME = "ses_user_username";
		public static final String FRONT_USER_TYPE = "ses_user_usertype";
	}

	public interface Pages {
		public static final String MAPPING_URL = "query";
		public static final String QUERY_JSP = "queryJSP";
		public static final String QUERY_DO = "queryDO";
		public static final String SUCCESS = "success";
		public static final String SUCCESS_CLOSE = "successClose";
	}

	public interface Notification {
		public static final String SUCCESS = "pageNotificationSuccess";
		public static final String ERROR = "pageNotificationError";
		public static final String ATTENTION ="pageNotificationAttention";
		public static final String INFO = "pageNotificationInfo";
		public static final String NOTICE = "pageNotificationNotice";
	}
	
	public interface Action {
		public static final String PAGE_REUSLT = "result";
		public static final String PAGINATION_INFO = "pageInfo";
	}
	
	public interface FileContentType {
		public static final String EXCEL = "application/vnd.ms-excel";
	}
}
