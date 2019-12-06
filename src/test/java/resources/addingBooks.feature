Feature: User can add a valid book to reading tips

  Scenario: user can add a valid book
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    When a valid booktitle "Tira-kirja" and author "Antti Laaksonen" are entered
    Then system will respond with "Tira-kirja tallennettu!"

  Scenario: user can not add a book without a name
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    When an empty booktitle "" and author "Pekkaliisa Peukaloinen" are entered
    Then system will respond with "Kirjan nimen tulee olla 3-100 merkkiä"

  Scenario: user can add a book without a author
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    When a valid booktitle "Tira-kirja 2 - Empire Strikes Back" and author "" are entered
    Then system will respond with "Tira-kirja 2 - Empire Strikes Back tallennettu!"

  Scenario: user cannot add a book with a title less than 3 characters long 
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    When too short booktitle "aa" and author "" are entered
    Then system will respond with "Kirjan nimen tulee olla 3-100 merkkiä"

  Scenario: user cannot add a book with a title more than 100 characters long 
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    When too long booktitle "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" and author "" are entered
    Then system will respond with "Kirjan nimen tulee olla 3-100 merkkiä"

  Scenario: user cannot add a book with a author more than 50 characters long 
    Given lisaa lukuvinkki is selected
    Given option kirja is selected
    When a valid booktitle "Art of the Deal" and a too long author name "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" are entered
    Then system will respond with "Kirjailijan nimen tulee olla 3-50 merkkiä"
