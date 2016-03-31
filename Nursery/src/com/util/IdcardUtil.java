package com.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * ����֤��֤�Ĺ��ߣ�֧��5λ��18λʡ��֤��<br/>
 * ����֤����ṹ��
 * <ol>
 * <li>17λ���ֺ�1λУ���룺6λ��ַ�����֣�8λ�������֣�3λ����ʱ��˳��ţ�1λУ���롣</li>
 * <li>��ַ�루ǰ6λ������ʾ����ס���������أ��С��������������������룬��GB/T2260�Ĺ涨ִ�С�</li>
 * <li>���������룬������λ ��ʮ��λ������ʾ�����������ꡢ�¡��գ���GB��GB/T7408�Ĺ涨ִ�У��ꡢ�¡��մ���֮�䲻�÷ָ�����</li>
 * <li>˳���루��ʮ��λ��ʮ��λ������ʾ��ͬһ��ַ������ʾ������Χ�ڣ���ͬ�ꡢͬ�¡�ͬ�ճ������˱ඩ��˳��ţ�
 * ˳�����������������ԣ�ż�������Ů�ԡ�</li>
 * <li>У���루��ʮ��λ������<br/>
 * <ul>
 * <li>ʮ��λ���ֱ������Ȩ��͹�ʽ s = sum(Ai*Wi), i = 0,,16���ȶ�ǰ17λ���ֵ�Ȩ��ͣ�
 *  Ai:��ʾ��iλ���ϵ�����֤��������ֵ.Wi:��ʾ��iλ���ϵļ�Ȩ��.Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2��</li>
 *<li>����ģ Y = mod(S, 11)</li>
 *<li>ͨ��ģ�õ���Ӧ��У���� Y: 0 1 2 3 4 5 6 7 8 9 10 У����: 1 0 X 9 8 7 6 5 4 3 2</li>
 *</ul>
 *</li>
 *</ol>
 *
 *@author cxy
 *@since 2011-1-7
 *@see {@link http://www.blogjava.net/zeroline/archive/2011/01/03/342227.html}
 */
public class IdcardUtil {
	final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();
	static {
		zoneNum.put(11, "����");
		zoneNum.put(12, "���");
		zoneNum.put(13, "�ӱ�");
		zoneNum.put(14, "ɽ��");
		zoneNum.put(15, "���ɹ�");
		zoneNum.put(21, "����");
		zoneNum.put(22, "����");
		zoneNum.put(23, "������");
		zoneNum.put(31, "�Ϻ�");
		zoneNum.put(32, "����");
		zoneNum.put(33, "�㽭");
		zoneNum.put(34, "����");
		zoneNum.put(35, "����");
		zoneNum.put(36, "����");
		zoneNum.put(37, "ɽ��");
		zoneNum.put(41, "����");
		zoneNum.put(42, "����");
		zoneNum.put(43, "����");
		zoneNum.put(44, "�㶫");
		zoneNum.put(45, "����");
		zoneNum.put(46, "����");
		zoneNum.put(50, "����");
		zoneNum.put(51, "�Ĵ�");
		zoneNum.put(52, "����");
		zoneNum.put(53, "����");
		zoneNum.put(54, "����");
		zoneNum.put(61, "����");
		zoneNum.put(62, "����");
		zoneNum.put(63, "�ຣ");
		zoneNum.put(64, "�½�");
		zoneNum.put(71, "̨��");
		zoneNum.put(81, "���");
		zoneNum.put(82, "����");
		zoneNum.put(91, "���");
	}

	final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
	final static int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
		5, 8, 4, 2};

	/**
	 *
	 * ����֤��֤
	 *
	 *@param s
	 *      ��������
	 *@return �Ƿ���Ч null��"" ����false
	 */
	public static boolean isIdcard(String s){
		if(s == null || (s.length() != 15 && s.length() != 18))
			return false;
		final char[] cs = s.toUpperCase().toCharArray();
		//У��λ��
		int power = 0;
		for(int i=0; i<cs.length; i++){
			if(i==cs.length-1 && cs[i] == 'X')
				break;//���һλ���� ��X��x
			if(cs[i]<'0' || cs[i]>'9')
				return false;
			if(i < cs.length -1){
				power += (cs[i] - '0') * POWER_LIST[i];
			}
		}

		//У����λ��
		if(!zoneNum.containsKey(Integer.valueOf(s.substring(0,2)))){
			return false;
		}

		//У�����
		String year = s.length() == 15 ? "19" + s.substring(6,8) :s
				.substring(6, 10);
		final int iyear = Integer.parseInt(year);
		if(iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
			return false;//1900���PASS�����������PASS

		//У���·�
		String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10,12);
		final int imonth = Integer.parseInt(month);
		if(imonth <1 || imonth >12){
			return false;
		}

		//У������

		String day = s.length() ==15 ? s.substring(10, 12) : s.substring(12, 14);
		final int iday = Integer.parseInt(day);
		if(iday < 1 || iday > 31)
			return false;

		//У��һ���Ϸ���������
		if(!validate(iyear, imonth, iday))
			return false;

		//У��"У����"
		if(s.length() == 15)
			return true;
		return cs[cs.length -1 ] == PARITYBIT[power % 11];
	}

	static boolean validate(int year, int imonth, int iday){
		//���翼�����£���С�µ�
		return true;
	}

	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			final String s = "35068119860212101"+i;
			System.out.println(s+" --> "+isIdcard(s));
		}
	}
}