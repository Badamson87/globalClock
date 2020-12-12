package Model;

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

    public int getJan() {
        return Jan;
    }

    public void setJan(int jan) {
        Jan = jan;
    }

    public int getFeb() {
        return Feb;
    }

    public void setFeb(int feb) {
        Feb = feb;
    }

    public int getMar() {
        return Mar;
    }

    public void setMar(int mar) {
        Mar = mar;
    }

    public int getApr() {
        return Apr;
    }

    public void setApr(int apr) {
        Apr = apr;
    }

    public int getMay() {
        return May;
    }

    public void setMay(int may) {
        May = may;
    }

    public int getJun() {
        return Jun;
    }

    public void setJun(int jun) {
        Jun = jun;
    }

    public int getJul() {
        return Jul;
    }

    public void setJul(int jul) {
        Jul = jul;
    }

    public int getAug() {
        return Aug;
    }

    public void setAug(int aug) {
        Aug = aug;
    }

    public int getSep() {
        return Sep;
    }

    public void setSep(int sep) {
        Sep = sep;
    }

    public int getOct() {
        return Oct;
    }

    public void setOct(int oct) {
        Oct = oct;
    }

    public int getNov() {
        return Nov;
    }

    public void setNov(int nov) {
        Nov = nov;
    }

    public int getDec() {
        return Dec;
    }

    public void setDec(int dec) {
        Dec = dec;
    }

    public void incrementJan(){
        Jan++;
    }
    public void incrementFeb(){
        Feb++;
    }
    public void incrementMar(){
        Mar++;
    }
    public void incrementApr(){
        Apr++;
    }
    public void incrementMay(){
        May++;
    }
    public void incrementJun(){
        Jun++;
    }
    public void incrementJul(){
        Jul++;
    }
    public void incrementAug(){
        Aug++;
    }
    public void incrementSep(){
        Sep++;
    }
    public void incrementOct(){
        Oct++;
    }
    public void incrementNov(){
        Nov++;
    }
    public void incrementDec(){
        Dec++;
    }

    public AppointmentType(String type){
        this.Type = type;
    }

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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

}
