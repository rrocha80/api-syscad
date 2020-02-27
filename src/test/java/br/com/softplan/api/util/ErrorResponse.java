package br.com.softplan.api.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;


public class ErrorResponse {

    public ErrorResponse(String string, int value, String reasonPhrase, String objectName2, List<ErrorObject> errors2) {
		// TODO Auto-generated constructor stub
	}
	private final String message = null;
    private final int code = 0;
    private final String status = null;
    private final String objectName = null;
    private final List<ObjectError> errors = new ArrayList<ObjectError>();
}
