package com.util;

public class PageControl {

  private int curpage;      //��ǰҳ��
  private int nextpage;     //��һҳ
  private int start;		 // �ӵ���ҳ��ʼ
  private int end;          //���ڼ��н���
  private int totalpage;    //�ܹ�ҳ��
  private int totalnum;     //ȫ����¼����
  private int prepage;      //ǰһҳ

  private int temp_curpage;
  private int temp_start;
  private int temp_end;
  private int temp_totalnum;
  private int temp_totalpage;
  /**
   * ��ʼ����ǰҳ��ҳ�������
   * @param curpage ��ǰҳ��
   * @param totalnum �ܹ���¼��
   */
  public void init(int curpage,int totalnum,int int_num){
    temp_totalnum=totalnum;
    if(curpage>1){
      if(curpage>(int)Math.ceil((double)totalnum/(double)int_num)){	//�жϵ�ǰҳ���Ƿ�������ҳ��
        temp_curpage=(int)Math.ceil((double)totalnum/(double)int_num);
      }
      else{
        temp_curpage=curpage;
      }
    }
    else{
      temp_curpage=1;
    }
    temp_start = (temp_curpage - 1) * int_num+1;	//��õ�ǰҳ�ĵ�һ����¼�к�
    temp_end = temp_curpage * int_num;				//��õ�ǰҳ�����һ����¼���к�
    if (temp_end > temp_totalnum) {					//�жϵ�ǰҳ�����һ����¼�Ƿ���ڼ�¼������������ھͰ����һ����¼���к�����Ϊʵ�ʼ�¼�����һ��
      temp_end=temp_totalnum;
    }
    //public static double ceil(double a)������С�ģ���ӽ��������double ֵ����ֵ���ڻ���ڲ��������ҵ���ĳ��������
    temp_totalpage=(int)Math.ceil((double)totalnum/(double)int_num);

    setCurpage();
    setNextpage();
    setStart();
    setEnd();
    setTotalpage();
    setTotalnum();
    setPrepage();

  }

  public void setCurpage() {
    this.curpage = temp_curpage;
  }

  public void setNextpage() {
    if(temp_curpage+1>temp_totalpage){
      nextpage=curpage;
    }
    else{
      nextpage=temp_curpage+1;
    }
  }

  public void setStart() {
    this.start = temp_start;
  }

  public void setEnd() {
    this.end = temp_end;
  }

  public void setTotalpage() {
    this.totalpage = temp_totalpage;
  }

  public void setTotalnum() {
    this.totalnum = temp_totalnum;
  }

  public void setPrepage() {
    if(temp_curpage-1<1){
      prepage=temp_curpage;
    }
    else{
      prepage=temp_curpage-1;
    }
  }


  public int getCurpage() {
    return curpage;
  }
  public int getNextpage() {
    return nextpage;
  }

  public int getStart() {
    return start;
  }

  public int getEnd() {
    return end;
  }

  public int getTotalpage() {
    return totalpage;
  }

  public int getTotalnum() {
    return totalnum;
  }

  public int getPrepage() {
    return prepage;
  }

}
