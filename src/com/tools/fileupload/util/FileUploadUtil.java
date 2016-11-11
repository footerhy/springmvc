package com.tools.fileupload.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil extends HttpServlet {

	/**
	 * 处理用户上传请求
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String describe = request.getParameter("describe");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		@SuppressWarnings("deprecation")
		String path = request.getRealPath("/upload");// 设置磁盘缓冲路径
		
		System.out.println("real path " + path);
		
		File file = new File(path);
		if(!file.exists()){
			file.mkdir();
		}

		
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024 * 1024);// 设置创建缓冲大小

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(-1);// 设置上传文件限制大小,-1无上限
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			
			System.out.println("upload list size is " + list.size());
			
			String va = null;
			for (FileItem item : list) {
				// String name = item.getFieldName();
				if (item.isFormField()) {// 判断是否是文件流
					va = item.getString("UTF-8");
//					 System.out.println(item.getName()+"="+va);
					// / request.setAttribute(name, value);
				} else {
					System.out.println("name is " + new String(item.getName().getBytes(),"utf-8"));
					String value = new String(item.getName().getBytes(),"utf-8");// 会将完整路径名传过来
					int start = value.lastIndexOf("\\");
					String fileName = value.substring(start + 1);
					// request.setAttribute(name, fileName);
					InputStream in1 = item.getInputStream();
					InputStream in2 = item.getInputStream();
					UploadDomain dao = new UploadDomain();
					// item.write(new File(realPath,fileName));
					int index = fileName.lastIndexOf(".");
					String realFileName = fileName.substring(0, index);
					String type = fileName.substring(index + 1);
					
					System.out.println("file name : " + value + ", file type : " + type);
					
					

					FileOutputStream fos = new FileOutputStream(path + "/" +realFileName + "." + type);

					byte[] buf = new byte[1024];

					int len = -1;

					while((len = in1.read(buf)) != -1){

						fos.write(buf, 0, len);

					}
					in1.close();
					fos.close();
					
					
					dao.insert(in2, realFileName, type, va);// 放入到数据库中
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
