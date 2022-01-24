SELECT a.FIRST_NAME, c.TYPE, d.PRICE

FROM CUSTOMER a, PURCHASE b, PHOTO_PACKAGE c, PURCHASE_DETAIL d
WHERE b.CUSTOMER_ID  = a. id
AND d.PURCHASE_ID = b.id
AND d.PHOTO_PACKAGE_ID = c.id;

select * from PURCHASE;

select * from PURCHASE_DETAIL;

select * from PHOTO_PACKAGE ;

select * from CUSTOMER;
