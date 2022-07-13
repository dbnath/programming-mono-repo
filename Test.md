Development teams have been simulating dependencies required for executing different types of tests (such as unit tests and integration tests) for a long time. Two techniques that have been, and still are, widely used are stubbing and mocking:

* Stubs are objects that replace a dependency by providing predefined responses to input delivered during tests. Stub behavior therefore is predetermined and fixed, making stubs suitable for state verification during test execution.
* Mocks are similar to stubs, with the difference being that the behavior of mocks is defined during test initialization. This means that two instances of the same mock can behave differently, depending on their initialization, which makes them suitable for behavior verification during test execution
