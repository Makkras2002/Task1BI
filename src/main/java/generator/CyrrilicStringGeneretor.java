package generator;

import java.security.SecureRandom;

public class CyrrilicStringGeneretor {
    private static SecureRandom random;
    private static final String CYRILLIC_CHARACTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static CyrrilicStringGeneretor instance;

    private CyrrilicStringGeneretor() {
        random = new SecureRandom();
    }
    public static CyrrilicStringGeneretor getInstance() {
        if(instance ==null) {
            instance = new CyrrilicStringGeneretor();
        }
        return instance;
    }
    public String generateRandomCyrrillicString(int length) {
        String cyrrilicString = "";
        for (int counter = 0; counter < length; counter++) {
            int randomCharIndex = random.nextInt(CYRILLIC_CHARACTERS.length());
            cyrrilicString = cyrrilicString + String.valueOf(CYRILLIC_CHARACTERS.charAt(randomCharIndex));
        }
        return cyrrilicString;
    }

}
