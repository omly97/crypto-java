package oumarly.app.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MyKey {

	/**
	 * @author Oumar Ly
	 * @apiNote Create secret key
	 * @param algorithm: name of algorithm as AES or DES
	 * @param keysize: size of key (bits)
	 * @throws Exception 
	 * @return instance of SecretKey
	 */
	public static SecretKey getSecretKey(String algorithm, int keysize) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(keysize);
		return keyGenerator.generateKey();
	}
	
	
	/**
	 * @author Oumar Ly
	 * @apiNote Create key pair
	 * @param algorithm: name of algorithm as DH DHA RSA
	 * @param keysize: size of key (bits)
	 * @throws Exception 
	 * @return instance of KeyPair
	 */
	public static KeyPair getKeyPair(String algorithm, int keysize) throws Exception {
		KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(algorithm);
		pairGenerator.initialize(keysize);
		return pairGenerator.generateKeyPair();
	}
}
