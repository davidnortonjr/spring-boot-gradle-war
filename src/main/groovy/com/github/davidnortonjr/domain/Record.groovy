package com.github.davidnortonjr.domain

import groovy.transform.ToString

import javax.persistence.*

@Entity
@ToString
class Record {
    @Id
    @GeneratedValue
    Long id

}
