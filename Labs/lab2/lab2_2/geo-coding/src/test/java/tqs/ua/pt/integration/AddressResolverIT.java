package tqs.ua.pt.integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import tqs.ua.pt.connection.TqsBasicHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import tqs.ua.pt.geocoding.Address;
import tqs.ua.pt.geocoding.AddressResolverService;

public class AddressResolverIT {

    private AddressResolverService addressResolver;

    @BeforeEach
    public void init() {
        addressResolver = new AddressResolverService(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = addressResolver.findAddressForLocation(40.63436, -8.65616);
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertEquals(result, Optional.of(expected));
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = addressResolver.findAddressForLocation(310, -310);
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");
        assertNotEquals(result, Optional.of(expected));

    }

}
