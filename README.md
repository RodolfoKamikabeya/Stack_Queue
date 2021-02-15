# Stack_Queue
## This program is part of COT 5405 Design & Analysis Of Algorithms course. 

### Algorithms Design Project to support Sorting Module.

Write and a program to implement a stack and a queue .  Implement both the stack and Queue using fixed arrays ( declare your  stack arrays size 25 and your queue array size 15 …. Implement it so that it is easy to change and recompile).   

The program is intended to fill the following steps:

**Step 1.** Design to program to read in a file of text  called “text.dat”.  The file will contain sentences the consist of words, punctuation marks, and numbers. 

**Step 2.** Extract each word from the input file  push it each word onto the  stack (ignore numbers and punctuation marks) until the stack becomes full. Stop pushing words onto the stack when it fills up. 

**Step 3.** Then pop each word from the stack one word at and time and enqueue each word into the queue. If the queue fills up before all the words are popped from the stack start dequeuing the words from the queue and write each word to the output file until the queue is empty then  resuming the enqueue (until all words are popped from the stack) and added to the queue . Repeat this process until all words are read from the input file and written to the output file.

**Step 4.** Flush all remaining  words from the stack and then the queue when the last word is read from the input file (.e.g, the stack my not be full when last word is encountered). 

Upon dequeueing each word from the queue print it to a file called  "text_out.rpt”.    
Print no more than 10 words per line of output to the output file, compute and print the average word length and the total word count to the screen.


 Average word length : 99

  Total Word count : 99
