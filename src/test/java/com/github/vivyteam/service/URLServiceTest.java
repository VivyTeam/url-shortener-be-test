package com.github.vivyteam.service;

import com.github.vivyteam.builder.UrlBuilder;
import com.github.vivyteam.domain.Url;
import com.github.vivyteam.repository.RedisUrlRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class URLServiceTest {

    @Mock
    private RedisUrlRepository repository;

    @InjectMocks
    private UrlServiceImpl urlService;

    @BeforeEach
    public void setUp() {
        BDDMockito.when(repository.findById(ArgumentMatchers.anyString()))
                .thenReturn( Mono.just(UrlBuilder.create()));

        BDDMockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn( Mono.just(UrlBuilder.create()));
    }

    @Test
    @DisplayName("It should return a Mono with url when it exists")
    public void givenShortUrl_whenGetLongURL_thenSuccessful() {
        Url expectedUrl = UrlBuilder.create();
        String shortenUrl = "localhost:9000/uje3ks45";
        StepVerifier.create(urlService.findByShortenUrl(shortenUrl))
                .expectSubscription()
                .expectNext(expectedUrl)
                .verifyComplete();
    }


    @Test
    @DisplayName("")
    public void givenLongURL_whenSave_thenShortenURL() {
        //arrange
        StringBuilder longUrl = new StringBuilder("https://www.nytimes.com/2023/02/22/world")
                .append("/americas/gloria-maria-dead.html?smid=tw-nytimes&smtyp=cur");

        ArgumentCaptor<Url> capturedUrl = ArgumentCaptor.forClass(Url.class);

        //action
        urlService.save(longUrl.toString());

        //assert
        verify(repository).save(capturedUrl.capture());
        Url capturedUrlValue = capturedUrl.getValue();

        assertEquals(longUrl.toString(), capturedUrlValue.getUrl());
    }

}
