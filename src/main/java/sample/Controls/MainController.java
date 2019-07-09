package sample.Controls;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Connectivity.ConnectionClass;

public class MainController {

    private double xOffset = 0;
    private double yOffset = 0;
    private User currentUser;

    private ConnectionClass conn;
    private int theme = 0;

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private AnchorPane primaryAnchorPane;

    @FXML
    private AnchorPane workPane;

    @FXML
    private AnchorPane title;

    @FXML
    private Button hideButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private Button menuButton1;

    @FXML
    private Button menuButton2;

    @FXML
    private Button menuButton3;

    @FXML
    private Button menuButton4;

    @FXML
    private AnchorPane rightAnchorPane;

    @FXML
    private AnchorPane menuPane1;

    @FXML
    private AnchorPane menuPane2;

    @FXML
    private AnchorPane menuPane3;

    @FXML
    private AnchorPane menuPane4;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, Integer> accessModeColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private Button connectionIndicator;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Label loginWarning;

    @FXML
    private Label currentUserLabel;

    @FXML
    private AnchorPane loginElementsPane;

    @FXML
    private Button logoutButton;

    @FXML
    void initialize() throws SQLException {
//        TimeUnit.SECONDS.sleep(5);
//        TimeUnit.SECONDS.sleep(5);
//        TimeUnit.SECONDS.sleep(5);
//        TimeUnit.SECONDS.sleep(5);
//        TimeUnit.SECONDS.sleep(5);
//        TimeUnit.SECONDS.sleep(5);

        conn = new ConnectionClass("jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");
        initUsersData();

        loginWarning.getStyleClass().add("loginWarning");
        connectionIndicator.getStyleClass().add("connectionIndicator");
        connectionIndicator.setOnAction(actionEvent -> {
            try {
                conn = new ConnectionClass("jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                        "&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");
                initUsersData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        title.getStyleClass().add("title");
        workPane.getStyleClass().add("workPane");
        loginPane.getStyleClass().add("loginPane");
        primaryAnchorPane.getStyleClass().add("primaryAnchorPane");
        primaryAnchorPane.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case DIGIT1:
                    menuButton1.fire();
                    break;
                case DIGIT2:
                    menuButton2.fire();
                    break;
                case DIGIT3:
                    menuButton3.fire();
                    break;
                case DIGIT4:
                    menuButton4.fire();
                    break;
            }
        });
        leftAnchorPane.getStyleClass().add("leftAnchorPane");
        rightAnchorPane.getStyleClass().add("rightAnchorPane");

        hideButton.getStyleClass().add("hideButton");
        minimizeButton.getStyleClass().add("minimizeButton");
        exitButton.getStyleClass().add("exitButton");

        hideButton.setOnAction(actionEvent -> {
            Stage stage2 = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage2.setIconified(true);
        });
        minimizeButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) minimizeButton.getScene().getWindow();
            if (stage.isMaximized() && theme == 0) {
                stage.setMaximized(false);
                usersTable.setPrefHeight(150d);
                minimizeButton.setStyle("-fx-background-image: url(assets/expand-white.png)");
                loginElementsPane.setLayoutX(250);
                loginElementsPane.setLayoutY(176);
                loginWarning.setLayoutX(45);
                loginWarning.setLayoutY(117);
            } else {
                stage.setMaximized(true);
                usersTable.setPrefHeight(500d);
                minimizeButton.setStyle("-fx-background-image: url(assets/minimize-white.png)");
                loginElementsPane.setLayoutX(610);
                loginElementsPane.setLayoutY(350);
                loginWarning.setLayoutX(405);
                loginWarning.setLayoutY(290);
            }
        });
        exitButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

        hideButton.setFocusTraversable(false);
        minimizeButton.setFocusTraversable(false);
        exitButton.setFocusTraversable(false);
        logoutButton.setFocusTraversable(false);

        menuButton1.setOnAction(actionEvent -> {
            setAllInvisible();
            menuPane1.setVisible(true);
        });
        menuButton2.setOnAction(actionEvent -> {
            setAllInvisible();
            menuPane2.setVisible(true);
        });
        menuButton3.setOnAction(actionEvent -> {
            setAllInvisible();
            menuPane3.setVisible(true);
        });
        menuButton4.setOnAction(actionEvent -> {
            setAllInvisible();
            menuPane4.setVisible(true);
        });

        menuPane1.setOnMouseClicked(mouseEvent -> {
            menuPane1.requestFocus();
            usersTable.getSelectionModel().clearSelection();
        });
        menuPane2.setOnMouseClicked(mouseEvent -> menuPane2.requestFocus());
        menuPane3.setOnMouseClicked(mouseEvent -> menuPane3.requestFocus());
        menuPane4.setOnMouseClicked(mouseEvent -> menuPane4.requestFocus());
        loginPane.setOnMouseClicked(mouseEvent -> usernameField.requestFocus());

        menuPane1.getStyleClass().add("menuPane");
        menuPane2.getStyleClass().add("menuPane");
        menuPane3.getStyleClass().add("menuPane");
        menuPane4.getStyleClass().add("menuPane");

        signUpButton.getStyleClass().add("signUpButton");
        loginButton.getStyleClass().add("loginButton");

        loginButton.setOnAction(actionEvent -> {
            loginWarning.setStyle("-fx-text-fill: #d85751");
            boolean was = false;
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            if(!(enteredPassword.length() < 3 || enteredUsername.length() < 3)) {
                if(conn.isConnected()) {
                    for (User u : usersData )
                        if(enteredUsername.equals(u.getUsername()) && enteredPassword.equals(u.getPassword())) {
                            was = true;
                            currentUser = u;
                            logoutButton.setVisible(true);
                            break;
                        }

                    if(was){
                        String mode;
                        if (currentUser.getAccessMode() == 1)
                            mode = "Admin: ";
                        else
                            mode = "User: ";
                        currentUserLabel.setText(mode + currentUser.getUsername());
                        leftAnchorPane.setDisable(false);
                        loginPane.setVisible(false);
                        loginWarning.setText("");
                    }
                    else
                        loginWarning.setText("Wrong login/password.");
                }
                else
                    loginWarning.setText("No connection.");
            }
            else
                loginWarning.setText("Username/password must be at least 3 characters");
        });
        signUpButton.setOnAction(actionEvent -> {
            loginWarning.setStyle("-fx-text-fill: #d85751");
            boolean was = false;
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            if(!(enteredPassword.length() < 3 || enteredUsername.length() < 3)) {
                if(conn.isConnected()) {
                    for (User u : usersData )
                        if(enteredUsername.equals(u.getUsername())) {
                            was = true;
                            break;
                        }

                    if(!was){
                        try {
                            String prepStat = "INSERT INTO `mydbtest`.`users` (`name`, `password`) VALUES (?, ?)";
                            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                            preparedStatement.setString(1, enteredUsername);
                            preparedStatement.setString(2, enteredPassword);
                            preparedStatement.execute();
                            initUsersData();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        loginWarning.setStyle("-fx-text-fill: #a1b56c");
                        loginWarning.setText(enteredUsername + " registered.");
                    }
                    else
                        loginWarning.setText("Username is not free.");
                }
                else
                    loginWarning.setText("No connection.");
            }
            else
                loginWarning.setText("Username/password must be at least 3 characters");
        });
        logoutButton.setOnAction(actionEvent -> login());
        loginPane.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER && !(usernameField.getText().equals("") || passwordField.getText().equals("")))
                loginButton.fire();
        });
        usernameField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER)
                passwordField.requestFocus();
        });
        login();
    }

    private void login() {
        logoutButton.setVisible(false);
        leftAnchorPane.setDisable(true);
        currentUser = null;
        currentUserLabel.setText("");
        setAllInvisible();
        loginPane.setVisible(true);
        usernameField.clear();
        passwordField.clear();
    }

    private void setAllInvisible() {
        menuPane1.setVisible(false);
        menuPane2.setVisible(false);
        menuPane3.setVisible(false);
        menuPane4.setVisible(false);
        loginPane.setVisible(false);
    }

    private void initUsersData() throws SQLException {
        if (conn.isConnected()) {
            connectionIndicator.setStyle("-fx-background-image: url(assets/indicator-green.png)");

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            accessModeColumn.setCellValueFactory(new PropertyValueFactory<>("accessMode"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            usersTable.setItems(usersData);

            String query = "SELECT * FROM users";
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            usersData.clear();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAccessMode(resultSet.getInt("access_mode"));
                user.setUsername(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEMail(resultSet.getString("email"));
                usersData.add(user);

                System.out.println(user);
            }
        } else
            connectionIndicator.setStyle("-fx-background-image: url(assets/indicator-red.png)");
    }
}
