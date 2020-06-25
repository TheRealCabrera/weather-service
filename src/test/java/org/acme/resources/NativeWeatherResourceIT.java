package org.acme.resources;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeWeatherResourceIT extends WeatherResourceTest {

    // Execute the same tests but in native mode.
}