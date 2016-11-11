<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.tools.print.util.PrintFunction" %>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>打印控件示例</title>

<script language="javascript">
  function PreviewDeposit()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=DepositPreview"
    document.FrmWebPrint.submit();
  }

  function DesignDeposit()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=DepositDesign"
    document.FrmWebPrint.submit();
  }

  function DesignDepositPost()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=DepositDesignPost"
    document.FrmWebPrint.submit();
  }

  function PrintDeposit()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=DepositPrint"
    document.FrmWebPrint.submit();
  }

  function PreviewMaster()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=MasterPreview"
    document.FrmWebPrint.submit();
  }

  function DesignMaster()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=MasterDesign"
    document.FrmWebPrint.submit();
  }
  
  function PreviewPicture()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=PicturePreview"
    document.FrmWebPrint.submit();
  }

  function DesignPicture()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=PictureDesign"
    document.FrmWebPrint.submit();
  }
  
  function PreviewDepositD()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=DepositPreviewD"
    document.FrmWebPrint.submit();
  }


  function PrintDepositD()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=DepositPrintD"
    document.FrmWebPrint.submit();
  }

  function PreviewMasterD()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=MasterPreviewD"
    document.FrmWebPrint.submit();
  }

  function PreviewPictureD()
  {
    document.FrmWebPrint.action = "print.jsp?PrintType=PicturePreviewD"
    document.FrmWebPrint.submit();
  }
  
</script>

</head>

<body>
<jsp:useBean id="pf" scope= "page" class="com.tools.print.util.PrintFunction" />

<%
  String PrintType = request.getParameter("PrintType");
  String ReportPath = "D:\\footerhy\\Android_KEPLER\\springmvc\\WebContent\\PrintSource\\Frp\\";
  String ImagePath = "D:\\footerhy\\Android_KEPLER\\springmvc\\WebContent\\PrintSource\\Image\\";
  
  //WebPrintmysql.PrintFunctionmysql为JavaBean，存放在WEB-INF文件夹，该文件夹要复制到Tomcat的webapps\\ROOT文件夹
  
  if( PrintType == null )
  {
    //out.println("null");
  }
  else if( PrintType.equals("DepositPreview") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from CashLog");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "DepositAmt.fr3");
    String ParaName = "ShopName`~PrintDepositAdd`~PrintPaperNo`~Title";  //`~为各参数的分隔符
   	System.out.print(PrintType + " 打印值 ：" + PrintValue);
    String ParaValue = "测试酒店"
               + "`~说明：本单据为贵客押金收取凭证，盖章有效。退房时请出示，遗失者自负，请妥善保存。退房时间为12:00时，延时退房18:00时以前按半天房费收取，18:00时以后算全天房价。押金单有效期为一个月，过期作废。       贵重物品请交前台寄存，未寄存丢失自负。      谢谢！"
               + "`~身份证：4325011980639512"
               + "`~押金单";
   
    //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + " ObjPrintMange.ShowReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
	System.out.println("result : " + ScriptStr);
    out.println(ScriptStr);
  }
  else if( PrintType.equals("DepositDesign") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from CashLog");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "DepositAmt.fr3");
    String ParaName = "ShopName`~PrintDepositAdd`~PrintPaperNo`~Title";  //`~为各参数的分隔符
    String ParaValue = "测试酒店"
               + "`~说明：本单据为贵客押金收取凭证，盖章有效。退房时请出示，遗失者自负，请妥善保存。退房时间为12:00时，延时退房18:00时以前按半天房费收取，18:00时以后算全天房价。押金单有效期为一个月，过期作废。       贵重物品请交前台寄存，未寄存丢失自负。      谢谢！"
               + "`~身份证：4325011980639512"
               + "`~押金单";
   
    //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + " ObjPrintMange.ReportFileName='DepositAmt.fr3'; "
          + " ObjPrintMange.DesignReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
  else if( PrintType.equals("DepositDesignPost") )
  { //票据编辑，Post方式保存
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from CashLog");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "DepositAmt.fr3");
    String ParaName = "ShopName`~PrintDepositAdd`~PrintPaperNo`~Title";  //`~为各参数的分隔符
    String ParaValue = "测试酒店"
               + "`~说明：本单据为贵客押金收取凭证，盖章有效。退房时请出示，遗失者自负，请妥善保存。退房时间为12:00时，延时退房18:00时以前按半天房费收取，18:00时以后算全天房价。押金单有效期为一个月，过期作废。       贵重物品请交前台寄存，未寄存丢失自负。      谢谢！"
               + "`~身份证：4325011980639512"
               + "`~押金单";
   
    //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + " ObjPrintMange.ReportFileName='DepositAmt.fr3'; "
          + " ObjPrintMange.PostURL='http://www.xinyuerj.com/ASPPost/Show.asp?FileName=DepsitAmt.fr3'; "
          + " ObjPrintMange.DesignReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
  else if( PrintType.equals("DepositPrint") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from CashLog");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "DepositAmt.fr3");
    String ParaName = "ShopName`~PrintDepositAdd`~PrintPaperNo`~Title";  //`~为各参数的分隔符
    String ParaValue = "测试酒店"
               + "`~说明：本单据为贵客押金收取凭证，盖章有效。退房时请出示，遗失者自负，请妥善保存。退房时间为12:00时，延时退房18:00时以前按半天房费收取，18:00时以后算全天房价。押金单有效期为一个月，过期作废。       贵重物品请交前台寄存，未寄存丢失自负。      谢谢！"
               + "`~身份证：4325011980639512"
               + "`~押金单";
               
   //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码   
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + " ObjPrintMange.PrintReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; "
          + " window.location='./print.jsp'; } </script>";
    out.println(ScriptStr);
  }
  else if( PrintType.equals("MasterPreview") )
  {
    pf.OpenDataConnection();
    String PrintValue1 = pf.TableToXml("Select * From InInfo");    
    String PrintValue2 = pf.TableToXml("Select * From InMaterial");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "InInfo.fr3");
    String ParaName = "ShopName";  //`~为各参数的分隔符
    String ParaValue = "测试酒店";
   
    //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码
    //ObjPrintMange.MasterOptions 主从关系，参数：主数据集序号，关联字段名，字段是否为数值型
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + " ObjPrintMange.MasterOptions(1, 'InNo', 0 );"
          + " ObjPrintMange.ShowReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue1 + "', '"
          + PrintValue2 + "', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
   else if( PrintType.equals("MasterDesign") )
  {
    pf.OpenDataConnection();
    String PrintValue1 = pf.TableToXml("Select * From InInfo");    
    String PrintValue2 = pf.TableToXml("Select * From InMaterial");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "InInfo.fr3");
    String ParaName = "ShopName";  //`~为各参数的分隔符
    String ParaValue = "测试酒店";
   
    //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码
    //ObjPrintMange.MasterOptions 主从关系，参数：主数据集序号，关联字段名，字段是否为数值型
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + " ObjPrintMange.MasterOptions(1, 'InNo', 0 );"
          + " ObjPrintMange.ReportFileName='InInfo.fr3'; "
          + " ObjPrintMange.DesignReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue1 + "', '"
          + PrintValue2 + "', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
 else if( PrintType.equals("PicturePreview") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from MaterialInfo");    
    String PictureStr = pf.TablePictureToStr("select * from MaterialInfo", "PictureFile", ImagePath);
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "Material.fr3");
    String ParaName = "ShopName";  //`~为各参数的分隔符
    String ParaValue = "测试酒店";
  
    String PictureFile = "Title.bmp";
    String PictureValue = pf.FileToStr(ImagePath + PictureFile);
    PictureStr = PictureStr + "ObjPrintMange.SavePictureFile('" + PictureFile + "' , '" + PictureValue + "' );";    //��釜�剧�
    
    //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码
    //增加单个图片打印，参数：报表中图片对象名，数据表的字段（为0），图片文件名，数据集序号，是否清除旧数据（第1次调用时为1，其它时候则为0）
    //增加数据表中的图片打印，参数：报表中图片对象名，数据表的字段（为1），图片文件名所对应的字段名，数据集序号，是否清除旧数据（第1次调用时为1，其它时候则为0）   
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + PictureStr
          + " ObjPrintMange.AddPicturePrint('PicTitle', 0, 'Title.bmp','1', 1 );"
          + " ObjPrintMange.AddPicturePrint('PicMaterial', 1, 'PictureFile','1', 0 );"
          + " ObjPrintMange.ShowReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
 else if( PrintType.equals("PictureDesign") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from MaterialInfo");    
    String PictureStr = pf.TablePictureToStr("select * from MaterialInfo", "PictureFile", ImagePath);
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "Material.fr3");
    String ParaName = "ShopName";  //`~为各参数的分隔符
    String ParaValue = "测试酒店";
  
    String PictureFile = "Title.bmp";
    String PictureValue = pf.FileToStr(ImagePath + PictureFile);
    PictureStr = PictureStr + "ObjPrintMange.SavePictureFile('" + PictureFile + "' , '" + PictureValue + "' );";    //��釜�剧�
    
    //ObjPrintMange.CheckReg 为注册函数，参数：公司名称，注册码
    //增加单个图片打印，参数：报表中图片对象名，数据表的字段（为0），图片文件名，数据集序号，是否清除旧数据（第1次调用时为1，其它时候则为0）
    //增加数据表中的图片打印，参数：报表中图片对象名，数据表的字段（为1），图片文件名所对应的字段名，数据集序号，是否清除旧数据（第1次调用时为1，其它时候则为0）   
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckReg('公司名称', '3B8E5B998A3125EE89983EA940BB2AEE'); "
          + PictureStr
          + " ObjPrintMange.AddPicturePrint('PicTitle', 0, 'Title.bmp','1', 1 );"
          + " ObjPrintMange.AddPicturePrint('PicMaterial', 1, 'PictureFile','1', 0 );"
          + " ObjPrintMange.ReportFileName='Material.fr3'; "
          + " ObjPrintMange.DesignReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
  else if( PrintType.equals("DepositPreviewD") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from CashLog");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "DepositAmt.fr3");
    String ParaName = "ShopName`~PrintDepositAdd`~PrintPaperNo`~Title";  //`~为各参数的分隔符
    String ParaValue = "测试酒店"
               + "`~说明：本单据为贵客押金收取凭证，盖章有效。退房时请出示，遗失者自负，请妥善保存。退房时间为12:00时，延时退房18:00时以前按半天房费收取，18:00时以后算全天房价。押金单有效期为一个月，过期作废。       贵重物品请交前台寄存，未寄存丢失自负。      谢谢！"
               + "`~身份证：4325011980639512"
               + "`~押金单";
   
    //ObjPrintMange.CheckDomainName 为按服务器域名注册的函数，参数：域名，注册码
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckDomainName('localhost', '3B8E5B998A3125EE89983EA940BB2AEE'); "  
          + " ObjPrintMange.ShowReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
  else if( PrintType.equals("DepositPrintD") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from CashLog");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "DepositAmt.fr3");
    String ParaName = "ShopName`~PrintDepositAdd`~PrintPaperNo`~Title";  //`~为各参数的分隔符
    String ParaValue = "测试酒店"
               + "`~说明：本单据为贵客押金收取凭证，盖章有效。退房时请出示，遗失者自负，请妥善保存。退房时间为12:00时，延时退房18:00时以前按半天房费收取，18:00时以后算全天房价。押金单有效期为一个月，过期作废。       贵重物品请交前台寄存，未寄存丢失自负。      谢谢！"
               + "`~身份证：4325011980639512"
               + "`~押金单";
               
   //ObjPrintMange.CheckDomainName 为按服务器域名注册的函数，参数：域名，注册码   
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckDomainName('localhost', '3B8E5B998A3125EE89983EA940BB2AEE'); "  
          + " ObjPrintMange.PrintReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; "
          + " window.location='./print.jsp'; } </script>";
    out.println(ScriptStr);
  }
  else if( PrintType.equals("MasterPreviewD") )
  {
    pf.OpenDataConnection();
    String PrintValue1 = pf.TableToXml("Select * From InInfo");    
    String PrintValue2 = pf.TableToXml("Select * From InMaterial");    
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "InInfo.fr3");
    String ParaName = "ShopName";  //`~为各参数的分隔符
    String ParaValue = "测试酒店";
   
    //ObjPrintMange.CheckDomainName 为按服务器域名注册的函数，参数：域名，注册码
    //ObjPrintMange.MasterOptions 主从关系，参数：主数据集序号，关联字段名，字段是否为数值型
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckDomainName('localhost', '3B8E5B998A3125EE89983EA940BB2AEE'); "  
          + " ObjPrintMange.MasterOptions(1, 'InNo', 0 );"
          + " ObjPrintMange.ShowReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue1 + "', '"
          + PrintValue2 + "', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
 else if( PrintType.equals("PicturePreviewD") )
  {
    pf.OpenDataConnection();
    String PrintValue = pf.TableToXml("select  * from MaterialInfo");    
    String PictureStr = pf.TablePictureToStr("select * from MaterialInfo", "PictureFile", ImagePath);
    pf.CloseDataConnection(); 
    String FileValue = pf.FileToStr(ReportPath + "Material.fr3");
    String ParaName = "ShopName";  //`~为各参数的分隔符
    String ParaValue = "测试酒店";
  
    String PictureFile = "Title.bmp";
    String PictureValue = pf.FileToStr(ImagePath + PictureFile);
    PictureStr = PictureStr + "ObjPrintMange.SavePictureFile('" + PictureFile + "' , '" + PictureValue + "' );";    //��釜�剧�
    
    //ObjPrintMange.CheckDomainName 为按服务器域名注册的函数，参数：域名，注册码
    //增加单个图片打印，参数：报表中图片对象名，数据表的字段（为0），图片文件名，数据集序号，是否清除旧数据（第1次调用时为1，其它时候则为0）
    //增加数据表中的图片打印，参数：报表中图片对象名，数据表的字段（为1），图片文件名所对应的字段名，数据集序号，是否清除旧数据（第1次调用时为1，其它时候则为0）   
    String ScriptStr = "<script language='javascript'>window.onload = function() { try { var ObjPrintMange =  new ActiveXObject('WebPrint.WebPrintUnit'); } catch(e) { if( confirm('打印控件未安装，现在下载吗?') ) { window.location='./PrintSource/plug-in/PrintActivex.exe'; } return; } var OldVersion=ObjPrintMange.Version; NewVerion='5.0(2011-08-01)'; if(OldVersion < NewVerion) { ObjPrintMange = null; alert('打印控件需升级。请先进行下载，下载后关闭IE，然后安装升级版。'); window.location='./PrintSource/plug-in/PrintActivex.exe'; return;  } "
          + " ObjPrintMange.CheckDomainName('localhost', '3B8E5B998A3125EE89983EA940BB2AEE'); "  
          + PictureStr
          + " ObjPrintMange.AddPicturePrint('PicTitle', 0, 'Title.bmp','1', 1 );"
          + " ObjPrintMange.AddPicturePrint('PicMaterial', 1, 'PictureFile','1', 0 );"
          + " ObjPrintMange.ShowReport('"
          + FileValue  + "' , '"
          + ParaName + "', '"
          + ParaValue + "', '"
          + PrintValue + "', '', '', '', '', ''); "
          + " ObjPrintMange = null; } </script>";
    out.println(ScriptStr);
  }
  
  
%>

<form id="FrmWebPrint" name="FrmWebPrint" method="post" action="">
  <table width="473" height="245" border="0">
    <tr>
      <td width="121"><input name="DepositPreview" type="submit" id="DepositPreview" value="票据预览" onclick="PreviewDeposit();"  /></td>
      <td width="135"><input name="DepositDesign" type="submit" id="DepositDesign" value="票据报表编辑" onclick="DesignDeposit();"></td>
      <td width="203"><label>
        <input name="DepositPreviewD" type="submit" id="DepositPreviewD" value="票据预览（按域名注册）" onclick="PreviewDepositD();" />
      </label></td>
    </tr>
    <tr>
      <td><input name="DepositPrint" type="submit" id="DepositPrint" value="票据直接打印" onclick="PrintDeposit();" /></td>
      <td><input name="DepositDesignPost" type="submit" id="DepositDesignPost" value="票据报表编辑(Post方式保存)" onClick="DesignDepositPost();"></td>
      <td><input name="DepositPrintD" type="submit" id="DepositPrintD" value="票据直接打印（按域名注册）" onclick="PrintDepositD();" /></td>
    </tr>
    <tr>
      <td><input name="MasterPreview" type="submit" id="MasterPreview" value="主从报表预览" onclick="PreviewMaster();" /></td>
      <td><input name="MasterDesign" type="submit" id="MasterDesign" value="主从报表编辑" onclick="DesignMaster();" /></td>
      <td><input name="MasterPreviewD" type="submit" id="MasterPreviewD" value="主从报表预览（按域名注册）" onclick="PreviewMasterD();" /></td>
    </tr>
    <tr>
      <td><input name="PicturePreview" type="submit" id="PicturePreview" value="含图片报表预览" onclick="PreviewPicture();" /></td>
      <td><input name="PictureDesign" type="submit" id="PictureDesign" value="含图片报表编辑" onclick="DesignPicture();" /></td>
      <td><input name="PicturePreviewD" type="submit" id="PicturePreviewD" value="含图片报表预览（按域名注册）" onclick="PreviewPictureD();" /></td>
    </tr>
  </table>
  <p>&nbsp;</p>
</form>
</body>
</html>
