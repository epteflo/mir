package hu.imsi.mir.ws;

import hu.imsi.mir.dao.MuseumRepository;
import hu.imsi.mir.dao.entities.HMuseum;
import hu.imsi.mir.mappers.MuseumMapper;
import hu.imsi.mir.museums.GetMuseumsRequest;
import hu.imsi.mir.museums.GetMuseumsResponse;
import hu.imsi.mir.museums.Museum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class MuseumEndpoint {
    public static final String NAMESPACE_URI = "http://imsi.hu/mir/museums";

    @Autowired private MuseumRepository museumRepository;
    @Autowired private MuseumMapper museumMapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMuseumsRequest")
    @ResponsePayload
    public GetMuseumsResponse getMuseums(@RequestPayload GetMuseumsRequest request) {
        GetMuseumsResponse response = new GetMuseumsResponse();
        final HMuseum example = new HMuseum();
        example.setName(request.getName());
        final ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());
        final List<Museum> museums = museumMapper.toWsList(museumRepository.findAll(Example.of(example, matcher)));

        response.getMuseum().addAll(museums);

        return response;
    }
}
