package hu.opm.opm.crypto;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//https://stackoverflow.com/questions/19743124/java-password-generator
public class RandomPasswordGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean usePunctuation;
    private int passwordLength;

    public RandomPasswordGenerator(boolean useLower, boolean useUpper, boolean useDigits, boolean usePunctuation, int passwordLength) {
        if (!useLower && !useUpper && !useDigits && !usePunctuation || passwordLength < 4) {
            System.out.println("The desired password cannot be generated due to the desired criteria are not viable or the length shorter than 4 characters.");
            return;
        }
        if (passwordLength < 20) {
            System.out.println("Password shorter than 20 characters are less safe. Unless the desired password MUST be shorter please consider longer password.");
        }
        this.useLower = useLower;
        this.useUpper = useUpper;
        this.useDigits = useDigits;
        this.usePunctuation = usePunctuation;
        this.passwordLength = passwordLength;
    }

    public String generatePassword() {
        ArrayList<Character> characterPool = setCharacterPool();
        int characterPoolLength = characterPool.size();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            password.append(characterPool.get(new SecureRandom().nextInt(characterPoolLength)));
        }
        String passwordStr = password.toString();
        boolean valid = validatePassword(passwordStr);
        if (!valid) {
            System.out.format("not valid: %s\nTrying again...\n", passwordStr);
            passwordStr = generatePassword();
        }
        return passwordStr;
    }

    private ArrayList<Character> setCharacterPool() {
        String characterPool = "";
        if (useLower)
            characterPool += LOWER;
        if (useUpper)
            characterPool += UPPER;
        if (useDigits)
            characterPool += DIGITS;
        if (usePunctuation)
            characterPool += PUNCTUATION;
        ArrayList<Character> chars = new ArrayList<Character>();
        for (char c : characterPool.toCharArray()) {
            chars.add(c);
        }
        Collections.shuffle(chars, new SecureRandom());
        return chars;
    }

    public boolean validatePassword(String password) {
        if (usePunctuation) {
            if (!criterionCheck(password, PUNCTUATION))
                return false;
        }
        if (useUpper) {
            if (!criterionCheck(password, UPPER))
                return false;
        }
        if (useLower) {
            if (!criterionCheck(password, LOWER))
                return false;
        }
        if (useDigits) {
            if (!criterionCheck(password, DIGITS))
                return false;
        }
        return true;
    }

    private boolean criterionCheck(String password, String criterion) {
        // https://www.geeksforgeeks.org/check-if-there-is-any-common-character-in-two-given-strings/

        // Convert the strings into sets of characters
        Set<Character> set1 = new HashSet<>();
        for (char c : password.toCharArray()) {
            set1.add(c);
        }
        Set<Character> set2 = new HashSet<>();
        for (char c : criterion.toCharArray()) {
            set2.add(c);
        } // Find the intersection of the sets
        for (char c : set1) {
            if (set2.contains(c)) {
                return true;
            }
        }
        return false;
    }
}
