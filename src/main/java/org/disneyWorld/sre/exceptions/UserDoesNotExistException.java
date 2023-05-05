package org.disneyWorld.sre.exceptions;

public class UserDoesNotExistException extends Exception {

        private String username;

        public UserDoesNotExistException(String username) {
            super(String.format("An account with the username %s does not exist!", username));
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }

