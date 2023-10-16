/* Vladyslav Yakubovskyi,
 * Lviv Polytechnic National University, Institute of Computer Science and Information Technologies,
 * Department of Information Systems and Networks, "PI-21" Student group,
 * Discipline "Applied Programming",
 * Laboratory work #03,
 * Task #03,
 * "program" package,
 * "Product.java" file */

package program;

import java.io.Serializable;
import java.util.Objects;

public record Product(String name, double price, ProductType type) implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Product product)) {
            return false;
        }

        return Double.compare(this.price, product.price) == 0 && Objects.equals(this.name, product.name) &&
               this.type == product.type;
    }

    @Override
    public String toString() {
        return "Product{" + "name='" + this.name + '\'' + ", price=" + this.price + ", type=" + this.type + '}';
    }
}
