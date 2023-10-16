/* Vladyslav Yakubovskyi,
 * Lviv Polytechnic National University, Institute of Computer Science and Information Technologies,
 * Department of Information Systems and Networks, "PI-21" Student group,
 * Discipline "Applied Programming",
 * Laboratory work #03,
 * Task #03,
 * "exceptions" package,
 * "ProductIsNotAvailableException.java" file */

package exceptions;

import program.Product;

public class ProductIsNotAvailableException extends Exception {
    public ProductIsNotAvailableException(final Product product) {
        super("Product is not available: %s, %f, %s".formatted(product.name(), product.price(), product.type()));
    }
}
