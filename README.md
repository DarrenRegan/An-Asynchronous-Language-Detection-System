# An-Asynchronous-Language-Detection-System
An Asynchronous Language Detection System

1). Split Database and LanguageEntry into multiple classes which promotes loose coupling and cohesion

2). Language portion of Database is in LanguageDB which implements interface as StorageDB with a Generic type as does KmerDB
3). Kmer portion of Database is in KmerDB which implements interface as StorageDB with a Generic Type as does LanguageDB

4). Both LanguageDB and KmerDB have a Add() function

5). OutOfPlaceMetric has its own class to promote loose coupling
5.1). This is used in a TreeSet in languageDB to calculcate the rank of languages and returning the first language with the lowest distance metric in the treeset

6). ServiceHandler has a very simple inQueue and outQueue
