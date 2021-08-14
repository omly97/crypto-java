package oumarly.app.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MyKey {

	/**
	 * @author Oumar Ly
	 * @apiNote Create secret key
	 * @param algorithm name of algorithm as AES or DES
	 * @param keysize size of key (bits)
	 * @throws Exception 
	 * @return instance of SecretKey
	 */
	public static SecretKey getSecretKey(String algorithm, int keysize) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(keysize, new SecureRandom());
		return keyGenerator.generateKey();
	}
	
	/**
	 * @author Oumar Ly
	 * @apiNote Create secret key choosing provider
	 * @param algorithm name of algorithm as AES or DES
	 * @param provider the provider
	 * @param keysize size of key (bits)
	 * @throws Exception 
	 * @return instance of SecretKey
	 */
	public static SecretKey getSecretKey(String algorithm, String provider, int keysize) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm, provider);
		keyGenerator.init(keysize, new SecureRandom());
		return keyGenerator.generateKey();
	}
	
	
	/**
	 * @author Oumar Ly
	 * @apiNote Create key pair
	 * @param algorithm is DH or DHA or RSA
	 * @param keysize is multiple of 64 between 512 and 1024 for DSA and DS, multiple of 8 greater than or equal to 512 for RSA
	 * @throws Exception 
	 * @return instance of KeyPair
	 */
	public static KeyPair getKeyPair(String algorithm, int keysize) throws Exception {
		KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(algorithm);
		pairGenerator.initialize(keysize, new SecureRandom());
		return pairGenerator.generateKeyPair();
	}
	
	
	/**
	 * @author Oumar Ly
	 * @apiNote Create key pair choosing provider
	 * @param algorithm is DH or DHA or RSA
	 * @param provider is SUN for RSA and DSA - SunJCE for DH 
	 * @param keysize is multiple of 64 between 512 and 1024 for DSA and DS, multiple of 8 greater than or equal to 512 for RSA
	 * @throws Exception 
	 * @return instance of KeyPair
	 */
	public static KeyPair getKeyPair(String algorithm, String provider, int keysize) throws Exception {
		KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(algorithm, provider);
		pairGenerator.initialize(keysize, new SecureRandom());
		return pairGenerator.generateKeyPair();
	}
}
