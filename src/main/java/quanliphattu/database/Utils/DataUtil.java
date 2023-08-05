package quanliphattu.database.Utils;

import java.util.Random;

public class DataUtil {
        public static String generatePassword(int length) {
            String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder password = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(CHARACTERS.length());
                char character = CHARACTERS.charAt(index);
                password.append(character);
            }

            return password.toString();
        }
}
