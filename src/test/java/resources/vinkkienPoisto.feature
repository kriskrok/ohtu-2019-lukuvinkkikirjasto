Feature: User can delete the reading tips they have added

  Scenario: user can select to delete a reading tip
    Given one book with title "Kalevala" and writer "Elias Lönnrot" has already been successfully added
    And katsele lukuvinkkeja is selected
    And valitse poista lukuvinkki is selected
    Then system will respond with "Haluatko varmasti poistaa vinkin?"

