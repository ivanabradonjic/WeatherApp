package com.valcon.WeatherApp.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {

        private LocalDateTime timestamp;
        private String status;
        private String message;

        public ErrorResponseDTO(LocalDateTime timestamp, String status, String message) {
            this.timestamp = timestamp;
            this.status = status;
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

    }

