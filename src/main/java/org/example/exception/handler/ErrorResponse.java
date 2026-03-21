package org.example.exception.handler;

import lombok.Builder;

@Builder
public record ErrorResponse(Integer code, String message) {
}
