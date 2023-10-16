/* Vladyslav Yakubovskyi,
 * Lviv Polytechnic National University, Institute of Computer Science and Information Technologies,
 * Department of Information Systems and Networks, "PI-21" Student group,
 * Discipline "Applied Programming",
 * Laboratory work #03,
 * Task #03,
 * "exceptions" package,
 * "ObjectIsNotArrayListException.java" file */

package exceptions;

public class ObjectIsNotArrayListException extends Exception {
    public ObjectIsNotArrayListException(Class<?> clazz) {
        super("The object is not ArrayList, but %s".formatted(clazz.getName()));
    }
}
