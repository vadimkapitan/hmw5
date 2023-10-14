import Exeption.WrongLoginException;
import Exeption.WrongPasswordException;

public class Main {

    private static final String VALIDATE_PATTERN = "^[A-Za-z0-9_]+$";

    public static void main(String[] args) {
        check("login", "passwor", "password");
    }

    public static boolean check(String login, String password, String confirmPassword) {
        boolean isValid = true;

        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Ошибка с введенным логином " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            System.out.println("Ошибка с введенным паролем " + e.getMessage());
            isValid = false;
        }
        
        if (isValid) {
            System.out.println("Логин и пароль верны.");
        }

        return isValid;
    }

    private static void checkLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Login - должен содержать в себе только латинские буквы, цифры и знак подчеркивания.");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Login - должен быть равен или меньше 20 символов");
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Password - должен содержать в себе только латинские буквы, цифры и знак подчеркивания.");
        } else if (password.length() > 20) {
            throw new WrongPasswordException("Password - должен быть равен или меньше 20 символов");
        } else if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Password и ConfirmPassword - должны быть равны.");
        }
    }
}