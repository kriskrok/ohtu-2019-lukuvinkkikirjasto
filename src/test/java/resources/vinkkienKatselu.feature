Feature: User can add a valid book to reading tips

  Scenario: user can add a valid book
    Given katsele lukuvinkkeja is selected
    Then system will respond with "Ei vielä lukuvinkkejä"

  Scenario: user can add a valid book
    Given one book with title "Kalevala" and writer "Elias Lönnrot" has already been successfully added
    And katsele lukuvinkkeja is selected
    Then system will respond with "Kalevala - Elias Lönnrot"