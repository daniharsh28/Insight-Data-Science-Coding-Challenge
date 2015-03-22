# Insight-Data-Science-Coding-Challenge

The repository contains the code I used for Insight Data Engineering Fellow Program's coding challenge.

First problem is to find the wordcount of each file placed in wc_input directory. The second problem is to find the running median of text files.

In the below section I will describle the two problems more briefly.

# 1. WordCount

In this problem is I have used Java Collections `TreeMap` which keeps the key-value pair in sorted order of the key and the lookup time is `log(n)`, hence we will not need to sort the map after all entries are filled. After all files are read into memory we will output it to the text file named as `wc_result.txt`. 

For reading the file I have used `TreeSet` which keeps the entries sorted. And each file is read in the alphabetical order.

#2. FindMedian

This program, keeps track of Running Median of stream of lines. For this problem I have used `two heaps`, one min heap and one max heap. Max heap will always hold the numbers less then current median and min heap will hold numbers greater than median.

When there are even numbers in both heaps we will use min heap and max heap's average as our median or else we will use maxHeap's root.

This program fetches current median in `O(1)` and adds new elments in `O(log(n))` time.

You can simply run the program by just `sh run.sh` command and you can see the output in wc_ouput direcotry.

If you have any questions please contact me daniharsh28@asu.edu
