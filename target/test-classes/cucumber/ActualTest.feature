@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page
  
  @Regression
  Scenario Outline: Positive test of Actual Test
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    And Checkout <productName> and submit the order
    Then verify "THANKYOU FOR THE ORDER." is displayed on Confirmation page.

    Examples: 
      | name  							| password 		| productName     |
      | dummy1997@gmail.com | Star@1996   | ADIDAS ORIGINAL |
      
