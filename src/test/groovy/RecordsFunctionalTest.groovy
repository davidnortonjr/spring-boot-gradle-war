import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RecordsFunctionalTest extends Specification implements ResetsDatabase {
    RestTemplate restTemplate = new RestTemplate()

    void 'can create and read records'() {
        given:
        assert listRecords().size() == 0

        when:
        restTemplate.postForLocation('http://localhost:8080/records', [:])
        List records = listRecords()

        then:
        records.size() == 1
        records[0].id == 1
    }

    void 'another test'() {
        given:
        assert listRecords().size() == 0

        when:
        3.times {
            restTemplate.postForLocation('http://localhost:8080/records', [:])
        }

        then:
        listRecords().size() == 3
    }

    private List listRecords() {
        return restTemplate.getForObject('http://localhost:8080/records', List)
    }
}
