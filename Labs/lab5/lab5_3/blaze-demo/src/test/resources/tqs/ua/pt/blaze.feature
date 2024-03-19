Feature: Buy a Trip
 
  Scenario: Plan a trip from San Diego to London
    When I navigate to "https://blazedemo.com"
    And I choose depart in "San Diego" and arrive in "London"
    And I click Find Flights
    Then I should be see the message "Flights from San Diego to London:"

    And I choose the flight number 4
    Then I should be see the message "Your flight from TLV to SFO has been reserved."

    And I fill in the blanks with my data: I'm "Michael Jackson", I live in "123 Main St.", 12345, "San Francisco", "California". My credit card from "American Express" has the number 13421645 and expires on 03/2025
    Then I should be see the message "Thank you for your purchase today!"
  