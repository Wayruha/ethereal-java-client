package trade.wayruha.ethereal.exception;

public class EtherealException extends RuntimeException {
  public EtherealException(String message) {
    super(message);
  }

  public EtherealException(String message, Throwable cause) {
    super(message, cause);
  }
}
