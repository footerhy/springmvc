package com.tools.print.util;

import java.io.*;
import java.sql.*;

public class PrintFunction {
	private Connection conn;

	public void PrintFunctionmysql() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.print("Error in PrintFunction():" + e.toString());
		}
	}

	public void OpenDataConnection() {
		try {
			System.out.println("start...");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/printtest?user=root&password=root";
			conn = DriverManager.getConnection(url);
			System.out.println("end.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CloseDataConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String TableToXml(String SqlStr) {
		Statement stmt;
		ResultSet rs;
		ResultSetMetaData rsmd;
		int i;

		StringBuffer XmlStr = new StringBuffer();
		int Recn;
		String DataType;
		int FieldCount;
		String RsValue;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SqlStr);
			rsmd = (ResultSetMetaData) rs.getMetaData();

			XmlStr.append("<xml xmlns:s=\"u\" xmlns:dt=\"u\" xmlns:rs=\"u\" xmlns:z=\"#R\">  ");
			XmlStr.append("<s:Schema id=\"RowsetSchema\"> ");
			XmlStr.append("<s:ElementType name=\"row\" content=\"eltOnly\" rs:updatable=\"true\"> ");
			FieldCount = rsmd.getColumnCount() + 1;

			for (Recn = 1; Recn < FieldCount; Recn++) {
				DataType = rsmd.getColumnClassName(Recn);
				if (DataType == "java.lang.String"
						|| DataType == "java.sql.Timestamp"
						|| DataType == "java.lang.Double"
						|| DataType == "java.lang.Integer"
						|| DataType == "java.lang.Boolean"
						|| DataType == "java.lang.Float") {
					XmlStr.append("<s:AttributeType name=\""
							+ rsmd.getColumnName(Recn)
							+ "\" rs:number=\""
							+ String.valueOf(Recn)
							+ "\" rs:nullable=\"true\" rs:maydefer=\"true\" rs:writeunknown=\"true\" "
							+ "   rs:basecolumn=\"" + rsmd.getColumnName(Recn)
							+ "\"> ");

					if (DataType == "java.lang.String") {
						XmlStr.append("<s:datatype dt:type=\"string\" ");
						XmlStr.append(" dt:maxLength=\""
								+ rsmd.getColumnDisplaySize(Recn) + "\" /> ");
					} else if (DataType == "java.sql.Timestamp") {
						XmlStr.append("<s:datatype dt:type=\"datetime\" ");
						XmlStr.append(" dt:maxLength=\"16\" /> ");
					} else if (DataType == "java.lang.Double") {
						XmlStr.append("<s:datatype dt:type=\"float\" ");
						XmlStr.append(" dt:maxLength=\"8\" /> ");
					} else if (DataType == "java.lang.Integer") {
						XmlStr.append("<s:datatype dt:type=\"int\" ");
						XmlStr.append(" dt:maxLength=\"4\" /> ");
					} else if (DataType == "java.lang.Boolean") {
						XmlStr.append("<s:datatype dt:type=\"boolean\" ");
						XmlStr.append(" dt:maxLength=\"2\" /> ");
					} else if (DataType == "java.lang.Float") {
						XmlStr.append("<s:datatype dt:type=\"r4\" ");
						XmlStr.append(" dt:maxLength=\"4\" /> ");
					}

					XmlStr.append("</s:AttributeType> ");
				}
			}
			XmlStr.append("</s:ElementType> ");
			XmlStr.append("</s:Schema> ");

			XmlStr.append("<rs:data> ");
			while (rs.next()) {
				XmlStr.append("<z:row ");
				for (Recn = 1; Recn < FieldCount; Recn++) {
					DataType = rsmd.getColumnClassName(Recn);
					if (DataType == "java.lang.String"
							|| DataType == "java.sql.Timestamp"
							|| DataType == "java.lang.Double"
							|| DataType == "java.lang.Integer"
							|| DataType == "java.lang.Boolean"
							|| DataType == "java.lang.Float") {
						RsValue = rs.getString(Recn);
						if (RsValue == null)
							continue;

						if (DataType == "java.sql.Timestamp") {
							RsValue = RsValue.substring(0, 10) + "T"
									+ RsValue.substring(11, 19);
						}
						XmlStr.append(rsmd.getColumnName(Recn) + "=\""
								+ RsValue + "\" ");
					}
				}
				XmlStr.append(" /> ");
			}
			XmlStr.append("</rs:data> ");
			XmlStr.append("</xml>");

			rs.close();
			stmt.close();

			return XmlStr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String TablePictureToStr(String SqlStr, String PictureFieldName,
			String ImagePath) {
		Statement stmt;
		ResultSet rs;
		String RsValue;
		String ScriptPicture = "";
		String PictureName, PictureValue;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SqlStr);
			while (rs.next()) {
				RsValue = rs.getString(PictureFieldName);
				if (RsValue == null || 0 == RsValue.length()) {
					continue;
				}

				PictureName = ImagePath + RsValue;
				PictureValue = FileToStr(PictureName);
				if (PictureValue != ""){
					ScriptPicture = ScriptPicture
							+ "ObjPrintMange.SavePictureFile('" + RsValue
							+ "' , '" + PictureValue + "' );"; // 读取图片文件至打印控件，参数：文件名，该文件内容
				}
			}
			rs.close();
			stmt.close();

			return ScriptPicture;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String FileToStr(String fileName) throws IOException {
		File imageFile = new File(fileName);

		if (!imageFile.exists() && !imageFile.canRead()) {
			return "";
		}

		StringBuffer FileStr = new StringBuffer();
		int ch = 0;
		int i1, i2;
		char A1, A2;

		InputStream image = new FileInputStream(imageFile);
		while ((ch = image.read()) != -1) {
			i1 = ch % 16;
			if (i1 > 9)
				i1 = i1 + 55;
			else
				i1 = i1 + 48;
			A1 = (char) i1;

			i2 = ch / 16;
			if (i2 > 9)
				i2 = i2 + 55;
			else
				i2 = i2 + 48;
			A2 = (char) i2;

			FileStr.append(String.valueOf(A2) + String.valueOf(A1));
		}
		image.close();
		return FileStr.toString();

	}
}
