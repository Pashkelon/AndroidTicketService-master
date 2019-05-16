package com.example.biletiki2.data.dto.auth;

public class AuthResponseLoginDto {
        private String token;

        public AuthResponseLoginDto() {
        }

        public AuthResponseLoginDto(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
}
