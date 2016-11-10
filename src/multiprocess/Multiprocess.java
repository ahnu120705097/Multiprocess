/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiprocess;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class Multiprocess{

    /**
     * @param args the command line arguments
     */
    private static int route=1;
    private static int coach=1;
    private static int seat=10;
    private static int station=4;
    public static void main(String[] args) {
      final TicketingDS tds=new TicketingDS(route,coach,seat,station);
      int i=20;
      while(i-->0){
      double chance=Math.random();
      if(chance>0.3&&chance<1)
      new Thread(new Runnable(){
          @Override
          public void run() {
            int r=random(1,route);
            int s1=random(1,station-1);
            int s2=random(s1+1,station);
            Ticket k=tds.buyTicket("hhhh",r-1,s1-1,s2-1);
            if(k!=null){
                System.out.println("成功订票:票的信息为:乘客姓名:"+k.passenger+" 车票编号:"+k.tid+" 列车车次:"+r+" 车厢编号:"+(k.coach+1)+" 座位号:"+(k.seat+1)+" 出发站编号:"+(k.departure+1)+" 终点站编号:"+(k.arrival+1));
            }
            else{
                System.out.println("购票失败,购票请求为:车次"+r+",始发站:"+s1+",终点站:"+s2);
            }
          }
      }).start();
       if(chance>0.1&&chance<0.3)
       new Thread(
            new Runnable(){
              @Override
              public void run() {
                 int r=random(1,route);
                 int s1=random(1,station-1);
                 int s2=random(s1+1,station);
                 int count= tds.inquiry(r-1,s1-1,s2-1);
                 if(count==-1){
                     System.out.println("无效的查询行为");
                 }else{
                 System.out.println("您查询的"+r+"次列车由"+s1+"站开往"+s2+"站还剩下"+count+"个席次");
                 }
              }
            } 
        ).start();
     }
    } 
    private static int random(int a,int b){
        if(a==b) return a;
        else{
           int x=b-a;
           return (int)Math.round(Math.random()*x)+a;                
        }
    }
}

  
