package com.github.davidnortonjr.domain

import org.springframework.data.repository.Repository

interface RecordRepository extends Repository<Record, Long> {

    List<Record> findAll()
    void save(Record record)
}
