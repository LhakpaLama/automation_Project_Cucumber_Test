Feature: Yahoo Search result

Scenario: User should be able to search elements in Yahoo
            Given user is navigated to yahoo home page
            When user inputs their text in search box
            And press the search button
            Then  user should be able to see the total search result