package grails2x.sql.logging

class Person {

    String name
    Integer age
    BloodType bloodType

    static constraints = {
        name blank: false, maxSize: 1000
        age min: 0
    }
}
