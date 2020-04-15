package project1.lockExample;

import java.io.*;
import java.util.Scanner;

public class Lock {
    private String userEnteredCode;
    private String correctCode;
    private static String masterCode = "999";

    public static String getMasterCode() {
        return masterCode;
    }

    public static void setMasterCode(String masterCode) {
        Lock.masterCode = masterCode;
    }

    public Lock () {
        correctCode = "123";
        userEnteredCode = "x";
    }

    public Lock (String code)  {
        if (code.length() == 0)
            throw new IllegalArgumentException();

        correctCode = code;
        userEnteredCode = "";
    }

    public void enterCode (String userEnterCode) {
        this.userEnteredCode = userEnterCode;
    }

    public boolean isUnlock() {
        if ((correctCode.equals(userEnteredCode)) ||
                (userEnteredCode.equals(masterCode)))
            return true;

        return false;
    }


    public boolean equals (Object other) {
        if (other != null) {
            if (other instanceof Lock) {
                Lock temp = (Lock) other;
                if (this.correctCode.equals(temp.correctCode))
                    return true;
            }
        }
        return false;
    }

    public static boolean equals(Lock lock1, Lock lock2) {

        if (lock1.correctCode.equals(lock2.correctCode))
            return true;
        else
            return false;

    }

    public void load(String fileName) {
        try{

            // open the data file
            Scanner fileReader = new Scanner(new File(fileName));

            // read one int
            correctCode = fileReader.next();
            userEnteredCode = fileReader.next();

            System.out.println (correctCode);
            System.out.println (userEnteredCode);
        }

        // problem reading the file
        catch(Exception error){
            throw new RuntimeException();
           // System.out.println("Oops!  Something went wrong.");
        }

    }

    public void save(String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        out.println(correctCode);
        out.println(userEnteredCode);
        out.close();
    }

    public String getUserEnteredCode() {
        return userEnteredCode;
    }
    public void setUserEnteredCode(String userEnteredCode) {
        this.userEnteredCode = userEnteredCode;
    }
    public String getCorrectCode() {
        return correctCode;
    }
    public void setCorrectCode(String correctCode) {
        this.correctCode = correctCode;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "userEnteredCode='" + userEnteredCode + '\'' +
                ", correctCode='" + correctCode + '\'' +
                '}';
    }
}
