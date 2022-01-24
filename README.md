# Photobooth

## Implementation 
Created a simple photo booth using CRUD design for Purchases, Customers, and PhotoPackages.

## Running the Application
In an IDE run the PhotoboothApplication class

## Test
### Unit Testing
Open a console in the home directory of this project and run mvn test.

## Future Work
- Separate into microservices
- Implement more repos and wrap them in the services

## Challenge: I’m feeling lucky!
At Apple Industries it’s important to remember that we sell fun: you and your friends climbing in one of
our mega booths for some silly group scenes, hanging out in the movie theater lobby and being part of
the movie magic, making your own memorabilia at a sports stadium or theme park.   We also like
winning prizes! 
Our booths have three types of PhotoPackages: prints (4x6 photo) for $5, panoramas (6x12 prints) for
$7, and strips (two 2x6 photo strips) for $5.  Once per hour at most, we want one customer
that orders each package type to have a chance to receive the other two packages of prints for free.   To
do that we’ll need some software that knows what package the customer ordered, determines if that
customer’s order is eligible to win, and then if they in fact won the free prints.

### Implementation
Prize winners are selected based off the listed criteria
- They purchased only 1 PhotoPackage with quantity of 1
- The PhotoPackage purhcased has not generated a prize in the last hour
- They pass "dice roll" to see if they are the lucky winner

Prize tracking is tracked by the luckEnabled column on each PhotoPackage. Every hour at the top of the hour all PhotoPackage's have their luckEnabled set to true

### Test
#### DB Test
- In an IDE run the PhotoboothApplication class
- Navigate to http://localhost:8080/h2-console/
- Click "Connect" on the login form
- Copy the contents of [displayDB](https://github.com/ZanePerrinSmith/photobooth/blob/master/db/sql/displayDB.sql) to the H2 console
- Select run
- The 1st table will show you the Prices that various Customers purchased PhotoPackages (the one's prized $0 are prizes)
- The 3rd table will show you the PurchaseDetails of those prizes linked the same Purchase in the Purchase_ID column
### Future Work
Increase a customer's luck with loyalty. I was once told that randomly selected winners are never actually random.
They are selected based on how influential they are to your business i.e. extremely loyal customer, or post many positive reviews online

- Add a column to customer to track number of purchases (or loyalty)
- Use this number to increase that Customer's chances of getting lucky
- Add a column to customer luckEnabled with similar functionality to the column in PhotoPackage
- Create a scheduled task to reset the new columns

## Challenge: Only two things in life are guaranteed…
Once per calendar month Agent Smith of the Internal Revenue Service (IRS) wants to know how much
tax we owe them.  For now, since our headquarters are on Long Island, New York, the IRS only requires
us to pay the local sales tax rate of 8.625% no matter where the customer paid to use our photo booth. 
Luckily, we don’t need to pay taxes on the packages we gave away for free.  To make this easy we’ll
need some software that can add up all the order revenue collected in a photo booth by calendar month
and determine how much tax we owe the IRS.

### Implementation
I created a scheduled task that would sum up the prices for all of the Purchases and then multiplied them with the current sales tax rate.
This task runs on the first of each month to calculate the previous month's taxes. Since the price of the prize PhotoPackages is $0,
they are not calculated for in the taxes.

### Future Work
- Add an entity, service, and controller for Tax information to be stored with columns month and taxes.
