# FXDealWarehouseAPI
 A data warehouse system for Bloomberg to analyze FX deals. It accepts deals and their details from the customers and persists these deals in the Database.
 Based on that, the application has one type of bean, that is the FXDeal bean. FXDeal consists of the following fields:		
   1. dealId.
   2. fromCurrencyISOCode.
   3. toCurrencyISOCode.
   4. dealTimestamp.
   5. dealAmount.
 
	In addition to the above fields, the bean has a Primary Key that is generated by the Database as a sequence and its name is sequentialId. All of the fields above are Not Nullable.
 
# Running the application
   To run the application, first of all you must pull the repository from GitHub in a folder
 then, go to the directory(context root) of the project and run the following commands. 
  
   If you're using Linux, use your terminal and enter the following commands: 
    1. make build
    2. docker-compose build
    3. docker-compose up
    
   Ensure that you have mingw32 installed on your machine.
    
   If you're using Windows, use your terminal and enter the following commands: 
    1. c:\mingw32-make.exe build
    2. docker-compose build
    3. docker-compose up
  
   # Testing the application
       To test the API endpoint of the application that is used to save the deals into the DB by sending a POST request using the following path/URL: 
       http://localhost:8080/bloomberg/warehouse/fxdeal/save.

	In the following link there is an example that you can refer to on Postman API that was used to test the FXDealWarehouseAPI application:
 	https://app.getpostman.com/join-team?invite_code=56e637b07e58a0ab47951aeabb924495&target_code=9988be92890e097334fff8e9612acf40.
  
  # Validation rules of the FXDeal Bean Fields:
   1. dealId: It has a unique Unique Constraint on the Database, and it is an Alphanumeric String and its length must be at least of 8 characters and does not exceed 20 characters. The FX Deal's ID cannot contain spaces. 
   2. fromCurrencyISOCode: The FX Deal's Currency ISO Codes must be String that contain exactly 3 digits with no spaces.
   3. toCurrencyISOCode: The FX Deal's Currency ISO Codes must be String that contain exactly 3 digits with no spaces.
   4. dealTimestamp: The FX Deal's Timestamp must be today or in future.
   5. dealAmount: The FX Deal's amount must be a positive whole number and do not contain a decimal point. The Deal Amount must have at least one digit and do not exceed 9 digits.

	For the FXDeal to be valid each field of the FxDeal fields cannot be empty. However, if any of the above constraints is not met the system will throw a customized exception with the proper error message, and the FXDeal Service will handle it and return the appropriate response based on the thrown exception.

 	In the end, Junit was used to carry out the Unit Testing process, especially to ensure that the FXDealValidator class was working appropriately.  
