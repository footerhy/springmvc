package com.mvc.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entity.beans.CashLog;
import com.tools.fileupload.util.FileUploadUtil;
import com.tools.jdbc.util.BaseDao;
import com.tools.xls.util.PoiKit;

@Controller
public class RestConstroller extends BaseController{
	
	public RestConstroller() {
	}

	@RequestMapping(value = "/login/{user}", method = RequestMethod.GET)
	public ModelAndView myMethod(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("user") String user,
			ModelMap modelMap) throws Exception {
		modelMap.put("loginUser", new String(user.getBytes("iso-8859-1"),"utf-8"));
		return new ModelAndView("/login/hello", modelMap);
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String registPost() {
		System.out.println("dddddd");
		return "/welcome";
	}
	
	@RequestMapping(value="/download/xls")
	public void downLoadXLS(HttpServletRequest request, HttpServletResponse response){
		String[] heades = { "支付流水号","币种"}; //excel标题
		String[] columns = { "cashno","payname"};//数据库列名,不写则导出SQL全部列
		String fileName="cash.xls";//下载文件名
		List<CashLog> list = new ArrayList<CashLog>();
		CashLog e = new CashLog();
		e.setCashno("CNO20150318000897");
		e.setPayname("CNY");
		list.add(e);
		PoiKit kit=new PoiKit(list);
		kit.sheetName("所有cash单");//excel的sheet名称
		kit.headers(heades);//设置显示名称
		kit.columns(columns);//设置列名
		kit.cellWidth(5000);//设置宽度
		renderExcel(request, response, kit,fileName);
	}
	@RequestMapping(value="/upload")
	public void uploadFile(HttpServletRequest request, HttpServletResponse response){
		FileUploadUtil load = new FileUploadUtil();
		try {
			load.doGet(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/download")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/x-msdownload;");
		
		String fileName = "新建文本文档.txt";
		
//		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
		
		String filePath = "C:\\Users\\Liufei\\Desktop\\";
		
		String fullPath = filePath + fileName;
		
		System.out.println(fullPath);
		
		long fileLength = new File(fullPath).length();  
		response.setHeader("Content-Length", String.valueOf(fileLength));  
		
		BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filePath + fileName)); 
			 bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];  
			int bytesRead;  
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            } 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
				try {
					if (bis != null)
						bis.close();
					if (bos != null)  
						bos.close();  
				} catch (IOException e) {
					e.printStackTrace();
				}  
        }  
		
		
	}
	
	@RequestMapping(value="/jdbctest")
	public void testJDBC(HttpServletRequest request, HttpServletResponse response){
		BaseDao dao = new BaseDao();
		dao.test();
	}
}
