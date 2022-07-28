package com.brrcorp.bclavis.cipher.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
public class RSAKey {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String aesKey;
	@Column(length = 2000)
	private String privateKey;
	@Column(length = 2000)
	private String publicKey;

	public RSAKey() {

	}

	@Builder
	public RSAKey(String aesKey, String privateKey, String publicKey) {
		this.aesKey = aesKey;
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
}
