package grails2x.sql.logging

import groovy.sql.Sql
import spock.lang.*
import grails.test.spock.IntegrationSpec

@Stepwise
class PersonSpec extends IntegrationSpec {

    static transactional = false

    def dataSource

    void "insert"() {
        given:
        def person = new Person(name: "Oda Nobunaga", age: 47, bloodType: BloodType.A)

        when:
        person.save(failOnError: true, flush: true)

        then:
        person.count() == 1
    }

    void "update"() {
        given:
        def person = Person.findByName("Oda Nobunaga")

        when:
        person.age = 99
        person.save(failOnError: true, flush: true)

        then:
        person.count() == 1
        person.age == 99
    }

    void "Groovy SQL"() {
        given:
        def sql = new Sql(dataSource)

        when:
        def persons = sql.rows("SELECT * FROM person WHERE name = ?", ["Oda Nobunaga"])

        then:
        persons*.name == ["Oda Nobunaga"]
    }

    void "delete"() {
        given:
        def person = Person.findByName("Oda Nobunaga")

        when:
        person.delete(flush: true)

        then:
        person.count() == 0
    }
}
