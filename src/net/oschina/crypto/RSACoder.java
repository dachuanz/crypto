﻿package net.oschina.crypto;

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

	public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);// 1024 512
		KeyPair keyPair = generator.generateKeyPair();
		RSAPublicKey key = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey key2 = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("public", key);
		map.put("private", key2);
		return map;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Map<String, Object> map = RSACoder.initKey();
		System.out.println(map.get("public"));

	}

}
