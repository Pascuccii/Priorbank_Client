package sample.Controls;

import com.mysql.cj.util.StringUtils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Connectivity.ConnectionClass;
import sample.enums.City;
import sample.enums.Country;
import sample.enums.Disability;
import sample.enums.MaritalStatus;

import java.io.*;
import java.sql.*;
import java.util.Iterator;

public class MainController extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private User currentUser;
    private String currentTheme;
    private String currentLanguage;

    private ConnectionClass conn;
    private int theme = 0;

    private ObservableList<User> usersData = FXCollections.observableArrayList();
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
    
    private ObservableList<Client> clientsData = FXCollections.observableArrayList();
    @FXML
    private TableView<Client> clientsTable;
    @FXML
    private TableColumn<Client,  Integer> idClientColumn;
    @FXML
    private TableColumn<Client,  String> name;
    @FXML
    private TableColumn<Client,  String> surname;
    @FXML
    private TableColumn<Client,  String> patronymic;
    @FXML
    private TableColumn<Client,  Date> birthDate;
    @FXML
    private TableColumn<Client,  String> passportSeries;
    @FXML
    private TableColumn<Client,  String> passportNumber;
    @FXML
    private TableColumn<Client,  String> issuedBy;
    @FXML
    private TableColumn<Client,  Date> issuedDate;
    @FXML
    private TableColumn<Client,  String> birthPlace;
    @FXML
    private TableColumn<Client,  City> actualResidenceCity;
    @FXML
    private TableColumn<Client,  String> actualResidenceAddress;
    @FXML
    private TableColumn<Client,  String> homeNumber;
    @FXML
    private TableColumn<Client,  String> mobileNumber;
    @FXML
    private TableColumn<Client,  String> email;
    @FXML
    private TableColumn<Client,  String> job;
    @FXML
    private TableColumn<Client,  String> position;
    @FXML
    private TableColumn<Client,  City> registrationCity;
    @FXML
    private TableColumn<Client,  MaritalStatus> maritalStatus;
    @FXML
    private TableColumn<Client,  Country> citizenship;
    @FXML
    private TableColumn<Client,  Disability> disability;
    @FXML
    private TableColumn<Client,  Boolean> retiree;
    @FXML
    private TableColumn<Client,  Double> monthlyIncome;
    @FXML
    private TableColumn<Client,  String> idNumber;
    @FXML
    private AnchorPane primaryAnchorPane;
    @FXML
    private AnchorPane title;
    @FXML
    private Button hideButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button logoutButtonAdmin;
    @FXML
    private Button logoutButtonUser;
    @FXML
    private Label currentUserLabelAdmin;
    @FXML
    private Label currentUserLabelUser;
    @FXML
    private AnchorPane workPane;
    @FXML
    private AnchorPane leftAnchorPane;
    @FXML
    private FlowPane menuAdmin;
    @FXML
    private Button menuAdminButton1;
    @FXML
    private Button menuAdminButton2;
    @FXML
    private Button menuAdminButton3;
    @FXML
    private Button menuAdminButton4;
    @FXML
    private FlowPane menuUser;
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
    private Button connectionIndicator;
    @FXML
    private Label menuPane1_DBLabel;
    @FXML
    private TextField searchField;
    @FXML
    private MenuButton criteriaButton;
    @FXML
    private MenuItem criteriaMenuItem_Id;
    @FXML
    private MenuItem criteriaMenuItem_Access;
    @FXML
    private MenuItem criteriaMenuItem_Username;
    @FXML
    private MenuItem criteriaMenuItem_Password;
    @FXML
    private MenuItem criteriaMenuItem_Email;
    @FXML
    private Button searchButton;
    @FXML
    private Button resetSearchButton;
    @FXML
    private ImageView fixImage;
    @FXML
    private AnchorPane createUser_AnchorPane;
    @FXML
    private TextField createUser_AnchorPane_Username;
    @FXML
    private TextField createUser_AnchorPane_Email;
    @FXML
    private TextField createUser_AnchorPane_Password;
    @FXML
    private Button createUserButton;
    @FXML
    private MenuButton createUser_AnchorPane_AccessMode_MenuButton;
    @FXML
    private MenuItem createUser_AccessMenuItem_User;
    @FXML
    private MenuItem createUser_AccessMenuItem_Admin;
    @FXML
    private AnchorPane changeUser_AnchorPane;
    @FXML
    private TextField changeUser_AnchorPane_Username;
    @FXML
    private TextField changeUser_AnchorPane_Email;
    @FXML
    private TextField changeUser_AnchorPane_Password;
    @FXML
    private TextField changeUser_AnchorPane_Id;
    @FXML
    private Button changeUser_AnchorPane_IdSubmitButton;
    @FXML
    private Button changeUserButton;
    @FXML
    private MenuButton changeUser_AnchorPane_AccessMode_MenuButton;
    @FXML
    private MenuItem changeUser_AccessMenuItem_User;
    @FXML
    private MenuItem changeUser_AccessMenuItem_Admin;
    @FXML
    private AnchorPane deleteUser_AnchorPane;
    @FXML
    private TextField deleteUserTextField;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Label deleteUserLabel;
    @FXML
    private AnchorPane menuPane2;
    @FXML
    private AnchorPane menuPane3;
    @FXML
    private AnchorPane menuPane31;
    @FXML
    private MenuButton languageButton;
    @FXML
    private MenuItem languageItem_Russian;
    @FXML
    private MenuItem languageItem_English;
    @FXML
    private Label languageLabel;
    @FXML
    private Label themeLabel;
    @FXML
    private MenuButton themeButton;
    @FXML
    private MenuItem themeItem_Dark;
    @FXML
    private MenuItem themeItem_Light;
    @FXML
    private Label customizationLabel;
    @FXML
    private AnchorPane accountSettingsPane;
    @FXML
    private ScrollPane clientManagementScrollPane;
    @FXML
    private AnchorPane clientManagementAnchorPane;
    @FXML
    private Label accountSettingsLabel;
    @FXML
    private Label databaseSettingsLabel;
    @FXML
    private TextField accountSettingsUsernameTextField;
    @FXML
    private TextField accountSettingsEmailTextField;
    @FXML
    private Label accountSettingsUsernameLabel;
    @FXML
    private Label accountSettingsPasswordLabel;
    @FXML
    private Label accountSettingsEmailLabel;
    @FXML
    private Button accountSettingsSaveButton;
    @FXML
    private PasswordField accountSettingsPasswordTextField;
    @FXML
    private AnchorPane databaseSettingsPane;
    @FXML
    private TextField databaseSettingsURLTextField;
    @FXML
    private Label databaseSettingsURLLabel;
    @FXML
    private Label databaseSettingsUsernameLabel;
    @FXML
    private Label databaseSettingsPasswordLabel;
    @FXML
    private Button databaseSettingsConnectButton;
    @FXML
    private TextField databaseSettingsUsernameTextField;
    @FXML
    private PasswordField databaseSettingsPasswordTextField;
    @FXML
    private AnchorPane menuPane4;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private AnchorPane loginElementsPane;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginUsernameLabel;
    @FXML
    private Label loginPasswordLabel;
    @FXML
    private Button signUpButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginWarning;
    @FXML
    private Label settingsWarningLabel;
    @FXML
    private Label databaseSettingsConnectionStatusLabel;
    @FXML
    private ProgressIndicator databaseSettingsConnectionProgressIndicator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting...");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml"));
        primaryStage.setTitle("Main");
        Scene scene = new Scene(root, 800, 500, Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() - xOffset);
            primaryStage.setY(mouseEvent.getScreenY() - yOffset);
        });
        primaryStage.setMaximized(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("Closing...");
    }

    @FXML
    void initialize() throws SQLException {
        primaryAnchorPane.getStylesheets().add("CSS/DarkTheme.css");
        translate("English");
        conn = new ConnectionClass("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");

        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        accessModeColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("access_mode"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        idClientColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));
        patronymic.setCellValueFactory(new PropertyValueFactory<Client, String>("patronymic"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Client, Date>("birthDate"));
        passportSeries.setCellValueFactory(new PropertyValueFactory<Client, String>("passportSeries"));
        passportNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("passportNumber"));
        issuedBy.setCellValueFactory(new PropertyValueFactory<Client, String>("issuedBy"));
        issuedDate.setCellValueFactory(new PropertyValueFactory<Client, Date>("issuedDate"));
        birthPlace.setCellValueFactory(new PropertyValueFactory<Client, String>("birthPlace"));
        actualResidenceCity.setCellValueFactory(new PropertyValueFactory<Client, City>("actualResidenceCity"));
        actualResidenceAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("actualResidenceAddress"));
        homeNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("homeNumber"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("mobileNumber"));
        email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        job.setCellValueFactory(new PropertyValueFactory<Client, String>("job"));
        position.setCellValueFactory(new PropertyValueFactory<Client, String>("position"));
        registrationCity.setCellValueFactory(new PropertyValueFactory<Client, City>("registrationCity"));
        maritalStatus.setCellValueFactory(new PropertyValueFactory<Client, MaritalStatus>("maritalStatus"));
        citizenship.setCellValueFactory(new PropertyValueFactory<Client, Country>("citizenship"));
        disability.setCellValueFactory(new PropertyValueFactory<Client, Disability>("disability"));
        retiree.setCellValueFactory(new PropertyValueFactory<Client, Boolean>("retiree"));
        monthlyIncome.setCellValueFactory(new PropertyValueFactory<Client, Double>("monthlyIncome"));
        idNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("idNumber"));
        
        initUsersData();
        initClientsData();


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
        menuPane31.getStyleClass().add("elementsPane");
        accountSettingsPane.getStyleClass().add("elementsPane");
        databaseSettingsPane.getStyleClass().add("elementsPane");
        workPane.getStyleClass().add("workPane");
        loginPane.getStyleClass().add("loginPane");
        primaryAnchorPane.getStyleClass().add("primaryAnchorPane");
        primaryAnchorPane.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case DIGIT1:
                    if (currentUser.getAccessMode() == 1)
                        menuAdminButton1.fire();
                    else
                        menuUserButton1.fire();
                    break;
                case DIGIT2:
                    if (currentUser.getAccessMode() == 1)
                        menuAdminButton2.fire();
                    else
                        menuUserButton2.fire();
                    break;
                case DIGIT3:
                    if (currentUser.getAccessMode() == 1)
                        menuAdminButton3.fire();
                    else
                        menuUserButton3.fire();
                    break;
                case DIGIT4:
                    if (currentUser.getAccessMode() == 1)
                        menuAdminButton4.fire();
                    else
                        menuUserButton4.fire();
                    break;
                case ESCAPE:
                    logoutButtonAdmin.fire();
                    break;
            }
        });
        leftAnchorPane.getStyleClass().add("leftAnchorPane");
        rightAnchorPane.getStyleClass().add("rightAnchorPane");
        createUser_AnchorPane.getStyleClass().add("elementsPane");
        changeUser_AnchorPane.getStyleClass().add("elementsPane");
        deleteUser_AnchorPane.getStyleClass().add("elementsPane");
        clientManagementAnchorPane.getStyleClass().add("clientManagementScrollPane");
        clientManagementScrollPane.getStyleClass().add("clientManagementScrollPane");
        clientManagementScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        fixImage.getStyleClass().add("fixImage");


        hideButton.getStyleClass().add("hideButton");
        minimizeButton.getStyleClass().add("minimizeButton");
        exitButton.getStyleClass().add("exitButton");

        hideButton.setOnAction(actionEvent -> {
            Stage stage2 = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage2.setIconified(true);
        });
        minimizeButton.setOnAction(actionEvent -> minimize());
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
        logoutButtonAdmin.setFocusTraversable(false);
        logoutButtonUser.setFocusTraversable(false);
        clientManagementScrollPane.setFocusTraversable(false);

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
        logoutButtonAdmin.getStyleClass().add("logoutButton");
        logoutButtonUser.getStyleClass().add("logoutButton");
        usersTable.getStyleClass().add("usersTable");

        loginPane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER && !(usernameField.getText().equals("") || passwordField.getText().equals("")))
                loginButton.fire();
            if (keyEvent.getCode() == KeyCode.ESCAPE)
                exitButton.fire();
        });
        usernameField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.TAB) {
                passwordField.requestFocus();
                passwordField.selectAll();
            }
        });
        passwordField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.TAB) {
                usernameField.requestFocus();
                usernameField.selectAll();
            }
        });

        changeUser_AnchorPane_Id.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                changeUser_AnchorPane_IdSubmitButton.fire();
        });
        changeUser_AnchorPane_Id.textProperty().addListener((observable, oldValue, newValue) ->
            changeUser_AnchorPane_Id.setStyle("-fx-border-color: transparent"));
        changeUser_AnchorPane_Id.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (!StringUtils.isStrictlyNumeric(changeUser_AnchorPane_Id.getText()) && !changeUser_AnchorPane_Id.getText().equals(""))
                    changeUser_AnchorPane_Id.setStyle("-fx-border-color: rgb(255,13,19)");
        });


        changeUser_AnchorPane_Username.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                changeUserButton.fire();
            changeUser_AnchorPane_Username.setStyle("-fx-border-color: transparent");
        });
        changeUser_AnchorPane_Username.textProperty().addListener((observable, oldValue, newValue) -> changeUser_AnchorPane_Username.setStyle("-fx-border-color: transparent"));
        changeUser_AnchorPane_Username.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (changeUser_AnchorPane_Username.getText().length() < 3 && !changeUser_AnchorPane_Username.getText().equals(""))
                    changeUser_AnchorPane_Username.setStyle("-fx-border-color: rgb(255,13,19)");
        });


        changeUser_AnchorPane_Password.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                changeUserButton.fire();
            changeUser_AnchorPane_Password.setStyle("-fx-border-color: transparent");
        });
        changeUser_AnchorPane_Password.textProperty().addListener((observable, oldValue, newValue) -> changeUser_AnchorPane_Password.setStyle("-fx-border-color: transparent"));
        changeUser_AnchorPane_Password.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (changeUser_AnchorPane_Password.getText().length() < 3 && !changeUser_AnchorPane_Password.getText().equals(""))
                    changeUser_AnchorPane_Password.setStyle("-fx-border-color: rgb(255,13,19)");
        });

        changeUser_AnchorPane_Email.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                changeUserButton.fire();
            changeUser_AnchorPane_Email.setStyle("-fx-border-color: transparent");
        });
        changeUser_AnchorPane_Email.textProperty().addListener((observable, oldValue, newValue) -> changeUser_AnchorPane_Email.setStyle("-fx-border-color: transparent"));
        changeUser_AnchorPane_Email.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (!changeUser_AnchorPane_Email.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+))") && !changeUser_AnchorPane_Email.getText().equals(""))
                    changeUser_AnchorPane_Email.setStyle("-fx-border-color: rgb(255,13,19)");
        });

        createUser_AnchorPane_Username.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                createUserButton.fire();
            createUser_AnchorPane_Username.setStyle("-fx-border-color: transparent");
        });
        createUser_AnchorPane_Username.textProperty().addListener((observable, oldValue, newValue) -> createUser_AnchorPane_Username.setStyle("-fx-border-color: transparent"));
        createUser_AnchorPane_Username.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (createUser_AnchorPane_Username.getText().length() < 3 && !createUser_AnchorPane_Username.getText().equals(""))
                    createUser_AnchorPane_Username.setStyle("-fx-border-color: rgb(255,13,19)");
        });

        createUser_AnchorPane_Password.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                createUserButton.fire();
            createUser_AnchorPane_Password.setStyle("-fx-border-color: transparent");
        });
        createUser_AnchorPane_Password.textProperty().addListener((observable, oldValue, newValue) -> createUser_AnchorPane_Password.setStyle("-fx-border-color: transparent"));
        createUser_AnchorPane_Password.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (createUser_AnchorPane_Password.getText().length() < 3 && !createUser_AnchorPane_Password.getText().equals(""))
                    createUser_AnchorPane_Password.setStyle("-fx-border-color: rgb(255,13,19)");
        });

        createUser_AnchorPane_Email.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                createUserButton.fire();
            createUser_AnchorPane_Email.setStyle("-fx-border-color: transparent");
        });
        createUser_AnchorPane_Email.textProperty().addListener((observable, oldValue, newValue) -> createUser_AnchorPane_Email.setStyle("-fx-border-color: transparent"));
        createUser_AnchorPane_Email.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (!createUser_AnchorPane_Email.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+))") && !createUser_AnchorPane_Email.getText().equals(""))
                    createUser_AnchorPane_Email.setStyle("-fx-border-color: rgb(255,13,19)");
        });

        deleteUserTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                deleteUserButton.fire();
            deleteUserTextField.setStyle("-fx-border-color: transparent");
        });
        deleteUserTextField.textProperty().addListener((observable, oldValue, newValue) -> deleteUserTextField.setStyle("-fx-border-color: transparent"));
        deleteUserTextField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            System.out.println(oldPropertyValue + " -> " + newPropertyValue);
            if (!newPropertyValue)
                if (deleteUserTextField.getText().matches("^[0-9]+(,[0-9]+)*$"))
                    deleteUserTextField.setStyle("-fx-border-color: rgb(255,13,19)");
        });

        accountSettingsSaveButton.setOnAction(actionEvent -> changeAccountData());
        databaseSettingsURLTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                databaseSettingsConnectButton.fire();
            }
        });
        databaseSettingsUsernameTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                databaseSettingsConnectButton.fire();
            }
        });
        databaseSettingsPasswordTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                databaseSettingsConnectButton.fire();
            }
        });
        accountSettingsUsernameTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                accountSettingsSaveButton.fire();
            }
        });
        accountSettingsPasswordTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                accountSettingsSaveButton.fire();
            }
        });
        accountSettingsEmailTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                accountSettingsSaveButton.fire();
            }
        });

        loginButton.setOnAction(actionEvent -> {
            loginWarning.setStyle("-fx-text-fill: #d85751");
            boolean was = false;
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            if (!(enteredPassword.length() < 3 || enteredUsername.length() < 3)) {
                if (conn.isConnected()) {
                    for (User u : usersData)
                        if (enteredUsername.equals(u.getUsername()) && enteredPassword.equals(u.getPassword())) {
                            was = true;
                            currentUser = u;
                            break;
                        }

                    if (was) {
                        try {
                            loginSuccess();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else
                        loginWarning.setText("Wrong login/password.");
                } else
                    loginWarning.setText("No connection.");
            } else
                loginWarning.setText("Username/password must be at least 3 characters");
        });
        signUpButton.setOnAction(actionEvent -> registration_User(loginWarning, usernameField.getText(), passwordField.getText()));
        logoutButtonAdmin.setOnAction(actionEvent -> {
            saveLastConfig();
            loginBegin();
        });
        logoutButtonAdmin.setOnAction(actionEvent -> {
            saveLastConfig();
            loginBegin();
        });
        changeUser_AnchorPane_IdSubmitButton.setOnAction(actionEvent -> submitId());
        changeUserButton.setOnAction(actionEvent -> changeUser());
        createUserButton.setOnAction(actionEvent -> registration_Admin(null, createUser_AnchorPane_Username.getText(), createUser_AnchorPane_Password.getText(), createUser_AnchorPane_Email.getText(), createUser_AnchorPane_AccessMode_MenuButton.getText()));
        deleteUserButton.setOnAction(actionEvent -> deleteUsers());

        searchField.setPromptText("Search...");
        criteriaMenuItem_Id.setOnAction(actionEvent -> criteriaButton.setText("Id"));
        criteriaMenuItem_Access.setOnAction(actionEvent -> criteriaButton.setText("Access"));
        criteriaMenuItem_Username.setOnAction(actionEvent -> criteriaButton.setText("Username"));
        criteriaMenuItem_Password.setOnAction(actionEvent -> criteriaButton.setText("Password"));
        criteriaMenuItem_Email.setOnAction(actionEvent -> criteriaButton.setText("E-mail"));
        createUser_AccessMenuItem_User.setOnAction(actionEvent -> {
            if (currentLanguage.equals("English"))
                createUser_AnchorPane_AccessMode_MenuButton.setText("User");
            if (currentLanguage.equals("Russian"))
                createUser_AnchorPane_AccessMode_MenuButton.setText("Пользователь");
        });
        createUser_AccessMenuItem_Admin.setOnAction(actionEvent -> {
            if (currentLanguage.equals("English"))
                createUser_AnchorPane_AccessMode_MenuButton.setText("Admin");
            if (currentLanguage.equals("Russian"))
                createUser_AnchorPane_AccessMode_MenuButton.setText("Администратор");
        });
        changeUser_AccessMenuItem_User.setOnAction(actionEvent -> {
            if (currentLanguage.equals("English"))
                changeUser_AnchorPane_AccessMode_MenuButton.setText("User");
            if (currentLanguage.equals("Russian"))
                changeUser_AnchorPane_AccessMode_MenuButton.setText("Пользователь");
        });
        changeUser_AccessMenuItem_Admin.setOnAction(actionEvent -> {
            if (currentLanguage.equals("English"))
                changeUser_AnchorPane_AccessMode_MenuButton.setText("Admin");
            if (currentLanguage.equals("Russian"))
                changeUser_AnchorPane_AccessMode_MenuButton.setText("Администратор");
        });


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
        themeItem_Light.setDisable(true);

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
            if (keyEvent.getCode() == KeyCode.ENTER)
                searchButton.fire();
        });
        searchButton.setOnAction(actionEvent -> {
            try {
                initUsersData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!searchField.getText().equals("")) {
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
        databaseSettingsConnectButton.setOnAction(actionEvent -> newConnection());

        loadLastConfig();
        loginBegin();
    }

    private void loadLastConfig() {
        try {
            File lastConfig = new File("src/main/java/sample/Controls/lastConfig.txt");
            BufferedReader reader = new BufferedReader(new FileReader(lastConfig));
            String text = reader.readLine();
            for (User u : usersData)
                if (text.equals(u.getUsername())) {
                    translate(u.getLanguage());
                    setTheme(u.getTheme());
                }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveLastConfig() {
        try {
            if (currentUser != null) {
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
        switch (language) {
            case "English":
                currentLanguage = "English";
                if (currentUser != null)
                    currentUser.setLanguageDB(conn, "English");
                searchField.setPromptText("Search...");
                languageButton.setText("English");
                loginUsernameLabel.setText("Username");
                loginPasswordLabel.setText("Password");
                loginButton.setText("Log In");
                menuAdminButton1.setText(" 1 User management");
                menuUserButton1.setText(" 1 User management");
                menuAdminButton2.setText(" 2 Client management");
                menuUserButton2.setText(" 2 Client management");
                menuAdminButton3.setText(" 3 Settings");
                menuUserButton3.setText(" 3 Settings");
                menuAdminButton4.setText(" 4 Information");
                menuUserButton4.setText(" 4 Information");
                logoutButtonAdmin.setText(" 5 Log Out");
                logoutButtonUser.setText(" 5 Log Out");


                menuPane1_DBLabel.setText("Database connection");
                searchButton.setText("Search");

                languageLabel.setText("Language");
                languageItem_Russian.setText("Russian");
                languageItem_English.setText("English");
                themeLabel.setText("Theme");
                customizationLabel.setText("Customization");
                accountSettingsLabel.setText("Account");
                databaseSettingsLabel.setText("Database connection");
                accountSettingsSaveButton.setText("Save");
                databaseSettingsConnectButton.setText("Connect");


                createUser_AnchorPane_Username.setPromptText("Username");
                if (createUser_AnchorPane_AccessMode_MenuButton.getText().equals("Пользователь")
                        || createUser_AnchorPane_AccessMode_MenuButton.getText().equals("User"))
                    createUser_AnchorPane_AccessMode_MenuButton.setText("User");
                if (createUser_AnchorPane_AccessMode_MenuButton.getText().equals("Администратор")
                        || createUser_AnchorPane_AccessMode_MenuButton.getText().equals("Admin"))
                    createUser_AnchorPane_AccessMode_MenuButton.setText("Admin");
                createUser_AccessMenuItem_User.setText("User");
                createUser_AccessMenuItem_Admin.setText("Admin");
                createUser_AnchorPane_Password.setPromptText("Password");
                createUser_AnchorPane_Email.setPromptText("E-Mail");
                createUserButton.setText("Add");

                changeUser_AnchorPane_Username.setPromptText("Username");
                changeUser_AnchorPane_Password.setPromptText("Password");
                changeUser_AnchorPane_Email.setPromptText("E-Mail");
                changeUser_AnchorPane_AccessMode_MenuButton.setText("User");
                changeUser_AccessMenuItem_User.setText("User");
                changeUser_AccessMenuItem_Admin.setText("Admin");
                changeUserButton.setText("Save");

                deleteUserLabel.setText("ID's to delete:");
                deleteUserButton.setText("Delete");

                break;
            case "Russian":
                currentLanguage = "Russian";
                if (currentUser != null)
                    currentUser.setLanguageDB(conn, "Russian");
                searchField.setPromptText("Искать...");
                languageButton.setText("Русский");
                loginUsernameLabel.setText("Имя");
                loginPasswordLabel.setText("Пароль");
                loginButton.setText("Войти");
                menuAdminButton1.setText(" 1 Управление пользователями");
                menuUserButton1.setText(" 1 Управление пользователями");
                menuAdminButton2.setText(" 2 Управление клиентами");
                menuUserButton2.setText(" 2 Управление клиентами");
                menuAdminButton3.setText(" 3 Настройки");
                menuUserButton3.setText(" 3 Настройки");
                menuAdminButton4.setText(" 4 Информация");
                menuUserButton4.setText(" 4 Информация");
                logoutButtonAdmin.setText(" 5 Выйти");
                logoutButtonUser.setText(" 5 Выйти");

                menuPane1_DBLabel.setText("Соединение с БД");
                searchButton.setText("Поиск");

                languageLabel.setText("Язык");
                languageItem_Russian.setText("Русский");
                languageItem_English.setText("Английский");
                themeLabel.setText("Тема");
                customizationLabel.setText("Кастомизация");
                accountSettingsLabel.setText("Аккаунт");
                databaseSettingsLabel.setText("Соединение с БД");
                accountSettingsSaveButton.setText("Сохранить");
                databaseSettingsConnectButton.setText("Подключить");

                createUser_AnchorPane_Username.setPromptText("Имя пользователя");
                if (createUser_AnchorPane_AccessMode_MenuButton.getText().equals("Пользователь")
                        || createUser_AnchorPane_AccessMode_MenuButton.getText().equals("User"))
                    createUser_AnchorPane_AccessMode_MenuButton.setText("Пользователь");
                if (createUser_AnchorPane_AccessMode_MenuButton.getText().equals("Администратор")
                        || createUser_AnchorPane_AccessMode_MenuButton.getText().equals("Admin"))
                    createUser_AnchorPane_AccessMode_MenuButton.setText("Администратор");
                createUser_AccessMenuItem_User.setText("Пользователь");
                createUser_AccessMenuItem_Admin.setText("Администратор");
                createUser_AnchorPane_Password.setPromptText("Пароль");
                createUser_AnchorPane_Email.setPromptText("Электронный адрес");
                createUserButton.setText("Добавить");

                changeUser_AnchorPane_Username.setPromptText("Имя пользователя");
                changeUser_AnchorPane_Password.setPromptText("Пароль");
                changeUser_AnchorPane_Email.setPromptText("Электронный адрес");
                changeUser_AnchorPane_AccessMode_MenuButton.setText("Пользователь");
                changeUser_AccessMenuItem_User.setText("Пользователь");
                changeUser_AccessMenuItem_Admin.setText("Администратор");
                changeUserButton.setText("Сохранить");

                deleteUserLabel.setText("Удаляемые ID:");
                deleteUserButton.setText("Удалить");

                break;
        }
    }

    private void setTheme(String theme) throws SQLException {
        theme = theme.trim();
        theme = theme.toLowerCase();
        switch (theme) {
            case "dark":
                themeButton.setText("Dark");
                currentTheme = "Dark";
                if (currentUser != null)
                    currentUser.setThemeDB(conn, "Dark");
                primaryAnchorPane.getStylesheets().clear();
                primaryAnchorPane.getStylesheets().add("CSS/DarkTheme.css");
                fixImage.setImage(new Image("assets/fix-black.png"));

                break;
            case "light":
                themeButton.setText("Light");
                currentTheme = "Light";
                if (currentUser != null)
                    currentUser.setThemeDB(conn, "Light");
                primaryAnchorPane.getStylesheets().clear();
                primaryAnchorPane.getStylesheets().add("CSS/LightTheme.css");
                fixImage.setImage(new Image("assets/fix-white.png"));

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
        if(pane == menuPane2) {
            menuPane2.setVisible(true);
            clientManagementScrollPane.setVisible(true);
            clientManagementAnchorPane.requestFocus();
        } else {
            pane.setVisible(true);
            pane.requestFocus();
        }
        if (currentUser.getTheme().equals("Dark"))
            menuItem.setStyle("-fx-background-image: url(assets/selected-white.png);" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 2pt 25pt;" +
                    "-fx-background-position: 1 1;");
        else
            menuItem.setStyle("-fx-background-image: url(assets/selected-black.png);" +
                    "-fx-background-repeat: no-repeat;" +
                    "-fx-background-size: 2pt 25pt;" +
                    "-fx-background-position: 1 1;");
    }

    private void loginBegin() {
        menuAdmin.setVisible(false);
        menuUser.setVisible(false);
        leftAnchorPane.setDisable(true);
        currentUser = null;
        currentUserLabelAdmin.setText("");
        currentUserLabelUser.setText("");
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
            databaseSettingsPane.setDisable(false);
        } else {
            menuUser.setVisible(true);
            menuUserButton1.fire();
            databaseSettingsPane.setDisable(true);
        }
        translate(currentUser.getLanguage());
        setTheme(currentUser.getTheme());
        currentTheme = currentUser.getTheme();
        currentLanguage = currentUser.getLanguage();
        currentUserLabelAdmin.setText(currentUser.getUsername());
        currentUserLabelUser.setText(currentUser.getUsername());
        accountSettingsUsernameTextField.setText(currentUser.getUsername());
        accountSettingsPasswordTextField.setText(currentUser.getPassword());
        accountSettingsEmailTextField.setText(currentUser.getEmail());

        loginPane.setVisible(false);
        loginWarning.setText("");
    }

    private void setAllInvisible() {
        menuPane1.setVisible(false);
        menuPane2.setVisible(false);
        menuPane3.setVisible(false);
        menuPane4.setVisible(false);
        clientManagementScrollPane.setVisible(false);
        loginPane.setVisible(false);
    }

    private void changeAccountData() {
        boolean was = false;
        String enteredUsername = accountSettingsUsernameTextField.getText();
        String enteredPassword = accountSettingsPasswordTextField.getText();
        String enteredEmail = accountSettingsEmailTextField.getText();

        settingsWarningLabel.setStyle("-fx-text-fill: #d85751");
        if (!(enteredPassword.length() < 3 || enteredUsername.length() < 3)) {
            if (conn.isConnected()) {
                for (User u : usersData)
                    if (enteredUsername.equals(u.getUsername()) && currentUser.getId() != u.getId()) {
                        was = true;
                        break;
                    }

                if (!was) {
                    try {
                        String prepStat = "UPDATE `test`.`users` SET `name` = ?, `password` = ?, `email` = ? WHERE (`id` = ?);";
                        PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                        preparedStatement.setString(1, enteredUsername);
                        preparedStatement.setString(2, enteredPassword);
                        preparedStatement.setString(3, enteredEmail);
                        preparedStatement.setInt(4, currentUser.getId());
                        preparedStatement.execute();
                        initUsersData();
                        settingsWarningLabel.setStyle("-fx-text-fill: #7f8e55");
                        settingsWarningLabel.setText("Account information saved");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else
                    settingsWarningLabel.setText("Username is not free");
            } else
                settingsWarningLabel.setText("No connection");
        } else
            settingsWarningLabel.setText("Username/password must be at least 3 characters");
    }

    private void changeUser() {
        boolean was = false;
        if (submitId()) {
            int enteredId = Integer.parseInt(changeUser_AnchorPane_Id.getText());
            String enteredUsername = changeUser_AnchorPane_Username.getText();
            String enteredPassword = changeUser_AnchorPane_Password.getText();
            String enteredEmail = changeUser_AnchorPane_Email.getText();
            int enteredAccessMode = (changeUser_AnchorPane_AccessMode_MenuButton.getText().equals("Admin") ||
                    changeUser_AnchorPane_AccessMode_MenuButton.getText().equals("Администратор")) ? 1 : 0;

            if (!(enteredPassword.length() < 3 && enteredUsername.length() < 3)) {
                if (conn.isConnected()) {
                    for (User u : usersData)
                        if (enteredUsername.equals(u.getUsername())) {
                            was = true;
                            break;
                        }

                    if (was) {
                        changeUser_AnchorPane_Id.setText("");
                        changeUser_AnchorPane_Username.setText("");
                        changeUser_AnchorPane_Password.setText("");
                        changeUser_AnchorPane_Email.setText("");
                        try {
                            String prepStat = "UPDATE `test`.`users` SET `name` = ?, `password` = ?, `email` = ?, `access_mode` = ? WHERE (`id` = ?);";
                            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                            preparedStatement.setString(1, enteredUsername);
                            preparedStatement.setString(2, enteredPassword);
                            preparedStatement.setString(3, enteredEmail);
                            preparedStatement.setInt(4, enteredAccessMode);
                            preparedStatement.setInt(5, enteredId);
                            preparedStatement.execute();
                            initUsersData();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else
            System.out.println("Unknown ID");
    }

    private boolean submitId() {
        if (StringUtils.isStrictlyNumeric(changeUser_AnchorPane_Id.getText())) {
            int id = Integer.parseInt(changeUser_AnchorPane_Id.getText());
            if (conn.isConnected())
                for (User u : usersData)
                    if (id == u.getId()) {
                        changeUser_AnchorPane_Username.setText(u.getUsername());
                        changeUser_AnchorPane_Password.setText(u.getPassword());
                        changeUser_AnchorPane_Email.setText(u.getEmail());
                        if (currentLanguage.equals("English"))
                            changeUser_AnchorPane_AccessMode_MenuButton.setText((u.getAccessMode() == 0) ? "User" : "Admin");
                        if (currentLanguage.equals("Russian"))
                            changeUser_AnchorPane_AccessMode_MenuButton.setText((u.getAccessMode() == 0) ? "Пользователь" : "Администратор");
                        return true;
                    }
        }
        System.out.println("WRONG ID");
        return false;
    }

    private void deleteUsers() {
        if (deleteUserTextField.getText().equals("all")) {
            deleteUserTextField.setText("");
            try {
                String prepStat = "DELETE FROM `test`.`users` WHERE (`id` > -1)";
                PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                preparedStatement.execute();
                initUsersData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String[] ids = deleteUserTextField.getText().split(",");
        for (String id : ids) {
            if (StringUtils.isStrictlyNumeric(id)) {
                deleteUserTextField.setText("");
                try {
                    String prepStat = "DELETE FROM `test`.`users` WHERE (`id` = ?)";
                    PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                    preparedStatement.setInt(1, Integer.parseInt(id));
                    preparedStatement.execute();
                    initUsersData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void registration_Admin(Label warningLabel, String username, String password, String email, String accessMode) {
        int access_mode = accessMode.equals("User") || accessMode.equals("Пользователь") ? 0 : 1;
        if (warningLabel == null)
            warningLabel = new Label();
        warningLabel.setStyle("-fx-text-fill: #d85751");
        boolean was = false;

        if (!(password.length() < 3 || username.length() < 3)) {
            if (conn.isConnected()) {
                for (User u : usersData)
                    if (username.equals(u.getUsername())) {
                        was = true;
                        break;
                    }

                if (!was) {
                    createUser_AnchorPane_Username.setText("");
                    createUser_AnchorPane_Password.setText("");
                    createUser_AnchorPane_Email.setText("");
                    try {
                        String prepStat = "INSERT INTO `test`.`users` (`name`, `password`, `email`,`access_mode`) VALUES (?, ?, ?, ?)";
                        PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        preparedStatement.setString(3, email);
                        preparedStatement.setInt(4, access_mode);
                        preparedStatement.execute();
                        initUsersData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    warningLabel.setStyle("-fx-text-fill: #7f8e55");
                    warningLabel.setText(username + " registered");
                } else
                    warningLabel.setText("Username is not free");
            } else
                warningLabel.setText("No connection");
        } else {
            warningLabel.setText("Username/password must be at least 3 characters");
        }
    }

    private void registration_User(Label warningLabel, String username, String password) {
        if (warningLabel == null)
            warningLabel = new Label();
        warningLabel.setStyle("-fx-text-fill: #d85751");
        boolean was = false;

        if (!(password.length() < 3 || username.length() < 3)) {
            if (conn.isConnected()) {
                for (User u : usersData)
                    if (username.equals(u.getUsername())) {
                        was = true;
                        break;
                    }

                if (!was) {
                    try {
                        String prepStat = "INSERT INTO `test`.`users` (`name`, `password`) VALUES (?, ?)";
                        PreparedStatement preparedStatement = conn.getConnection().prepareStatement(prepStat);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        preparedStatement.execute();
                        initUsersData();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    warningLabel.setStyle("-fx-text-fill: #7f8e55");
                    warningLabel.setText(username + " registered");
                } else
                    warningLabel.setText("Username is not free");
            } else
                warningLabel.setText("No connection");
        } else
            warningLabel.setText("Username/password must be at least 3 characters");
    }

    private void newConnection() {
        String enteredURL = databaseSettingsURLTextField.getText();
        String enteredUsername = databaseSettingsUsernameTextField.getText();
        String enteredPassword = databaseSettingsPasswordTextField.getText();

        databaseSettingsConnectionStatusLabel.setStyle("-fx-text-fill: #d85751");
        if (!(enteredUsername.length() < 3 || enteredPassword.length() < 3))
            switch (conn.setConnection(enteredURL, enteredUsername, enteredPassword)) {
                case 1:
                    databaseSettingsConnectionStatusLabel.setStyle("-fx-text-fill: #7f8e55");
                    databaseSettingsConnectionStatusLabel.setText("Connected!");
                    break;
                case 0:
                    databaseSettingsConnectionStatusLabel.setText("Wrong URL!");
                    break;
                case 1045:
                    databaseSettingsConnectionStatusLabel.setText("Wrong username/password!");
                    break;
                case 555:
                    databaseSettingsConnectionStatusLabel.setText("???");
                    break;
            }
    }

    private void minimize() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        if (stage.isMaximized() && theme == 0) {
            stage.setMaximized(false);
            usersTable.setPrefHeight(150d);
            clientsTable.setPrefWidth(513d);
            createUser_AnchorPane.setLayoutY(212);
            if (currentTheme.equals("Dark"))
                minimizeButton.setStyle("-fx-background-image: url(assets/expand-white.png)");
            else
                minimizeButton.setStyle("-fx-background-image: url(assets/expand-black.png)");
            loginElementsPane.setLayoutX(250);
            loginElementsPane.setLayoutY(176);
            loginWarning.setLayoutX(45);
            loginWarning.setLayoutY(117);
            searchField.setPrefWidth(136);

            clientManagementScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        } else {
            stage.setMaximized(true);
            usersTable.setPrefHeight(606d);
            clientsTable.setPrefWidth(1250d);
            createUser_AnchorPane.setLayoutY(667);
            if (currentTheme.equals("Dark"))
                minimizeButton.setStyle("-fx-background-image: url(assets/minimize-white.png)");
            else
                minimizeButton.setStyle("-fx-background-image: url(assets/minimize-black.png)");
            loginElementsPane.setLayoutX(610);
            loginElementsPane.setLayoutY(350);
            loginWarning.setLayoutX(405);
            loginWarning.setLayoutY(290);
            searchField.setPrefWidth(350);

            clientManagementScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        }
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
                while (resultSetConfigs.next())
                    if (resultSetConfigs.getInt("userId") == user.getId()) {
                        user.setTheme(resultSetConfigs.getString("theme"));
                        user.setLanguage(resultSetConfigs.getString("language"));
                    }
                usersData.add(user);
                System.out.println(user);
            }
        } else
            connectionIndicator.setStyle("-fx-background-image: url(assets/indicator-red.png)");
    }
    private void initClientsData() throws SQLException {
        if (conn.isConnected()) {
            connectionIndicator.setStyle("-fx-background-image: url(assets/indicator-green.png)");
            Statement statement = conn.getConnection().createStatement();
            Statement statement2 = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            clientsTable.setItems(clientsData);
            clientsData.clear();

            System.out.println();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("Id"));
                client.setName(resultSet.getString("Name"));
                client.setSurname(resultSet.getString("Surname"));
                client.setPatronymic(resultSet.getString("Patronymic"));
                client.setBirthDate(resultSet.getDate("Birth_date"));
                client.setPassportSeries(resultSet.getString("Passport_series"));
                client.setPassportNumber(resultSet.getString("Passport_number"));
                client.setIssuedBy(resultSet.getString("Issued_by"));
                client.setIssuedDate(resultSet.getDate("Issued_date"));
                client.setBirthPlace(resultSet.getString("Birth_place"));
                client.setActualResidenceCity(City.valueOf(resultSet.getString("Actual_residence_city")));
                client.setActualResidenceAddress(resultSet.getString("Actual_residence_address"));
                client.setHomeNumber(resultSet.getString("Home_number"));
                client.setMobileNumber(resultSet.getString("Mobile_number"));
                client.setEmail(resultSet.getString("Email"));
                client.setJob(resultSet.getString("Job"));
                client.setPosition(resultSet.getString("Position"));
                client.setRegistrationCity(City.valueOf(resultSet.getString("Registration_city")));
                client.setMaritalStatus(MaritalStatus.valueOf(resultSet.getString("Marital_status")));
                client.setCitizenship(Country.valueOf(resultSet.getString("Citizenship")));
                client.setDisability(Disability.valueOf(resultSet.getString("Disability")));
                client.setRetiree(resultSet.getBoolean("Is_retiree"));
                client.setMonthlyIncome(resultSet.getDouble("Monthly_income"));
                client.setIdNumber(resultSet.getString("Id_number"));
                clientsData.add(client);
                System.out.println(client); //информация выводится но невидимым шрифтом
            }
        } else
            connectionIndicator.setStyle("-fx-background-image: url(assets/indicator-red.png)");
    }
}
