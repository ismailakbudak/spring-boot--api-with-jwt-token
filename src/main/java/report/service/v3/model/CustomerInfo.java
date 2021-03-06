package report.service.v3.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_infos")
public class CustomerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "expiryMonth")
    private String expiryMonth;

    @Column(name = "expiryYear")
    private String expiryYear;

    @Column(name = "startMonth")
    private String startMonth;

    @Column(name = "startYear")
    private String startYear;

    @Column(name = "issueNumber")
    private String issueNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private String gender;

    @Column(name = "billingTitle")
    private String billingTitle;

    @Column(name = "billingFirstName")
    private String billingFirstName;

    @Column(name = "billingLastName")
    private String billingLastName;

    @Column(name = "billingCompany")
    private String billingCompany;

    @Column(name = "billingAddress1")
    private String billingAddress1;

    @Column(name = "billingAddress2")
    private String billingAddress2;

    @Column(name = "billingCity")
    private String billingCity;

    @Column(name = "billingPostcode")
    private String billingPostcode;

    @Column(name = "billingState")
    private String billingState;

    @Column(name = "billingCountry")
    private String billingCountry;

    @Column(name = "billingPhone​")
    private String billingPhone​;

    @Column(name = "billingFax")
    private String billingFax;

    @Column(name = "shippingTitle")
    private String shippingTitle;

    @Column(name = "shippingFirstName")
    private String shippingFirstName;

    @Column(name = "shippingLastName​")
    private String shippingLastName​;

    @Column(name = "shippingCompany")
    private String shippingCompany;

    @Column(name = "shippingAddress1​")
    private String shippingAddress1​;

    @Column(name = "shippingAddress2​")
    private String shippingAddress2​;

    @Column(name = "shippingCity​")
    private String shippingCity​;

    @Column(name = "shippingPostcode​")
    private String shippingPostcode​;

    @Column(name = "shippingState")
    private String shippingState;

    @Column(name = "shippingCountry")
    private String shippingCountry;

    @Column(name = "shippingPhone")
    private String shippingPhone;

    @Column(name = "shippingFax")
    private String shippingFax;

    @Column(name = "token")
    private String token;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBillingTitle() {
        return billingTitle;
    }

    public void setBillingTitle(String billingTitle) {
        this.billingTitle = billingTitle;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingCompany() {
        return billingCompany;
    }

    public void setBillingCompany(String billingCompany) {
        this.billingCompany = billingCompany;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostcode() {
        return billingPostcode;
    }

    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingFax() {
        return billingFax;
    }

    public void setBillingFax(String billingFax) {
        this.billingFax = billingFax;
    }

    public String getBillingPhone​() {
        return billingPhone​;
    }

    public void setBillingPhone​(String billingPhone​) {
        this.billingPhone​ = billingPhone​;
    }

    public String getShippingTitle() {
        return shippingTitle;
    }

    public void setShippingTitle(String shippingTitle) {
        this.shippingTitle = shippingTitle;
    }

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingLastName​() {
        return shippingLastName​;
    }

    public void setShippingLastName​(String shippingLastName​) {
        this.shippingLastName​ = shippingLastName​;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingAddress1​() {
        return shippingAddress1​;
    }

    public void setShippingAddress1​(String shippingAddress1​) {
        this.shippingAddress1​ = shippingAddress1​;
    }

    public String getShippingAddress2​() {
        return shippingAddress2​;
    }

    public void setShippingAddress2​(String shippingAddress2​) {
        this.shippingAddress2​ = shippingAddress2​;
    }

    public String getShippingCity​() {
        return shippingCity​;
    }

    public void setShippingCity​(String shippingCity​) {
        this.shippingCity​ = shippingCity​;
    }

    public String getShippingPostcode​() {
        return shippingPostcode​;
    }

    public void setShippingPostcode​(String shippingPostcode​) {
        this.shippingPostcode​ = shippingPostcode​;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingFax() {
        return shippingFax;
    }

    public void setShippingFax(String shippingFax) {
        this.shippingFax = shippingFax;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
