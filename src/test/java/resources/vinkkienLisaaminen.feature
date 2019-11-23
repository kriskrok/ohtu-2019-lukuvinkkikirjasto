Feature: User can add a valid book to reading tips

  Scenario: user can add a valid book
    Given lisaa kirja lukuvinkkeihin is selected
    When a valid booktitle "Tira-kirja" and writer "Antti Laaksonen" are entered
    Then system will respond with "Tira-kirja tallennettu!"

  Scenario: user can not add a book without a name
    Given lisaa kirja lukuvinkkeihin is selected
    When an empty booktitle "" and writer "Pekkaliisa Peukaloinen" are entered
    Then system will respond with "Täytäthän kummatkin tiedot!"

  Scenario: user can not add a book without a writer
    Given lisaa kirja lukuvinkkeihin is selected
    When a booktitle "Muumipeikko ja Pyrstötähti" and empty writer "" are entered
    Then system will respond with "Täytäthän kummatkin tiedot!" 

