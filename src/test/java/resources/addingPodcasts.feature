Feature: User can add a valid podcast to reading tips

Scenario: user can add a podcast with a valid title
    Given lisaa lukuvinkki is selected
    Given option podcast is selected
    When a valid episode title "Auta Antti" is given and other fields are empty 
    Then system will respond with "Auta Antti"

Scenario: user cannot add a podcast with too short episode title
    Given lisaa lukuvinkki is selected
    Given option podcast is selected
    When an invalid episode title "aa" is given and other fields are empty 
    Then system will respond with "Podcastin nimen tulee olla 3-100 merkkiä"

Scenario: user cannot add a podcast with too long episode title
    Given lisaa lukuvinkki is selected
    Given option podcast is selected
    When an invalid episode title "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" is given and other fields are empty 
    Then system will respond with "Podcastin nimen tulee olla 3-100 merkkiä"

Scenario: user cannot add a podcast with too short series title
    Given lisaa lukuvinkki is selected
    Given option podcast is selected
    When a valid episode title and an invalid series title "ii" are given and other fields are empty 
    Then system will respond with "Sarjan nimen tulee olla 3-100 merkkiä"