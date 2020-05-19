package com.volkangurbuz.spring5webfluxrest.controller


import spock.lang.Specification

class CategoryControllerTestGroovy extends Specification {

    def "max(#a, #b) == #c"() {

        expect:
        Test.getMax(a, b) == c

        where:
        a | b | c
        1 | 2 | 2

    }

}
