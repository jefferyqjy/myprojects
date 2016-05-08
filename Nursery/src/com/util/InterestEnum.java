package com.util;

public enum InterestEnum {
		reading("¶ÁÊé"), singsing("³ª¸è"), dancing("ÌøÎè"), drawing("»­»­");
		
		private String info;
		
		public String getInfo() {
			return info;
		}
		
		InterestEnum(String info) {
			this.info = info;
		}
}
