package com.github.davidnortonjr.controller

import com.github.davidnortonjr.domain.Record
import com.github.davidnortonjr.domain.RecordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/records")
class RecordsController {

    @Autowired
    RecordRepository recordRepository

    @RequestMapping(method = RequestMethod.GET)
    public List<Record> list() {
        return recordRepository.findAll()
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save() {
        recordRepository.save(new Record())
    }

}