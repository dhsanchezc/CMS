package com.example.contactmanager.services;

import org.springframework.data.repository.CrudRepository;

import com.example.contactmanager.pojo.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
