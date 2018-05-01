package com.bbt.irs;

import java.util.*;
import java.text.*;

public final class Validation {

    // All Variable Declarations are here.
    public static String sDigits = "0123456789";
    public static String sDate = sDigits + "/";
    public static String sLowerCase = "abcdefghijklmnopqrstuvwxyz";
    public static String sUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String sAlphabetic = sLowerCase + sUpperCase;
    public static String sAlphabeticWithSpaces = sAlphabetic + " ";
    public static String sPhoneChars = sDigits + "+-() ";
    public static String sGSMPhoneChars = sDigits;
    public static String sEmailChars = sLowerCase + sUpperCase + sDigits + "@._-+";
    public static String sUrlChars = sLowerCase + sUpperCase + sDigits + ".";
    public static String sAlphaNumeric = sLowerCase + sUpperCase + sDigits;
    public static String sAlphaNumericWithSpaces = sAlphaNumeric + " ";
    public static String sDigitsWithDot = sDigits + ".";
    public static String sOperators = "+-"; // */";
    public static String sSpecialCharsForWeb = "<>\"&";
    public static String sValidNameChars = sAlphabetic + " -'.&";
    public static String sValidUserid = sAlphabetic + sDigits;
    public static String sValidPassword = sAlphabetic + sDigits + "@";
    public static String sValidFullNameChars = sValidNameChars + " ";
    public static String sValidFullNameCharsNum = sValidNameChars + sDigits + " &";
    public static String sValidAddressChars = sAlphaNumeric + " /,-#\"()*.";
    public static String sValidHouseChars = sAlphaNumeric + " \"BLOCK\"\"PLOT\", ";
    public static String sValidPostalChars = sAlphaNumeric + " -";
    static final boolean bDebugOn = false;
    public static Hashtable hashSpecialChars = new Hashtable(5, 1);

    static {
        hashSpecialChars.put("<", "&lt;");
        hashSpecialChars.put(">", "&gt;");
        hashSpecialChars.put("&", "&amp;");
        hashSpecialChars.put("\"", "&quot;");
    }
    private static NumberFormat numberFormat = NumberFormat.getInstance();

    /*
     * This method checks if the String contains Digits 0 thru 9.
     * Returns false if any other character is encountered.
     */
    public static boolean isDigit(String sNum) {
        int i;

        for (i = 0; i < sNum.length(); i++) {
            char c = sNum.charAt(i);

            if (sDigits.lastIndexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

    /*
     * This method checks if the String contains any character from 0 thru 9.
     * Returns false if any character is encountered.
     */
    public static boolean hasDigit(String sNum) {
        int i;
        boolean bRetResult = false;

        for (i = 0; i < sNum.length(); i++) {
            char c = sNum.charAt(i);

            if (sDigits.lastIndexOf(c) != -1) {
                bRetResult = true;
            }

        }
        return bRetResult;
    }

    /*
     * This method checks if the String contains only LowerCase characters.
     * Returns false if any other character is encountered.
     */
    public static boolean isLowerCase(String sLower) {
        int i;

        for (i = 0; i < sLower.length(); i++) {
            char c = sLower.charAt(i);

            if (sLowerCase.lastIndexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    /*
     * This method checks if the String contains only UpperCase characters.
     * Returns false if any other character is encountered.
     */
    public static boolean isUpperCase(String sUpper) {
        int i;

        for (i = 0; i < sUpper.length(); i++) {
            char c = sUpper.charAt(i);

            if (sUpperCase.lastIndexOf(c) == -1) {

                return false;
            }

        }
        return true;
    }

    /*
     * This method checks for All Alpha Numeric Characters in the Passing String.
     * Returns true if Only Alpha Numeric Characters are found and false if else.
     */
    public static boolean isAlphaNumeric(String sAplha) {
        if (isEmpty(sAplha)) {
            return false;
        }

        int i;

        for (i = 0; i < sAplha.length(); i++) {
            char c = sAplha.charAt(i);

            if (sAlphaNumeric.lastIndexOf(c) == -1) {

                return false;
            }

        }
        return true;

    }


    /*
     * This method checks for All Valid Email Characters in the Passing String.
     * Returns false even if one Non Email character is found.
     */
    public static boolean isValidEmailCharacter(String sMail) {
        int i;
        boolean isValid = true;

        for (i = 0; i < sMail.length(); i++) {
            char c = sMail.charAt(i);

            if (sEmailChars.lastIndexOf(c) == -1) {

                isValid = false;
            }

        }


        return isValid;

    }

    /*
     * This method checks for All Valid Url Characters in the Passing String.
     * Returns false even if one NonUrl character is found.
     */
    public static boolean isValidUrlCharacter(String sStr) {
        int i;
        boolean isValid = true;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);

            if (sUrlChars.lastIndexOf(c) == -1) {

                isValid = false;
            }

        }


        return isValid;

    }

    /*
     * This method checks if the String passed is a valid E mail Address.
     * First it checks whether the String is Empty. Then it checks whether
     * the String contains any blank spaces. After these checks are passed
     * the method checks for the positions of @ and the . characters and
     * determines if the Passed string is a valid Email Character.
     */
    public static boolean isValidEmail(String sMail) {

        if (isEmpty(sMail)) {
            return false;
        }

        if (!(sMail.trim().length() > 2)) {
            return false;
        }

        if (hasSpaces(sMail)) {
            return false;
        }

        if (isValidEmailCharacter(sMail.trim())) {

            int iAtOffset = sMail.lastIndexOf('@');
            int iDotOffset;

            if (iAtOffset < 1) {
                return false;
            } else {
                iDotOffset = sMail.indexOf('.', iAtOffset);
            }

            if ((iDotOffset < iAtOffset + 2) || (iDotOffset > sMail.length() - 2)) {
                return false;
            }

        } else {
            return false;
        }

        return true;

    }

    /*
     * This method checks if the String passed is a valid Url.
     * First it checks whether the String is Empty. Then it checks whether
     * the String contains any blank spaces. After these checks are passed
     * the method checks for the positions of . characters and
     * determines if the Passed string is a valid Url Character.
     */
    public static boolean isValidUrl(String sStr) {

        if (isEmpty(sStr)) {
            return false;
        }

        if (sStr.trim().length() < 2) {
            return false;
        }

        if (hasSpaces(sStr)) {
            return false;
        }

        if (!isValidUrlCharacter(sStr.trim())) {
            return false;
        }

        return true;

    }

    /*
     * This method checks if there are any blank spaces in the String
     * that is passed to the method. Even if one blank space is found
     * the method returns true.
     */
    public static boolean hasSpaces(String sStr) {
        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);

            if (c == ' ') {
                return true;
            }

        }
        return false;

    }

    public static boolean isAlphabetic(String sStr) {
//	    if(isEmpty(sStr))
//            return false;
        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);

            if (sAlphabetic.lastIndexOf(c) == -1) {
                return false;
            }

        }
        return true;

    }

    /*
     * This method checks if the string contains only Alphabetic characters.
     * The characters can be Upper or Lower case alphabets.
     * This method returns false if any character other
     * than alphabets are encountered.
     */
    public static boolean isAlphabetic2(String sStr) {
        if (isEmpty(sStr)) {
            return false;
        }
        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);

            if (sAlphabetic.lastIndexOf(c) == -1) {
                return false;
            }

        }
        return true;

    }

    public static boolean isValidPhoneChar(String sStr) {
        if (isEmpty(sStr)) {
            return false;
        }

        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            if (sPhoneChars.lastIndexOf(c) == -1) {
                return false;
            }

        }
        return true;

    }

    /*
     * This method checks if the String contains Valid Phone Digits.
     * Even if one non valid Phone Digit is encountered it returns false.
     */
    public static boolean isValidGSMPhoneChar(String sStr) {
        if (isEmpty(sStr)) {
            return false;
        }

        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            if (sGSMPhoneChars.lastIndexOf(c) == -1) {
                return false;
            }

        }
        return true;

    }

    public static boolean isValidGSMNo(String sStr) {
        if (sStr == null || "".equals(sStr)) {
            return false;
        }
        if ((sStr.trim().length() > 10 || sStr.trim().length() < 10)) {
            return false;
        }

        if ((sStr.trim().startsWith("0"))) {
            return false;
        }

        if (!isValidGSMPhoneChar(sStr)) {
            return false;
        }

        return true;

    }
    /*
     * This method checks if the String contains Valid Phone Digits.
     * Even if one non valid Phone Digit is encountered it returns false.
     */

    public static boolean isValidPhoneNo(String sStr) {

        //if (!(sStr.trim().length()>3))
        //  	return false;

        if (!isValidPhoneChar(sStr)) {
            return false;
        }

        return true;

    }

    /*
     * This method checks if the string contains only Alphabetic characters.
     * The characters can be Upper or Lower case alphabets.
     * This method returns false if any character other
     * than alphabets are encountered.
     */
    public static boolean isAlphabeticWithSpaces(String sStr) {

        if (isEmpty(sStr)) {
            return false;
        }

        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            if (sAlphabeticWithSpaces.lastIndexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

    /*
     * This method checks if the string contains only Alphabetic characters.
     * The characters can be Upper or Lower case alphabets.
     * This method returns false if any character other
     * than alphabets are encountered.
     */
    public static boolean isAlphaNumericWithSpaces(String sStr) {
        if (isEmpty(sStr)) {
            return false;
        }

        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            if (sAlphaNumericWithSpaces.lastIndexOf(c) == -1) {
                return false;
            }

        }
        return true;

    }


    /*
     *This method checks whether the given three integers are a valid date.
     *Inputs should be date, month year format and returns true if a correct date.
     *
     */
    public static boolean isValidDate(int imyMonth, int imyDate, int imyYear) {


        if (imyMonth == 2) {
            if ((imyYear % 4) == 0) {
                if (imyDate > 29) {
                    return false;
                }
            } else {
                if (imyDate > 28) {
                    return false;
                }
            }
        } else if (imyMonth == 4 || imyMonth == 6 || imyMonth == 9 || imyMonth == 11) {
            if (imyDate > 30) {
                return false;
            }
        } else if (imyMonth == 1 || imyMonth == 3 || imyMonth == 5 || imyMonth == 7 || imyMonth == 8 || imyMonth == 10 || imyMonth == 12) {
            if (imyDate > 31) {
                return false;
            }
        }
        if (imyMonth > 12) {
            return false;
        }
        return true;

    }


    /*
     *This method checks for a blank string and returns true if everything is spac
     *This method is only called from the isEmpty method and is a private method.
     *
     */
    public static boolean isblank(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c != ' ') && (c != '\t') && (c != '\n')) {
                return false;
            }
        }
        return true;
    }


    /*
     * Checks whether the Given String is Empty or Not.
     */
    public static boolean isEmpty(String s) {
        if ((s == null) || (s.equals("")) || isblank(s) || (s.length() == 0)) {
            return true;
        }

        return false;
    }


    /*
     This method checks for a correct Double Number from a String.
     */
    public static boolean isValidDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException eNum) {
            return false;
        }
    }

    /*
     This method checks for a correct Integer Number from a String.
     */
    public static boolean isValidInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException eNum) {
            return false;
        }
    }


    /*
     * This method checks if the String contains Digits 0 thru 9.
     * Returns false if any other character is encountered.
     */
    public static boolean isDigitWithDot(String sNum) {
        int i;

        for (i = 0; i < sNum.length(); i++) {
            char c = sNum.charAt(i);
            if (sDigitsWithDot.lastIndexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    /*
     * This method checks if the Opened brackets are Closed.
     * Returns false if anything other than 0 is encountered.
     */
    public static boolean isBracketsClosed(String str) {
        int i = 0;
        int count = 0;

        while (i < str.length()) {

            if (str.charAt(i) == '(') {
                count++;
            } else if (str.charAt(i) == ')') {
                count--;
            }
            i++;
        }

        return (count == 0) ? true : false;
    }


    /* -- Redundant Method
     * This method checks if the Opened Curly brackets are Closed.
     * Returns false if anything other than 0 is encountered.
     */
    public static boolean isCurlyBracketsClosed(String str) {
        int i = 0;
        int count = 0;

        while (i < str.length()) {

            if (str.charAt(i) == '{') {
                count++;
            } else if (str.charAt(i) == '}') {
                count--;
            }
            i++;
        }

        return (count == 0) ? true : false;
    }

    /*
     * This method is to check whether two operators exist side by side in a string
     */
    public static boolean isOperatorsNextToNext(String str) {
        //if (bDebugOn) System.out.println("Coming into the isOperatorsNextToNext");
        int i = 0;
        int iStLen = str.length();

        if (iStLen == 0 || (!hasOperator(str))) {
            return false;
        }

        boolean bRetVal = false;

        while (i < iStLen) {
            char c = str.charAt(i);

            if ((i != (iStLen - 1)) && (isOperator(c))) {

                if (isOperator(str.charAt(i + 1))) {
                    bRetVal = true;
                }
            }
            i++;
        }
        return bRetVal;
    }

    /*
     * This method checks if the string contains only Operator characters.
     * This method returns false if any character other
     * than Operators are encountered.
     */
    public static boolean hasOperator(String sStr) {
        int i;
        boolean bretVal = false;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            for (int j = 0; j < sOperators.length(); j++) {
                if (sOperators.charAt(j) == sStr.charAt(i)) {
                    bretVal = true;
                }
            }

        }
        return bretVal;

    }

    public static boolean isOperator(char c) {
        int i;

        boolean bretVal = false;


        if (sOperators.indexOf(c) != -1) {
            bretVal = true;
        }


        return bretVal;

    }

    public static String RemoveWhiteSpaces(String sStr) {

        return stripCharsInBag(sStr, " ");
    }

    public static String stripCharsInBag(String sStr, String sBag) {
        int i;
        String sRetString = "";

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            if (sBag.indexOf(c) == -1) {
                sRetString += c;
            }
        }

        return sRetString;
    }

    /*
     * This method replaces all the instances of the Second Parameter from
     * the first Parameter with the third Parameter. Returns a String
     * containing the replaced sOldTest with sNewText.
     */
    public static String ReplaceSubString(String sOrigString, String sOldText, String sNewText) {
        sOrigString = sOrigString.toLowerCase();
        sOldText = sOldText.toLowerCase();
        String sRetString = "";
        //if (bDebugOn) System.out.println("The Original String is " +sOrigString);
        //if (bDebugOn) System.out.println("The Old and New String are " +sOldText + "\t" +sNewText);
        if (sOrigString.indexOf(sOldText) == -1) {
            sRetString = sOrigString;
        } else {
            sRetString = sOrigString;
            while (sRetString.indexOf(sOldText) != -1) {
                int iIndex = sRetString.indexOf(sOldText);
                String sTempString = sRetString.substring(sRetString.indexOf(sOldText) + sOldText.length());
                //if (bDebugOn) System.out.println("The Temp String is " +sTempString);
                sRetString = sRetString.substring(0, iIndex) + sNewText + sTempString;
                //if (bDebugOn) System.out.println("Final RetString " +sRetString);
            }
        }
        //if (bDebugOn) System.out.println("The Returning String is " +sRetString);
        return sRetString;
    }

    public static String ReplaceSubStringUpper(String sOrigString, String sOldText, String sNewText) {
        //sOrigString	= sOrigString.toLowerCase();
        //sOldText	= sOldText.toLowerCase();
        String sRetString = "";
        //if (bDebugOn) System.out.println("The Original String is " +sOrigString);
        //if (bDebugOn) System.out.println("The Old and New String are " +sOldText + "\t" +sNewText);
        if (sOrigString.indexOf(sOldText) == -1) {
            sRetString = sOrigString;
        } else {
            sRetString = sOrigString;
            while (sRetString.indexOf(sOldText) != -1) {
                int iIndex = sRetString.indexOf(sOldText);
                String sTempString = sRetString.substring(sRetString.indexOf(sOldText) + sOldText.length());
                //if (bDebugOn) System.out.println("The Temp String is " +sTempString);
                sRetString = sRetString.substring(0, iIndex) + sNewText + sTempString;
                //if (bDebugOn) System.out.println("Final RetString " +sRetString);
            }
        }
        //if (bDebugOn) System.out.println("The Returning String is " +sRetString);
        return sRetString;
    }

    /*
     * Checks whether the First Parameter contains only characters
     * which exist in the second string. That is if the second
     * String contains a character which is not available in the
     * first parameter it returns false.
     */
    public static boolean checkString(String sBigString, String sStr) {
        int i;

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            if (sBigString.lastIndexOf(c) == -1) {
                return false;
            }

        }
        return true;

    }

    public static boolean isBracketsOK(String sStr) {
        // First check if there are brackets: If not return true
        if ((sStr.indexOf('(') == -1) && (sStr.indexOf(')') == -1)) {
            return true;
        } else {
            int iFOpen = sStr.indexOf('(');
            int iFClosed = sStr.indexOf(')');
            int iLOpen = sStr.lastIndexOf('(');
            int iLClosed = sStr.lastIndexOf(')');
            //if (bDebugOn) System.out.println("The Four Indexes are " + iFOpen + "\t" + iFClosed );
            //if (bDebugOn) System.out.println("The Four Indexes are " + iLOpen + "\t" + iLClosed );
            if ((iFOpen > iFClosed) || (iLOpen > iLClosed)) {
                return false;
            } else {
                return true;
            }
        }

    }

    public static int countCharsInString(String sCountString, char c) {
        int iCount = 0;
        if (sCountString.indexOf(c) == -1) {
            return 0;
        } else {
            int iNum = 0;
            while (iNum < sCountString.length()) {
                if (sCountString.charAt(iNum) == c) {
                    iCount++;
                }
                iNum++;
            }

            return iCount;
        }

    }


    /*
     * This method counts the number of parameter2 Strings in Parameter1.
     */
    public static int countStringsInString(String sBigString, String sSmallString) {

        String sTempBigStr = sBigString;

        if (sTempBigStr.indexOf(sSmallString) == -1) {
            return 0;
        } else {
            int iCount = 0;
            while (sTempBigStr.indexOf(sSmallString) != -1) {
                iCount++;
                //if (bDebugOn) System.out.println("The String Before cutting is "+ sTempBigStr);
                sTempBigStr = sTempBigStr.substring(sTempBigStr.indexOf(sSmallString) + sSmallString.length());
                //if (bDebugOn) System.out.println("The String After cutting is "+ sTempBigStr);
            }

            return iCount;
        }

    }

    /*
     * This method checks whether all curly brackets are in open close format.
     * If one curly bracket is opened and not closed before another is opened
     * it returns a false.
     */
    public static boolean areOpenedBracketsClosed(String sBracketString) {
        int[] a1 = new int[sBracketString.length()];
        int[] a2 = new int[sBracketString.length()];
        boolean bOpened = true;



        int iCount = 0;
        int ia1 = 0, ia2 = 0;
        while (iCount < sBracketString.length()) {
            if (sBracketString.charAt(iCount) == '{') {
                a1[ia1] = iCount;
                ia1++;
            } else if (sBracketString.charAt(iCount) == '}') {
                a2[ia2] = iCount;
                ia2++;
            } else {
                // Do Nothing
            }
            iCount++;
        }

        if (ia1 != ia2) {
            bOpened = false;
            //  if (bDebugOn) System.out.println("The length bracket problem" + ia1 + "\t" + ia2);
        }

        //if (bDebugOn) System.out.println("The Open Bracket Count is " +ia1 + "\t" + ia2);
        for (int iNewCount = 0; iNewCount + 1 < ia1; iNewCount++) {

            if (a1[iNewCount + 1] < a2[iNewCount]) {
                bOpened = false;
            }
        }

        return bOpened;
    }

    /*
     * This method checks if the string contains special chars fpr web pages.
     * it will replace them with web safe chars
     */
    public static String formatStringForWeb(String sStr) {
        if (isEmpty(sStr)) {
            return null;
        }

        int i;
        String sReturnString = "";

        for (i = 0; i < sStr.length(); i++) {
            char c = sStr.charAt(i);
            if (sSpecialCharsForWeb.indexOf(c) != -1) {
                sReturnString = sReturnString + (String) hashSpecialChars.get(c + "");
            } else {
                sReturnString = sReturnString + c;
            }
        }

        return sReturnString;
    }

    public static String parseCheckboxValue(String sStr) {
        if (sStr != null) {
            return "Y";
        } else {
            return "N";
        }
    }

    public static String formatDouble(double dNumber) {
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(dNumber);
    }

    public static String removeNull(String s) {
        if ((s == null) || (s.equals("null"))) {
            return "";
        } else {
            return s;
        }
    }


    /*
     The function checks whether if the String contains only the characters
     allowed for Names. This follows the specification of the consultants.
     Added on 25 Febreary 2002.
     */
    public static boolean isValidName(String sName) {

        if (countCharsInString(sName.trim(), ' ') > 5) {
            return false;
        }


        int i;

        for (i = 0; i < sName.length(); i++) {
            char c = sName.charAt(i);

            if (sValidNameChars.lastIndexOf(c) == -1) {

                return false;
            }
        }
        return true;
    }

    public static boolean isValidGSM(String sStr) {
        if (sStr == null || "".equals(sStr)) {
            return false;
        }
        if ((sStr.trim().length() > 11 || sStr.trim().length() < 11)) {
            return false;
        }

        if (!isValidPhoneChar(sStr)) {
            return false;
        }

        return true;


    }

    public static boolean validGsm(String sStr) {
        if (sStr == null || "".equals(sStr)) {
            return false;
        }
        if ((sStr.trim().length() > 11 || sStr.trim().length() < 11)) {
            return false;
        }

        if (!isValidPhoneChar(sStr)) {
            return false;
        }

        return true;


    }

    
    
    /*
     The function checks whether if the String contains only the characters
     allowed for Names. This follows the specification of the consultants.
    
     */
    public static boolean isValidUserid(String sName) {

        if (countCharsInString(sName.trim(), ' ') > 5) {
            return false;
        }

        if (sName != null && sName.length() < 5) {
            return false;
        }

        int i;

        for (i = 0; i < sName.length(); i++) {
            char c = sName.charAt(i);

            if (sValidUserid.lastIndexOf(c) == -1) {

                return false;
            }
        }
        return true;
    }

    /*
     The function checks whether if the String contains only the characters
     allowed for Names. This follows the specification of the consultants.
    
     */
    public static boolean isValidPassword(String sName) {

        if (countCharsInString(sName.trim(), ' ') > 5) {
            return false;
        }

        if (sName != null && sName.length() < 8) {
            return false;
        }

        int i;

        for (i = 0; i < sName.length(); i++) {
            char c = sName.charAt(i);

            if (sValidPassword.lastIndexOf(c) == -1) {

                return false;
            }
        }
        return true;
    }

    public static boolean sValidFullNameCharsNum(String sName) {
//
        if (sName.contains("  ")) {
            return false;
        }


        int i;

        for (i = 0; i < sName.length(); i++) {
            char c = sName.charAt(i);

            if (sValidFullNameCharsNum.lastIndexOf(c) == -1) {

                return false;
            }
        }
        return true;
    }
    /*
     The function checks whether if the String contains only the characters
     allowed for full name. This follows the specification of the consultants.
     Added on 25 Febreary 2002.
     */

    public static boolean isValidFullName(String sName) {
//
//        if (countCharsInString(sName.trim(), ' ') > 5) {
//            return false;
//        }

        if (sName.contains("  ")) {
            return false;
        }
        int i;

        for (i = 0; i < sName.length(); i++) {
            char c = sName.charAt(i);

            if (sValidFullNameChars.lastIndexOf(c) == -1) {

                return false;
            }
        }
        return true;
    }

    public static boolean isValidFullNameNum(String sName) {
//
//        if (countCharsInString(sName.trim(), ' ') > 5) {
//            return false;
//        }


        int i;

        for (i = 0; i < sName.length(); i++) {
            char c = sName.charAt(i);

            if (sValidFullNameChars.lastIndexOf(c) == -1) {

                return false;
            }
        }
        return true;
    }

    /*
     The function checks whether if the String contains only the characters
     allowed for Addresses. This follows the specification of the consultants.
     Added on 25 Febreary 2002.
     */
    public static boolean isValidAddress(String sAddress) {

////        if (countCharsInString(sAddress.trim(), ' ') > 10) {
////            return false;
////        }


        int i;

        for (i = 0; i < sAddress.length(); i++) {
            char c = sAddress.charAt(i);

            if (sValidAddressChars.lastIndexOf(c) == -1) {

                return false;
            }

        }
        return true;
    }

    public static boolean isValidHouseNum(String sHouseNum) {
        String blkplt;
        boolean decision = true;
        if (sHouseNum.contains("  ")) {
            return false;
        }
        //System.out.println("==----- "+sHouseNum.substring(0, 4));
//        if(sHouseNum.length()>5 && !isDigit(sHouseNum.substring(0,1))){
////            blkplt = sHouseNum.substring(0, 4);
////            if(!blkplt.equalsIgnoreCase("PLOT")||!blkplt.equalsIgnoreCase("BLOC")){
////               
////              return false;  
////            }
//            
//        }



        if (!(sHouseNum.contains("BLOCK") || sHouseNum.contains("PLOT")) && !isDigit(sHouseNum.substring(0, 1))) {
            return false;
        }


        int i;

        for (i = 0; i < sHouseNum.length(); i++) {
            char c = sHouseNum.charAt(i);

            if (sValidHouseChars.lastIndexOf(c) == -1) {

                return false;
            }

        }
        return true;
    }

    /*
     The function checks whether if the String contains only the characters
     allowed for PostalCode. This follows the specification of the consultants.
     Added on 25 Febreary 2002.
     */
    public static boolean isValidPostalCodeOrCity(String sPostalCode) {

        if (countCharsInString(sPostalCode.trim(), ' ') > 5) {
            return false;
        }


        int i;

        for (i = 0; i < sPostalCode.length(); i++) {
            char c = sPostalCode.charAt(i);

            if (sValidPostalChars.lastIndexOf(c) == -1) {

                return false;
            }

        }
        return true;
    }

    /*
     *Verifies whether this string is an valid Nic No
     * This method verifies the check digit based on modulas 11 check
     * Written by Anuradha for the Ewallet Project.
     */
    public static boolean isValidNic(String sNic) {
        boolean bDebugMode = true;
        sNic = sNic.trim();
        int iLength = sNic.length();

        if (iLength != 10) {
            return false;
        } else // length is ok
        {
            String sLastDigit = sNic.substring(9, 10).toUpperCase();  // Retrives the last digit
            if (bDebugOn) {
                System.out.println("Number :" + sNic + " " + "length:" + iLength + "Last Digit" + sLastDigit);
            }


            if ((!sLastDigit.equals("V")) && (!sLastDigit.equals("X"))) {
                if (bDebugOn) {
                    System.out.println("not valid last character");
                }
                return false;
            } else // Last character is ok
            {

                String sNUmber = sNic.substring(0, 8);
                String sCheckDigit = sNic.substring(8, 9);
                if (bDebugOn) {
                    System.out.println("Number :" + sNUmber);
                }
                if (bDebugOn) {
                    System.out.println("Check Digit:" + sCheckDigit);
                }
                boolean bOk = false;
                char aNum[] = sNUmber.toCharArray();
                int aWeights[] = {3, 2, 7, 6, 5, 4, 3, 2};  // Weigths for sequence no
                int iSum = 0;
                int iMod = 0;
                int iCheckDigit;
                for (int i = 0; i < 8; i++) {
                    iSum += aWeights[i] * (Integer.parseInt(String.valueOf(aNum[i])));
                }

                iMod = iSum % 11;
                if (iMod == 0 || iMod == 1) {
                    iCheckDigit = 0;
                } else {
                    iCheckDigit = 11 - iMod;
                }

                if (iCheckDigit == Integer.parseInt(sCheckDigit)) // check digit is ok
                {
                    if (bDebugOn) {
                        System.out.println("Valid Number");
                    }
                    bOk = true;
                } else {
                    if (bDebugOn) {
                        System.out.println("Invalid  Number");
                    }

                }
                return bOk;
            }
        }
    } // End of isValidNic

    public static String LeftPadString(String sStr, int iNum) {

        int iStr = sStr.length();
        String sTemp = "";
        for (int i = 0; i < iNum - iStr; i++) {
            sTemp = sTemp.concat(" ");
        }

        sTemp = sTemp.concat(sStr);

        return sTemp;

    }

    public static String LeftPadStringWithZeros(String sStr, int iNum) {

        int iStr = sStr.length();
        String sTemp = "";
        for (int i = 0; i < iNum - iStr; i++) {
            sTemp = sTemp.concat("0");
        }

        sTemp = sTemp.concat(sStr);

        return sTemp;

    }

    public static String RightPadString(String sStr, int iNum) {

        int iStr = sStr.length();

        if (iStr == iNum) {
            return sStr;
        } else if (iStr > iNum) {
            return sStr.substring(0, iNum);
        } else {
            String sTemp = sStr;
            for (int i = 0; i < iNum - iStr; i++) {
                sTemp = sTemp.concat(" ");
            }
            return sTemp;
        }

    }

    public String convertDateToString(Date res) {
        String result = null;

        Calendar now = Calendar.getInstance();
        now.setTime(res);
        result = now.get(Calendar.DATE) + "/" + +(now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR);
        System.out.println(result);
        return result;
    }

    public static boolean validateBlank(String value) {
        boolean result = false;
        if (value == null || "".equals(value)) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public boolean validateCheckDigit(String dynamicNumber) {
        int total = 0;
        boolean result = false;
        String checkDigit = dynamicNumber.substring(11);
        String otherNUm = dynamicNumber.substring(5, 11);
        System.out.println("checkDigit and otherNum " + checkDigit + "," + otherNUm);
        if (Validation.isDigit(checkDigit) && Validation.isDigit(otherNUm)) {
            int checkDig = Integer.parseInt(checkDigit);
            for (int i = 0; i < otherNUm.length(); i++) {
                int num = Integer.parseInt(otherNUm.substring(i, i + 1));
                total = total + num;

                System.out.println("this is the id otherNUm " + total);
            }
            if (total == checkDig) {
                result = true;
            } else {
                result = false;
            }
        } else {
            result = false;
        }

        System.out.println("total is : " + total);
        return result;
    }
} // End of Class

