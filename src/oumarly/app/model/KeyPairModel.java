package oumarly.app.model;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import oumarly.app.crypto.Converter;

public class KeyPairModel {

	private Long id;
	private String nom;
	private String algorithme;
	private int taille;
	private String provider;
	private String publicKeyEncodageHex;
	private String privateKeyEncodageHex;

	public KeyPairModel() {
		// TODO Auto-generated constructor stub
	}

	public KeyPairModel(String nom, String algorithme, String provider, int taille, KeyPair keyPair) {
		this.nom = nom;
		this.algorithme = algorithme;
		this.provider = provider;
		this.taille = taille;
		publicKeyEncodageHex = Converter.toHex(keyPair.getPublic().getEncoded());
		privateKeyEncodageHex = Converter.toHex(keyPair.getPrivate().getEncoded());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAlgorithme() {
		return algorithme;
	}

	public void setAlgorithme(String algorithme) {
		this.algorithme = algorithme;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPublicKeyEncodageHex() {
		return publicKeyEncodageHex;
	}

	public void setPublicKeyEncodageHex(String publicKeyEncodageHex) {
		this.publicKeyEncodageHex = publicKeyEncodageHex;
	}

	public String getPrivateKeyEncodageHex() {
		return privateKeyEncodageHex;
	}

	public void setPrivateKeyEncodageHex(String privateKeyEncodageHex) {
		this.privateKeyEncodageHex = privateKeyEncodageHex;
	}
	
	public PublicKey getPublicKey() throws Exception {
		byte[] encodedKey = Converter.toBytes(publicKeyEncodageHex);
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithme);
		return keyFactory.generatePublic(pubKeySpec);
	}
	
	public PrivateKey getPrivateKey() throws Exception {
		byte[] encodedKey = Converter.toBytes(privateKeyEncodageHex);
		PKCS8EncodedKeySpec privkeySpec = new PKCS8EncodedKeySpec(encodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithme);
		return keyFactory.generatePrivate(privkeySpec);
	}
}
