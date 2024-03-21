package com.texo.gra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerIntervalDTO {

    @NotBlank
    @Size(min = 0, max = 100)
    private String producer;

    @NotNull
    private int interval;

    @NotNull
    private int previousWin;

    @NotNull
    private int followingWin;

}
