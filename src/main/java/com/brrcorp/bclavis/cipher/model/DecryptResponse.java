package com.brrcorp.bclavis.cipher.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DecryptResponse {
	private String content;
}
