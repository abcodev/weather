package com.web.weather.controller;

import com.web.weather.client.dto.MeteoRequest;
import com.web.weather.client.dto.MeteoResponse;
import com.web.weather.service.MeteoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

  private final MeteoService meteoService;

  @PostMapping("/scrap/meteo")
  public ResponseEntity<List<MeteoResponse>> getVilageFcst(
      @RequestBody MeteoRequest request
  ) {
    List<MeteoResponse> list = meteoService.getVilageFcst(request);
    return ResponseEntity.ok(list);
  }

}
