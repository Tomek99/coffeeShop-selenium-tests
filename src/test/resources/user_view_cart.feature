Feature: User view cart
    Checking functions on user view cart

    Background: The user is the viewing cart page
      Given The user is located on main page
      When The user opens product page
      And The user adds products to cart
      And The user opens quick overview cart
      And The user opens View Cart page

    Scenario: Verify cart quantity
      Then The sum quantity of products displayed in the cart should be correct

    Scenario:  Delete all product in the cart
      And The user clears the cart
      Then The title about empty cart is displayed

    Scenario: Delete products from cart
      And The user deletes product from the cart
      Then The notification about deleted products is displayed

    Scenario: Change product quantity
      And The user changes product quantity
      Then The sum quantity of the products displayed in the cart should be correct

    Scenario: Add wrong discount
      And The user inputs incorrect discount
      Then The notification about incorrect discount is displayed

    Scenario: Open product details
      And The user opens product details
      Then The product details is displayed
