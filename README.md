# AndroidDatabaseLibraryComparison
A test between a few of the popular libraries running a speed test on how fast they load and save data.

## Benchmark Description

There are two benchmarks.  The Simple trial uses a flat schema for an address book so each row is composed of name, address, city, state, and phone columns.  

![Simple Address Item Schema](images/SimpleAddressItem.png "Simple Address Item Schema")

The Complex trial is hierarchical and has support for multiple address books where each address book has contacts and addresses.

![Address Book Schema](images/AddressBook.png "Address Book Schema")

## Results

These are the results for the Simple trial:

![Simple Trial](images/simpletrial.png "Simple Trial")

And these are the results for the Complex trial:

![Complex Trial](images/complextrial.png "Complex Trial")
