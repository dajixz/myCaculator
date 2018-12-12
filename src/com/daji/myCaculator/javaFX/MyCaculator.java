package com.daji.myCaculator.javaFX;/**
 * @author 大稽
 * @date2018/12/1211:45
 */

import com.daji.myCaculator.core.CoreOperation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//基于javaFX
public class MyCaculator extends Application implements EventHandler<ActionEvent> {

    //初始化按钮 二维数组
    private Button[][] jbs = new Button[][]{
            {new Button("清零"), new Button("("), new Button(")"), new Button("/")},
            {new Button("7"), new Button("8"), new Button("9"), new Button("*")},
            {new Button("4"), new Button("5"), new Button("6"), new Button("-")},
            {new Button("1"), new Button("2"), new Button("3"), new Button("+")},
            {new Button("0"), new Button("."), new Button("="), new Button("回退")}
    };
    private TextField textField;
    //flag 用来判断是否已经进行运算
    private boolean flag = false;
    private BorderPane borderPane;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) {
        borderPane = new BorderPane();
        addCompent(borderPane);
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("基于javaFX 之 我的计算器");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCompent(BorderPane borderPane) {
        textField = new TextField();
        textField.setMinSize(450, 100);
        textField.setLayoutX(10);
        textField.setLayoutY(15);
//        只读
        textField.setEditable(false);
//        内容居中显示
        textField.setAlignment(Pos.CENTER);
//        设置字体
        textField.setFont(new Font("Consolas", 20));
        gridPane = new GridPane();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                gridPane.add(jbs[i][j], j, i);
                jbs[i][j].setOnAction(this);
            }
        }
        borderPane.setTop(textField);
        borderPane.setCenter(gridPane);
    }

    @Override
    public void handle(ActionEvent event) {
        String text = ((Button) event.getSource()).getText();
        if (text == "清零") {
            textField.setText("");
        } else if (text == "=") {
            if (textField.getText() != null && !textField.getText().trim().equals("")) {
                System.out.println(textField.getText());
                CoreOperation coreOperation = new CoreOperation(textField.getText());
                double v = coreOperation.doCalculation();
                textField.setText(String.valueOf(v));
                flag = true;
            }
        } else if (text == "回退") {
            String str = textField.getText();
            if (str.length() == 1) {
                return;
            }
            textField.setText(str.substring(0, str.length() - 1));
        }
        else {
            //往文本框中追加内容
            if (flag) {
                textField.setText("");
                flag = false;
            }
            textField.setText(textField.getText() + text);
        }

    }

    /**
     * 自定义按钮的布局
     */
    class Button extends javafx.scene.control.Button {
        public Button(String text) {
            super(text);
            //高
            setPrefHeight(110);
            //宽
            setPrefWidth(115);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
