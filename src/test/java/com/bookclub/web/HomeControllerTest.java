/*
 * References
 *
 * (n.d.). Disable security for unit tests with spring boot. Stackoverflow. https://stackoverflow.com/questions/31169720/disable-security-for-unit-tests-with-spring-boot
 *
 * (2024, January 2). Spring boot profiles not working correctly. Stackoverflow. Retrieved May 3, 2025, from https://stackoverflow.com/questions/77681970/spring-boot-profiles-not-working-correctly
 *
 * (2018, March 23). Disable security for unit tests with spring boot. Stackoverflow. Retrieved May 3, 2025, from https://stackoverflow.com/questions/31169720/disable-security-for-unit-tests-with-spring-boot
 *
 * Witt, C. (2025). CIS 530 Intermediate Java Programming. Bellevue University, all rights reserved.
 */

package com.bookclub.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

// Added active profile to get around security issues in testing
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class HomeControllerTest {

    // Class Attributes
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    /**
     * Tests login page is returned with /login
     */
    @Test
    void testGetLoginPage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login",
                String.class)).contains("Bookclub | Login");
    }

    /**
     * Tests index page is returned with /
     */
    @Test
    void testIndex() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Bookclub | Welcome");
    }

    /**
     * Tests about page is returned with /about
     */
    @Test
    void testAbout() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/about",
                String.class)).contains("Bookclub | About");
    }

    /**
     * Tests contact page is returned with /contact
     */
    @Test
    void testGetContact() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/contact",
                String.class)).contains("Bookclub | Contact");
    }

    /**
     * Tests feature to query with /{id}
     */
    @Test
    void testFindBook() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/9780062237354",
                String.class)).contains("Reaper man");
    }

}
