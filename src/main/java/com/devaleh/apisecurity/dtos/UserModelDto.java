package com.devaleh.apisecurity.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserModelDto(@NotBlank String username, @NotNull String password) {
}
