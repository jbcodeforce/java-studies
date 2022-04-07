
# two D array
a = [[1,2,3],[1,3,4],[2,5,6],[5,6,7]]
print(a)
# len gives the number of rows
print(len(a))
sizeOfA = len(a)*len(a[0])
print(sizeOfA)
# In 2D array, if a user wants to enter all the values/elements then two for loops are used

def buildArrayByEnteringValue():
    print("build one Array By Entering Value row by row")
    b = []
    for row in range(1,3):
        print (" Row " + str(row))
        Arow = []
        for col in range(1,3):
            Arow.append(input("enter value: "))
        b.append(Arow)
    return b
#print(buildArrayByEnteringValue())

# browse rows
for row in a:
    print(row)

# update data
a[1][2]=20
print(a)

# Add a row
c = [8,6,7]
a.append(c)
print(a)
# add a column with same value
for row in a:
    row.append(9)
assert( a == [[1, 2, 3, 9], [1, 3, 20, 9], [2, 5, 6, 9], [5, 6, 7, 9], [8, 6, 7, 9]])

# find the least frequent element present in an array: 
# - brute force: go over the array twice, count the number of time the value in outer loop in inner loop
# Time complexity: O(N²) , Space complexity: O(1)
c=[1,2,3,1,1,2,3,2,1]

def findLeastFreqElementNaive(c):
    leastElement,leastElementCount = -99, len(c)
    for r in range(len(c)):
        currentCount = 0
        for b in range(len(c)):
            if (c[r] == c[b]):
                currentCount = currentCount + 1
        if (currentCount < leastElementCount):
            leastElementCount = currentCount
            leastElement = c[r]
    return leastElement,leastElementCount

assert(findLeastFreqElementNaive(c) == (3,2))

# - using a sorted array we can just count until <>
# Time complexity: O(N log(N)) , Space complexity: O(N)
def findLeastFreqElementBySorting(c):
    d = c.copy()
    d.sort()  # log(N)
    leastElement,leastElementCount, currentCount = -99, len(d), 1
    for i in range(len(d)-1):
       
        if (d[i] == d[i+1]):
            currentCount = currentCount + 1
        else:
            if (currentCount < leastElementCount):
                leastElementCount = currentCount
                leastElement = d[i]
            currentCount = 1
    if (currentCount < leastElementCount):
        leastElementCount = currentCount
        leastElement = d[len(d)-1]
    return leastElement,leastElementCount
le,lec = findLeastFreqElementBySorting(c)
assert((le,lec) == (3,2))
assert(findLeastFreqElementBySorting([1,2,3,1,1,2,3,4,1]) == (4,1))

# Use HashMap or dictionary
# O(N) and space O(1)
def findLeastFreqElementOptimized(c):
    d = {}
    for i in range(len(c)):
        if (c[i] in d.keys()):
            d[c[i]] += 1
        else:
            d[c[i]] = 1
    leastElementCount = min(d.values())
    for i in d:
        if d[i] == leastElementCount:
            leastElement = i
            break
    return leastElement,leastElementCount

assert(findLeastFreqElementOptimized(c) == (3,2))