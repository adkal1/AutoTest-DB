package utilits;

import aquality.selenium.elements.interfaces.ICheckBox;


import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomGenerateUtil {

    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERALS = "0123456789";
    private static final String CYRILLIC_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final int MIN_LENGTH_PASS = 10;
    private static final int MAX_LENGTH_PASS = 20;
    private static final int MAX_LENGTH_EMAIL = 10;
    private static final Random random = new Random();


    public static String generateEmail() {
        StringBuilder emailBuilder = new StringBuilder();
        int remainingLength = random.nextInt(MAX_LENGTH_EMAIL) + 1 - emailBuilder.length();
        for (int i = 0; i < remainingLength; i++) {
            emailBuilder.append(CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length())));
        }
        return emailBuilder.toString();
    }

    public static String generatePassword(String emailString) {
        StringBuilder passwordBuilder = new StringBuilder();
        String ALL_CHARACTERS = CAPITAL_LETTERS + NUMERALS + CYRILLIC_CHARACTERS + emailString;

        passwordBuilder.append(CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length())));
        passwordBuilder.append(NUMERALS.charAt(random.nextInt(NUMERALS.length())));
        passwordBuilder.append(CYRILLIC_CHARACTERS.charAt(random.nextInt(CYRILLIC_CHARACTERS.length())));
        passwordBuilder.append(emailString.charAt(random.nextInt(emailString.length())));

        int remainingLength = random.nextInt(MAX_LENGTH_PASS - MIN_LENGTH_PASS) + MIN_LENGTH_PASS - passwordBuilder.length();
        for (int i = 0; i < remainingLength; i++) {
            passwordBuilder.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }
        return passwordBuilder.toString();
    }

    public static int[] randomListOfNumbersInterest(List<ICheckBox> listCheckBoxes, int numberOfSelectCheckBoxes) {
        int[] randomList = new int[numberOfSelectCheckBoxes];
        for (int i = 0; i < randomList.length; i++) {

            int randomNumber = random.nextInt(listCheckBoxes.size());
            boolean isDuplicate = false;

            for (int j = 0; j < i; j++) {
                if (randomList[j] == randomNumber) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate & Objects.equals(listCheckBoxes.get(randomNumber).getText(), "Select all") || Objects.equals(listCheckBoxes.get(randomNumber).getText(), "Unselect all")) {
                i--;
            } else {
                randomList[i] = randomNumber;
            }
        }
        return randomList;
    }
}
