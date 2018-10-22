package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import java.io.*;

public class Controller implements Initializable {

    @FXML private ListView<Word> listView;
    ObservableList<Word> words = FXCollections.observableArrayList();
    ObservableList<Word> History = FXCollections.observableArrayList();
    @FXML private TextField wordtarget;
    @FXML private WebView webView;
    @FXML private RadioButton history;
    private WebEngine webEngine;
    @Override
    public void initialize (URL location, ResourceBundle resources) {

        ReadDatabase readDatabase = new ReadDatabase();
        readDatabase.selectAll(words);
        Collections.sort(words, new WordComparator());

        webEngine = webView.getEngine();

        listView.setItems(words);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                webEngine.loadContent(listView.getSelectionModel().getSelectedItem().getWord_explain());
                if (!history.isSelected()) {
                    History.add(listView.getSelectionModel().getSelectedItem());
                }
            }
        });

        wordtarget.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String target = wordtarget.getText();
                ObservableList<Word> search = FXCollections.observableArrayList();
                if (!history.isSelected()) {
                    if (wordtarget.getText().length() > 0) {
                        for (int i = 0; i < words.size(); i++) {
                            if (words.get(i).getWord_target().startsWith(target)) {
                                search.addAll(words.get(i));
                            }
                        }
                        listView.setItems(search);
                    } else {
                        listView.setItems(words);
                    }
                } else {
                    if (wordtarget.getText().length() > 0) {
                        for (int i = 0; i < History.size(); i++) {
                            if (History.get(i).getWord_target().startsWith(target)) {
                                search.addAll(History.get(i));
                            }
                        }
                        listView.setItems(search);
                    } else {
                        listView.setItems(History);
                    }
                }
            }
        });
    }

    public int binarySearch(String s) {
        int mid,fi = 0;
        int la = words.size() - 1;
        while (fi <= la) {
            mid = fi +(la-fi)/2 ;
            int c = words.get(mid).getWord_target().compareTo(s);
            if (c > 0) {
                la = mid -1 ;
            }
            else if (c < 0) {
                fi = mid+1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    public void printTranslate (ActionEvent event) {
        String target = wordtarget.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        if (target.length() == 0) {
            alert.setContentText("Vui lòng nhập từ muốn dịch");
            alert.show();
        } else {
            int i = binarySearch(target);
            if (i == -1) {
                alert.setContentText("Từ không có trong từ điển");
                alert.show();
                wordtarget.clear();
            } else {
                webEngine.loadContent(words.get(i).getWord_explain());
                wordtarget.clear();
                History.add(0, words.get(i));
            }
        }
    }

    public void addWord (ActionEvent event) {
        Dialog<Word> dialog = new Dialog<>();
        dialog.setTitle("Thêm từ");
        dialog.setHeaderText("Nhập dữ liệu vào các khung, ấn 'X' để hủy");
        dialog.setResizable(true);

        Label label1 = new Label("Từ: ");
        Label label2 = new Label("Loại từ: ");
        Label label3 = new Label("Nghĩa của từ: ");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(label1, 1, 1);
        gridPane.add(label2, 1, 2);
        gridPane.add(label3, 1,3);
        gridPane.add(text1, 2, 1);
        gridPane.add(text2, 2, 2);
        gridPane.add(text3, 2, 3);
        dialog.getDialogPane().setContent(gridPane);

        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(new Callback<ButtonType, Word>() {
            @Override
            public Word call(ButtonType param) {

                if (param == buttonTypeOk) {
                    Word newWord = new Word();
                    newWord.setWord_target(text1.getText());
                    newWord.setWord_explain("<html><i>" + text1.getText() + "</i><br/><ul><li><b><i>" + text2.getText()
                            + "</i><br/><ul><li><font color='#cc0000'><b> " + text3.getText() + "</b></i></html>");
                    return newWord;
                }
                return null;
            }
        });

        Optional<Word> result = dialog.showAndWait();
        if (result.isPresent()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            int i = binarySearch(result.get().getWord_target());
            if (i != -1) {
                alert.setContentText("Từ đã tồn tại trong danh sách");
                alert.show();
            } else {
                words.add(result.get());
                Collections.sort(words, new WordComparator());
                alert.setContentText("Thêm từ thành công");
                alert.show();
                listView.setItems(words);
                ReadDatabase readDatabase = new ReadDatabase();
                readDatabase.insert(result.get().getWord_target(), result.get().getWord_explain());
            }
        }
    }

    public void deleteWord (ActionEvent event) {
        Alert noti = new Alert(Alert.AlertType.INFORMATION);
        noti.setTitle("Thông báo");
        noti.setHeaderText(null);

        if (listView.getSelectionModel().isEmpty()) {
            noti.setContentText("Vui lòng chọn từ muốn xóa");
            noti.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Xóa từ");
            alert.setContentText("Bạn có chắc muốn xóa từ này?");
            Optional<ButtonType> result1 = alert.showAndWait();
            if (result1.get() == ButtonType.OK) {
                String item = listView.getSelectionModel().getSelectedItem().getWord_target();
                int i = binarySearch(item);
                words.remove(i);
                listView.setItems(words);

                noti.setContentText("Xóa từ thành công");
                noti.show();

                webEngine.loadContent("");
                wordtarget.clear();
                ReadDatabase readDatabase = new ReadDatabase();
                readDatabase.delete(item);
            }
        }
    }

    public void changeWord (ActionEvent event) {
        Alert noti = new Alert(Alert.AlertType.INFORMATION);
        noti.setTitle("Thông báo");

        if (listView.getSelectionModel().isEmpty()) {
            noti.setContentText("Vui lòng chọn từ muốn sửa");
            noti.setHeaderText(null);
            noti.show();
        } else {

            Dialog<Word> dialog = new Dialog<>();
            dialog.setTitle("Sửa từ " + listView.getSelectionModel().getSelectedItem().getWord_target());
            dialog.setHeaderText("Nhập dữ liệu vào các khung, ấn 'X' để hủy");
            dialog.setResizable(true);

            String s = listView.getSelectionModel().getSelectedItem().getWord_target();
            int i = binarySearch(s);

            Label label1 = new Label("Loại từ: ");
            Label label2 = new Label("Nghĩa của từ: ");
            TextField text1 = new TextField();
            TextField text2 = new TextField();

            GridPane gridPane = new GridPane();
            gridPane.add(label1, 1, 1);
            gridPane.add(label2, 1, 2);
            gridPane.add(text1, 2, 1);
            gridPane.add(text2, 2, 2);
            dialog.getDialogPane().setContent(gridPane);

            ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

            dialog.setResultConverter(new Callback<ButtonType, Word>() {
                @Override
                public Word call(ButtonType param) {
                    if (param == buttonTypeOk) {
                        words.get(i).setWord_explain("<html><i>" + s + "</i><br/><ul><li><b><i>" + text1.getText()
                                + "</i><br/><ul><li><font color='#cc0000'><b> " + text2.getText() + "</b></i></html>");
                        return words.get(i);
                    }
                    return null;
                }
            });

            Optional<Word> result = dialog.showAndWait();
            if (result.isPresent()) {
                noti.setHeaderText(null);
                noti.setContentText("Sửa từ thành công");
                noti.show();
                listView.setItems(words);
                webEngine.loadContent(listView.getSelectionModel().getSelectedItem().getWord_explain());
                ReadDatabase readDatabase = new ReadDatabase();
                readDatabase.update(words.get(i).getWord_target(), words.get(i).getWord_explain());
                wordtarget.clear();
            }
        }
    }

    public void spellus (ActionEvent event) {

        System.setProperty("mbrola.base", "mbrola");
        Voice voice;
        String speech = listView.getSelectionModel().getSelectedItem().getWord_target();
        voice = VoiceManager.getInstance().getVoice("mbrola_us1");
        voice.allocate();
        voice.speak(speech);
        voice.deallocate();
    }

    public void spelluk (ActionEvent event) {

        System.setProperty("mbrola.base", "mbrola");
        Voice voice;
        String speech = listView.getSelectionModel().getSelectedItem().getWord_target();
        voice = VoiceManager.getInstance().getVoice("mbrola_us2");
        voice.allocate();
        voice.speak(speech);
        voice.deallocate();
    }

    public void about (ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông tin");
        alert.setHeaderText("Từ điển Anh - Việt");
        alert.setContentText("Tác giả: Nguyễn Tuấn Linh - Nguyễn Thị Liên");
        alert.show();
    }

    public void historyView (ActionEvent event) {
        if (history.isSelected()) {
            listView.setItems(History);
        } else {
            listView.setItems(words);
        }
    }
}
