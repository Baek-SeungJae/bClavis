package com.brrcorp.bclavis.cipher.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptResponse {
	private String aesKey;
	private String publicKey;
	private String encryptedContent;
}
