Group_0110:

Authors: Tian Yue Dong, Jinyu Liu, Murray Smith, Melody Yang, Riya Razdan, Tingyu Liang, Callan Murphy, Louis Scheffer V


The Trade System (Phase 2):
The Trade System is a program that allows users to view and trade items with each other!
The interface is of this program is a Java.swing GUI. To navigate through the program, first choose your preferred
language: English or French. You will then be able to create a new user account or login either as a user or as an admin.


INSTRUCTIONS:
1. Go to Intellij and click run. Navigate to then click 'main'.
        (a) If an error occurs at this point, it is likely caused by corrupted .ser files.
            You may need to delete all .ser files and run the program again.
2. At this point, you can:
          (a) Create an account.
        (b) Login as either a admin or a user.
                (I) to login as an admin, use the username: "admin" and the password: "admin".
                        (i) More admin accounts can be created from the admin menu.
3. The relevant menu can be accessed and all functionality will be available at this point.



IF USER:
    If creating an account, you will have the option of being a regular user or a “browse-only” user, the
        latter having the same functionality except you will not be able to make trades.
An added feature is Metropolitan Area Selection, in which you can select the closest area in which you want
    to make trades. This area can later be changed, and you will only be able to see other users who are located
        in the same region.
    Upon login, you will have access to the user menu:
*  View items and wishlist
   * You can view your current items and wishlist, request to add an item, and remove items from your list or wishlist.
*  View user stats
   * Here you can view items borrowed, items lent, frozen status, number of incomplete trades, number of weekly
        transactions, most recently traded items and your most frequent trading partners
*  View other users in your area
   * You can select a user to view, and from there you have the choice of sending them a message, viewing their
        items and requesting a trade, and adding their items to your wishlist.
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
   * You can select a user and then have the option to send them a message, remove an item from their wishlist,
        remove an item from their inventory, or freeze the user
* View messages
* View item validation requests


MANDATORY EXTENSIONS:

* To undo an action taken by a user, create a user account and request to add an item. Relogin in as an admin and
    approve of the item. You can now also choose to remove that item. If you create a second user, add an item and propose
    a trade by viewing the original user's profile, you can also remove an item from a user’s wishlist or cancel a trade.
    The admin can also cancel any trade request created by a user.
* To see a trade suggestion, create two user accounts and have them add items approved by the admin such that each
    other’s items are added to their wishlists. Then a suggest trade option is available.
* To be a browse-only user that doesn’t need to make any trades, select the browse-only option at the top of the
    “create an account” page.
* To change any user threshold, log in as an admin and click on the threshold change menu buttons.
    New thresholds can be confirmed by viewing the “view current threshold values” button in the main admin menu
* To change the account status as a user, log in as a user and choose the change active status in the user menu to
    let other users know that you are currently inactive and cannot make trades


OPTIONAL EXTENSIONS:
* GUI using Java.swing
* Home City: when creating a new user account, you have the option of choosing from a list of cities in which
    you want to trade. Once logged in as a user, this location can be changed. If another user is created with the
    same chosen city but then later changed, the list of other users in your area (main menu for users) will no
    longer show that user.
* Extra admin Functionality: Admin's can message users, as well as view all user profiles and take additional actions
there.
* Multiple languages: From the beginning itself, you have the choice between using the trade system either
    in English or in French (which is set up such that it can be extended to any other language).


DESIGN PATTERNS:

    We implemented:
        * Strategy for login logic. We had multiple different ways of logging in and wanted to make logging in
            extensible for security concerns in the future. In ValidateLoginStrategy.
        * Dependency injection throughout our use-case layer and controller/presenter layer to ease dependencies
            between classes. We use UseCaseGrouper and ControllerPresenterGrouper to hold and distribute all of our
            instances.

    We chose not to implement:
        * Observer pattern because we did not find any points in our program where we wanted to separate our real-time
            causes and effects. We considered implementing it for our now-deceased alert system, however we scrapped
            that system because it violated clean architecture.
        * Facade pattern because we payed close attention to SRP violations in our code, and thus did not recognize an
            antipattern for Facade (A single class responsible to multiple actors). We considered applying a Facade pattern to
            our UserManager use case, but we decided instead to split up the class into many smaller Use Cases responsible
            for different things in the program, as the facade we would have implemented here would quickly have bloated.
        * Builder pattern because there are no single instances complicated enough to warrant their own class for building.
            We considered implementing a builder for our TradeSystem class, but we decided that one of TradeSystem's main purposes
            is to link together the controllers, presenters, and gateways in our program, which is not conducive of a builder.
            Additionally, we did not need to encapsulate the construction of the TradeSystem class as it was overall fairly
            straightforward (one line of code in main).
        * We had considered implementing a factory pattern for our now-scrapped alert system, however we decided to
            get rid of this system because of clean architecture violations.
        * We did not find semblances of antipatterns for the other design patterns (Adapter, Iterator, Abstract Factory)

IMPROVEMENTS FROM PHASE 1:
    * Our UI! We decided to implement a java swing UI that wraps over our existing code. We had to significantly refactor
        and de-couple our controller/presenter stuff from our input/output logic, as this was nescesary for the previous
        text-based UI. As a result of this decoupling, our UI can now be switched out for a different UI (an app, for example)
        with ease.
    *
    * We created a new use case class (ItemManager) to fix coupling and SRP issues in our old UserManager. Based on our
        TA's feedback, we determined that UserManager was responsible for too many different systems, so we decided to
        split it up into two classes, UserManager (now just for user functionality) and ItemManager (for item stuff).
        This has made our code significantly more extensible and adding features to Item management has been a breeze
        in phase 2 as a result. In particular, undo actions for the admin have been a very easy to extend because of
        this.
    *
    * We have packaged our code by layer, meaning our usecases, controller/presenter code, entities, and GUI are all
        in seperate packages.