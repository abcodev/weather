package com.web.weather.service;

import com.web.weather.client.MeteoApiClient;
import com.web.weather.client.dto.MeteoRequest;
import com.web.weather.client.dto.MeteoResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeteoService {

  private final MeteoApiClient meteoApiClient;
  private final RedisCacheService redisCacheService;

  private static final String CACHE_KEY_PREFIX = "meteo:";


  public List<MeteoResponse> getVilageFcst(MeteoRequest request) {
    String cacheKey = generateCacheKey(request);

    List<MeteoResponse> cachedData = redisCacheService.getFromCache(cacheKey, List.class);
    if (cachedData != null) {
      return cachedData;
    }

    List<MeteoResponse> apiData = meteoApiClient.getVilageFcst("getVilageFcst", request);
    redisCacheService.saveToCache(cacheKey, apiData, 10, TimeUnit.MINUTES);
    return apiData;
  }

  private String generateCacheKey(MeteoRequest request) {
    return CACHE_KEY_PREFIX + request.getBaseDate() + ":" + request.getBaseTime() + ":" + request.getNx() + ":" + request.getNy();
  }



}
