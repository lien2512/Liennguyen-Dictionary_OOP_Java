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
import javafx.util.Callback;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.io.*;

public class Controller implements Initializable {

    @FXML private ListView<Word> listView;
    ObservableList<Word> words = FXCollections.observableArrayList();
    @FXML private TextField wordtarget;
    @FXML private TextArea translate;
    @Override
    public void initialize (URL location, ResourceBundle resources) {
        try {
            File file = new File("D://Dictionaries.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null){
                Word w = new Word(line);
                words.addAll(w);
            }
            in.close();
        }
        catch (Exception e){
        }
        listView.setItems(words);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String[] splitExplain = listView.
                        getSelectionModel().getSelectedItem().getWord_explain().split("\\s", 3);
                translate.setText(listView.getSelectionModel().getSelectedItem().getWord_target() + "\n" + "\n"
                        +splitExplain[0] + "\n" + splitExplain[1] + "\n" + splitExplain[2]);
            }
        });

        wordtarget.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String target = wordtarget.getText();
                ObservableList<Word> search = FXCollections.observableArrayList();
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

    public void dictionaryExportToFile() {
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D://Dictionaries.txt"), "UTF-8"));
            for(int i = 0; i < words.size(); i++) {
                file.write(words.get(i).getWord_target() + "\t" + words.get(i).getWord_explain());
                file.newLine();
            }
            file.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void printTranslate (ActionEvent event) {
        String target = wordtarget.getText();
        int i = binarySearch(target);
        if(i == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Từ không có trong từ điển");
            alert.show();
            wordtarget.clear();
        }
        else {
            String[] splitExplain = words.get(i).getWord_explain().split("\\s", 3);
            translate.setText(target + "\n" + "\n" +splitExplain[0] + "\n" + splitExplain[1] + "\n" + splitExplain[2]);
            wordtarget.clear();
        }
    }

    public void addWord (ActionEvent event) {
        Dialog<Word> dialog = new Dialog<>();
        dialog.setTitle("Thêm từ");
        dialog.setHeaderText("Nhập dữ liệu vào các khung, ấn 'X' để hủy");
        dialog.setResizable(true);

        Label label1 = new Label("Từ: ");
        Label label2 = new Label("Loại từ: ");
        Label label3 = new Label("Phiên âm: ");
        Label label4 = new Label("Nghĩa của từ: ");
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        TextField text4 = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(label1, 1, 1);
        gridPane.add(label2, 1, 2);
        gridPane.add(label3, 1, 3);
        gridPane.add(label4, 1, 4);
        gridPane.add(text1, 2, 1);
        gridPane.add(text2, 2, 2);
        gridPane.add(text3, 2, 3);
        gridPane.add(text4, 2, 4);
        dialog.getDialogPane().setContent(gridPane);

        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(new Callback<ButtonType, Word>() {
            @Override
            public Word call(ButtonType param) {

                if (param == buttonTypeOk) {
                    Word newWord = new Word();
                    newWord.setWord_target(text1.getText());
                    newWord.setWord_explain(text2.getText() + " " + text3.getText() + " " + text4.getText());
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
            for (int i = 0; i < words.size() - 1; i++) {
                if (result.get().getWord_target().equals(words.get(i).getWord_target())) {
                    alert.setContentText("Từ đã tồn tại trong danh sách");
                    alert.show();
                    break;
                } else if (result.get().getWord_target().compareTo(words.get(i).getWord_target()) > 0
                            && result.get().getWord_target().compareTo(words.get(i + 1).getWord_target()) < 0) {
                        words.add(i + 1, result.get());
                        alert.setContentText("Thêm từ thành công");
                        alert.show();
                        break;
                } else if (i == words.size() - 2) {
                    words.add(result.get());
                    alert.setContentText("Thêm từ thành công");
                    alert.show();
                    break;
                }
            }
        }
        dictionaryExportToFile();
    }

    public void deleteWord (ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Xóa từ");
        alert.setContentText("Bạn có chắc muốn xóa từ này?");
        Optional<ButtonType>result1 = alert.showAndWait();
        if (result1.get() == ButtonType.OK) {
            String item = listView.getSelectionModel().getSelectedItem().getWord_target();
            int i = binarySearch(item);
            words.remove(i);
            listView.setItems(words);
            Alert noti = new Alert(Alert.AlertType.INFORMATION);
            noti.setTitle("Thông báo");
            noti.setHeaderText(null);
            noti.setContentText("Xóa từ thành công");
            noti.show();
            translate.clear();
            wordtarget.clear();
        }
        dictionaryExportToFile();
    }

    public void changeWord (ActionEvent event) {
        Dialog<Word> dialog = new Dialog<>();
        dialog.setTitle("Sửa từ");
        dialog.setHeaderText("Nhập dữ liệu vào các khung, ấn 'X' để hủy");
        dialog.setResizable(true);

        String s = listView.getSelectionModel().getSelectedItem().getWord_target();
        int i = binarySearch(s);
        String[] explain = words.get(i).getWord_explain().split("\\s", 3);

        Label label1 = new Label("Loại từ: ");
        Label label2 = new Label("Phiên âm: ");
        Label label3 = new Label("Nghĩa của từ: ");
        TextField text1 = new TextField(explain[0]);
        TextField text2 = new TextField(explain[1]);
        TextField text3 = new TextField(explain[2]);

        GridPane gridPane = new GridPane();
        gridPane.add(label1, 1, 1);
        gridPane.add(label2, 1, 2);
        gridPane.add(label3, 1, 3);
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
                    if (text1.getText().trim().length() > 0) {
                        explain[0] = text1.getText();
                    }
                    if (text2.getText().trim().length() > 0) {
                        explain[1] = text2.getText();
                    }
                    if (text3.getText().trim().length() > 0) {
                        explain[2] = text3.getText();
                    }
                    words.get(i).setWord_explain(explain[0] + " " + explain[1] + " " + explain[2]);
                    return words.get(i);
                }
                return null;
            }
        });

        Optional<Word> result = dialog.showAndWait();
        if (result.isPresent()) {
            Alert noti = new Alert(Alert.AlertType.INFORMATION);
            noti.setTitle("Thông báo");
            noti.setHeaderText(null);
            noti.setContentText("Sửa từ thành công");
            noti.show();
            listView.setItems(words);
            String[] splitExplain = listView.getSelectionModel().getSelectedItem().getWord_explain().split("\\s", 3);
            translate.setText(listView.getSelectionModel().getSelectedItem().getWord_target() + "\n" + "\n"
                    +splitExplain[0] + "\n" + splitExplain[1] + "\n" + splitExplain[2]);
            wordtarget.clear();
        }
        dictionaryExportToFile();
    }

    public void spellus (ActionEvent event) {

        System.setProperty("mbrola.base", "D:\\Downloads\\freetts\\mbrola");
        Voice voice;
        String speech = listView.getSelectionModel().getSelectedItem().getWord_target();
        voice = VoiceManager.getInstance().getVoice("mbrola_us1");
        voice.allocate();
        voice.speak(speech);
        voice.deallocate();
    }

    public void spelluk (ActionEvent event) {

        System.setProperty("mbrola.base", "D:\\Downloads\\freetts\\mbrola");
        Voice voice;
        String speech = listView.getSelectionModel().getSelectedItem().getWord_target();
        voice = VoiceManager.getInstance().getVoice("mbrola_us2");
        voice.allocate();
        voice.speak(speech);
        voice.deallocate();
    }
}
