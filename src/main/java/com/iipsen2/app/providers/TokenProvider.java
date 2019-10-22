package com.iipsen2.app.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

public class TokenProvider {
    static final String TOKEN_OUTPUT_NAME = "token";
    public final RSAPrivateKey PRIVATE_KEY;
    public final RSAPublicKey PUBLIC_KEY;
    public final Algorithm ALGORITHM_RS;
    public final JWTVerifier VERIFIER;

    public TokenProvider() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        if (tokenExists())
            generateKeypair();

        PRIVATE_KEY = (RSAPrivateKey) getPrivateKey();
        PUBLIC_KEY = (RSAPublicKey) getPublicKey();
        ALGORITHM_RS = Algorithm.RSA256(PUBLIC_KEY, PRIVATE_KEY);
        VERIFIER = JWT.require(ALGORITHM_RS)
                .withIssuer("auth0")
                .build();
    }

    public String generateToken(long user_id) {
        try {
            return JWT.create()
                    .withIssuer("iipsen2")
                    .withClaim("user_id", user_id)
                    .sign(ALGORITHM_RS);
        } catch (JWTCreationException exception) {
            System.err.println("Invalid Signing configuration / Couldn't convert Claims.");
        }

        return null;
    }

    public boolean isVerifiedToken(String token) {
        try {
            VERIFIER.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public DecodedJWT getDecodedJWT(String token) {
        if (isVerifiedToken(token))
            return VERIFIER.verify(token);

        return null;
    }

    private void generateKeypair() {
        try {
            // Generate token pair
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);

            // Generate RSA key pair
            KeyPair kp = kpg.generateKeyPair();

            // Extract the public and private key
            Key pub = kp.getPublic();
            Key pvt = kp.getPrivate();

            // Write keys to files
            FileOutputStream out = new FileOutputStream(TOKEN_OUTPUT_NAME + ".key");
            out.write(pvt.getEncoded());
            out.close();

            out = new FileOutputStream(TOKEN_OUTPUT_NAME + ".pub");
            out.write(pub.getEncoded());
            out.close();

            System.err.println("Private key format: " + pvt.getFormat());
            System.err.println("Public key format: " + pub.getFormat());


        } catch (NoSuchAlgorithmException e) {
            System.err.println("No such algorithm....");
        } catch (FileNotFoundException e) {
            System.err.println("File not fount....");
        } catch (IOException e) {
            System.err.println("Write error....");
            e.printStackTrace();
        }
    }

    public static PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        /* Read all bytes from the private key file */
        Path path = Paths.get(TOKEN_OUTPUT_NAME + ".key");
        byte[] bytes = Files.readAllBytes(path);

        /* Generate private key. */
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pvt = kf.generatePrivate(ks);

        return pvt;
    }

    public static PublicKey getPublicKey() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        /* Read all the public key bytes */
        Path path = Paths.get(TOKEN_OUTPUT_NAME + ".pub");
        byte[] bytes = Files.readAllBytes(path);

        /* Generate public key. */
        X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pub = kf.generatePublic(ks);

        return pub;
    }

    static boolean tokenExists() {
        return Files.exists(Paths.get(TOKEN_OUTPUT_NAME + ".key"));
    }
}
