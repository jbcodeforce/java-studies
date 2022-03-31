# Some simple question on tree or graph

* Using TreeMap in [TestTreeMap].

## Given an encoded string, return its decoded string.

    * what is an encoded string, what is the encoding rules. 
    * response: The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
    * example Input: s = "3[a]2[bc]"  -> Output: "aaabcbc"
    * constraints? 
    
        * 1 <= s.length <= 30
        * s consists of lowercase English letters, digits, and square brackets '[]'.
        * s is guaranteed to be a valid input.

    * String Decoder in [TestStringDecoder] and class StringDecoder

## Use tests to validate the solution

```
mvn verify
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
quarkus dev
```