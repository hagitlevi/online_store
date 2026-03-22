/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the abstract User class representing a user in the store system.
 */
package store.core;

/**
 * The User abstract class represents a user in the store system.
 */
public abstract class User {
    private final String username;
    private final String email;

    /**
     * Constructor for User class.
     * @param username username
     * @param email email address
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * Get the username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the email address.
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * String representation of the User.
     * @return string representation
     */
    public  String toString() {
        return "User: " + getUsername() + "\n" + "Email: " + getEmail() + "\n";
    }

    /**
     * Check equality based on username.
     * @param obj object to compare
     * @return true if usernames are equal, false otherwise
     */
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User other = (User) obj;

        return this.getUsername().equals(other.getUsername());
    }
}
