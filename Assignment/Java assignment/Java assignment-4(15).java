package courierdb;
import java.sql.*;
import java.util.*;
public class AddressSimilarity {
    public static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1),
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
    public static List<String> fetchAddressesFromDB() {
        List<String> addresses = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/courierdbms";
        String user = "root";
        String password = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT address FROM user")) {

            System.out.println("Fetched Addresses from Database:");
            while (rs.next()) {
                String address = rs.getString("address");
                addresses.add(address);
                System.out.println(address); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    public static List<String[]> findSimilarAddresses(List<String> addresses, int threshold) {
        List<String[]> similarPairs = new ArrayList<>();

        for (int i = 0; i < addresses.size(); i++) {
            for (int j = i + 1; j < addresses.size(); j++) {
                int distance = levenshteinDistance(addresses.get(i), addresses.get(j));
                if (distance <= threshold) {
                    similarPairs.add(new String[]{addresses.get(i), addresses.get(j)});
                }
            }
        }
        return similarPairs;
    }

    public static void updateAddress(int userId, String newAddress) {
        String url = "jdbc:mysql://localhost:3306/courierdbms";
        String user = "root";
        String password = "admin";

        String updateQuery = "UPDATE user SET address = ? WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, newAddress);
            pstmt.setInt(2, userId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User address updated successfully.");
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        updateAddress(1, "56 Gandhi Road, Chennai");
        List<String> addresses = fetchAddressesFromDB();
        int threshold = 2; 

        List<String[]> similarAddresses = findSimilarAddresses(addresses, threshold);

        System.out.println("\nSimilar Address Pairs:");
        if (similarAddresses.isEmpty()) {
            System.out.println("No similar addresses found.");
        } else {
            for (String[] pair : similarAddresses) {
                System.out.println(pair[0] + "  <->  " + pair[1]);
            }
        }
    }
}
