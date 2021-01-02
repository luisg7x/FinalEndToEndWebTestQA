Feature: Complete testing basic functions

Scenario: Verify if adding button is adding one item succesfully to shoppig cart
Given User is on the home page
When User add "Brocoli" to shopping cart
Then Shopping cart show only that item added
And That button for add items show text ADDED

Scenario: Verify if shoppig cart keep the item previusly added when website is refreshed
Given User is on the home page
When User add "Brocoli" to shopping cart
And User refresh website
Then Shopping cart show only that item added

Scenario: Verify if adding button is adding one item with a quantity higther than one succesfully to shoppig cart
Given User is on the home page
When User add "5" "Brocoli" to shopping cart
Then Shopping cart show only that item added
And That button for add items show text ADDED

Scenario: Verify if adding button is adding one ittem with a quantity higther than one succesfully to shoppig cart with correct price
Given User is on the home page
When User add "5" "Brocoli" to shopping cart
Then Shopping cart show the correct sum of the item in the price 
And Shopping cart show only that item added


Scenario: Verify if can add multiple items to the shoppig cart
Given User is on the home page
When User add "Brocoli" "Tomato" "Carrot" "Beans" to shopping cart
Then Shopping cart show that items added

Scenario Outline: Verify if shoppig cart show correct information when add multiple items with quantity
Given User is on the home page
When User add <item> <quantity> to shopping cart
Then Shopping cart show the correct sum of the item in the price 

Examples:
|item 			| quantity |
|Brocoli 		| 1		|
|Tomato 		| 6		|
|Carrot			| 12	|

Scenario: Verify if clear button in the shopping cart is working 
Given User is on the home page
When User add "Brocoli" "Tomato" "Carrot" "Beans" to shopping cart
And User open the shopping cart and click clear buttom on all items
Then Shopping cart show message "Cart is Empty!"
And Button to proceed to checkout is disabled
