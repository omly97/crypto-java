package oumarly.app.model;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import oumarly.app.crypto.Converter;

public class SecretKeyModel {

	private Long id;
	private String nom;
	private String algorithme;
	private String provider;
	private int taille;
	private String encodageHex;

	public SecretKeyModel() {
		// TODO Auto-generated constructor stub
	}
	
	public SecretKeyModel(String nom, String provider, SecretKey secretKey) {
		this.nom = nom;
		this.provider = provider;
		algorithme = secretKey.getAlgorithm();
		taille = secretKey.getEncoded().length * 8;
		encodageHex = Converter.toHex(secretKey.getEncoded());
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

	public String getEncodageHex() {
		return encodageHex;
	}

	public void setEncodageHex(String encodageHex) {
		this.encodageHex = encodageHex;
	}
	
	public SecretKey getSecretKey() {
		return new SecretKeySpec(Converter.toBytes(encodageHex), algorithme);
	}
}
