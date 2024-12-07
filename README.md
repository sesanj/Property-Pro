# Property Pro

Property pro is a database management software used to track and manage a real estate business. 
It keeps track of clients/guest information, properties owned by the business and all data related to generated revenue.

Businesses can perform certain operations like updating, creating, retrieving and deleting data from the database.

<br>

## Managing Data

The application will manage the following data:


### Monthly Revenue:

The application provides a clean UI/UX that allows the business to track and manage revenue as well as transactions related to the business.
Generated revenue can be tracked on different data points by day, month or years.


### Client Information:

This involves managing customers personal information like Name, Phone Number, Email, amount income generated for the business, total bookings, as well as the property they stayed at.


### Properties Owned:

This involves managing data related to properties owned by the business, these data include, property Type, location, availability, revenue generated. The business can keep track of all 
properties in various locations in a clean and refined manner.


### Contact Support:

The settings section provides an option to contact support, in this case, the developers which can be a helpful tool just in case you run into a problem when utilizing the application.

<br>

## Performing CRUD Operations

All CRUD operations will be performed from the applications front end dashboard. See the highlights below:

<br>

#### CREATE:

The business will be able to create new data entry for their clients, properties and revenue/transactions.

#### RETRIEVE:

Useful and important information will be read from the database and displayed on the front end, these information include displaying a well charted growth or decline in revenue, which clients have spent the most, best performing properties, location or property type based on revenue generated.

#### UPDATE:

Existing Information in the database can be manipulated or updated by the business. These information include name, email, phone number or amount spent. Transactions can also be updated. The business would also be able to updated properties based on availability.

#### DELETE:

The business will have an option to delete or remove any unwanted or irrelevant data entry related to transactions from the database.

<br>

## A Quick Guide To Get Started:

Below are some tools needed to run the application:

- An IDE, preferably IntelliJ
- JavaFx Enabled
- Updated Gradle, Preferably "Amazon Corretto, Version 21.0.5".
- Access to a database, preferably SQL based.

<br>

### Demo Data & Database Setup

The application comes with embedded demo data that creates 6 tables in your database already normalized for a smooth use of the application.
These are what to expect from the tables:
- 1000 demo transaction data (Transaction Table)
- 1000 demo client data (Client Table)
- 25 demo properties (Property Table)
- 6 demo province (Province Table)
- 18 demo cities (City Table)
- 8 demo property types (Property Type Table)

<br>

### On Application Launch
Make sure your database is ready to receive a connection from the application before launch. When that's done, launch the application. The application will then display a log in page where you're required to enter your database credentials as see below:

<br>

![login.png](src%2Fmain%2Fresources%2Flogin.png)

<br>

Enter your correct database credentials as requested in the form fields and click on test connection. If connection is established, the login page will display a confirmation and provide a button to log into the application. 
Your IDE will display a few confirmations of your tables being created and data inserted into your database as shown below.

<br>

- Credential File Created With Credentials!
- The Client Table Has Been Created Successfully!
- Data Has Been Inserted Into The Client Table Successfully!
- The Province Table Has Been Created Successfully!
- Data Has Been Inserted Into The Province Table Successfully!
- The City Table Has Been Created Successfully!
- Data Has Been Inserted Into The City Table Successfully!
- The Property_type Table Has Been Created Successfully!
- Data Has Been Inserted Into The Property_type Table Successfully!
- The Property Table Has Been Created Successfully!
- Data Has Been Inserted Into The Property Table Successfully!
- The Transaction Table Has Been Created Successfully!
- Data Has Been Inserted Into The Transaction Table Successfully!
- Created Connection!

<br>

### Exploring The Application
When login is successful, you get access to the application where all relevant operations can be performed on different sections of the application as shown in the images below.

<br> 

#### Overview Tab
![overview.png](src%2Fmain%2Fresources%2Foverview.png)

<br>

#### Revenue Tab
![revenue.png](src%2Fmain%2Fresources%2Frevenue.png)

<br>

#### Manage Client Tab
![client.png](src%2Fmain%2Fresources%2Fclient.png)

<br>

#### Manage Property Tab
![property.png](src%2Fmain%2Fresources%2Fproperty.png)

<br>

#### Settings Tab
![support.png](src%2Fmain%2Fresources%2Fsupport.png)



## Authors

**Name:** Sesan Popoola <br>
**Email:** W0836046@myscc.ca <br>
**GitHub:** Sesanj

**Name:** Irene Eweka <br>
**Email:** W0780653@myscc.ca <br>
**GitHub:** ireneewe

**Name:** Emilin Syju <br>
**Email:** W0847462@myscc.ca <br>
**GitHub:** emilinsj

**Name:** Aarav Abraham <br>
**Email:** W0848228@myscc.ca <br>
**GitHub:** AaravAbraham

