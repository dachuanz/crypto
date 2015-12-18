package net.oschina.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author 张大川
 *
 */
public abstract class SHACoder {

	/**
	 * 
	 * @param bs
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encodeSHA(byte[] bs) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA");
		return digest.digest(bs);

	}

	/**
	 * SHA-1 生成20个字节的摘要
	 * @param string
	 * @return
	 */
	public static byte[] encodeSHA1(String string) {
		return DigestUtils.sha1(string);

	}
}
