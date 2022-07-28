package com.brrcorp.bclavis.cipher.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EncryptRequest {
	private String AesKey;
	private String encryptedContent;
}
