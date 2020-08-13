Group_0110:

Authors: Tian Yue Dong, Jinyu Liu, Murray Smith, Melody Yang, Riya Razdan, Tingyu Liang, Callan Murphy, Louis Scheffer V


The Trade System (Phase 2):
The Trade System is a program that allows users to view and trade items with each other!
The interface is of this program is a Java.swing GUI. To navigate through the program, first choose your preferred language: English or French. You will then be able to create a new user account or login either as a user or as an admin.


INSTRUCTIONS:
1. Go to Intellij and click run. Navigate to then click 'main'.
        (a) If an error occurs at this point, it is likely caused by corrupted .ser files. You may need to delete all .ser files and run the program again.
2. At this point, you can:
          (a) Create an account.
        (b) Login as either a admin or a user.
                (I) to login as an admin, use the username: "admin" and the password: "admin".
                        (i) More admin accounts can be created from the admin menu.
3. The relevant menu can be accessed and all functionality will be available at this point.




IF USER:
    If creating an account, you will have the option of being a regular user or a “browse-only” user, the latter having the same functionality except you will not be able to make trades. 
An added feature is Metropolitan Area Selection, in which you can select the closest area in which you want to make trades. This area can later be changed, and you will only be able to see other users who are located in the same region.
    Upon login, you will have access to the user menu:
*  View items and wishlist
   * You can view your current items and wishlist, request to add an item, and remove items from your list or wishlist.
*  View user stats
   * Here you can view items borrowed, items lent, frozen status, number of incomplete trades, number of weekly transactions, most recently traded items and your most frequent trading partners
*  View other users in your area
   * You can select a user to view, and from there you have the choice of sending them a message, viewing their items and requesting a trade, and adding their items to your wishlist.
* View pending trades
   * You can view your pending trades after selecting a specific trade
* View dead trades
   * You can view any dead trades that didn’t reach completion
* View trade requests
   * You can view any trade requests sent by another user
* View completed trades
   * All completed trades will be visible here
* View active temporary trades
   * You can view your active temporary trades after selecting a specific trade
* View messages
   * Each message is accompanied by which user sent it and the date it was sent
* Set active status
* Change metro area
* Request unfreeze




IF ADMIN:
     Admin Login Menu
* Set borrow/lend threshold
* Set complete trade threshold
* Set incomplete trade threshold
* Add a new admin
* View current threshold values
* Edit or undo a trade
* View all users
   * You can select a user and then have the option to send them a message, remove an item from their wishlist, remove an item from their inventory, or freeze the user
* View messages
* View item validation requests






MANDATORY EXTENSIONS:
* To undo an action taken by a user, create a user account and request to add an item. Relogin in as an admin and approve of the item. You can now also choose to remove that item. If you create a second user, add an item and propose a trade, you can also remove an item from a user’s wishlist or cancel a trade
* To see a trade suggestion, create two user accounts and have them add items approved by the admin such that each other’s items are added to their wishlists. Then a suggest trade option is available
* To be a browse-only user that doesn’t need to make any trades, select the browse-only option at the top of the “create an account” page
* To change any user threshold, log in as an admin and click on the threshold change menu buttons. New thresholds can be confirmed by viewing the “view current threshold values” button in the main admin menu
* To change the account status as a user, log in as a user and choose the change active status in the user menu to let other users know that you are currently inactive and cannot make trades


OPTIONAL EXTENSIONS:
* GUI using Java.swing
* Home City: when creating a new user account, you have the option of choosing from a list of cities in which you want to trade. Once logged in as a user, this location can be changed. If another user is created with the same chosen city but then later changed, the list of other users in your area (main menu for users) will no longer show that user
* Multiple languages: From the beginning itself, you have the choice between using the trade system either in English or in French (which is set up such that it can be extended to any other language).