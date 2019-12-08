Feature: User can edit the reading tips they have previously added

  Scenario: The user can edit a book they have previously added
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    Given testbook has been added to reading tips
    Given katsele lukuvinkkeja is selected
    When muokkaa is selected
    When author is edited to ..1..
    Then tip list contains new author name ..1..

