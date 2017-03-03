package com.duxact.pm.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.duxact.pm.entity.UserInfo;

/**
 * <p>User: tanhaican
 * <p>Date: 2017-3-2
 * <p>Version: 1.0
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String algorithmName = "md5";
    private static int hashIterations = 1;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
    	PasswordHelper.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
    	PasswordHelper.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
    	PasswordHelper.hashIterations = hashIterations;
    }

    public static String encryptPassword(String umCode, String passwordIn, String userSalt) {
        String password = new SimpleHash(
                algorithmName,
                passwordIn,
                ByteSource.Util.bytes(umCode + userSalt),
                hashIterations).toHex();

        return password;
    }
    
    public static void encryptNewPassword(UserInfo user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
