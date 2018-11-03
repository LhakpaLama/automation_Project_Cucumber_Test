$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("sample.feature");
formatter.feature({
  "line": 1,
  "name": "Yahoo Search result",
  "description": "",
  "id": "yahoo-search-result",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "User should be able to search elements in Yahoo",
  "description": "",
  "id": "yahoo-search-result;user-should-be-able-to-search-elements-in-yahoo",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "user is navigated to yahoo home page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "user inputs their text in search box",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "press the search button",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "user should be able to see the total search result",
  "keyword": "Then "
});
formatter.match({
  "location": "cucumberR.user_is_navigated_to_yahoo_home_page()"
});
formatter.result({
  "duration": 9287866455,
  "status": "passed"
});
formatter.match({
  "location": "cucumberR.user_inputs_their_text_in_search_box()"
});
formatter.result({
  "duration": 1555866502,
  "status": "passed"
});
formatter.match({
  "location": "cucumberR.press_the_search_button()"
});
formatter.result({
  "duration": 511233791,
  "status": "passed"
});
formatter.match({
  "location": "cucumberR.user_should_be_able_to_see_the_total_search_result()"
});
formatter.result({
  "duration": 3335161030,
  "status": "passed"
});
});