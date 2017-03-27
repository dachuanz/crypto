package net.oschina.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 
 * @author �Ŵ� ��ϢժҪ�㷨
 */
public abstract class SHACoder {

	/**
	 * SHA �㷨Ҳ���Ƽ�ʹ��
	 * @param bs
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeSHA1(byte[] bs) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA");
		return digest.digest(bs);

	}

	/**
	 * SHA-256 ����32���ֽڵ�ժҪ
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeSHA256(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return digest.digest(data);

	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeSHA512(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		return digest.digest(data);

	}

	/**
	 * java 8 �ṩ�� sha 224 ժҪ
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeSHA224(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-224");
		return digest.digest(data);

	}

	/**
	 * MD5����16���ֽڵ�ժҪ MD5 Ŀǰ�Ѿ�����ȫ��
	 * 
	 * @param string
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeMD5(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		return digest.digest(data);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {

		String string = "123456789";
		byte[] bs = string.getBytes();
		System.err.println("����" + string);
		byte[] bs2 = SHACoder.encodeSHA224(bs);
		/**
		 * ʹ��java 8�Դ�base64�㷨
		 */
		System.err.println("����:" + Base64.getEncoder().encodeToString(bs2));
		// byte[] bs4 = AES_OFB_Coder.initIV();
		// System.err.println("����:" + Base64.getEncoder().encodeToString(bs4));

	}

}