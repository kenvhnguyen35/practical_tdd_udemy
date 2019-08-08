# Stub
* replace/override some external dependency
* used to test data 
* used to test an outcome using when().thenReturn()
* created by Mockito as a mock: mock()
* other framework might provide a stub -> use it for it is more lightweight compared to a mock 
# Mock
* replace/override some external dependency
* used to test behavior using verify().methodNeedsTesting()
# Fake (or Dummies)
* more lightweight than Stub
* replace smt that you are not interested in testing -> ignore it your test
* also created by Mockito as a mock and we need not to imnplement anything
* only mock(), no when().thenReturns()
# Tautologies
* using the same logic of your test in your code
* rule: test should not have logic, only examples and expected outcomes