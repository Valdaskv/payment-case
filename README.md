UnresolvedWe don't expect the task or the technical requirements to be perfectly clear to you. Ask questions if there are any.

The task:
You are writing a backend service for a new case handling system. The system will handle cases when payments could not go through automatically. Case handler will have to look at a case and resolve it - either let the payment through or return it to the sender. 
Functional requirements:
- 2 case types: for normal payment and for returned payment. Returned payments are payments which were sent to another bank, but could not be processed there and were returned back. They cannot be returned again.
- Each case has a related payment that has a:
-- PaymentId sent by case creator. There can only be one case per payment. 
-- Amount.
-- Currency.
- Each case has an unique system generated caseId.
- Case for normal payment can be resolved by a case handler with one of the resolutions:
-- RESUBMIT
-- RETURN
- Case for returned payment can only be resolved with RESUBMIT.
- A case can only be resolved once.
Bonus points if you develop functionality that allows:
- Manager to know what is the total amount of payments in unresolved cases at any time.

Techical requirements:
- Coding should be done in Java.
- Cases should to be created, retrieved and resolved through REST endpoints.
- Manager wants to access data related to cases through REST endpoints.
- Database is not necessary, you can keep everything in memory.
- Code should be unit tested. Unit test coverage >80%. Try using classicist testing approach (no mocks, only fakes) and write tests at controller level (coupling tests to behavior, not implementation details).
- Try developing a Rich Domain Model.
- Push code to a git repository and create a pull request when you are ready.
- We will review your code, write comments and ask you to improve the code if needed. There may be several iterations of feedback.
