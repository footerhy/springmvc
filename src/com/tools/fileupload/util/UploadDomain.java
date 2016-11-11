package com.tools.fileupload.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tools.jdbc.util.JdbcUtils;

/**
 * �ṩ�ļ��ϴ�֧��
 * 
 * @author Administrator
 * 
 */
public class UploadDomain {
	/**
	 * ���ϴ����ļ������뵽���ݿ���
	 */
	public void insert(InputStream in, String fileName, String type,
			String describe) throws Exception {// �����ݿ���д��ͼƬ
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println("describe : " + describe);
		try {
			// 2.�������� www.2cto.com
			conn = JdbcUtils.getConnection();
			// 3.�������
			String sql = "insert into fileupload(file,filename,type,des) values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setBlob(1, in);
			ps.setString(2, fileName);
			ps.setString(3, type);
			ps.setString(4, describe);
			// 4.ִ�����
//			ps.executeUpdate();
			ps.execute();

			in.close();

		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
