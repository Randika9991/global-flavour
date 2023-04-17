package lk.ijse.global_flavour.util;

public class ValidateField {
    public static boolean numberCheck(String input) {
        if (input.matches("\\d+")) {
            return true; // Input contains only numeric characters
        } else {
            return false; // Input contains non-numeric characters
        }
    }
    public static boolean priceCheck(String input) {
        if (input.matches("\\d+(\\.\\d{1,2})?")) {
            return true; // Input contains only numeric characters, including up to two decimal points
        } else {
            return false; // Input contains non-numeric characters or invalid decimal points
        }
    }

}
