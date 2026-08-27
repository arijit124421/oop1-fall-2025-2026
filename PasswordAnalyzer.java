import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PasswordAnalyzer {
    private static final List<String> COMMON_PASSWORDS = Arrays.asList(
            "password", "password123", "admin", "welcome", "qwerty", "abc123",
            "letmein", "monkey", "dragon", "baseball", "football", "iloveyou",
            "sunshine", "master", "princess", "login", "trustno1", "passw0rd"
    );

    private static final List<String> KEYBOARD_PATTERNS = Arrays.asList(
            "qwerty", "asdf", "zxcv", "123456", "654321", "abcde", "password"
    );

    public static void main(String[] args) {
        String password = getPassword(args);
        AnalysisReport report = analyzePassword(password);
        printReport(report);
    }

    private static String getPassword(String[] args) {
        if (args.length > 0) {
            return String.join(" ", args);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a password to analyze: ");
        return scanner.nextLine();
    }

    public static AnalysisReport analyzePassword(String password) {
        AnalysisReport report = new AnalysisReport();

        if (password == null || password.trim().isEmpty()) {
            report.score = 0;
            report.label = "Empty";
            report.summary = "Password is empty. Please provide a value to analyze.";
            report.issues.add("No password entered.");
            report.recommendations.add("Enter a password before running analysis.");
            return report;
        }

        String cleanPassword = password.trim();
        String lowerCasePassword = cleanPassword.toLowerCase(Locale.ROOT);

        report.length = cleanPassword.length();

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        int uniqueCharacters = 0;
        int repeatedRuns = 0;

        List<Character> seenCharacters = new ArrayList<>();

        for (int i = 0; i < cleanPassword.length(); i++) {
            char currentChar = cleanPassword.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(currentChar)) {
                hasLowercase = true;
            } else if (Character.isDigit(currentChar)) {
                hasDigit = true;
            } else {
                hasSymbol = true;
            }

            if (!seenCharacters.contains(currentChar)) {
                seenCharacters.add(currentChar);
                uniqueCharacters++;
            }

            if (i > 0 && currentChar == cleanPassword.charAt(i - 1)) {
                repeatedRuns++;
            }
        }

        report.hasUppercase = hasUppercase;
        report.hasLowercase = hasLowercase;
        report.hasDigit = hasDigit;
        report.hasSymbol = hasSymbol;
        report.uniqueCharacters = uniqueCharacters;
        report.characterSetCount = (hasUppercase ? 1 : 0)
                + (hasLowercase ? 1 : 0)
                + (hasDigit ? 1 : 0)
                + (hasSymbol ? 1 : 0);

        int score = 0;

        if (cleanPassword.length() >= 16) {
            score += 35;
        } else if (cleanPassword.length() >= 12) {
            score += 25;
        } else if (cleanPassword.length() >= 8) {
            score += 15;
        } else {
            score += 5;
            report.issues.add("Password is shorter than 8 characters.");
        }

        if (hasUppercase) {
            score += 10;
        } else {
            report.issues.add("Add at least one uppercase letter.");
        }

        if (hasLowercase) {
            score += 10;
        } else {
            report.issues.add("Add at least one lowercase letter.");
        }

        if (hasDigit) {
            score += 15;
        } else {
            report.issues.add("Add at least one number.");
        }

        if (hasSymbol) {
            score += 15;
        } else {
            report.issues.add("Add at least one symbol such as !, @, #, or $.");
        }

        if (report.characterSetCount >= 3) {
            score += 10;
        }

        if (cleanPassword.length() >= 8 && uniqueCharacters >= Math.max(6, cleanPassword.length() / 2)) {
            score += 5;
        }

        if (repeatedRuns > 0) {
            score -= 10;
            report.issues.add("Repeated characters make the password easier to guess.");
        }

        if (containsSequentialPattern(cleanPassword)) {
            score -= 15;
            report.issues.add("Sequential characters such as 123 or abc were detected.");
        }

        if (containsKeyboardPattern(lowerCasePassword)) {
            score -= 20;
            report.issues.add("Keyboard pattern detected; it is common in weak passwords.");
        }

        if (containsCommonPasswordPattern(lowerCasePassword)) {
            score -= 25;
            report.issues.add("Password contains a common password pattern.");
        }

        if (containsDictionaryWord(lowerCasePassword)) {
            score -= 10;
            report.issues.add("Password includes a dictionary word or user-related term.");
        }

        double entropy = estimateEntropy(cleanPassword);
        report.entropy = entropy;

        if (entropy >= 80) {
            score += 10;
        } else if (entropy >= 60) {
            score += 5;
        }

        score = Math.max(0, Math.min(100, score));
        report.score = score;

        if (score >= 85) {
            report.label = "Strong";
            report.summary = "This password is strong and resilient against common attacks.";
        } else if (score >= 65) {
            report.label = "Moderate";
            report.summary = "This password is acceptable, but it could be improved.";
        } else if (score >= 40) {
            report.label = "Weak";
            report.summary = "This password is vulnerable to brute-force and pattern-based attacks.";
        } else {
            report.label = "Very Weak";
            report.summary = "This password is easily guessable and should not be used.";
        }

        if (report.issues.isEmpty()) {
            report.issues.add("No major issues detected.");
        }

        if (score < 85) {
            report.recommendations.add("Use a longer password with at least 12 characters.");
        }
        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSymbol) {
            report.recommendations.add("Mix uppercase letters, lowercase letters, numbers, and symbols.");
        }
        if (containsSequentialPattern(cleanPassword) || containsKeyboardPattern(lowerCasePassword) || repeatedRuns > 0) {
            report.recommendations.add("Avoid repeated characters, keyboard patterns, and obvious sequences like 123 or abc.");
        }
        if (report.recommendations.isEmpty()) {
            report.recommendations.add("Keep using the current password and store it in a password manager.");
        }

        return report;
    }

    private static boolean containsCommonPasswordPattern(String lowerPassword) {
        for (String pattern : COMMON_PASSWORDS) {
            if (lowerPassword.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDictionaryWord(String lowerPassword) {
        List<String> bannedWords = Arrays.asList("password", "admin", "welcome", "login", "user", "letmein");

        for (String word : bannedWords) {
            if (lowerPassword.contains(word)) {
                return true;
            }
        }

        return false;
    }

    private static boolean containsSequentialPattern(String value) {
        if (value == null || value.length() < 3) {
            return false;
        }

        String lower = value.toLowerCase(Locale.ROOT);
        for (int i = 2; i < lower.length(); i++) {
            char previous = lower.charAt(i - 2);
            char middle = lower.charAt(i - 1);
            char current = lower.charAt(i);

            if (Character.isLetter(previous) && Character.isLetter(middle) && Character.isLetter(current)) {
                int firstDifference = current - middle;
                int secondDifference = middle - previous;
                if ((firstDifference == 1 && secondDifference == 1)
                        || (firstDifference == -1 && secondDifference == -1)) {
                    return true;
                }
            }

            if (Character.isDigit(previous) && Character.isDigit(middle) && Character.isDigit(current)) {
                int firstDifference = current - middle;
                int secondDifference = middle - previous;
                if ((firstDifference == 1 && secondDifference == 1)
                        || (firstDifference == -1 && secondDifference == -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean containsKeyboardPattern(String lowerValue) {
        for (String pattern : KEYBOARD_PATTERNS) {
            if (lowerValue.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    private static double estimateEntropy(String password) {
        if (password == null || password.isEmpty()) {
            return 0;
        }

        int poolSize = 0;
        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                poolSize += 26;
            } else if (Character.isUpperCase(ch)) {
                poolSize += 26;
            } else if (Character.isDigit(ch)) {
                poolSize += 10;
            } else {
                poolSize += 33;
            }
        }

        double entropy = (Math.log(poolSize) / Math.log(2)) * password.length();
        return Math.round(entropy * 10.0) / 10.0;
    }

    private static void printReport(AnalysisReport report) {
        System.out.println("\nPassword Security Analysis");
        System.out.println("=========================");
        System.out.println("Score: " + report.score + "/100");
        System.out.println("Strength: " + report.label);
        System.out.println("Summary: " + report.summary);
        System.out.println("Length: " + report.length + " characters");
        System.out.println("Estimated entropy: " + report.entropy + " bits");
        System.out.println("Character diversity: " + report.characterSetCount + " categories used");

        System.out.println("\nIssues:");
        for (String issue : report.issues) {
            System.out.println("- " + issue);
        }

        System.out.println("\nRecommendations:");
        for (String recommendation : report.recommendations) {
            System.out.println("- " + recommendation);
        }
    }

    public static class AnalysisReport {
        int score;
        String label;
        String summary;
        int length;
        double entropy;
        int characterSetCount;
        int uniqueCharacters;
        boolean hasUppercase;
        boolean hasLowercase;
        boolean hasDigit;
        boolean hasSymbol;
        final List<String> issues = new ArrayList<>();
        final List<String> recommendations = new ArrayList<>();
    }
}
