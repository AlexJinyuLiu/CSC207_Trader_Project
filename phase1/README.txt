Group_0110:

Authors: Tian Yue Dong, Jinyu Liu, Murray Smith, Melody Yang, Riya Razdan, Tingyu Liang, Callan Murphy, Louis Scheffer V

The Trade Master 9000 (Phase 1):

The Trade Master 9000 is a program that allows users to trade items with each other!

The interface is of this program is text through the java console, to navigate through the program, the presenter will
prompt you for valid user input. For menus, you will need to enter a number ranging from 0-10 to the corresponding menu
number, and for other uses cases, simply enter what the program tells you to do and hit enter.

Instructions:
1. Go to Intellij and click run. Navigate to then click 'main'.
	(a) If an error occurs at this point, it is likely caused by corrupted .ser files. You may need to delete all .ser files and run the program again.
2. At this point, you can:
  	(a) Create an account.
	(b) Login as either a admin or a user.
		(I) to login as an admin, use the username: "admin" and the password: "admin".
			(i) More admin accounts can be created from the admin menu.
3. Users (Admin or regular) will be prompted with alerts which outline things that need their attention at that moment. All alerts must be handled before the menu can be accessed.
4. The menu can be accessed and all functionality will be available at this point.

IF USER:
    Upon login, you will be first be prompted with a Queue of UserAlerts that you need to handle, these include:
        - Expiration Alert (The due date of a temporary trade has passed and the trade is not complete)
        - FrozenAlert (your account has been frozen by administrator)
        - ItemValidationRequestDeclinedAlert (your itemValidation has been declined)
        - MessageAlert (Another user has messaged you)
        - TradeRequestAlert (Another User has sent you a Trade request)
        - TradeCancelledAlert (The item involved in this trade is no longer available, This trade has been cancelled)
        - TradeDeclinedAlert (The trade offer you sent has been declined by the other user)
        - TradeRequestAcceptedAlert (Trade has been accepted by the other user)
        - TradePastDateAlert (The exchange date of the trade has passed, report what happened)

    After handling the user alerts, you will have access to the Usermenu
        (1) View items and wishlist
            (1)Send ItemValidationRequest
            (2) Remove an item from wishlist
            (3) Remove an item from available items
        (2) View user stats
            (1) Send Item Validation request
            (2) Remove an item from available items
            (3) Remove an item from your wishlist
            (0) Exit to main menu
        (3) Request an unfreeze
        (4) View other users
            Enter a number to view a User's page:
            (1) send a message to userToView
            (2) add one of userToView's items to the wishlist
            (3) send a trade request to userToView
        (5) View your pending trades
        (6) View active temporary trades
        (0) Quit


IF ADMIN:
    Upon login, you will be first be prompted with a Queue of UserAlerts that you need to handle, these include:
        - FreeUserAlert (A user has violated a rule and needs to be frozen)
        - ItemValidationAlert (A user has sent a request for their item to be added to their inventory)
        - ReportAlert (A User has been reported by another user, freeze them)
        - UnfreezeRequestAlert (A user has sent a request to unfreeze their account)

     Admin Login Menu
        (1) Set borrow/lend threshold
        (2) Set complete trade threshold
        (3) Set incomplete trade threshold
        (4) Add new admin
        (5) View all threshold values
        (0) Quits
