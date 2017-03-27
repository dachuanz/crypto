package net.oschina.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 
 * @author 张大川 消息摘要算法
 */
public abstract class SHACoder {

	/**
	 * SHA 算法也不推荐使用
	 * @param bs
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeSHA1(byte[] bs) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA");
		return digest.digest(bs);

	}

	/**
	 * SHA-256 生成32个字节的摘要
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
	 * java 8 提供了 sha 224 摘要
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeSHA224(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-224");
		return digest.digest(data);

	}

	/**
	 * MD5生成16个字节的摘要 MD5 目前已经不安全了
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
		System.err.println("明文" + string);
		byte[] bs2 = SHACoder.encodeSHA224(bs);
		/**
		 * 使用java 8自带base64算法
		 */
		System.err.println("密码:" + Base64.getEncoder().encodeToString(bs2));
		// byte[] bs4 = AES_OFB_Coder.initIV();
		// System.err.println("向量:" + Base64.getEncoder().encodeToString(bs4));

	}

}