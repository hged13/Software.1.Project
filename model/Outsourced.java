package model;

/**This creates the Outsourced class*/
public class Outsourced extends Part{
    private String companyName;



    /** This is the constructor. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.setCompanyName(companyName);
    }



    /** This is the company name getter.
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /** This is the company name setter.
     * @param companyName the name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

