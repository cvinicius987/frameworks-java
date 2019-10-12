package br.com.vehicle.delivery.infra.error;

import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Assert;
import org.junit.Test;

import br.com.vehicle.delivery.infra.error.ErrorResponse;

public class ErrorResponseTest {

	@Test
	public void craeteMessageResponseTest() {
		
		String message = "Erro";
		
		ErrorResponse response = ErrorResponse.createNewMessage(message);
		
		Assert.assertThat(response.getMessage(), equalTo(message));
	}
}