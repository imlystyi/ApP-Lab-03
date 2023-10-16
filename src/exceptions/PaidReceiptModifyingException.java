/* Vladyslav Yakubovskyi,
 * Lviv Polytechnic National University, Institute of Computer Science and Information Technologies,
 * Department of Information Systems and Networks, "PI-21" Student group,
 * Discipline "Applied Programming",
 * Laboratory work #03,
 * Task #03,
 * "exceptions" package,
 * "PaidReceiptModifyingException.java" file */

package exceptions;

public class PaidReceiptModifyingException extends Exception {
    public PaidReceiptModifyingException() {
        super("Paid receipt cannot be modified.");
    }
}
