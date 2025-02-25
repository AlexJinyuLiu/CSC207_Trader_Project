Group_0110:
______________               ________                _________             ______  ___                _____                      _______ _______ _______ _______
___  __/___  /_ _____        ___  __/______________ _______  /_____        ___   |/  /______ ___________  /______ ________       __  __ \__  __ \__  __ \__  __ \
__  /   __  __ \_  _ \       __  /   __  ___/_  __ `/_  __  / _  _ \       __  /|_/ / _  __ `/__  ___/_  __/_  _ \__  ___/       _  /_/ /_  / / /_  / / /_  / / /
_  /    _  / / //  __/       _  /    _  /    / /_/ / / /_/ /  /  __/       _  /  / /  / /_/ / _(__  ) / /_  /  __/_  /           _\__, / / /_/ / / /_/ / / /_/ /
/_/     /_/ /_/ \___/        /_/     /_/     \__,_/  \__,_/   \___/        /_/  /_/   \__,_/  /____/  \__/  \___/ /_/            /____/  \____/  \____/  \____/

Authors: Tian Yue Dong, Jinyu Liu, Murray Smith, Melody Yang, Riya Razdan, Tingyu Liang, Callan Murphy, Louis Scheffer V

The Trade Master 9000 (Phase 1):

The Trade Master 9000 is a program that allows users to trade items with each other!

The interface is of this program is text through the java console, to navigate through the program, the presenter will
prompt you for valid user input. For menus, you will need to enter a number ranging from 0-10 to the corresponding menu
number, and for other uses cases, simply enter the information as prompted.

UML diagrams have been created for the src folder and the package alertpack. Both of these diagrams are inside of the design.pdf file inside the phase1 folder.

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
        - FreezeUserAlert (A user has violated a rule and needs to be frozen)
        If a user borrowed an item that make their borrowed greater than or equal to their lent, admin will be alerted
        of this and has the option to freeze that user.
        - ItemValidationAlert (A user has sent a request for their item to be added to their inventory)
        - ReportAlert (A User has been reported by another user, freeze them)
        If a user did show up to a trade that was accepted by both parties, the other user can report them. Admin must
        accept this report for it to count towards incomplete trades. A user will be frozen if the admin approves a
        report that puts their incomplete transactions over the threshold.
        - UnfreezeRequestAlert (A user has sent a request to unfreeze their account)

     Admin Login Menu
        (1) Set borrow/lend threshold
        (2) Set complete trade threshold
        (3) Set incomplete trade threshold
        (4) Add new admin
        (5) View all threshold values
        (0) Quits

** Please QUIT from main menu instead of stopping the program to ensure .ser files get updated **