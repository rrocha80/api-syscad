package br.com.softplan.api.handler;

import java.util.Date;

public class ExceptionResponse {
	  private Date timestamp;
	  private String message;
	  private String detalles;
	  private String httpCodeMessage;
	  
	  public ExceptionResponse(java.util.Date date, String message, String details,String httpCodeMessage) {
	    super();
	    this.timestamp = date;
	    this.message = message;
	    this.detalles = details;
	    this.httpCodeMessage = httpCodeMessage;
	  }
	  
	  public String getHttpCodeMessage() {
	    return httpCodeMessage;
	  }
	  
	  public Date getTimestamp() {
	    return timestamp;
	  }
	  
	  public String getMessage() {
	    return message;
	  }
	  
	  public String getDetalles() {
	    return detalles;
	  }
	}