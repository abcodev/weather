package com.web.weather.client;

import com.web.weather.client.dto.MeteoApiResponse;
import com.web.weather.client.dto.MeteoRequest;
import com.web.weather.client.dto.MeteoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MeteoApiClient {

  private final RestTemplate restTemplate;

  private static final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";

  @Value("${meteo.client_id}")
  private String apiKey;

  public List<MeteoResponse> getVilageFcst(String serviceName, MeteoRequest request) {
    URI uri = UriComponentsBuilder.fromUriString(BASE_URL + "/" + serviceName)
        .queryParam("serviceKey", apiKey)
        .queryParam("pageNo", request.getPageNo())
        .queryParam("numOfRows", request.getNumOfRows())
        .queryParam("dataType", request.getDataType())
        .queryParam("base_date", request.getBaseDate())
        .queryParam("base_time", request.getBaseTime())
        .queryParam("nx", request.getNx())
        .queryParam("ny", request.getNy())
        .build()
        .toUri();

    log.info("uri {} ", uri);

    try {
      ResponseEntity<MeteoApiResponse> response = restTemplate.getForEntity(uri, MeteoApiResponse.class);

      MeteoApiResponse apiResponse = response.getBody();
      if (apiResponse.getResponse().getBody().getItems() != null) {
        return apiResponse.getResponse().getBody().getItems().getItem();
      } else {
        throw new RuntimeException("응답이 예상과 다릅니다.");
      }

    } catch (HttpClientErrorException e) {
      throw new RuntimeException("데이터를 가져오는데 실패하였습니다. 클라이언트 오류: ", e);
    } catch (HttpServerErrorException e) {
      throw new RuntimeException("데이터를 가져오는데 실패하였습니다. 서버 오류: ", e);
    } catch (RestClientException e) {
      throw new RuntimeException("데이터를 가져오는데 시간이 오래걸려 에러가 발생하였습니다.", e);
    } catch (Exception e) {
      throw new RuntimeException("에러가 발생하였습니다.", e);
    }
  }
}