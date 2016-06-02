package co.com.techandsolve.creditcard.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;

@SuppressWarnings("deprecation")
@Provider
public class ViolationException implements ExceptionMapper<MethodConstraintViolationException> {

	@Override
	public Response toResponse(MethodConstraintViolationException ex) {

		
		String message = "";
		
        for (MethodConstraintViolation<?> methodConstraintViolation : ex.getConstraintViolations()) {

        	message += methodConstraintViolation.getMessage() + ", ";
 
        }

        if(message.length() > 0){
        	message = message.substring(0, message.length() - 2);
        }
        
		return Response.serverError()
				.header("internalServerError", message)
				.build();
	}
	
	
	
}
