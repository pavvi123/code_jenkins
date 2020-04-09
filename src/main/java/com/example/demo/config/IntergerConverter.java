package com.example.demo.config;

import java.security.InvalidKeyException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

public class IntergerConverter implements AttributeConverter<Long, String> {

	private static final String AES = "AES";
	private static final String SECRET = "secret-key-12345";

	private final SecretKeySpec key;
	private final Cipher cipher;

	public IntergerConverter() throws Exception {
		key = new SecretKeySpec(SECRET.getBytes(), AES);
		cipher = Cipher.getInstance(AES);
	}

	@Override
	public String convertToDatabaseColumn(Long attribute) {
		Long val = attribute;

		// try {
		// return Base64.getEncoder().encodeToString(val.toString().getBytes());
		// } catch (Exception e) {
		// throw new IllegalStateException("Invalid number: " + attribute);
		// }
		//
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(val.toString().getBytes()));
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Long convertToEntityAttribute(String dbData) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
//			byte[] byteArr = dbData.getBytes();
//			new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
//			byte[] decodeText = Base64.getDecoder().decode(byteArr);
			String str = new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
			long decodeId = Integer.parseInt(str);
			return decodeId;
		} catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
	
	}
}
