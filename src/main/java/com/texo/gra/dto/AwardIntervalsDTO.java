package com.texo.gra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class AwardIntervalsDTO {

    private List<ProducerIntervalDTO> min;
    private List<ProducerIntervalDTO> max;

}
