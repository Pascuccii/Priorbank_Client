package sample.Controls;

import sample.enums.*;

import java.sql.Date;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private String passportSeries;
    private String passportNumber;
    private String issuedBy;
    private Date issuedDate;
    private String birthPlace;
    private City actualResidenceCity;
    private String actualResidenceAddress;
    private String homeNumber;
    private String mobileNumber;
    private String email;
    private String job;
    private String position;
    private City registrationCity;
    private MaritalStatus maritalStatus;
    private Country citizenship;
    private Disability disability;
    private boolean retiree;
    private double monthlyIncome;
    private String idNumber;

    public Client() {}
    public Client(int id, String name, String surname, String patronymic, Date birthDate, String passportSeries, String passportNumber, String issuedBy, Date issuedDate, String birthPlace, City actualResidence_city, String actualResidenceAddress, String homeNumber, String mobileNumber, String email, String job, String position, City registrationCity, MaritalStatus maritalStatus, Country citizenship, Disability disability, boolean retiree, double monthlyIncome, String idNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.issuedBy = issuedBy;
        this.issuedDate = issuedDate;
        this.birthPlace = birthPlace;
        this.actualResidenceCity = actualResidence_city;
        this.actualResidenceAddress = actualResidenceAddress;
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.job = job;
        this.position = position;
        this.registrationCity = registrationCity;
        this.maritalStatus = maritalStatus;
        this.citizenship = citizenship;
        this.disability = disability;
        this.retiree = retiree;
        this.monthlyIncome = monthlyIncome;
        this.idNumber = idNumber;
    }


    //Object
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setActualResidenceCity(City actualResidenceCity) {
        this.actualResidenceCity = actualResidenceCity;
    }

    public void setActualResidenceAddress(String actualResidenceAddress) {
        this.actualResidenceAddress = actualResidenceAddress;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRegistrationCity(City registrationCity) {
        this.registrationCity = registrationCity;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setCitizenship(Country citizenship) {
        this.citizenship = citizenship;
    }

    public void setDisability(Disability disability) {
        this.disability = disability;
    }

    public void setRetiree(boolean retiree) {
        this.retiree = retiree;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    //DB
    public void setIdDB(int id) {
        this.id = id;
    }

    public void setNameDB(String name) {
        this.name = name;
    }

    public void setSurnameDB(String surname) {
        this.surname = surname;
    }

    public void setPatronymicDB(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthDateDB(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassportSeriesDB(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public void setPassportNumberDB(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setIssuedByDB(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public void setIssuedDateDB(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setBirthPlaceDB(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setActualResidence_cityDB(City actualResidence_city) {
        this.actualResidenceCity = actualResidence_city;
    }

    public void setActualResidenceAddressDB(String actualResidenceAddress) {
        this.actualResidenceAddress = actualResidenceAddress;
    }

    public void setHomeNumberDB(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setMobileNumberDB(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailDB(String email) {
        this.email = email;
    }

    public void setJobDB(String job) {
        this.job = job;
    }

    public void setPositionDB(String position) {
        this.position = position;
    }

    public void setRegistrationCityDB(City registrationCity) {
        this.registrationCity = registrationCity;
    }

    public void setMaritalStatusDB(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setCitizenshipDB(Country citizenship) {
        this.citizenship = citizenship;
    }

    public void setDisabilityDB(Disability disability) {
        this.disability = disability;
    }

    public void setRetireeDB(boolean retiree) {
        this.retiree = retiree;
    }

    public void setMonthlyIncomeDB(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setIdNumberDB(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public City getActualResidenceCity() {
        return actualResidenceCity;
    }

    public String getActualResidenceAddress() {
        return actualResidenceAddress;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getJob() {
        return job;
    }

    public String getPosition() {
        return position;
    }

    public City getRegistrationCity() {
        return registrationCity;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Country getCitizenship() {
        return citizenship;
    }

    public Disability getDisability() {
        return disability;
    }

    public boolean isRetiree() {
        return retiree;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public String getIdNumber() {
        return idNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", issuedDate=" + issuedDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", actualResidenceCity=" + actualResidenceCity +
                ", actualResidenceAddress='" + actualResidenceAddress + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", job='" + job + '\'' +
                ", position='" + position + '\'' +
                ", registrationCity=" + registrationCity +
                ", maritalStatus=" + maritalStatus +
                ", citizenship=" + citizenship +
                ", disability=" + disability +
                ", retiree=" + retiree +
                ", monthlyIncome=" + monthlyIncome +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
