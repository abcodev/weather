package com.web.weather.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeteoRequest {

  private String pageNo;
  private String numOfRows;
  private String dataType;
  private String baseDate;
  private String baseTime;
  private int nx;
  private int ny;

}
