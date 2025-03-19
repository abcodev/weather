package com.web.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoResponse {

  @JsonProperty("baseDate")
  private String baseDate;

  @JsonProperty("baseTime")
  private String baseTime;

  @JsonProperty("category")
  private String category;

  @JsonProperty("fcstDate")
  private String fcstDate;

  @JsonProperty("fcstTime")
  private String fcstTime;

  @JsonProperty("fcstValue")
  private String fcstValue;

  @JsonProperty("nx")
  private int nx;

  @JsonProperty("ny")
  private int ny;
}
