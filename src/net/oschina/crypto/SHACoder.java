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
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] encodeSHA1(String data) {
		return DigestUtils.sha1(data);

	}

	/**
	 * SHA-256 生成32个字节的摘要
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] encodeSHA256(String data) {
		return DigestUtils.sha256(data);

	}
	
	/**
	 * MD5生成16个字节的摘要
	 * @param string
	 * @return
	 */
	public static byte[] encodeMD5(String data)
	{
		return DigestUtils.md5(data);
	}
	/**
	 * SHA-384生成48个字节的摘要
	 * @param data
	 * @return
	 */
	public static byte[] encodeSHA384(String data)
	{
		return DigestUtils.sha384(data);
	}
	
}
