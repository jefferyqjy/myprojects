package com.util;

public class PageControl {

  private int curpage;      //当前页数
  private int nextpage;     //下一页
  private int start;		 // 从第行页开始
  private int end;          //到第几行结束
  private int totalpage;    //总共页数
  private int totalnum;     //全部记录行数
  private int prepage;      //前一页

  private int temp_curpage;
  private int temp_start;
  private int temp_end;
  private int temp_totalnum;
  private int temp_totalpage;
  /**
   * 初始化当前页分页相关数据
   * @param curpage 当前页码
   * @param totalnum 总共记录数
   */
  public void init(int curpage,int totalnum,int int_num){
    temp_totalnum=totalnum;
    if(curpage>1){
      if(curpage>(int)Math.ceil((double)totalnum/(double)int_num)){	//判断当前页码是否大于最大页码
        temp_curpage=(int)Math.ceil((double)totalnum/(double)int_num);
      }
      else{
        temp_curpage=curpage;
      }
    }
    else{
      temp_curpage=1;
    }
    temp_start = (temp_curpage - 1) * int_num+1;	//获得当前页的第一条记录行号
    temp_end = temp_curpage * int_num;				//获得当前页的最后一条记录的行号
    if (temp_end > temp_totalnum) {					//判断当前页的最后一条记录是否大于记录总数，如果大于就把最后一条记录的行号设置为实际记录的最后一条
      temp_end=temp_totalnum;
    }
    //public static double ceil(double a)返回最小的（最接近负无穷大）double 值，该值大于或等于参数，并且等于某个整数。
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
