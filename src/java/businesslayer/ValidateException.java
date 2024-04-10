package java.businesslayer;

public class ValidateException {
    public static class ValidationException extends Exception {

        public ValidationException(){
            super("Data not in valid format");
        }

        public ValidationException(String message){
            super(message);
        }

        public ValidationException(String message, Throwable throwable){
            super(message, throwable);
        }

        public ValidationException(Throwable throwable){
            super(throwable);
        }
    }
}