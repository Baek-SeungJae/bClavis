package com.brrcorp.bclavis.cipher.repository;

import org.springframework.data.repository.CrudRepository;

import com.brrcorp.bclavis.cipher.entity.RSAKey;

public interface RSAKeyRepository extends CrudRepository<RSAKey, Long> {
	RSAKey findByAesKey(String aesKey);
}
