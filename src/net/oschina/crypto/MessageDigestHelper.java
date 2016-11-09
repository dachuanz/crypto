package net.oschina.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author zdc52
 *
 */
public abstract class MessageDigestHelper {
	public static byte[] encodeSHA224(byte[] bs) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-224");
		return digest.digest(bs);

	}

	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bs = "some string".getBytes("utf-8");
		byte[] bs2 = MessageDigestHelper.encodeSHA224(bs);
		System.out.println(bs2);
	}
}
