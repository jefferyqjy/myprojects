package com.util;

public enum InterestEnum {
		reading("����"), singsing("����"), dancing("����"), drawing("����");
		
		private String info;
		
		public String getInfo() {
			return info;
		}
		
		InterestEnum(String info) {
			this.info = info;
		}
}
