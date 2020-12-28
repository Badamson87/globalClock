package Model;


/**
 * This class focuses on appointment types. It is used in the reporting section
 */
public class AppointmentType {
    private String Type;
    private int Jan;
    private int Feb;
    private int Mar;
    private int Apr;
    private int May;
    private int Jun;
    private int Jul;
    private int Aug;
    private int Sep;
    private int Oct;
    private int Nov;
    private int Dec;

    /**
     *
     * @return amount of appointments in january
     */
    public int getJan() {
        return Jan;
    }

    /**
     *
     * @param jan set amount of appointments in january
     */
    public void setJan(int jan) {
        Jan = jan;
    }

    /**
     *
     * @return amount of appointments in Febuary
     */
    public int getFeb() {
        return Feb;
    }

    /**
     *
     * @param feb set amount of appointments in febuary
     */
    public void setFeb(int feb) {
        Feb = feb;
    }

    /**
     *
     * @return the amount of appointments in march
     */
    public int getMar() {
        return Mar;
    }

    /**
     *
     * @param mar set the amount of appointments in march
     */
    public void setMar(int mar) {
        Mar = mar;
    }

    /**
     *
     * @return amount of appointments in april
     */
    public int getApr() {
        return Apr;
    }

    /**
     *
     * @param apr sets the amount of appointments in april
     */
    public void setApr(int apr) {
        Apr = apr;
    }

    /**
     *
     * @return returns the amount of appointments in may
     */
    public int getMay() {
        return May;
    }

    /**
     *
     * @param may set as the amount of appointments in may
     */
    public void setMay(int may) {
        May = may;
    }

    /**
     *
     * @return the amount of appointments in june
     */
    public int getJun() {
        return Jun;
    }

    /**
     *
     * @param jun set as the amount of appointments in june
     */
    public void setJun(int jun) {
        Jun = jun;
    }

    /**
     *
     * @return return the amount of appointments in july
     */
    public int getJul() {
        return Jul;
    }

    /**
     *
     * @param jul set as the amount of appointments in july
     */
    public void setJul(int jul) {
        Jul = jul;
    }

    /**
     *
     * @return as the amount of appointments in aug
     */
    public int getAug() {
        return Aug;
    }

    /**
     *
     * @param aug set as the amount of appointments in aug
     */
    public void setAug(int aug) {
        Aug = aug;
    }

    /**
     *
     * @return the amount of appointments in September
     */
    public int getSep() {
        return Sep;
    }

    /**
     *
     * @param sep set as the amount of appointments in september
     */
    public void setSep(int sep) {
        Sep = sep;
    }

    /**
     *
     * @return as amount of appointments in october
     */
    public int getOct() {
        return Oct;
    }

    /**
     *
     * @param oct set as amount of appointments in october
     */
    public void setOct(int oct) {
        Oct = oct;
    }

    /**
     *
     * @return as the amount of appointments in nov
     */
    public int getNov() {
        return Nov;
    }

    /**
     *
     * @param nov set as the amount of appointments in nov
     */
    public void setNov(int nov) {
        Nov = nov;
    }

    /**
     *
     * @return as the amount of appointments in december
     */
    public int getDec() {
        return Dec;
    }

    /**
     *
     * @param dec set as amount of appointments in dec
     */
    public void setDec(int dec) {
        Dec = dec;
    }

    /**
     * increment january appointments by 1
     */
    public void incrementJan(){
        Jan++;
    }
    /**
     * increment feb appointments by 1
     */
    public void incrementFeb(){
        Feb++;
    }
    /**
     * increment mar appointments by 1
     */
    public void incrementMar(){
        Mar++;
    }
    /**
     * increment april appointments by 1
     */
    public void incrementApr(){
        Apr++;
    }
    /**
     * increment may appointments by 1
     */
    public void incrementMay(){
        May++;
    }
    /**
     * increment june appointments by 1
     */
    public void incrementJun(){
        Jun++;
    }
    /**
     * increment july appointments by 1
     */
    public void incrementJul(){
        Jul++;
    }
    /**
     * increment august appointments by 1
     */
    public void incrementAug(){
        Aug++;
    }
    /**
     * increment september appointments by 1
     */
    public void incrementSep(){
        Sep++;
    }
    /**
     * increment october appointments by 1
     */
    public void incrementOct(){
        Oct++;
    }
    /**
     * increment november appointments by 1
     */
    public void incrementNov(){
        Nov++;
    }
    /**
     * increment dec appointments by 1
     */
    public void incrementDec(){
        Dec++;
    }

    /**
     *
     * @param type sets the appointment type as a string
     */
    public AppointmentType(String type){
        this.Type = type;
    }

    /**
     *  creates an appointment with provided parms
     * @param type set as appointment type
     * @param jan set as number of Jan appointments
     * @param feb set as number of feb appointments
     * @param mar set as number of mar appointments
     * @param apr set as number of apr appointments
     * @param may set as number of may appointments
     * @param jun set as number of June appointments
     * @param jul set as number of Jul appointments
     * @param aug set as number of aug appointments
     * @param sep set as number of sep appointments
     * @param oct set as number of oct appointments
     * @param nov set as number of nov appointments
     * @param dec set as number of dec appointments
     */
    public AppointmentType(String type, int jan, int feb, int mar, int apr, int may, int jun, int jul, int aug, int sep, int oct, int nov, int dec){
        this.Type = type;
        this.Jan = jan;
        this.Feb = feb;
        this.Mar = mar;
        this.Apr = apr;
        this.May = may;
        this.Jun = jun;
        this.Jul = jul;
        this.Aug = aug;
        this.Sep = sep;
        this.Oct = oct;
        this.Nov = nov;
        this.Dec = dec;
    }

    /**
     *
     * @return appointment type as a string
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @param type set as appointment type
     */
    public void setType(String type) {
        Type = type;
    }

}
