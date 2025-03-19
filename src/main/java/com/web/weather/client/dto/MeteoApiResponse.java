package com.web.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoApiResponse {
  private Response response;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {
    private Header header;
    private Body body;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Header {
    private String resultCode;
    private String resultMsg;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Body {
    private String dataType;
    private Items items;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Items {
    @JsonProperty("item")
    private List<MeteoResponse> item;
  }
}
