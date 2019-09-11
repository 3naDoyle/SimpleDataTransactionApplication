# Data Transaction Application Assignment 

A REST service for handling simple Json data transactions. 
The web service accepts transactions data in JSON format and saves them to local json file. 
A transaction is uniquely identified by date and type. If such a transaction already exists in a file, then the amount of two transactions is summed but only one instance of the transaction is saved.

### To Note

This application auto generates a local persistence file to store transactional data 
(Default location C:\dataTransactions\transcations.json).

* Sample GET request data
```json
[

    {

        "date": "11-12-2018",

        "type": "credit",

        "amount": "9898.36"

    },

    {

        "date": "11-12-2018",

        "type": "credit",

        "amount": "9898.36"

    }

]
```

### Built With

* [Spring Boot](https://spring.io/projects/spring-boot) 


### Author

* **Triona Doyle** 
* [3naDoyle GitHub](https://github.com/3naDoyle) 




* This application was developed as a given assignment as part of an job-application process. 


### Acknowledgments

* This application was developed as a given assignment as part of an job-application process. 

