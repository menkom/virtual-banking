# Task is to create Virtual Banking system and communicate using own build in command line processor.

## Thoughts during development

1. For less additional manipulation during test start it is better to use json files as storage. No need to install and
   setup anything. ~~Selecting from DB storage and json file storage I prefer database~~
2. This should be a terminal application (not a simple CLI) that accept known list of commands with operations to
   process
3. I'm using Java 17 as it is installed on my machine
4. No info about currency exchange rates, so I expect that it is not possible to transfer between accounts with
   different currencies
5. I divide application into three tasks
    * Model
    * CLI
    * ORM
6. Output data is divided for logs and console. Technical information is logged by log4j to file and user info is in
   console. Log are stored at c:\logs\
7. For customization, we can do property parametrization but let's leave this is for further development
8. At the moment application read whole data at the start and stores on correct exit. Of course, it is possible to
   organize work with data without keeping it in memory using transactions but this is for future.
9. Text fields can contain space in it. Just wrap text in quotes. ~~Names can't contain spaces. I'll try to fix it, but
   I'm not sure to manage it in time.~~
10. The task says that we need to have opportunity to delete Bank and Client.
    * First there are links on banks and clients. So to keep data consistent we have to delete and dependent data. This
      is deletion of too much info
    * Second in bank data have to be stored all the time

    According to this logic I decided to use logic deletion. I took it upon myself to add property 'active' to all model
    classes to control this.
11. I found it confusing "при добавлении клиента в банк" and "клиент может иметь счета в разных банках". This thing took
    some time to decide how I can interpret this. Decision is next. There is a person that can be a client in different
    banks.
12. I know that it is possible to make program much better but during given time managed to do it this way.
13. At the moment of creation of account we have zero balance so we have to add money manual at the moment
14. Left 'update' operation from CRUD for future
15. Using Jackson add-on to support jsr310 (Java 8 Date & Time API) ~~There is problem with saving transfer because of time.~~

## Model

* Person
* Client
* Currency
* Bank
* Account
* Transfer

## Menu

1. User can always leave field blank or enter 'help' and press Enter. In this case he(she) will get basic help
   information about possible options.
2. There are operations for all model entities
    * person
    * client
    * currency
    * bank
    * account
    * transfer
3. Every entity has common operations and some of them have own. Every operation has its own help with example
    * Common operations
        * help - get list of all possible operations for this entity
        * blank (no parameter) - the same as 'help'
        * getAll - get list of all records
        * findById - get entity by id
        * create - create entity according to parameters. Of course every entity has its own list of parameters.
    * Own operations
        * person create personName - creation of person with name 'personName'
        * currency create currencyCode
        * bank create bankName individualTransferRate legalTransferRate - creation of bank
            * bankName - bank name
            * individualTransferRate - transfer rate for individual client(not percent). Using rate we don't need to use
              additional operation of multiplication
            * legalTransferRate - transfer rate for legal client(not percent).
        * client create isIndividual bankId personId defaultCurrencyId - creation of client. By default one account is being opened with defaultCurrencyId in the same bank as client is. 
        * account create currencyId clientId - account creation for existing customer
        * transfer create fromAccountId toAccountId amount - transfering 'amount' of money from 'from' account to 'to'.
        * bank delete bankId - logical deletion of bank and all dependencies with id=bankId 
        * client delete clientId - logical  deletion of client with id=clientId
        * transfer find dateFrom dateTo - get list of transfers during specified period 

## Instructions to use
1. User can delete existing data file and application will start from scratch or you can continue to use existing data
2. Application load data at start and save it after 'exit' command.
3. Command start to process after pressing "Enter"