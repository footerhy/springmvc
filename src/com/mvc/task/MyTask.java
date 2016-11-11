package com.mvc.task;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {
    @Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行
    public void taskCycle(){
//        System.out.println("无主题(www.wuzhuti.cn) <span style=\"color: #000000;\">专注于前端开发技术和<span id=\"2_nwp\" style=\"width: auto; height: auto; float: none;\"><a id=\"2_nwl\" href=\"http://cpro.baidu.com/cpro/ui/uijs.php?c=news&cf=1001&ch=0&di=128&fv=11&jk=e25d9166cd6111e7&k=%B3%CC%D0%F2%BF%AA%B7%A2&k0=%B3%CC%D0%F2%BF%AA%B7%A2&kdi0=0&luki=5&n=10&p=baidu&q=06003100_cpr&rb=0&rs=1&seller_id=1&sid=e71161cd66915de2&ssp2=1&stid=0&t=tpclicked3_hc&tu=u1948625&u=http%3A%2F%2Fwuzhuti%2Ecn%2F850%2Ehtml&urlid=0\" target=\"_blank\" mpid=\"2\" style=\"text-decoration: none;\"><span style=\"color:#0000ff;font-size:12px;width:auto;height:auto;float:none;\">程序开发</span></a></span>研究的技术博客</span>");
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
}
