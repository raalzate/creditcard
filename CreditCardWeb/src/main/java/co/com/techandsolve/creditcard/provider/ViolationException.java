package co.com.techandsolve.creditcard.provider;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ViolationException implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException exception) {
		
		return Response.serverError()
				.header("internalServerError", exception.getMessage())
				.build();
	}
}