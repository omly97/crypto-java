package oumarly.app.model;

import javax.crypto.SecretKey;

import oumarly.app.utils.Converter;

public class SecretKeyModel {

	private Long id;
	private String nom;
	private String algorithme;
	private int taille;
	private String format;
	private String encodageHex;


	public SecretKeyModel() {
		// TODO Auto-generated constructor stub
	}
	
	public SecretKeyModel(String nom, SecretKey secretKey) {
		this.nom = nom;
		algorithme = secretKey.getAlgorithm();
		taille = secretKey.getEncoded().length * 8;
		format = secretKey.getFormat();
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getEncodageHex() {
		return encodageHex;
	}

	public void setEncodageHex(String encodageHex) {
		this.encodageHex = encodageHex;
	}
	
}
