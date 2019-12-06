Feature: User can see the reading tips they have added

  Scenario: user can see one reading tip, they have just added
    Given one book with title "Kalevala" and author "Elias Lönnrot" has already been successfully added
    And katsele lukuvinkkeja is selected
    Then system will respond with "Kalevala"

  Scenario: user can see one reading tip added without the author
    Given one book with title "Tira-kirja 2 - Empire Strikes Back" and author "" has already been successfully added
    And katsele lukuvinkkeja is selected
    Then system will respond with "Tira-kirja 2 - Empire Strikes Back"