package Model;

public class CountryReport {
    /**
     *
     * @return country name for report
     */
    public String getCountry() {
        return Country;
    }

    /**
     *
     * @param country name set as country for report
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     *
     * @return division name for report
     */
    public String getDivision() {
        return Division;
    }

    /**
     *
     * @param division set as division name for report
     */
    public void setDivision(String division) {
        Division = division;
    }

    /**
     *
     * @return number of appointments for country report
     */
    public int getNumberOfAppointments() {
        return NumberOfAppointments;
    }

    /**
     *
     * @param numberOfAppointments set number of appointments for country report
     */
    public void setNumberOfAppointments(int numberOfAppointments) {
        NumberOfAppointments = numberOfAppointments;
    }

    /**
     *
     * @return get number of customers for country report
     */
    public int getNumberOfCustomers() {
        return NumberOfCustomers;
    }

    /**
     *
     * @param numberOfCustomers set as number of customers for country report
     */
    public void setNumberOfCustomers(int numberOfCustomers) {
        NumberOfCustomers = numberOfCustomers;
    }

    private String Country;
    private String Division;
    private int NumberOfAppointments;
    private int NumberOfCustomers;

    /**
     * returns a country report instance with set params
     * @param country name set for country report
     * @param division division set for country report
     * @param numberOfAppointments number of appointments set for country report
     * @param numberOfCustomers number of customers set for country report
     */
    public CountryReport(String country, String division, int numberOfAppointments, int numberOfCustomers){
        this.Country = country;
        this.Division = division;
        this.NumberOfAppointments = numberOfAppointments;
        this.NumberOfCustomers = numberOfCustomers;
    }
}
