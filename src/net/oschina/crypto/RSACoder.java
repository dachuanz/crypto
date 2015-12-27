package net.oschina.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA 算法
 * @author zdc52
 *
 */
public class RSACoder {

	public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(512);
		KeyPair keyPair = generator.generateKeyPair();
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey key2 = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("public", key);
		map.put("private", key2);
		return map;
	}
}
