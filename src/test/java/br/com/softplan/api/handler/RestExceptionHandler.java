package br.com.softplan.api.handler;

/*@ControllerAdvice*/
public class RestExceptionHandler {
	/*@ExceptionHandler(ResourceNotFoundException.class)*/
	/*public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rfnEsception){
		ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
		resourceNotFoundDetails.setTimestamp(new Date().getTime());
		resourceNotFoundDetails.setStatus(HttpStatus.NOT_FOUND.value());
		resourceNotFoundDetails.setTitle("Resource not found");
		resourceNotFoundDetails.setDetail(rfnEsception.getMessage());
		resourceNotFoundDetails.setMessage(rfnEsception.getMessage());
		
		
		
		return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);
		
	}*/

}
