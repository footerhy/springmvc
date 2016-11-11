package com.tools.fileupload.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tools.jdbc.util.JdbcUtils;

/**
 * 提供文件上传支持
 * 
 * @author Administrator
 * 
 */
public class UploadDomain {
	/**
	 * 将上传的文件流放入到数据库中
	 */
	public void insert(InputStream in, String fileName, String type,
			String describe) throws Exception {// 向数据库中写入图片
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("describe : " + describe);
		try {
			// 2.建立连接 www.2cto.com
			conn = JdbcUtils.getConnection();
			// 3.创建语句
			String sql = "insert into fileupload(file,filename,type,des) values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setBlob(1, in);
			ps.setString(2, fileName);
			ps.setString(3, type);
			ps.setString(4, describe);
			// 4.执行语句
//			ps.executeUpdate();
			ps.execute();

			in.close();

		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
