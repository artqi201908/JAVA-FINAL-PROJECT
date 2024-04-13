package algonquin.businesslayer;

import java.util.Date;

public class ValidateUtil {
    public static void validateString(String value, String fieldName, int maxLength)
            throws ValidationException {
        if (value == null || value.trim().equalsIgnoreCase("")) {
            throw new ValidationException(String.format("%s cannot be null, empty or only whitespace",
                    fieldName));
        } else if (value.length() > maxLength) {
            throw new ValidationException(String.format("%s cannot exceed %d characters",
                    fieldName, maxLength));
        }
    }

    public static void validateDate(Date value, String fieldName)
            throws ValidationException {
        if (value == null) {
            throw new ValidationException(String.format("%s cannot be null",
                    fieldName));
        }
    }

    public static void validateLong(long value, String fieldName)
            throws ValidationException {
        if (value < 0) {
            throw new ValidationException(String.format("%s cannot be a negative number",
                    fieldName));
        }
    }

    public static void validateInt(int value, String fieldName)
            throws ValidationException {
        if (value < 0) {
            throw new ValidationException(String.format("%s cannot be a negative number",
                    fieldName));
        }
    }
    
}
