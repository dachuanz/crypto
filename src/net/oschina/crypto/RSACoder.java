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
 * 
 * @author 张大川
 */

public abstract class RSACoder {
	public static final String KEY_ALGORITHM = "RSA";

	public static final String PUBLIC_KEY = "RSAPublicKey";

	public static final String PRIVATE_KEY = "RSAPrivateKey";
	public static final int KEY_SIZE = 512;

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
		//实例化密钥生成器
		KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		generator.initialize(KEY_SIZE);// 1024 512
		KeyPair keyPair = generator.generateKeyPair();
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey key2 = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, key);
		map.put(PRIVATE_KEY, key2);
		return map;
	}
	
	
	/**
	 * 
	 * @param i
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	
	public static Map<String, Object> initKey(int i) throws NoSuchAlgorithmException {
		//实例化密钥生成器
		KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		generator.initialize(i);// 1024 512
		KeyPair keyPair = generator.generateKeyPair();
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey key2 = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(PUBLIC_KEY, key);
		map.put(PRIVATE_KEY, key2);
		return map;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Map<String, Object> map = RSACoder.initKey(2048);
		System.out.println(map.get(PUBLIC_KEY));

	}

}
