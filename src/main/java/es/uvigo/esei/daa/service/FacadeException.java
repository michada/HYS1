package es.uvigo.esei.daa.service;

public class FacadeException extends Exception {
	private static final long serialVersionUID = 1L;

	public FacadeException() {
	}

	public FacadeException(String message) {
		super(message);
	}

	public FacadeException(Throwable cause) {
		super(cause);
	}

	public FacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FacadeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
