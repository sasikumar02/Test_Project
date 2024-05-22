Feature: automate amazon website

  @Regression @Browser
  Scenario Outline: Login amazon website using browserstack
  Given Launch the site
    Then create user using "<testData>"

    Examples:
    |testData|
    |Create_Customer|