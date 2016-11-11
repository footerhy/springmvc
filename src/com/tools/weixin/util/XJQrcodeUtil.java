package com.tools.weixin.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class XJQrcodeUtil {
	/**
	 * ���ɶ�ά��(QRCode)ͼƬ
	 * 
	 * @param content
	 *            ��ά��ͼƬ������
	 * @param imgPath
	 *            ���ɶ�ά��ͼƬ������·��
	 * @param ccbpath
	 *            ��ά��ͼƬ�м��logo·��
	 */
	public static int createQRCode(String content, String imgPath,
			String ccbPath) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// ���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС
			qrcodeHandler.setQrcodeErrorCorrect('M');
			// N��������,A�����ַ�a-Z,B���������ַ�
			qrcodeHandler.setQrcodeEncodeMode('B');
			// �������ö�ά��汾��ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��
			qrcodeHandler.setQrcodeVersion(8);

			byte[] contentBytes = content.getBytes("gb2312");
			BufferedImage bufImg = new BufferedImage(149, 149,BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 149, 149);

			// �趨ͼ����ɫ > BLACK
			gs.setColor(Color.blue);

			// ����ƫ���� �����ÿ��ܵ��½�������
			int pixoff = 2;
			// ������� > ��ά��
			if (contentBytes.length > 0 && contentBytes.length < 150) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,125]. ");
				return -1;
			}
			Image img = ImageIO.read(new File(ccbPath));// ʵ����һ��Image����
			gs.drawImage(img, 62, 63, null);
			gs.dispose();
			bufImg.flush();

			// ���ɶ�ά��QRCodeͼƬ
			File imgFile = new File(imgPath);
			ImageIO.write(bufImg, "png", imgFile);

		} catch (Exception e) {
			e.printStackTrace();
			return -100;
		}

		return 0;
	}

	public static void main(String[] args) {
		String imgPath = "C:/Users/Tom/Desktop/logo_QRCode.png";
		String imgPath1 = "C:/Users/Tom/Desktop/1.jpg";
		String encoderContent = "�ֻ��ţ�18754299621:�������벻�����ұ������������޲�:1,�ҵ�������ʲô��2,����һ��Ŀ�������3,����������ʲô��4,�ҵ�Ǯ;�ؼ��Σ�";
		XJQrcodeUtil logo_Two_Code = new XJQrcodeUtil();
		logo_Two_Code.createQRCode(encoderContent, imgPath, imgPath1);
		System.out.println("end...");
	}
}
