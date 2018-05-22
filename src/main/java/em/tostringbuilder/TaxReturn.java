package em.tostringbuilder;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * TaxReturn class
 *
 * @author Administrator
 * @date
 */
public class TaxReturn {
    public static void main(String...args)
    {
        TaxReturn return1 = new TaxReturn("012-68-3242", 1998, "O'Brien", new BigDecimal(43000.00));
        TaxReturn return2 = new TaxReturn("012-68-3242", 1999, "O'Brien", new BigDecimal(45000.00));
        TaxReturn return3 = new TaxReturn("012-68-3242", 1999, "O'Brien", new BigDecimal(53222.00));
        System.out.println("ToStringBuilder: " + return1.toString());
    }


    private String ssn;
    private int year;
    private String lastName;
    private BigDecimal taxableIncome;

    public TaxReturn() {
    }

    public TaxReturn(String pSsn, int pYear, String pLastName, BigDecimal pTaxableIncome) {
        setSsn(pSsn);
        setYear(pYear);
        setLastName(pLastName);
        setTaxableIncome(pTaxableIncome);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ssn", ssn)
                .append("year", year)
                .append("lastName", lastName)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 7)
                .append(ssn)
                .append(year)
                .toHashCode();
    }

    @Override
    public boolean equals(Object pObject) {
        boolean equals = false;
        if (pObject instanceof TaxReturn) {
            TaxReturn bean = (TaxReturn) pObject;
            equals = (
                    new EqualsBuilder()
                            .append(ssn, bean.ssn)
                            .append(year, bean.year)).isEquals();
        }
        return equals;
    }

    public int compareTo(Object pObject) {
        return CompareToBuilder.reflectionCompare(this, pObject);
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTaxableIncome(BigDecimal taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getTaxableIncome() {
        return taxableIncome;
    }

}
