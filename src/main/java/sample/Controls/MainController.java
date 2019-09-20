package sample.Controls;

import java.io.*;
import java.sql.*;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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
    private Button menuAdminButton1;
    @FXML
    private Button menuAdminButton2;
    @FXML
    private Button menuAdminButton3;
    @FXML
    private Button menuAdminButton4;

    @FXML
    private Button menuUserButton1;
    @FXML
    private Button menuUserButton2;
    @FXML
    private Button menuUserButton3;
    @FXML
    private Button menuUserButton4;

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
    private AnchorPane loginElementsPane; //блок с полями ввода

    @FXML
    private FlowPane menuAdmin;

    @FXML
    private FlowPane menuUser;

    @FXML
    private Button logoutButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button resetSearchButton;

    @FXML
    private MenuButton criteriaButton;

    @FXML
    private TextField searchField;

    @FXML
    private MenuItem criteriaMenuItem_Id;

    @FXML
    private MenuItem criteriaMenuItem_Username;

    @FXML
    private MenuItem criteriaMenuItem_Password;

    @FXML
    private MenuItem criteriaMenuItem_Access;

    @FXML
    private MenuItem criteriaMenuItem_Email;

    @FXML
    private AnchorPane menuPane31;

    @FXML
    private Label languageLabel;
    @FXML
    private MenuButton languageButton;
    @FXML
    private MenuItem languageItem_Russian;
    @FXML
    private MenuItem languageItem_English;

    @FXML
    private Label themeLabel;
    @FXML
    private MenuButton themeButton;
    @FXML
    private MenuItem themeItem_Dark;
    @FXML
    private MenuItem themeItem_Light;

    @FXML
    private Label menuAdminLabel;

    @FXML
    private Label menuUserLabel;

    @FXML
    private Label menuPane1_DBLabel;

    @FXML
    private Button loginLanguageButton;

    @FXML
    private Label loginUsernameLabel;

    @FXML
    private Label loginPasswordLabel;

    @FXML
    private Button loginPaneTranslate;

    @FXML
    void initialize() throws SQLException {
        primaryAnchorPane.getStylesheets().add("CSS/DarkTheme.css");
        translate("English");
        conn = new ConnectionClass("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");
        initUsersData();

        //Дальше - функционал элементов
        loginWarning.getStyleClass().add("loginWarning");
        connectionIndicator.getStyleClass().add("connectionIndicator");
        connectionIndicator.setOnAction(actionEvent -> {
            try {
                conn = new ConnectionClass("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                        "&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");
                initUsersData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        title.getStyleClass().add("title");
        menuPane31.getStyleClass().add("menuPane31");
        workPane.getStyleClass().add("workPane");
        loginPane.getStyleClass().add("loginPane");
        primaryAnchorPane.getStyleClass().add("primaryAnchorPane");
        primaryAnchorPane.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case DIGIT1:
                    if(currentUser.getAccessMode() == 1)
                        menuAdminButton1.fire();
                    else
                        menuUserButton1.fire();
                    break;
                case DIGIT2:
                    if(currentUser.getAccessMode() == 1)
                        menuAdminButton2.fire();
                    else
                        menuUserButton2.fire();
                    break;
                case DIGIT3:
                    if(currentUser.getAccessMode() == 1)
                        menuAdminButton3.fire();
                    else
                        menuUserButton3.fire();
                    break;
                case DIGIT4:
                    if(currentUser.getAccessMode() == 1)
                        menuAdminButton4.fire();
                    else
                        menuUserButton4.fire();
                    break;
                case ESCAPE:
                    logoutButton.fire();
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
                if(currentUser.getTheme().equals("Dark")) 
                    minimizeButton.setStyle("-fx-background-image: url(assets/expand-white.png)");
                else
                    minimizeButton.setStyle("-fx-background-image: url(assets/expand-black.png)");
                loginElementsPane.setLayoutX(250);
                loginElementsPane.setLayoutY(176);
                loginWarning.setLayoutX(45);
                loginWarning.setLayoutY(117);
                searchField.setPrefWidth(136);
            } else {
                stage.setMaximized(true);
                usersTable.setPrefHeight(500d);
                if(currentUser.getTheme().equals("Dark"))
                    minimizeButton.setStyle("-fx-background-image: url(assets/minimize-white.png)");
                else
                    minimizeButton.setStyle("-fx-background-image: url(assets/minimize-black.png)");
                loginElementsPane.setLayoutX(610);
                loginElementsPane.setLayoutY(350);
                loginWarning.setLayoutX(405);
                loginWarning.setLayoutY(290);
                searchField.setPrefWidth(350);

            }
        });
        exitButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            saveLastConfig();
            stage.close();
        });

        languageButton.setFocusTraversable(false);
        themeButton.setFocusTraversable(false);
        hideButton.setFocusTraversable(false);
        minimizeButton.setFocusTraversable(false);
        exitButton.setFocusTraversable(false);
        logoutButton.setFocusTraversable(false);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        accessModeColumn.setCellValueFactory(new PropertyValueFactory<>("accessMode"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        menuAdminButton1.setOnAction(actionEvent -> selectMenuItem(menuAdminButton1, menuPane1));
        menuUserButton1.setOnAction(actionEvent -> selectMenuItem(menuUserButton1, menuPane1));
        menuAdminButton2.setOnAction(actionEvent -> selectMenuItem(menuAdminButton2, menuPane2));
        menuUserButton2.setOnAction(actionEvent -> selectMenuItem(menuUserButton2, menuPane2));
        menuAdminButton3.setOnAction(actionEvent -> selectMenuItem(menuAdminButton3, menuPane3));
        menuUserButton3.setOnAction(actionEvent -> selectMenuItem(menuUserButton3, menuPane3));
        menuAdminButton4.setOnAction(actionEvent -> selectMenuItem(menuAdminButton4, menuPane4));
        menuUserButton4.setOnAction(actionEvent -> selectMenuItem(menuUserButton4, menuPane4));

        menuPane1.setOnMouseClicked(mouseEvent -> {
            menuPane1.requestFocus();
            usersTable.getSelectionModel().clearSelection();
        });
        menuPane2.setOnMouseClicked(mouseEvent -> menuPane2.requestFocus());
        menuPane3.setOnMouseClicked(mouseEvent -> menuPane3.requestFocus());
        menuPane4.setOnMouseClicked(mouseEvent -> menuPane4.requestFocus());
        loginPane.setOnMouseClicked(mouseEvent -> loginPane.requestFocus());

        menuPane1.getStyleClass().add("menuPane");
        menuPane2.getStyleClass().add("menuPane");
        menuPane3.getStyleClass().add("menuPane");
        menuPane4.getStyleClass().add("menuPane");

        signUpButton.getStyleClass().add("signUpButton");
        loginButton.getStyleClass().add("loginButton");
        logoutButton.getStyleClass().add("logoutButton");


        loginButton.setOnAction(actionEvent -> {
            loginWarning.setStyle("-fx-text-fill: #d85751");
            boolean was = false;
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            try {
                if (enteredUsername.equals("admin"))
                    currentUser = new User("admin","admin");
                    logoutButton.setVisible(true);
                    loginSuccess();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//
//            if(!(enteredPassword.length() < 3 || enteredUsername.length() < 3)) {
//                if(conn.isConnected()) {
//                    for (User u : usersData )
//                            if(enteredUsername.equals(u.getUsername()) && enteredPassword.equals(u.getPassword())) {
//                            was = true;
//                            currentUser = u;
//                            logoutButton.setVisible(true);
//                            break;
//                        }
//
//                    if(was) {
//                        try {
//                            loginSuccess();
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    else
//                        loginWarning.setText("Wrong login/password.");
//                }
//                else
//                    loginWarning.setText("No connection.");
//            }
//            else
//                loginWarning.setText("Username/password must be at least 3 characters");
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
                            String prepStat = "INSERT INTO `test`.`users` (`name`, `password`) VALUES (?, ?)";
                            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                            preparedStatement.setString(1, enteredUsername);
                            preparedStatement.setString(2, enteredPassword);
                            preparedStatement.execute();
                            initUsersData();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        loginWarning.setStyle("-fx-text-fill: #7f8e55");
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
        logoutButton.setOnAction(actionEvent -> {
            saveLastConfig();
            loginBegin();
        });
        loginPane.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER && !(usernameField.getText().equals("") || passwordField.getText().equals("")))
                loginButton.fire();
        });
        usernameField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.TAB) {
                passwordField.requestFocus();
                passwordField.selectAll();
            }
        });
        passwordField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.TAB) {
                usernameField.requestFocus();
                usernameField.selectAll();
            }
        });

        searchField.setPromptText("Search...");
        criteriaMenuItem_Id.setOnAction(actionEvent -> criteriaButton.setText("Id"));
        criteriaMenuItem_Access.setOnAction(actionEvent -> criteriaButton.setText("Access"));
        criteriaMenuItem_Username.setOnAction(actionEvent -> criteriaButton.setText("Username"));
        criteriaMenuItem_Password.setOnAction(actionEvent -> criteriaButton.setText("Password"));
        criteriaMenuItem_Email.setOnAction(actionEvent -> criteriaButton.setText("E-mail"));

        languageItem_English.setOnAction(actionEvent -> {
            try {
                translate("English");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        languageItem_Russian.setOnAction(actionEvent -> {
            try {
                translate("Russian");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        themeItem_Dark.setOnAction(actionEvent -> {
            try {
                setTheme("Dark");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        themeItem_Light.setOnAction(actionEvent -> {
            try {
                setTheme("Light");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        resetSearchButton.getStyleClass().add("resetSearchButton");
        resetSearchButton.setOnAction(actionEvent -> {
            try {
                searchField.clear();
                initUsersData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER)
                searchButton.fire();
        });
        searchButton.setOnAction(actionEvent -> {
            try {
                initUsersData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(!searchField.getText().equals("")) {
                Iterator<User> i = usersData.iterator();
                switch (criteriaButton.getText()) {
                    case "Id":
                        while (i.hasNext()) {
                            if (i.next().getId() != Integer.parseInt(searchField.getText())) {
                                i.remove();
                            }
                        }
                        break;
                    case "Access":
                        while (i.hasNext()) {
                            if (i.next().getAccessMode() != Integer.parseInt(searchField.getText())) {
                                i.remove();
                            }
                        }
                        break;
                    case "Username":
                        while (i.hasNext()) {
                            if (!i.next().getUsername().equals(searchField.getText())) {
                                i.remove();
                            }
                        }
                        break;
                    case "Password":
                        while (i.hasNext()) {
                            if (!i.next().getPassword().equals(searchField.getText())) {
                                i.remove();
                            }
                        }
                        break;
                    case "E-mail":
                        while (i.hasNext()) {
                            if (!i.next().getEmail().equals(searchField.getText())) {
                                i.remove();
                            }
                        }
                        break;
                }
            }
        });


        loadLastConfig();
        loginBegin();
    }

    private void loadLastConfig() {
        try {
            File lastConfig = new File("src/main/java/sample/Controls/lastConfig.txt");
            BufferedReader reader = new BufferedReader(new FileReader(lastConfig));
            String text = reader.readLine();
            for (User u : usersData )
                if(text.equals(u.getUsername()))
                {
                    translate(u.getLanguage());
                    setTheme(u.getTheme());
                }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveLastConfig() {
        try {
            if(currentUser != null) {
                File lastConfig = new File("src/main/java/sample/Controls/lastConfig.txt");
                FileWriter lastConfigWriter = new FileWriter(lastConfig, false);
                if (!lastConfig.exists())
                    if (lastConfig.createNewFile())
                        System.out.println("lastConfig.txt created.");

                lastConfigWriter.write(currentUser.getUsername());
                lastConfigWriter.flush();
                System.out.println("last config saved");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void translate(String language) throws SQLException {
        switch (language){
            case "English":
                if(currentUser != null)
                    currentUser.setLanguageDB(conn,"English");
                searchField.setPromptText("Search...");
                languageButton.setText("English");
                loginUsernameLabel.setText("Username");
                loginPasswordLabel.setText("Password");
                loginButton.setText("Log In");
                logoutButton.setText("Log Out");

                menuAdminLabel.setText("Menu a");
                menuUserLabel.setText("Menu u");

                menuAdminButton1.setText(" 1 User management");
                menuUserButton1.setText(" 1 User management");
                menuAdminButton2.setText(" 2 Car management");
                menuUserButton2.setText(" 2 Car management");
                menuAdminButton3.setText(" 3 Settings");
                menuUserButton3.setText(" 3 Settings");
                menuAdminButton4.setText(" 4 Exit");
                menuUserButton4.setText(" 4 Exit");

                menuPane1_DBLabel.setText("Database connection");
                searchButton.setText("Search");

                languageLabel.setText("Language");
                languageItem_Russian.setText("Russian");
                languageItem_English.setText("English");
                themeLabel.setText("Theme");
                break;
            case "Russian":
                if(currentUser != null)
                    currentUser.setLanguageDB(conn,"Russian");
                searchField.setPromptText("Искать...");
                languageButton.setText("Русский");
                loginUsernameLabel.setText("Имя");
                loginPasswordLabel.setText("Пароль");
                loginButton.setText("Войти");
                logoutButton.setText("Выйти");

                menuAdminLabel.setText("Меню а");
                menuUserLabel.setText("Меню п");

                menuAdminButton1.setText(" 1 Управление пользователями");
                menuUserButton1.setText(" 1 Управление пользователями");
                menuAdminButton2.setText(" 2 Управление автомобилями");
                menuUserButton2.setText(" 2 Управление автомобилями");
                menuAdminButton3.setText(" 3 Настройки");
                menuUserButton3.setText(" 3 Настройки");
                menuAdminButton4.setText(" 4 Выход");
                menuUserButton4.setText(" 4 Выход");

                menuPane1_DBLabel.setText("Соединение с БД");
                searchButton.setText("Поиск");

                languageLabel.setText("Язык");
                languageItem_Russian.setText("Русский");
                languageItem_English.setText("Английский");
                themeLabel.setText("Тема");
                break;
        }
    }

    private void setTheme(String theme) throws SQLException {
        theme = theme.trim();
        theme = theme.toLowerCase();
        switch (theme){
            case "dark":
                themeButton.setText("Dark");
                if(currentUser != null)
                    currentUser.setThemeDB(conn,"Dark");
                primaryAnchorPane.getStylesheets().clear();
                primaryAnchorPane.getStylesheets().add("CSS/DarkTheme.css");

                primaryAnchorPane.getScene().getStylesheets().add("CSS/LightTheme.css");
                break;
            case "light":
                themeButton.setText("Light");
                if(currentUser != null)
                    currentUser.setThemeDB(conn,"Light");
                primaryAnchorPane.getStylesheets().clear();
                primaryAnchorPane.getStylesheets().add("CSS/LightTheme.css");

                break;
        }
    }

    private void selectMenuItem(Button menuItem, AnchorPane pane) {
        menuAdminButton1.setStyle("");
        menuUserButton1.setStyle("");
        menuAdminButton2.setStyle("");
        menuUserButton2.setStyle("");
        menuAdminButton3.setStyle("");
        menuUserButton3.setStyle("");
        menuAdminButton4.setStyle("");
        menuUserButton4.setStyle("");

        setAllInvisible();
        pane.setVisible(true);
        if(currentUser.getTheme().equals("Dark"))
            menuItem.setStyle("-fx-background-image: url(assets/exit-white.png);" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 2pt 25pt;" +
                    "-fx-background-position: 1 1;");
        else
            menuItem.setStyle("-fx-background-image: url(assets/exit-black.png);" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 2pt 25pt;" +
                    "-fx-background-position: 1 1;");
    }

    private void loginBegin() {
        logoutButton.setVisible(false);
        menuAdmin.setVisible(false);
        menuUser.setVisible(false);
        leftAnchorPane.setDisable(true);
        currentUser = null;
        currentUserLabel.setText("");
        setAllInvisible();
        loginPane.setVisible(true);
        usernameField.clear();
        passwordField.clear();
    }

    private void loginSuccess() throws SQLException {
        leftAnchorPane.setDisable(false);
        if (currentUser.getAccessMode() == 1) {
            menuAdmin.setVisible(true);
            menuAdminButton1.fire();
        }
        else {
            menuUser.setVisible(true);
            menuUserButton1.fire();
        }
        translate(currentUser.getLanguage());
        setTheme(currentUser.getTheme());
        currentUserLabel.setText(currentUser.getUsername());

        loginPane.setVisible(false);
        loginWarning.setText("");
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
            usersTable.setItems(usersData);
            Statement statement = conn.getConnection().createStatement();
            Statement statement2 = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM users");
            usersData.clear();

            System.out.println();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAccessMode(resultSet.getInt("access_mode"));
                user.setUsername(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEMail(resultSet.getString("email"));
                ResultSet resultSetConfigs = statement2.executeQuery("SELECT * FROM user_configs");
                while(resultSetConfigs.next())
                    if(resultSetConfigs.getInt("userId") == user.getId()){
                        user.setTheme(resultSetConfigs.getString("theme"));
                        user.setLanguage(resultSetConfigs.getString("language"));
                    }
                usersData.add(user);
                System.out.println(user);
            }
        } else
            connectionIndicator.setStyle("-fx-background-image: url(assets/indicator-red.png)");
    }
}
