package net.oschina.crypto;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESTest1 {
	public static void main(String[] args) throws Exception {
		String string = "ZDC524";
		byte[] bs = string.getBytes();
		System.err.println("ԭ��" + string);
		byte[] bs2 = AESCoder.initKey();
		System.err.println("����" + Base64.getEncoder().encodeToString(bs2));
		bs = AESCoder.encypt(bs, bs2);
		System.err.println("���ܺ�" + Base64.getEncoder().encodeToString(bs));
		byte[] bs3 = AESCoder.decypt(bs, bs2);
		String string2 = new String(bs3);
		System.err.println("���ܺ�" + string2);
	}
}
