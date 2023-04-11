package hu.opm.opm.crypto;

import hu.opm.opm.login.LoginFrame;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class Main {
    static private final String masterPassword = "Master_P4ssw0rd";
    static private final String masterUsername = "master@username.com";
    static private final String charset = "UTF-8";
    static private final int iterationCount = 30000;
    static private final int derivedKeyLength = 256;
    private static byte[] derivedKey;

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException {

        /////////////////////////////
        // LOGIN FRAME
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        /////////////////////////////
        // AES GCM
        /*derivedKey = PBKDF2_HMAC_SHA256(masterPassword, masterUsername, charset, iterationCount, derivedKeyLength);
        try {
            AES aes = new AES();
            aes.byteArray2SecretKey(derivedKey);
            String encryptedMessage = aes.encrypt("The X Coders");
            String decryptedMessage = aes.decrypt(encryptedMessage);

            System.out.println("Encrypted Message : " + encryptedMessage);
            System.out.println("Decrypted Message : " + decryptedMessage);
        } catch (Exception ignored) {
        }*/

        /////////////////////////////
        // RANDOM PASSWORD GENERATOR
        /*RandomPasswordGenerator rpg2 = new RandomPasswordGenerator(true, true, true, true, 20);
        String generateRandomPassword = rpg2.generatePassword();
        System.out.println(generateRandomPassword + "\nLength: " + generateRandomPassword.length());*/
    }

    public static byte[] PBKDF2_HMAC_SHA256(String password, String salt, String charset, int iterationCount,
                                            int derivedKeyLength) throws UnsupportedEncodingException {
        // https://stackoverflow.com/questions/22580853/reliable-implementation-of-pbkdf2-hmac-sha256-for-java
        PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA256Digest());
        gen.init(password.getBytes(charset), salt.getBytes(), iterationCount);
        return ((KeyParameter) gen.generateDerivedParameters(derivedKeyLength)).getKey();
    }
}