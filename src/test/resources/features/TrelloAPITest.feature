Feature: Testing the API

  Scenario: Trello test
    When Create a board
    And Get a list from created board
    And Create two cards on created board
    Then Update one of them randomly
    Then Delete Cards
    And Delete Board



