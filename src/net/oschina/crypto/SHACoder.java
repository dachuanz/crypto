package net.oschina.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author zdc52
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
}
