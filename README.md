# Hashtag-counter-using-Fibonacci-Max-Heap
This github repository is used to implement Fibonacci max heap which is used to find the n most popular hashtags that appear on
social media. It makes use of a hashtable where key of the hashtable contains the hashtag and the value points to the 
corresponding node in the Fibonacci max heap.

Programmimg language: Java
The file: ADS_project_report.pdf gives an in-depth description of all the functions used in this project.

I have implemented the following classes in this repository:
1) hashtagcounter: This is the main class which has the main function. I have explained its logic in the later section.
2) fibMaxNode: This class is again used in the class fibMaxHeap. It is used to represent each of the nodes in the Fibonacci 
max heap. It contains a constructor where all the node attributes have been initialized.
3) fibMaxHeap : This class represents the Fibonacci Max Heap. It also has a constructor to initialize all its attributes 
along with various other methods the Max Fibonacci heap like- insert, remove max, increase key, cascading cut etc.

Procedure to compile and run the file:
● First unzip the folder. The zip folder contains the following files: ADS_Project_report, Makefile, hashtagcounter.java,
fibMaxNode.java, fibMaxHeap.java
● Then use the command cd Hashtag-counter-using-Fibonacci-Max-Heap
● Use the command ‘make’. The command will generate the following classes: hashtagcounter.class, fibMaxNode.class and  
FibMaxHeap.class.
● Then use either of the following commands:
1)$java hashtagcounter <input_filename> <outputfilename>
This will generate the results in the output file. Make sure that the input file in the current directory.
2)$java hashtagcounter <input_filename>
This will generate in the output console. Make sure that the input file in the current directory.

Input Format:
Hashtags appear one per line in the input file and start with # sign. After the hashtag an integer will appear and that is 
the count of the hashtag (There is a space between hashtag and the integer). You need to increment the hashtag frequency 
by that count. Queries will also appear in the input file and once a query appears you should append the answer to the query
to the output file. A query appears as an integer number (n) without # sign in the beginning. The answer to the query n 
is n hashtags with the highest frequency. These should be written to the output file. An input line with the word “stop” 
(without hashtag symbol) causes the program to terminate. The following is an example of an input file.

#saturday 5
#sunday 3
#saturday 10
#monday 2
#reading 4
#playing_games 2
#saturday 6
#sunday 8
#friday 2
#tuesday 2
#saturday 12
#sunday 11
#monday 6
3
#saturday 12
#monday 2
#stop 3
#playing 4
#reading 15
#drawing 3
#friday 12
#school 6
#class 5
5
stop

Output Format:
For each query n, you need to write the n most popular hashtags (i.e., highest frequency) to the output
file in descending order of frequency (ties may be broken arbitrarily). The output for a query should be
a comma separated list occupying a single line in the output “output_file.txt”. There should be no space
character after the commas.
Following is the output file for the above input file.
saturday,sunday,monday
saturday,sunday,reading,friday,monday

These are the following assumptions:
1.No uppercase letters in the input stream
2.No spaces in the hashtags. (only one word per one hashtag)
3.One query has only one integer.
4.Query integer, n<=20. i.e. you need to find at most 20 popular hashtags.
5.Input file may consist of more than 1 million hashtags.
