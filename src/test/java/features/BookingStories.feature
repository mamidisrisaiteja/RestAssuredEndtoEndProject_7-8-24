
Feature: Books API

Background:
Given set the base url

Scenario: To make a booking
When check the health of the api
Then Validate the 'resPing' to be 201



Scenario: To get all the booking ids
When to get the booking ids
Then Validate the 'resBooking' to be 200


