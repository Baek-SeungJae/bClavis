package com.brrcorp.bclavis.cipher.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DecryptRequest {
	private String privateKeyStr;
	private String aesKey;
	private String publicKeyStr;
	private String encryptedContent;
}
