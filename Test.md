## Stubs, Mocks and Service Virtualization

Development teams have been simulating dependencies required for executing different types of tests (such as unit tests and integration tests) for a long time. Two techniques that have been, and still are, widely used are stubbing and mocking:

* Stubs are objects that replace a dependency by providing predefined responses to input delivered during tests. Stub behavior therefore is predetermined and fixed, making stubs suitable for state verification during test execution.
* Mocks are similar to stubs, with the difference being that the behavior of mocks is defined during test initialization. This means that two instances of the same mock can behave differently, depending on their initialization, which makes them suitable for behavior verification during test execution

https://www.soapui.org/learn/mocking/what-is-api-virtualization/

| Stubbing and mocking  | Service virtualization |
| ------------- | ------------- |
| Used mostly to support unit and unit integration testing | Used primarily to support system, acceptance, and performance testing, although it can be used to support unit and unit integration testing just as well  |
| Created and used mostly by developers  | Can be created by any authorized individual and then shared and used within the team or across teams  |
|Used mostly within the confines of a single team or project | |
| | |
| | |
| | |
| | |


## Selecting a Service Virtualization Approach

The first question that you should ask when considering service virtualization is whether it is necessary in the first place. Service virtualization implementation is not a quick fix, as you will see in the next section. Although the ease of use and the speed of creation of virtual assets is one of the drivers of service virtualization success, its implementation requires proper planning and, depending on the implementation scale, a significant investment of time and effort. This applies especially to organization-wide service virtualization projects. Of course, it is very well possible to begin the implementation process on a small scale to test the waters.

One of the alternatives to service virtualization could be to develop, deploy, use, and maintain your own set of stubs. Be sure to take into consideration the additional costs associated with software maintenance. These costs, including corrective maintenance costs (costs associated with fixing defects) and enhancements (costs associated with continuing innovations), often exceed the initial development and implementation costs significantly and you should not overlook them.

After you establish that service virtualization is the way forward, it is advised that you take some time to identify the bottlenecks in your test environment that are most painful when it comes to delays in development and testing progress. Here are some questions that could be asked:

* Which dependencies are responsible for the delay that your team is experiencing?
* What gains are to be won with service virtualization implementation?
* Do these gains really solve the problem at hand?
* Are there any other quick wins that can either speed up the development process or at least result in management buy-in (preferably both!)?

After you have decided on what bottlenecks to attack first, it is time to consider the various options and tools available on the market. There are a lot of options available nowadays, and one of the most important choices you need to make is between open source solutions (such as Hoverfly from SpectoLabs and WireMock, which is developed and maintained by Tom Akehurst) and commercially licensed service virtualization engines (such as HPE Service Virtualization and Parasoft Virtualize).

Some considerations that you need to take into account when making this decision like if it is any license fees, any limited functionality, scalable and if it is readily available

## Earlier Testing (“Shift Left”)

When you have virtual assets that simulate dependency behavior at your disposal at the beginning of your software development activities, no more time is wasted waiting until every dependency is in place before you can begin integration and end-to-end testing. The ability to test earlier means that potential defects are uncovered earlier in the development process, when they are relatively easy, fast, and less expensive to fix.

The ability to test earlier in the software development life cycle is commonly known as the shift left of testing.

Figure of Shift left


## Examples of mocking

# dynamic mapping example - e.g. to be sure that caches are properly loaded
<details>
  <summary>Click to expand!</summary>
  
  ```json
  {
  "id": "d8f39db8-c9ad-3228-b3b8-20ff202b0372",
  "priority": "95",
  "request": {
    "url": "/some/cool/soap/webservice/DocumentService",
    "method": "POST",
    "bodyPatterns": [
      {"contains": "getDocuments>"}
    ]
  },
  "response": {
    "status": 200,
    "headers": {"Content-Type": "application/xop+xml; charset=UTF-8; type=\"text/xml\""},
    "transformers": ["response-template"],
    "body": "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:getDocumentsResponse xmlns:ns2=\"http://cool.document.service.com/\"><return><docId>mocked_docId_{{soapXPath request.body '/query/rangeIds/text()'}}_{{soapXPath request.body '/query/queryBean[1]/@value'}}_{{randomValue length=1 type='NUMERIC'}}</docId><documentType englishLabel=\"Product Data Sheet\" id=\"36287{{randomValue length=5 type='NUMERIC'}}\" name=\"Product Data Sheet\" translation=\"Product Data Sheet\"/><files><file><extension>.PDF</extension><filename>SOME_ID_DATASHEET_{{soapXPath request.body '/scope/country/text()'}}_{{soapXPath request.body '/locale/isoLanguage/text()'}}-{{soapXPath request.body '/locale/isoCountry/text()'}}.pdf</filename><id>{{randomValue length=5 type='NUMERIC'}}</id></file></files><isoCountry>{{soapXPath request.body '/locale/isoCountry/text()'}}</isoCountry><isoLanguage>{{soapXPath request.body '/locale/isoLanguage/text()'}}</isoLanguage><numberOfPage>{{randomValue length=1 type='NUMERIC'}}</numberOfPage></return></ns2:getDocumentListResponse></soap:Body></soap:Envelope>"
  },
  "uuid": "d8f39db8-c9ad-3228-b3b8-20ff202b0372"
}
```
</details>

## wsdl mapping example

<details>
  <summary>Click to expand!</summary>
  
  ```json
  {
  "id": "0ac2f2d4-6c7c-4cb7-9dee-89ec3f500667",
  "priority" : 98,
  "request" : {
    "urlPattern" : "/some/cool/soap/webservice/DocumentService\\?(wsdl|WSDL)",
    "method" : "GET"
  },
  "response" : {
    "status" : 200,
    "transformers": ["response-template"],
    "body" : "<?xml version='1.0' encoding='UTF-8'?><wsdl:definitions name=\"DocumentService\" targetNamespace=\"http://cool.document.service.com/\" xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\" xmlns:tns=\"http://cool.document.service.com/\" xmlns:wsdl=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><wsdl:types>
...
<wsdl:service name=\"DocumentService\"><wsdl:port binding=\"tns:DocumentServiceSoapBinding\" name=\"DocumentServicePort\"><soap:address location=\"http://{{request.requestLine.host}}:{{request.requestLine.port}}/some/cool/soap/webservice/DocumentService\"/></wsdl:port></wsdl:service></wsdl:definitions>"
  },
  "uuid": "0ac2f2d4-6c7c-4cb7-9dee-89ec3f500667"
}
  ```
</details>
