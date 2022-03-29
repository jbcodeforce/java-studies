# snapshot-array Project

Implement a SnapshotArray that supports the following interface:

* SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
* void set(index, val) sets the element at the given index to be equal to val.
* int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
* int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id

## Use tests to validate the solution

```
mvn verify
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
quarkus dev
```