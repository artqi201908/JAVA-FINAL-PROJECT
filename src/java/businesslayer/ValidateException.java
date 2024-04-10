package java.businesslayer;


    public class ValidateException extends Exception {

        public ValidateException(){
            super("Data not in valid format");
        }

        public ValidateException(String message){
            super(message);
        }

        public ValidateException(String message, Throwable throwable){
            super(message, throwable);
        }

        public ValidateException(Throwable throwable){
            super(throwable);
        }
    }

