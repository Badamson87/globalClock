package Model;

public class CountryReport {
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public int getNumberOfAppointments() {
        return NumberOfAppointments;
    }

    public void setNumberOfAppointments(int numberOfAppointments) {
        NumberOfAppointments = numberOfAppointments;
    }

    public int getNumberOfCustomers() {
        return NumberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        NumberOfCustomers = numberOfCustomers;
    }

    private String Country;
    private String Division;
    private int NumberOfAppointments;
    private int NumberOfCustomers;

    public CountryReport(String country, String division, int numberOfAppointments, int numberOfCustomers){
        this.Country = country;
        this.Division = division;
        this.NumberOfAppointments = numberOfAppointments;
        this.NumberOfCustomers = numberOfCustomers;
    }
}
