package com.brrcorp.bclavis.cipher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brrcorp.bclavis.cipher.bo.CipherBO;
import com.brrcorp.bclavis.cipher.model.DecryptRequest;
import com.brrcorp.bclavis.cipher.model.DecryptResponse;
import com.brrcorp.bclavis.cipher.model.EncryptResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("bClavis")
@AllArgsConstructor
public class BClavisController {
	private CipherBO cipherBO;

	@GetMapping("encrypt")
	public EncryptResponse encrypt(String content) throws Exception {
		return cipherBO.encrypt(content);
	}

	@GetMapping("decrypt")
	public DecryptResponse decrypt(DecryptRequest decryptRequest) throws Exception {
		return cipherBO.decrypt(decryptRequest);
	}
}



