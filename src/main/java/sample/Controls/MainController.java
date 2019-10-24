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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sample.Connectivity.ConnectionClass;
import sample.enums.Disability;
import sample.enums.MaritalStatus;
import sample.enums.Retiree;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

@SuppressWarnings("ALL")
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
    private TableColumn<Client, Integer> idClientColumn;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> surnameColumn;
    @FXML
    private TableColumn<Client, String> patronymicColumn;
    @FXML
    private TableColumn<Client, String> birthDateColumn;
    @FXML
    private TableColumn<Client, String> birthPlaceColumn;
    @FXML
    private TableColumn<Client, String> passportSeriesColumn;
    @FXML
    private TableColumn<Client, String> passportNumberColumn;
    @FXML
    private TableColumn<Client, String> issuedByColumn;
    @FXML
    private TableColumn<Client, String> issuedDateColumn;
    @FXML
    private TableColumn<Client, String> actualResidenceCityColumn;
    @FXML
    private TableColumn<Client, String> actualResidenceAddressColumn;
    @FXML
    private TableColumn<Client, String> homeNumberColumn;
    @FXML
    private TableColumn<Client, String> mobileNumberColumn;
    @FXML
    private TableColumn<Client, String> emailClientColumn;
    @FXML
    private TableColumn<Client, String> jobColumn;
    @FXML
    private TableColumn<Client, String> positionColumn;
    @FXML
    private TableColumn<Client, String> registrationCityColumn;
    //  @FXML
//  private TableColumn<Client, MaritalStatus> maritalStatusColumn;
//  private MenuItem maritalStatusMenuItemSingle;
//  private MenuItem maritalStatusMenuItemDivorced;
//  private MenuItem maritalStatusMenuItemMarried;
//  private MenuButton maritalStatusColumnMenuButton;
    private TableColumn maritalStatusColumn;
    private TableColumn disabilityColumn;
    private TableColumn retireeColumn;
    private TableColumn deleteColumn;


    @FXML
    private TableColumn<Client, String> citizenshipColumn;
    @FXML
    private TableColumn<Client, String> monthlyIncomeColumn;
    @FXML
    private TableColumn<Client, String> idNumberColumn;
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
    private TextField searchFieldClient;
    @FXML
    private MenuButton criteriaButtonClient;
    @FXML
    private MenuItem criteriaClientName;
    @FXML
    private MenuItem criteriaClientSurname;
    @FXML
    private MenuItem criteriaClientPatronymic;
    @FXML
    private MenuItem criteriaClientFIO;
    @FXML
    private MenuItem criteriaClientPassportSeries;
    @FXML
    private MenuItem criteriaClientPassportNumber;
    @FXML
    private MenuItem criteriaClientIssuedBy;
    @FXML
    private MenuItem criteriaClientIssuedDate;
    @FXML
    private MenuItem criteriaClientBirthDate;
    @FXML
    private MenuItem criteriaClientBirthPlace;
    @FXML
    private MenuItem criteriaClientActCity;
    @FXML
    private MenuItem criteriaClientActAddress;
    @FXML
    private MenuItem criteriaClientRegCity;
    @FXML
    private MenuItem criteriaClientJob;
    @FXML
    private MenuItem criteriaClientPosition;
    @FXML
    private MenuItem criteriaClientEmail;
    @FXML
    private MenuItem criteriaClientHomePhone;
    @FXML
    private MenuItem criteriaClientMobilePhone;
    @FXML
    private MenuItem criteriaClientDisability;
    @FXML
    private MenuItem criteriaClientRetiree;
    @FXML
    private MenuItem criteriaClientMonthlyIncome;
    @FXML
    private MenuItem criteriaClientIDNumber;
    @FXML
    private MenuItem criteriaClientMaritalStatus;
    @FXML
    private MenuItem criteriaClientID;
    @FXML
    private MenuItem criteriaClientCitizenship;
    @FXML
    private MenuItem criteriaClientMenuFIO;
    @FXML
    private MenuItem criteriaClientMenuPassport;
    @FXML
    private MenuItem criteriaClientMenuResidence;
    @FXML
    private MenuItem criteriaClientMenuJob;
    @FXML
    private MenuItem criteriaClientMenuContacts;
    @FXML
    private MenuItem criteriaClientMenuOther;
    @FXML
    private Button searchButtonClient;
    @FXML
    private Button resetSearchButtonClient;
    @FXML
    private ImageView fixImage;
    @FXML
    private AnchorPane createUser_AnchorPane;
    @FXML
    private AnchorPane createClient_AnchorPane;
    @FXML
    private AnchorPane createClient_AnchorPane_NameJobResidencePane;
    @FXML
    private AnchorPane createClient_AnchorPane_PassportDataPane;
    @FXML
    private AnchorPane createClient_AnchorPane_ContactsOtherPane;
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
    private ImageView fixImage2;
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
    private Label addClientLabel;
    @FXML
    private Button addClientButton;
    @FXML
    private Label databaseSettingsConnectionStatusLabel;
    @FXML
    private ProgressIndicator databaseSettingsConnectionProgressIndicator;

    private Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory;

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
        conn =
                new ConnectionClass("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                        "&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        accessModeColumn.setCellValueFactory(new PropertyValueFactory<>("access_mode"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


        idClientColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        passportSeriesColumn.setCellValueFactory(new PropertyValueFactory<>("passportSeries"));
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));
        issuedByColumn.setCellValueFactory(new PropertyValueFactory<>("issuedBy"));
        issuedDateColumn.setCellValueFactory(new PropertyValueFactory<>("issuedDate"));
        birthPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        actualResidenceCityColumn.setCellValueFactory(new PropertyValueFactory<>("actualResidenceCity"));
        actualResidenceAddressColumn.setCellValueFactory(new PropertyValueFactory<>("actualResidenceAddress"));
        homeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("homeNumber"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        emailClientColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("job"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        registrationCityColumn.setCellValueFactory(new PropertyValueFactory<>("registrationCity"));
        citizenshipColumn.setCellValueFactory(new PropertyValueFactory<>("citizenship"));
        monthlyIncomeColumn.setCellValueFactory(new PropertyValueFactory<>("monthlyIncome"));
        idNumberColumn.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        clientsTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);


        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("[а-яА-Я]{2,20}"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setNameDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("[а-яА-Я]{2,20}"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setSurnameDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        patronymicColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        patronymicColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("[а-яА-Я]{2,30}"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPatronymicDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        birthDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        birthDateColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            String s = t.getNewValue().trim();
            System.out.println(s);
            if (t.getNewValue().trim().matches("^\\d{4}[-/.](((0)[0-9])|((1)[0-2]))[-/.]([0-2][0-9]|(3)[0-1])$"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setBirthDateDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        passportSeriesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passportSeriesColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("[a-zA-Z]{2}"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPassportSeriesDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        passportNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passportNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("^\\d{7}$"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPassportNumberDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        issuedByColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        issuedByColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().length() > 4)
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setIssuedByDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        issuedDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        issuedDateColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            String s = t.getNewValue().trim();
            System.out.println(s);
            if (t.getNewValue().trim().matches("^\\d{4}[-/.](((0)[0-9])|((1)[0-2]))[-/.]([0-2][0-9]|(3)[0-1])$"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setIssuedDateDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        birthPlaceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        birthPlaceColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().length() > 4)
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setBirthPlaceDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        actualResidenceCityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        actualResidenceCityColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("[а-яА-Я]{2,20}"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setActualResidenceCityDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        actualResidenceAddressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        actualResidenceAddressColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().length() > 4)
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setActualResidenceAddressDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        homeNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        homeNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("^\\d{7}$"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setHomeNumberDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        mobileNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mobileNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("^(\\+375|375)?[\\s\\-]?\\(?(17|29|33|44)\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setMobileNumberDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        emailClientColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailClientColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("(?:[a-z0-9!_-]+(?:\\.[a-z0-9!_-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+))"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmailDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        jobColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        jobColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().length() > 4)
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setJobDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().length() > 0)
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setPositionDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        registrationCityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        registrationCityColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().length() > 4)
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setRegistrationCityDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        citizenshipColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        citizenshipColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().length() > 4)
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setCitizenshipDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        monthlyIncomeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        monthlyIncomeColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("^[0-9]+(\\.[0-9]+)?$"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setMonthlyIncomeDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        idNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idNumberColumn.setOnEditCommit((TableColumn.CellEditEvent<Client, String> t) -> {
            if (t.getNewValue().trim().matches("[A-Z0-9]{14}"))
                (t.getTableView().getItems().get(t.getTablePosition().getRow())).setIdNumberDB(conn, t.getNewValue());
            else {
                try {
                    initClientsData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        clientsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        initUsersData();
        initClientsData();
        addButtonsToTable();


        //Дальше - функционал элементов
        loginWarning.getStyleClass().add("loginWarning");
        connectionIndicator.getStyleClass().add("connectionIndicator");
        connectionIndicator.setOnAction(actionEvent -> {
            try {
                conn =
                        new ConnectionClass("jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                                "&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow", "root", "root");
                initUsersData();
                initClientsData();
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
        createClient_AnchorPane.getStyleClass().add("elementsPane");
        createClient_AnchorPane_NameJobResidencePane.getStyleClass().add("oneBorderPane");
        createClient_AnchorPane_PassportDataPane.getStyleClass().add("oneBorderPane");
        clientManagementAnchorPane.getStyleClass().add("clientManagementAnchorPane");
        clientManagementScrollPane.getStyleClass().add("clientManagementScrollPane");
        clientManagementScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        clientManagementScrollPane.setMinWidth(550);
        clientManagementScrollPane.setMaxWidth(1920);
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
                checkUsernamePassword(changeUser_AnchorPane_Username);
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
                checkUsernamePassword(changeUser_AnchorPane_Password);
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
                checkEmail(changeUser_AnchorPane_Email);
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
                checkUsernamePassword(createUser_AnchorPane_Username);
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
                checkUsernamePassword(createUser_AnchorPane_Password);
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
                checkEmail(createUser_AnchorPane_Email);
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
                checkDeleteField(deleteUserTextField);
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
        changeUserButton.setOnAction(actionEvent -> {
            System.out.println(changeUser_AnchorPane_Username.getText() + " " + changeUser_AnchorPane_Password.getText());
            checkEmail(changeUser_AnchorPane_Email);
            checkUsernamePassword(changeUser_AnchorPane_Username);
            checkUsernamePassword(changeUser_AnchorPane_Password);
            if (checkEmail(changeUser_AnchorPane_Email) && checkUsernamePassword(changeUser_AnchorPane_Username) && checkUsernamePassword(changeUser_AnchorPane_Password))
                changeUser();
        });
        createUserButton.setOnAction(actionEvent -> {
            checkEmail(createUser_AnchorPane_Email);
            checkUsernamePassword(createUser_AnchorPane_Username);
            checkUsernamePassword(createUser_AnchorPane_Password);
            if (checkEmail(createUser_AnchorPane_Email) && checkUsernamePassword(createUser_AnchorPane_Username) && checkUsernamePassword(createUser_AnchorPane_Password))
                registration_Admin(null, createUser_AnchorPane_Username.getText(), createUser_AnchorPane_Password.getText(), createUser_AnchorPane_Email.getText(), createUser_AnchorPane_AccessMode_MenuButton.getText());
        });
        deleteUserButton.setOnAction(actionEvent -> {
            if (checkDeleteField(deleteUserTextField))
                deleteUsers();
        });

        criteriaMenuItem_Id.setOnAction(actionEvent -> criteriaButton.setText("Id"));
        criteriaMenuItem_Access.setOnAction(actionEvent -> criteriaButton.setText("Access"));
        criteriaMenuItem_Username.setOnAction(actionEvent -> criteriaButton.setText("Username"));
        criteriaMenuItem_Password.setOnAction(actionEvent -> criteriaButton.setText("Password"));
        criteriaMenuItem_Email.setOnAction(actionEvent -> criteriaButton.setText("E-mail"));

        criteriaClientName.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientName.getText()));
        criteriaClientSurname.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientSurname.getText()));
        criteriaClientPatronymic.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientPatronymic.getText()));
        criteriaClientFIO.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientFIO.getText()));
        criteriaClientPassportSeries.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientPassportSeries.getText()));
        criteriaClientPassportNumber.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientPassportNumber.getText()));
        criteriaClientIssuedBy.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientIssuedBy.getText()));
        criteriaClientIssuedDate.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientIssuedDate.getText()));
        criteriaClientBirthDate.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientBirthDate.getText()));
        criteriaClientBirthPlace.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientBirthPlace.getText()));
        criteriaClientActCity.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientActCity.getText()));
        criteriaClientActAddress.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientActAddress.getText()));
        criteriaClientRegCity.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientRegCity.getText()));
        criteriaClientJob.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientJob.getText()));
        criteriaClientPosition.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientPosition.getText()));
        criteriaClientEmail.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientEmail.getText()));
        criteriaClientHomePhone.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientHomePhone.getText()));
        criteriaClientMobilePhone.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientMobilePhone.getText()));
        criteriaClientDisability.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientDisability.getText()));
        criteriaClientRetiree.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientRetiree.getText()));
        criteriaClientMonthlyIncome.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientMonthlyIncome.getText()));
        criteriaClientIDNumber.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientIDNumber.getText()));
        criteriaClientMaritalStatus.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientMaritalStatus.getText()));
        criteriaClientCitizenship.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientCitizenship.getText()));
        criteriaClientID.setOnAction(actionEvent -> criteriaButtonClient.setText(criteriaClientID.getText()));

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
        resetSearchButtonClient.getStyleClass().add("resetSearchButton");
        resetSearchButtonClient.setOnAction(actionEvent -> {
            try {
                searchFieldClient.clear();
                initClientsData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
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
        searchFieldClient.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                searchButtonClient.fire();
        });
        searchButton.setOnAction(actionEvent -> searchUser());
        searchButtonClient.setOnAction(actionEvent -> searchClient());
        databaseSettingsConnectButton.setOnAction(actionEvent -> newConnection());

        translate("English");
        loadLastConfig();
        loginBegin();
    } //INITIALIZE

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
                searchFieldClient.setPromptText("Search...");
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


                menuPane1_DBLabel.setText("Connection");
                searchButton.setText("Search");
                searchButtonClient.setText("Search");
                criteriaButtonClient.setText("ID");

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

                nameColumn.setText("Name");
                surnameColumn.setText("Surname");
                patronymicColumn.setText("Patronymic");
                birthDateColumn.setText("Birth date");
                birthPlaceColumn.setText("Birth place");
                passportSeriesColumn.setText("Passport series");
                passportNumberColumn.setText("Passport number");
                issuedByColumn.setText("Issued by");
                issuedDateColumn.setText("Issued date");
                actualResidenceCityColumn.setText("Act. residence city");
                actualResidenceAddressColumn.setText("Act. residence address");
                homeNumberColumn.setText("Home phone");
                mobileNumberColumn.setText("Mobile phone");
                emailClientColumn.setText("E-Mail");
                jobColumn.setText("Job");
                positionColumn.setText("Position");
                registrationCityColumn.setText("Registration city");
                maritalStatusColumn.setText("Marital status");
                citizenshipColumn.setText("Citizenship");
                disabilityColumn.setText("Disability");
                retireeColumn.setText("Retiree");
                monthlyIncomeColumn.setText("Monthly income");
                idNumberColumn.setText("ID number");

                criteriaClientName.setText("Name");
                criteriaClientSurname.setText("Surname");
                criteriaClientPatronymic.setText("Patronymic");
                criteriaClientFIO.setText("Full name");
                criteriaClientBirthDate.setText("Birth date");
                criteriaClientBirthPlace.setText("Birth place");
                criteriaClientPassportSeries.setText("Passport series");
                criteriaClientPassportNumber.setText("Passport number");
                criteriaClientIssuedBy.setText("Issued by");
                criteriaClientIssuedDate.setText("Issued date");
                criteriaClientActCity.setText("Act. residence city");
                criteriaClientActAddress.setText("Act. residence address");
                criteriaClientHomePhone.setText("Home phone");
                criteriaClientMobilePhone.setText("Mobile phone");
                criteriaClientEmail.setText("E-Mail");
                criteriaClientJob.setText("Job");
                criteriaClientPosition.setText("Position");
                criteriaClientRegCity.setText("Registration city");
                criteriaClientMaritalStatus.setText("Marital status");
                criteriaClientCitizenship.setText("Citizenship");
                criteriaClientDisability.setText("Disability");
                criteriaClientRetiree.setText("Retiree");
                criteriaClientMonthlyIncome.setText("Monthly income");
                criteriaClientIDNumber.setText("ID number");


                criteriaClientMenuFIO.setText("Full Name");
                criteriaClientMenuPassport.setText("Passport Data");
                criteriaClientMenuResidence.setText("Residence");
                criteriaClientMenuJob.setText("Job");
                criteriaClientMenuContacts.setText("Contacts");
                criteriaClientMenuOther.setText("Other");

                break;
            case "Russian":
                currentLanguage = "Russian";
                if (currentUser != null)
                    currentUser.setLanguageDB(conn, "Russian");
                searchField.setPromptText("Искать...");
                searchFieldClient.setPromptText("Искать...");
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

                menuPane1_DBLabel.setText("Соединение");
                searchButton.setText("Поиск");
                searchButtonClient.setText("Поиск");
                criteriaButtonClient.setText("ID");
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

                nameColumn.setText("Имя");
                surnameColumn.setText("Фамииля");
                patronymicColumn.setText("Отчество");
                birthDateColumn.setText("Дата рождения");
                birthPlaceColumn.setText("Место рождения");
                passportSeriesColumn.setText("Серия паспорта");
                passportNumberColumn.setText("Номер паспорта");
                issuedByColumn.setText("Орган выдачи");
                issuedDateColumn.setText("Дата выдачи");
                actualResidenceCityColumn.setText("Город проживания");
                actualResidenceAddressColumn.setText("Адрес проживания");
                homeNumberColumn.setText("Домашний телефон");
                mobileNumberColumn.setText("Мобильный телефон");
                emailClientColumn.setText("E-Mail");
                jobColumn.setText("Место работы");
                positionColumn.setText("Должность");
                registrationCityColumn.setText("Город прописки");
                maritalStatusColumn.setText("Семейное положение");
                citizenshipColumn.setText("Гражданство");
                disabilityColumn.setText("Инвалидность");
                retireeColumn.setText("Пенсионер");
                monthlyIncomeColumn.setText("Месячный доход");
                idNumberColumn.setText("Идент. номер");

                criteriaClientName.setText("Имя");
                criteriaClientSurname.setText("Фамииля");
                criteriaClientPatronymic.setText("Отчество");
                criteriaClientFIO.setText("ФИО");
                criteriaClientBirthDate.setText("Дата рождения");
                criteriaClientBirthPlace.setText("Место рождения");
                criteriaClientPassportSeries.setText("Серия паспорта");
                criteriaClientPassportNumber.setText("Номер паспорта");
                criteriaClientIssuedBy.setText("Орган выдачи");
                criteriaClientIssuedDate.setText("Дата выдачи");
                criteriaClientActCity.setText("Город проживания");
                criteriaClientActAddress.setText("Адрес проживания");
                criteriaClientHomePhone.setText("Домашний телефон");
                criteriaClientMobilePhone.setText("Мобильный телефон");
                criteriaClientEmail.setText("E-Mail");
                criteriaClientJob.setText("Место работы");
                criteriaClientPosition.setText("Должность");
                criteriaClientRegCity.setText("Город прописки");
                criteriaClientMaritalStatus.setText("Семейное положение");
                criteriaClientCitizenship.setText("Гражданство");
                criteriaClientDisability.setText("Инвалидность");
                criteriaClientRetiree.setText("Пенсионер");
                criteriaClientMonthlyIncome.setText("Месячный доход");
                criteriaClientIDNumber.setText("Идент. номер");

                criteriaClientMenuFIO.setText("ФИО");
                criteriaClientMenuPassport.setText("Паспортные данные");
                criteriaClientMenuResidence.setText("Проживание");
                criteriaClientMenuJob.setText("Работа");
                criteriaClientMenuContacts.setText("Контакты");
                criteriaClientMenuOther.setText("Другое");
                break;
        }
        clientsTable.refresh();
        //initClientsData();
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
        if (pane == menuPane2) {
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
                        String prepStat =
                                "UPDATE `test`.`users` SET `name` = ?, `password` = ?, `email` = ? WHERE (`id` = ?);";
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
        int enteredId =
                StringUtils.isStrictlyNumeric(changeUser_AnchorPane_Id.getText()) ? Integer.parseInt(changeUser_AnchorPane_Id.getText()) : 0;
        String enteredUsername = changeUser_AnchorPane_Username.getText();
        String enteredPassword = changeUser_AnchorPane_Password.getText();
        String enteredEmail = changeUser_AnchorPane_Email.getText();
        int enteredAccessMode = (changeUser_AnchorPane_AccessMode_MenuButton.getText().equals("Admin") ||
                changeUser_AnchorPane_AccessMode_MenuButton.getText().equals("Администратор")) ? 1 : 0;

        if (submitId()) {
            if (!(enteredPassword.length() < 3 && enteredUsername.length() < 3)) {
                if (conn.isConnected()) {
                    for (User u : usersData)
                        if (enteredUsername.equals(u.getUsername())) {
                            was = true;
                            break;
                        }

                    if (!was) {
                        changeUser_AnchorPane_Id.setText("");
                        changeUser_AnchorPane_Username.setText("");
                        changeUser_AnchorPane_Password.setText("");
                        changeUser_AnchorPane_Email.setText("");
                        try {
                            String prepStat =
                                    "UPDATE `test`.`users` SET `name` = ?, `password` = ?, `email` = ?, `access_mode` = ? WHERE (`id` = ?);";
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
        } else {
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
                        String prepStat =
                                "INSERT INTO `test`.`users` (`name`, `password`, `email`,`access_mode`) VALUES (?, ?, ?, ?)";
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
            usersTable.setPrefHeight(154d);
            clientsTable.setPrefWidth(513d);
            clientsTable.setPrefHeight(200d);
            fixImage2.setLayoutX(526);
            fixImage2.setLayoutY(233);
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
            clientManagementScrollPane.setPrefWidth(573);
            clientManagementAnchorPane.setPrefWidth(556);
            clientManagementScrollPane.setPrefHeight(462);
            clientManagementAnchorPane.setPrefHeight(1200);
            createClient_AnchorPane.setPrefHeight(930);

            createClient_AnchorPane_NameJobResidencePane.setLayoutX(10);
            createClient_AnchorPane_NameJobResidencePane.setLayoutY(26);
            createClient_AnchorPane_NameJobResidencePane.setPrefWidth(504);
            createClient_AnchorPane_NameJobResidencePane.setPrefHeight(297);

            createClient_AnchorPane_PassportDataPane.setLayoutX(10);
            createClient_AnchorPane_PassportDataPane.setLayoutY(325);
            createClient_AnchorPane_PassportDataPane.setPrefWidth(504);
            createClient_AnchorPane_PassportDataPane.setPrefHeight(295);

            createClient_AnchorPane_ContactsOtherPane.setLayoutX(10);
            createClient_AnchorPane_ContactsOtherPane.setLayoutY(623);
            createClient_AnchorPane_ContactsOtherPane.setPrefWidth(504);
            createClient_AnchorPane_ContactsOtherPane.setPrefHeight(250);

            createClient_AnchorPane_NameJobResidencePane.setStyle("-fx-border-width: 0 0 1 0");
            createClient_AnchorPane_PassportDataPane.setStyle("-fx-border-width: 0 0 1 0");

            addClientLabel.setLayoutX(176);
            addClientButton.setLayoutX(215);
        } else {
            stage.setMaximized(true);
            usersTable.setPrefHeight(606d);
            clientsTable.setPrefWidth(1250d);
            clientsTable.setPrefHeight(370d);
            fixImage2.setLayoutX(1274);
            fixImage2.setLayoutY(402.7);
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
            clientManagementScrollPane.setPrefWidth(1310);
            clientManagementAnchorPane.setPrefWidth(1304);
            clientManagementScrollPane.setPrefHeight(850);
            clientManagementAnchorPane.setPrefHeight(820);
            createClient_AnchorPane.setPrefHeight(380);

            createClient_AnchorPane_NameJobResidencePane.setLayoutX(10);
            createClient_AnchorPane_NameJobResidencePane.setLayoutY(35);
            createClient_AnchorPane_NameJobResidencePane.setPrefWidth(420);
            createClient_AnchorPane_NameJobResidencePane.setPrefHeight(297);

            createClient_AnchorPane_PassportDataPane.setLayoutX(430);
            createClient_AnchorPane_PassportDataPane.setLayoutY(35);
            createClient_AnchorPane_PassportDataPane.setPrefWidth(420);
            createClient_AnchorPane_PassportDataPane.setPrefHeight(295);

            createClient_AnchorPane_ContactsOtherPane.setLayoutX(850);
            createClient_AnchorPane_ContactsOtherPane.setLayoutY(35);
            createClient_AnchorPane_ContactsOtherPane.setPrefWidth(420);
            createClient_AnchorPane_ContactsOtherPane.setPrefHeight(250);

            createClient_AnchorPane_NameJobResidencePane.setStyle("-fx-border-width: 0 1 0 0");
            createClient_AnchorPane_PassportDataPane.setStyle("-fx-border-width: 0 1 0 0");

            addClientLabel.setLayoutX(555);
            addClientButton.setLayoutX(1005);
            addClientButton.setLayoutY(320);
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
                client.setBirthDate(resultSet.getDate("Birth_date").toString());
                client.setPassportSeries(resultSet.getString("Passport_series"));
                client.setPassportNumber(resultSet.getString("Passport_number"));
                client.setIssuedBy(resultSet.getString("Issued_by"));
                client.setIssuedDate(resultSet.getDate("Issued_date").toString());
                client.setBirthPlace(resultSet.getString("Birth_place"));
                client.setActualResidenceCity(resultSet.getString("Actual_residence_city"));
                client.setActualResidenceAddress(resultSet.getString("Actual_residence_address"));
                client.setHomeNumber(resultSet.getString("Home_number"));
                client.setMobileNumber(resultSet.getString("Mobile_number"));
                client.setEmail(resultSet.getString("Email"));
                client.setJob(resultSet.getString("Job"));
                client.setPosition(resultSet.getString("Position"));
                client.setRegistrationCity(resultSet.getString("Registration_city"));
                client.setMaritalStatus(MaritalStatus.valueOf(resultSet.getString("Marital_status")));
                client.setCitizenship(resultSet.getString("Citizenship"));
                client.setDisability(Disability.valueOf(resultSet.getString("Disability")));
                client.setRetiree(Retiree.valueOf(resultSet.getString("Is_retiree")));
                client.setMonthlyIncome(resultSet.getString("Monthly_income"));
                client.setIdNumber(resultSet.getString("Id_number"));
                clientsData.add(client);
                System.out.println(client);
            }
        } else
            connectionIndicator.setStyle("-fx-background-image: url(assets/indicator-red.png)");

    }

    private void addButtonsToTable() {
        maritalStatusColumn = new TableColumn("Marital Status");
        disabilityColumn = new TableColumn("Disability");
        retireeColumn = new TableColumn("Retiree");
        deleteColumn = new TableColumn("");

        maritalStatusColumn.setMinWidth(180);
        disabilityColumn.setMinWidth(180);
        retireeColumn.setMinWidth(80);
        deleteColumn.setMaxWidth(23);
        deleteColumn.setMinWidth(23);

        maritalStatusColumn.setResizable(false);
        disabilityColumn.setResizable(false);
        retireeColumn.setResizable(false);
        deleteColumn.setResizable(false);

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory1 = new Callback<>() {
            @Override
            public TableCell<Client, Void> call(TableColumn<Client, Void> param) {

                return new TableCell<>() {
                    MenuItem mi1 = new MenuItem("Single");
                    MenuItem mi2 = new MenuItem("Married");
                    MenuItem mi3 = new MenuItem("Divorced");
                    MenuItem mi4 = new MenuItem("Unknown");

                    private MenuButton btn =
                            new MenuButton("Unknown", null, mi1, mi2, mi3, mi4);

                    {
                        btn.setMinWidth(170);
                        mi1.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setMaritalStatusDB(conn, MaritalStatus.Single);
                            btn.setText(mi1.getText());
                        });
                        mi2.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setMaritalStatusDB(conn, MaritalStatus.Married);
                            btn.setText(mi2.getText());
                        });
                        mi3.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setMaritalStatusDB(conn, MaritalStatus.Divorced);
                            btn.setText(mi3.getText());
                        });
                        mi4.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setMaritalStatusDB(conn, MaritalStatus.Unknown);
                            btn.setText(mi4.getText());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            String toSet;
                            Client data = getTableView().getItems().get(getIndex());
                            if (currentLanguage.equals("English")) {
                                switch (data.getMaritalStatus()) {
                                    case Single:
                                        toSet = "Single";
                                        break;
                                    case Married:
                                        toSet = "Married";
                                        break;
                                    case Divorced:
                                        toSet = "Divorced";
                                        break;
                                    case Unknown:
                                        toSet = "Unknown";
                                        break;
                                    default:
                                        toSet = "Error";
                                }
                                btn.setText(toSet);
                                mi1.setText("Single");
                                mi2.setText("Married");
                                mi3.setText("Divorced");
                                mi4.setText("Unknown");
                            }
                            if (currentLanguage.equals("Russian")) {
                                switch (data.getMaritalStatus()) {
                                    case Single:
                                        toSet = "Не женат/не замужем";
                                        break;
                                    case Married:
                                        toSet = "Женат/За мужем";
                                        break;
                                    case Divorced:
                                        toSet = "Разведён/разведена";
                                        break;
                                    case Unknown:
                                        toSet = "Не указано";
                                        break;
                                    default:
                                        toSet = "Error";
                                }
                                btn.setText(toSet);
                                mi1.setText("Не женат/не замужем");
                                mi2.setText("Женат/За мужем");
                                mi3.setText("Разведён/разведена");
                                mi4.setText("Не указано");
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory2 = new Callback<>() {
            @Override
            public TableCell<Client, Void> call(TableColumn<Client, Void> param) {
                return new TableCell<>() {
                    MenuItem mi1 = new MenuItem("First group");
                    MenuItem mi2 = new MenuItem("Second group");
                    MenuItem mi3 = new MenuItem("Third group");
                    MenuItem mi4 = new MenuItem("No");
                    MenuItem mi5 = new MenuItem("Unknown");

                    private MenuButton btn =
                            new MenuButton("No", null, mi1, mi2, mi3, mi4);

                    {
                        btn.setMinWidth(170);
                        mi1.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setDisabilityDB(conn, Disability.First_group);
                            btn.setText(mi1.getText());
                        });
                        mi2.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setDisabilityDB(conn, Disability.Second_group);
                            btn.setText(mi2.getText());
                        });
                        mi3.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setDisabilityDB(conn, Disability.Third_group);
                            btn.setText(mi3.getText());
                        });
                        mi4.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setDisabilityDB(conn, Disability.No);
                            btn.setText(mi4.getText());
                        });
                        mi5.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setDisabilityDB(conn, Disability.Unknown);
                            btn.setText(mi4.getText());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            String toSet;
                            Client data = getTableView().getItems().get(getIndex());
                            if (currentLanguage.equals("English")) {
                                switch (data.getDisability()) {
                                    case First_group:
                                        toSet = "First group";
                                        break;
                                    case Second_group:
                                        toSet = "Second group";
                                        break;
                                    case Third_group:
                                        toSet = "Third group";
                                        break;
                                    case No:
                                        toSet = "No";
                                        break;
                                    case Unknown:
                                        toSet = "Unknown";
                                        break;
                                    default:
                                        toSet = "Error";
                                }
                                btn.setText(toSet);
                                mi1.setText("First group");
                                mi2.setText("Second group");
                                mi3.setText("Third group");
                                mi4.setText("No");
                                mi5.setText("Unknown");
                            }
                            if (currentLanguage.equals("Russian")) {
                                switch (data.getDisability()) {
                                    case First_group:
                                        toSet = "Первая группа";
                                        break;
                                    case Second_group:
                                        toSet = "Вторая группа";
                                        break;
                                    case Third_group:
                                        toSet = "Третья группа";
                                        break;
                                    case No:
                                        toSet = "Нет";
                                        break;
                                    case Unknown:
                                        toSet = "Не указано";
                                        break;
                                    default:
                                        toSet = "Error";
                                }
                                btn.setText(toSet);
                                mi1.setText("Первая группа");
                                mi2.setText("Вторая группа");
                                mi3.setText("Третья группа");
                                mi4.setText("Нет");
                                mi5.setText("Не указано");
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory3 = new Callback<>() {
            @Override
            public TableCell<Client, Void> call(TableColumn<Client, Void> param) {

                return new TableCell<>() {
                    MenuItem mi1 = new MenuItem("Yes");
                    MenuItem mi2 = new MenuItem("No");

                    private MenuButton btn =
                            new MenuButton("No", null, mi1, mi2);

                    {
                        btn.setMinWidth(70);
                        mi1.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setRetireeDB(conn, Retiree.Yes);
                            btn.setText(mi1.getText());
                        });
                        mi2.setOnAction(actionEvent -> {
                            Client data = getTableView().getItems().get(getIndex());
                            data.setRetireeDB(conn, Retiree.No);
                            btn.setText(mi2.getText());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            String toSet;
                            Client data = getTableView().getItems().get(getIndex());
                            if (currentLanguage.equals("English")) {
                                switch (data.getRetiree()) {
                                    case Yes:
                                        toSet = "Yes";
                                        break;
                                    case No:
                                        toSet = "No";
                                        break;
                                    default:
                                        toSet = "Error";
                                }
                                btn.setText(toSet);
                                mi1.setText("Yes");
                                mi2.setText("No");
                            }
                            if (currentLanguage.equals("Russian")) {
                                switch (data.getRetiree()) {
                                    case Yes:
                                        toSet = "Да";
                                        break;
                                    case No:
                                        toSet = "Нет";
                                        break;
                                    default:
                                        toSet = "Error";
                                }
                                btn.setText(toSet);
                                mi1.setText("Да");
                                mi2.setText("Нет");
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory4 = new Callback<>() {
            @Override
            public TableCell<Client, Void> call(TableColumn<Client, Void> param) {
                return new TableCell<>() {

                    private Button btn =
                            new Button("");

                    {
                        btn.getStyleClass().add("deleteClientButton");
                        btn.setMinWidth(15);
                        btn.setPrefWidth(15);
                        btn.setOnAction(event -> {
                            getTableView().getItems().get(getIndex()).deleteDB(conn);
                            try {
                                initClientsData();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            String toSet;
                            Client data = getTableView().getItems().get(getIndex());
                            if (currentLanguage.equals("English")) {
                                btn.setText("");
                            }
                            if (currentLanguage.equals("Russian")) {
                                btn.setText("");
                            }
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        maritalStatusColumn.setCellFactory(cellFactory1);
        disabilityColumn.setCellFactory(cellFactory2);
        retireeColumn.setCellFactory(cellFactory3);
        deleteColumn.setCellFactory(cellFactory4);

        clientsTable.getColumns().add(18, maritalStatusColumn);
        clientsTable.getColumns().add(18, disabilityColumn);
        clientsTable.getColumns().add(18, retireeColumn);
        clientsTable.getColumns().add(0, deleteColumn);
    }

    private boolean checkEmail(TextField toCheck) {
        if (!toCheck.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+))") && !toCheck.getText().equals("")) {
            toCheck.setStyle("-fx-border-color: rgb(255,13,19)");
            return false;
        }
        return true;
    }

    private boolean checkUsernamePassword(TextField toCheck) {
        if (toCheck.getText().length() < 3) {
            toCheck.setStyle("-fx-border-color: rgb(255,13,19)");
            return false;
        }
        return true;
    }

    private boolean checkDeleteField(TextField toCheck) {
        if (!toCheck.getText().matches("^[0-9]+(,[0-9]+)*$")) {
            toCheck.setStyle("-fx-border-color: rgb(255,13,19)");
            return false;
        }
        return true;
    }

    private void searchUser() {
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
    }

    private void searchClient() {
        try {
            initClientsData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!searchFieldClient.getText().equals("")) {
            Iterator<Client> i = clientsData.iterator();
            switch (criteriaButtonClient.getText()) {
                case "ID":
                    while (i.hasNext()) {
                        if (i.next().getId() != Integer.parseInt(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Name":
                case "Имя":
                    while (i.hasNext()) {
                        if (!i.next().getName().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Surname":
                case "Фамилия":
                    while (i.hasNext()) {
                        if (!i.next().getSurname().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Patronymic":
                case "Отчество":
                    while (i.hasNext()) {
                        if (!i.next().getPatronymic().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Full name":
                case "ФИО":
                    String[] buffer = searchFieldClient.getText().split(" ");
                    while (i.hasNext()) {
                        if (!i.next().getSurname().equals(buffer[0])
                                || !i.next().getName().equals(buffer[1])
                                || !i.next().getPatronymic().equals(buffer[2])) {
                            i.remove();
                        }
                    }
                    break;
                case "Passport series":
                case "Серия паспорта":
                    while (i.hasNext()) {
                        if (!i.next().getPassportSeries().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Passport number":
                case "Номер паспорта":
                    while (i.hasNext()) {
                        if (!i.next().getPassportNumber().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Issued by":
                case "Орган выдачи":
                    while (i.hasNext()) {
                        if (!i.next().getIssuedBy().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Issued date":
                case "Дата выдачи":
                    while (i.hasNext()) {
                        if (!i.next().getIssuedDate().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Birth date":
                case "Дата рождения":
                    while (i.hasNext()) {
                        if (!i.next().getBirthDate().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Birth place":
                case "Место рождения":
                    while (i.hasNext()) {
                        if (!i.next().getBirthPlace().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "ID number":
                case "Идент. номер":
                    while (i.hasNext()) {
                        if (!i.next().getIdNumber().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Citizenship":
                case "Гражданство":
                    while (i.hasNext()) {
                        if (!i.next().getCitizenship().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Act. residence city":
                case "Город проживания":
                    while (i.hasNext()) {
                        if (!i.next().getActualResidenceCity().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Act. residence address":
                case "Адрес проживания":
                    while (i.hasNext()) {
                        if (!i.next().getActualResidenceAddress().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Registration city":
                case "Город прописки":
                    while (i.hasNext()) {
                        if (!i.next().getRegistrationCity().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Job":
                case "Место работы":
                    while (i.hasNext()) {
                        if (!i.next().getJob().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Position":
                case "Должность":
                    while (i.hasNext()) {
                        if (!i.next().getPosition().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "E-Mail":
                    while (i.hasNext()) {
                        if (!i.next().getEmail().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Home phone":
                case "Домашний телефон":
                    while (i.hasNext()) {
                        if (!i.next().getHomeNumber().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Mobile phone":
                case "Мобильный телефон":
                    while (i.hasNext()) {
                        if (!i.next().getMobileNumber().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Disability":
                case "Инвалидность":
                    while (i.hasNext()) {
                        if (!i.next().getDisability().toString().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Retiree":
                case "Пенсионер":
                    while (i.hasNext()) {
                        if (!i.next().getRetiree().toString().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Monthly income":
                case "Месячный доход":
                    while (i.hasNext()) {
                        if (i.next().getMonthlyIncome().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;
                case "Marital status":
                case "Семейное положение":
                    while (i.hasNext()) {
                        if (!i.next().getMaritalStatus().toString().equals(searchFieldClient.getText())) {
                            i.remove();
                        }
                    }
                    break;

            }
        }
    }

    //TODO: back-end формы добавления
    // сделать date-picker'ы в самой таблице
}
