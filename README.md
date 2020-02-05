# Interpreter-Backend
- Simple notebook server that can execute pieces of code in an interpreter using Spring Boot technology
- Currently Supported languages :
    - [x] JavaScript (js)
    - [x] Python (python)
    

# Installation 

Clone project into a local folder.

```$shell 
$ git clone https://github.com/GamalFahd/Interpreter-Backend.git .
```

### GraalVM 
GraalVM must be installed in order to be able to build the project. 
You can download graalVM from the [GraalVM homepage](https://www.graalvm.org/). 


### Build the project 

Once every thing is installed, run a maven clean install package or maven package in the project so the jar file can be generated. (You can skip Tests).

````
$ mvn clean install package -U -DskipTests
````

OR 

````
$ mvn package -DskipTests
````

Then You can run the project using the java -jar command.

```
$ java -jar target/interpreter-backend-0.0.1-SNAPSHOT.jar
```

If all is okay, you should be able to interact with the server in:
```
http://localhost:8080/
```

The Interpreter API is available via http POST method at:
```
/execute
```

### Interpreter request body

The **/execute** interpreter End-Point accepts JSON as request body. 
The json object must have the following format

```json
{
  "code": "string",
  "sessionId": "string"
}
```

Here is a small description of the request body fields:
- code: the code to be interpreted, it must have the format:
```json
%language code
```
where language is one of the supported languages (look project description), and code is the code to be interpreted. 
See example below.

- sessionId: is the id of the session we are using. this field is used to differentiate between users and also to allow
users to continue their interaction with the interpreter in the same execution context (example declare variable and reuse them).
Note that you may have same sessionId for different languages but the codes run in seperate contexts for different sessions. Also the sessionId is not mandatory in the first request and the API will provide you with a sessionId in case not specified but it must be used to remembre the previous declaration...



### Interpreter response body

The **/execute** returns a json object as response. The response have the following format:

```json
{
  "response": "string",
  "errors": "string",
  "sessionId": "string"
}
```

- response: the output of the code interpretation.
- errors: errors information of the code interpretation (also content of standard error).
- sessionId: the sessionId used during the interpretation for future usage.
