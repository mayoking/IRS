/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class IRSDialog {

    public static boolean showConfirmDialog(final String title, final String text) {
        final Stage stage = new Stage();
        stage.setTitle(title);
        prepareDialogStage(stage);
        final BooleanProperty response = new SimpleBooleanProperty(false);
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Label message = new Label(text);
        Button ok = new Button("YES");
        ok.setDefaultButton(true);
        ok.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                response.setValue(true);
                stage.close();
            }
        });
        Button cancel = new Button("NO");
        cancel.setCancelButton(true);
        cancel.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                response.setValue(false);
                stage.close();
            }
        });
        HBox actions = new HBox(20);
        actions.setPrefHeight(20);
        actions.setAlignment(Pos.CENTER);
        actions.getChildren().add(ok);
        actions.getChildren().add(cancel);
        root.getChildren().add(message);
        root.getChildren().add(actions);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
        return response.getValue();
    }

    public static Stage createDialogStage() {
        Stage stage = new Stage();
        prepareDialogStage(stage);
        return stage;
    }

    public static Stage createDialogStagePassword() {
        Stage stage = new Stage();
        prepareDialogStageForPassword(stage);
        return stage;
    }

    public static boolean showAlertLock(final String title, final String text) {
        final Stage stage = new Stage();
        stage.setTitle(title);
        prepareDialogStage(stage);
        final BooleanProperty response = new SimpleBooleanProperty(false);
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Label message = new Label(text);
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        ok.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                response.setValue(true);
                stage.close();
            }
        });

        HBox actions = new HBox(20);
        actions.setPrefHeight(20);
        actions.setAlignment(Pos.CENTER);
        actions.getChildren().add(ok);
        // actions.getChildren().add(cancel);
        root.getChildren().add(message);
        root.getChildren().add(actions);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
        return response.getValue();
    }

    public static Stage getAlert(final String title, final String text) {
        final Stage stage = new Stage();
        prepareDialogStage(stage);
        stage.setTitle(title);
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Label message = new Label(text);
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        ok.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                stage.close();
            }
        });
        root.setPadding(new Insets(10));
        root.getChildren().add(message);
        root.getChildren().add(ok);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
        stage.setScene(scene);
        return stage;
    }

    public static void showAlert(final String title, final String text) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                final Stage stage = new Stage();
                prepareDialogStage(stage);
                stage.setTitle(title);
                VBox root = new VBox(10);
                root.setAlignment(Pos.CENTER);
                Label message = new Label(text);
                Button ok = new Button("OK");
                ok.setDefaultButton(true);
                ok.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        stage.close();
                    }
                });
                root.setPadding(new Insets(10));
                root.getChildren().add(message);
                root.getChildren().add(ok);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
                stage.setScene(scene);
                stage.showAndWait();
            }
        });

    }

    public static boolean showAlertUnLock(final String title, final String text) {
        final Stage stage = new Stage();
        stage.setTitle(title);
        prepareDialogStage(stage);
        final BooleanProperty response = new SimpleBooleanProperty(false);
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Label message = new Label(text);
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        ok.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                response.setValue(true);
                stage.close();
            }
        });

        HBox actions = new HBox(20);
        actions.setPrefHeight(20);
        actions.setAlignment(Pos.CENTER);
        actions.getChildren().add(ok);
        // actions.getChildren().add(cancel);
        root.getChildren().add(message);
        root.getChildren().add(actions);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
        return response.getValue();
    }

    /**
     * Shows the alert with the stage parameter as the parent.
     *
     * @param title
     * @param text
     * @param parentStage
     */
    public static void showAlert(final String title, final String text, final Stage parentStage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                final Stage stage = new Stage();
                prepareDialogStage(stage);
                stage.initOwner(parentStage);
                stage.setTitle(title);
                VBox root = new VBox(10);
                root.setAlignment(Pos.CENTER);
                Label message = new Label(text);
                Button ok = new Button("OK");
                ok.setDefaultButton(true);
                ok.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        stage.close();
                    }
                });
                root.setPadding(new Insets(10));
                root.getChildren().add(message);
                root.getChildren().add(ok);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
                stage.setScene(scene);
                stage.showAndWait();
            }
        });
    }

    public static void showAlert1(String title, String text) {
        final Stage stage = new Stage();
        prepareDialogStage(stage);
        stage.setTitle(title);
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Label message = new Label(text);
        Button ok = new Button("OK");
        ok.setDefaultButton(false);
        ok.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                stage.close();
                return;
            }
        });
        root.setPadding(new Insets(20));
        root.getChildren().add(message);
        root.getChildren().add(ok);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();

    }

//    public static void PDFDIalog() {
//        Stage stage = new Stage(StageStyle.UNDECORATED);
//        // prepareDialogStage(stage);
//        prepareDialogStage4Lock(stage);
//        PDFViewer pdf = new PDFViewer();
//        pdf.start(stage);
//    }

    public static String textDialog(String title, String text) {
        final Stage stage = new Stage();
        prepareDialogStage(stage);
        stage.setTitle(title);
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Label message = new Label(text);
        TextField response = new TextField();
        response.setPrefColumnCount(100);
        Button ok = new Button("OK");
        ok.setDefaultButton(true);
        ok.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                stage.close();
            }
        });
        root.setPadding(new Insets(10));
        root.getChildren().add(message);
        root.getChildren().add(response);
        root.getChildren().add(ok);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
        return response.getText();
    }

    private static void prepareDialogStage(Stage stage) {
        stage.setResizable(false);
        stage.setHeight(200);
        stage.setWidth(500);
        stage.initOwner(IRS.getInstance().getMainStage());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    private static void prepareDialogStageForPassword(Stage stage) {
        stage.setResizable(false);
        stage.setHeight(400);
        stage.setWidth(1200);
        stage.initOwner(IRS.getInstance().getMainStage());
        //  stage.initModality(Modality.WINDOW_MODAL);
        //  stage.initStyle(StageStyle.TRANSPARENT);
    }

//    public void minorDialog() {
//        System.out.println("inside minorDialog");
//        final Stage stage = new Stage();
//        prepareDialogStage(stage);
//        stage.setHeight(500);
//        stage.setWidth(1200);
//        stage.setTitle("Minor");
//        GridPane root = new GridPane();
//        Dependant minor = new Dependant();
//        Button ok = new Button("Submit");
//        ok.setDefaultButton(true);
//        ok.setOnAction(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                stage.close();
//            }
//        });
//        root.setAlignment(Pos.CENTER);
//        root.setPadding(new Insets(10));
//        root.add(minor, 0, 2);
//        root.add(ok, 0, 4);
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("mystyle_2.css").toExternalForm());
//        scene.getStylesheets().add(getClass().getResource("test.css").toExternalForm());
//        scene.getStylesheets().add(getClass().getResource("calendarstyle.css").toExternalForm());
//        scene.getStylesheets().add(getClass().getResource("control.css").toExternalForm());
//        stage.setScene(scene);
//        stage.showAndWait();
//        System.out.println("minor done");
//    }

    private static void prepareDialogStage4Lock(Stage stage) {
        stage.setResizable(true);
//        stage.setHeight(500);
//        stage.setWidth(1000);
//        stage.initOwner(Lasrra.getInstance().getMainStage());
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initStyle(StageStyle.TRANSPARENT);
    }

    public static void showAlert4Lock(String title, String text) {
        final Stage stage = new Stage();
        prepareDialogStage4Lock(stage);
        stage.setTitle(title);
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Label message = new Label(text);
        Button ok = new Button("OK");
        ok.setDefaultButton(false);
        ok.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                stage.close();
                return;
            }
        });
        root.setPadding(new Insets(20));
        root.getChildren().add(message);
        root.getChildren().add(ok);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(IRSDialog.class.getResource("mystyle_2.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();

    }
}
