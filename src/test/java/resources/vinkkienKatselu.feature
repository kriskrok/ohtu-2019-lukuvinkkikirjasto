Feature: User can see the reading tips they have added

  Scenario: user can not see any reading tips, if none were yet added
    Given katsele lukuvinkkeja is selected
    Then system will respond with "Ei vielä lukuvinkkejä"

  Scenario: user can see one reading tip, they have just added
    Given one book with title "Kalevala" and writer "Elias Lönnrot" has already been successfully added
    And katsele lukuvinkkeja is selected
    Then system will respond with "Kalevala - Elias Lönnrot"

  Scenario: user can see one reading tip added without the author
    Given one book with title "Tira-kirja 2 - Empire Strikes Back" and writer "" has already been successfully added
    And katsele lukuvinkkeja is selected
    Then system will respond with "Tira-kirja 2 - Empire Strikes Back"