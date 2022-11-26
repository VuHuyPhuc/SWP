// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package dao;

import entity.Loan;
import entity.OverdueLoan;
import java.util.List;

/**
 *
 * @author s
 */
public interface LoanDAO {

    public Loan getByID(int id);

    public List<Loan> getByUserID(int id);

    public List<Loan> getBorrowing(int id);

    public int[] countBorrowPerMonth();

    public int countBorrowedToday();

    public int countReturnedToday();

    public int countReturnLoan();

    public int countOverdueLoan();

    public int countBorrowingLoan();

    public List<Loan> getReturnedList(int index, int perPage);

    public List<Loan> getBorrowingList(int index, int perPage);
    
    public List<OverdueLoan> getOverdueList(int index, int perPage, int finePerDay);
    
    public OverdueLoan getOverdueByID(int id, int finePerDay);
}
