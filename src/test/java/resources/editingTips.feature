Feature: User can edit the reading tips they have previously added


  Scenario: user can select to delete all reading tips
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    Given testbook has been added to reading tips
    Given katsele lukuvinkkeja is selected
    When  poista lukuvinkki is selected until no tips remain
    Then the page has content "Ei vielä lukuvinkkejä"