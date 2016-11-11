package com.mvc.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tools.consts.utils.Consts;
import com.tools.xls.util.PoiKit;

public class BaseController {
	public void renderExcel(HttpServletRequest request,HttpServletResponse response,PoiKit kit,String fileName) {
		response.reset();
        response.setHeader("Content-disposition", "attachment; filename="+fileName);
        response.setContentType(Consts.CONTENT_TYPE);
        try {
		    kit.export().write( response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
