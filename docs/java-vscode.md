# Java programming with VScode

## Some Editor tricks

The access to the command pallette shift -> cmd -> P

pallette and access to per to preview a file like a csv.

### Autocompletion

* main to get public void main.... 
* sysout for System.out...
* F2 to refactor name

### Navigate

* cmd -> click to navigate to the source of a class
* opt -> Shift -> o to organize import
* cmd -> shift -> O get the code outline
* cmd ->  P navigate project file

## VSCode tricks

* Control the file exposed (like removing eclipse related file) -> code -> Preferences -> Settings -> your-existing-projectname -> Commonly used, file exclude and then enter a pattern like (`**/.classpath`)
* Java Depenencies view helps to get the code of the used dependencies
* Run maven goals from the maven projects view in the Explorer

## Debugging

[Codelens debugging](https://code.visualstudio.com/docs/java/java-debugging)

## Error

* `Failed to launch debuggee in terminal. Reason: Failed to launch debuggee in terminal. Reason: java.util.concurrent.TimeoutException: timeout`: this was done by unknonw localhost resolution due to some DNS setting on mac.
