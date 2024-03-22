package com.dev.servlet.security;

public class UserInfo {
    private final String username;
    private final String password;
    private final String role;
    private final boolean isActive;
    private final boolean isBanned;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isBanned() {
        return isBanned;
    }

    private UserInfo(Builder builder, String role, boolean isActive, boolean isBanned) {
        username = builder.username;
        password = builder.password;
        this.role = role;
        this.isActive = isActive;
        this.isBanned = isBanned;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String username;
        private String password;
        private String role;
        private boolean isActive;
        private boolean isBanned;

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withRole(String val) {
            role = val;
            return this;
        }

        public Builder isBanned(boolean val) {
            isBanned = val;
            return this;
        }

        public Builder isActive(boolean val) {
            isActive = val;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this, role, isActive, isBanned);
        }
    }
}
