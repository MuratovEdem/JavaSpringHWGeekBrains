package ru.gb.timesheet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimesheetControllerTest {

    @Autowired
    TimesheetRepository timesheetRepository;

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
        timesheetRepository.deleteAll();
    }

    @Test
    void getByIdAllOk() {
        Timesheet timesheet = new Timesheet();
        timesheet.setMinutes(3);
        timesheet.setProjectId(1L);
        Timesheet expected = timesheetRepository.save(timesheet);

        ResponseEntity<Timesheet> actual = restClient.get()
                .uri("/timesheets/" + expected.getId())
                .retrieve()
                .toEntity(Timesheet.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        Timesheet responseBody = actual.getBody();
        assertNotNull(responseBody);
        assertEquals(timesheet.getId(), responseBody.getId());
        assertEquals(timesheet.getProjectId(), responseBody.getProjectId());
        assertEquals(timesheet.getMinutes(), responseBody.getMinutes());
    }

    @Test
    void getAll() {
        List<Timesheet> expected = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Timesheet timesheet = new Timesheet();
            timesheet.setMinutes(i+10);
            timesheet.setProjectId((long) i);
            expected.add(timesheetRepository.save(timesheet));
        }

        ResponseEntity<List<Timesheet>> actual = restClient.get()
                .uri("/timesheets")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Timesheet>>() {
                });

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        List<Timesheet> responseBody = actual.getBody();
        assertNotNull(responseBody);

        for (int i = 0; i < expected.size(); i++) {
            Timesheet request = expected.get(i);
            Timesheet response = responseBody.get(i);

            assertEquals(request.getId(), response.getId());
            assertEquals(request.getProjectId(), response.getProjectId());
            assertEquals(request.getMinutes(), response.getMinutes());
        }
    }

    @Test
    void testCreate() {
        Timesheet toCreate = new Timesheet();
        toCreate.setProjectId(1L);
        toCreate.setMinutes(3);
        toCreate.setCreatedAt(LocalDate.MIN);

        ResponseEntity<Timesheet> response = restClient.post()
                .uri("/timesheets")
                .body(toCreate)
                .retrieve()
                .toEntity(Timesheet.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Timesheet responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody.getProjectId(), toCreate.getProjectId());
        assertEquals(responseBody.getMinutes(), toCreate.getMinutes());
        assertEquals(responseBody.getCreatedAt(), toCreate.getCreatedAt());

        assertTrue(timesheetRepository.existsById(responseBody.getId()));
    }

    @Test
    void testDeleteById() {
        Timesheet toDelete = new Timesheet();
        toDelete.setMinutes(3);
        toDelete.setProjectId(1L);
        toDelete = timesheetRepository.save(toDelete);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/timesheets/" + toDelete.getId())
                .retrieve()
                .toBodilessEntity();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        assertFalse(timesheetRepository.existsById(toDelete.getId()));
    }


}
