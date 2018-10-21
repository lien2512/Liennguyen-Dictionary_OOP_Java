package sample;

import javafx.collections.ObservableList;

import java.sql.*;

public class ReadDatabase {

    private Connection connect() {
        String url = "jdbc:sqlite:Dictionaries.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(ObservableList<Word> words){
        String sql = "SELECT word, info FROM Dictionary";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                Word newWord  = new Word();
                newWord.setWord_target(rs.getString("word"));
                newWord.setWord_explain(rs.getString("info"));
                words.addAll(newWord);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String word, String info) {
        String sql = "INSERT INTO Dictionary(word,info) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.setString(2, info);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String word) {
        String sql = "DELETE FROM Dictionary WHERE word = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, word);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String word, String info) {
        String sql = "UPDATE Dictionary SET info = ? "  + "WHERE word = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, word);
            pstmt.setString(1, info);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
